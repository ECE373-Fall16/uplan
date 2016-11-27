#include <iostream>
#include <cstdlib>
#include <cstring>
#include <sstream>
#include <cstddef>
#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h> 
#include <pthread.h>

using namespace std;

// Locks for controlling access to the structure
// below in each of the SQL callback functions.
// Ignore this for now. We will discuss in class later.
pthread_mutex_t count_mutex;
pthread_mutex_t callback_mutex;

// A structure to pass into our SQL callback function
// We include two arrays for the items and topics that
// we want to get from the query. We also include two
// variables that we use to first get the number of rows
// for the query, and then to count the number of times
// the callback function is called.
struct return_structure {
  string items[4];
  string topics[4];  
  int temp_row_count;
  int count_done;
};

// Example of the callback function; prints out each column value for
// the row associated with this callback function
// void* data = pointer from the sql statement that identifies this callback
// int argc = the number of columns in each row
// char** argv = array of strings, one for each column field value
// char** azColName = array of strings, one for each column name
static int callback(void* data, int argc, char **argv, char **azColName) 
{
   for(int i=0; i<argc; i++){
      printf("%s = %s\n", azColName[i], argv[i]);
   }
   printf("\n");

   return 0;
}

// Example of a callback for a SELECT SQL query, similar to the assignment. 
// Here, we pass in the structure above, and get the column values for item number
// and topic and put them in the array that we want to return. We also increment
// the temp_row_count by 1 because we need to keep track of the number of times
// this function has been called; in the main thread, we wait until the function
// has been called once for each row we expect. We need to hold a lock here, since
// each callback is on a separate thread, and each thread will modify these shared
// variables.
static int select_callback(void* data, int argc, char **argv, char **azColName) 
{
   pthread_mutex_lock(&callback_mutex);
   return_structure* return_value = (return_structure*)data;
   for(int i=0; i<argc; i++){
      printf("%s = %s\n", azColName[i], argv[i]);
      if (strncmp(azColName[i],"ITEM_NUMBER",11) == 0) {
	return_value->items[return_value->temp_row_count]=argv[i];	
      }
      if (strncmp(azColName[i],"TOPIC",5) == 0) {
	return_value->topics[return_value->temp_row_count]=argv[i];	
      }
   }
   printf("\n");
   return_value->temp_row_count++;
   pthread_mutex_unlock(&callback_mutex);

   return 0;
}

// Example of a callback for a simple query to get the expected number
// of output rows for a select query.  Here, we set the count_done variable
// in our structure equal to the total number of expected rows.  
static int count_callback(void* data, int argc, char **argv, char **azColName) 
{
   pthread_mutex_lock(&count_mutex);
   return_structure* return_value = (return_structure*)data;
   return_value->count_done = atoi(argv[0]);
   pthread_mutex_unlock(&count_mutex);

   return 0;
 }

int main(int argc, char* argv[])
{
   sqlite3 *db;
   char *zErrMsg = 0;
   int rc;
   const char* sql;
   string sql_string;  
   return_structure return_value;

   // Initializing the mutex locks
   pthread_mutex_init(&count_mutex, NULL);
   pthread_mutex_init(&callback_mutex, NULL);

   // The first set of functions below open, create, and insert
   // the initial items into our database. None of these operations
   // require us to use the callback function to get results.
   rc = sqlite3_open("inventory.db", &db);
   if( rc ){
      fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
      exit(0);
   }else{
      fprintf(stderr, "Opened database successfully\n");
   }

   sql = "CREATE TABLE INVENTORY("  \
         "ITEM_NUMBER INT PRIMARY KEY NOT NULL," \
         "QUANTITY INT NOT NULL," \
	 "TITLE TEXT NOT NULL," \
	 "TOPIC TEXT NOT NULL," \
	 "COST DOUBLE NOT NULL);";
 
   rc = sqlite3_exec(db, sql, NULL, 0, &zErrMsg);
   if( rc != SQLITE_OK ){
   fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }else{
      fprintf(stdout, "Table created successfully\n");
   }

   sql_string = "INSERT INTO INVENTORY VALUES (53477, 1, 'Achieving Less Bugs in your Code', 'software systems', 19.99);";
   sql = sql_string.c_str();
   rc = sqlite3_exec(db, sql, NULL, 0, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }else{
      fprintf(stdout, "Records created successfully\n");
   }

   sql_string = "INSERT INTO INVENTORY VALUES (53573, 2, 'Software Design for Dummies', 'software systems', 29.99);";
   sql = sql_string.c_str();
   rc = sqlite3_exec(db, sql, NULL, 0, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }else{
      fprintf(stdout, "Records created successfully\n");
   }

   sql_string = "INSERT INTO INVENTORY VALUES (12365, 1, 'Surviving College', 'college life', 39.99);";
   sql = sql_string.c_str();
   rc = sqlite3_exec(db, sql, NULL, 0, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }else{
      fprintf(stdout, "Records created successfully\n");
   }

   sql_string = "INSERT INTO INVENTORY VALUES (12498, 3, 'Cooking for the Impatient Undergraduate', 'college life', 49.99);";
   sql = sql_string.c_str();
   rc = sqlite3_exec(db, sql, NULL, 0, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }else{
      fprintf(stdout, "Records created successfully\n\n");
   }

   // The query below will get a count of the expected number of rows for our subsequent
   // SQL select query. We need to know this because we will need to wait until our callback
   // function for the select query is called the expected number of times before proceeding.
   // Since the callback for this query is also asynchronous, we also need to wait after
   // calling sqlite3_exec() until the count_callback is finished. We use the count_done variable
   // as a flag for this; we set it to -1 below. The count_callback function will set it to a value >=0
   // when it is finished.
   return_value.count_done = -1;
   sql = "SELECT COUNT(*) from INVENTORY WHERE TOPIC='college life'";
   rc = sqlite3_exec(db, sql, count_callback, (void*)&return_value, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }

   // We wait until the count_callback function from above is finished executing. We 
   // loop until count_done is not -1.  count_done stores the total number of rows we
   // expect from our SQL query. Note here that we need to hold the lock, since the 
   // callback function is also modifying count_done.
   while (1) {
     pthread_mutex_lock(&count_mutex);
     if (return_value.count_done != -1) {
	break;
     }
     pthread_mutex_unlock(&count_mutex);
   }

   // We now execute our SQL query from above. We give it a separate callback function, and pass in
   // a pointer to the same structure as before. Below, we wait until the callback function has been
   // called for each row expected by the query.
   return_value.temp_row_count = 0;
   sql = "SELECT * from INVENTORY WHERE TOPIC='college life'";
   rc = sqlite3_exec(db, sql, select_callback, (void*)&return_value, &zErrMsg);
   if( rc != SQLITE_OK ){
      fprintf(stderr, "SQL error: %s\n", zErrMsg);
      sqlite3_free(zErrMsg);
   }

   // The select_callback function above increments temp_row_count each time it is called. Once temp_row_count
   // equals count_done, we know that the select_callback function has been called the expected number of 
   // times, and we can move on. We need to hold the lock here, since the callback functions are modifying
   // temp_row_count.
   while (1) {
     pthread_mutex_lock(&callback_mutex);
     if (return_value.temp_row_count == return_value.count_done) {
	break;
     }
     pthread_mutex_unlock(&callback_mutex);
   }

   // Here, we just print out the results from the query that we stored in the select_callback function.
   for(int i = 0; i < return_value.count_done; i++) {
     cout << "ITEM_NUMBER = " << return_value.items[i] << endl;
     cout << "TOPIC = " << return_value.topics[i] << endl;
   } 

   sqlite3_close(db);
}
