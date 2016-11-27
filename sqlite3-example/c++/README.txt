This is example C++ code for programmatically interacting
with a sqlite3 database to store, retrieve, and update data.
The code includes extensive comments that describe exactly
what is happening. You can also read about the C/C++ sqlite3
library here: https://www.tutorialspoint.com/sqlite/sqlite_c_cpp.htm

To run you must install sqlite3 developer libraries by running
the following commands:

sudo apt-get update
sudo apt-get libsqlite3-dev

To build the code and then run it, type:

make
./initialize_db

This will create a sqlite3 database in a file called inventory.db.
You can access this DB directly using the sqlite3 command-line client 
by running the following command:

sqlite3 inventory.db

You can type .help to see a list of commands for the sqlite3
shell.  You can also look here: https://www.sqlite.org/cli.html.
To view the contents of the database, run the following command
in the sqlite3 shell. In this case, INVENTORY is the name of the 
database table created by initialize_db

.dump INVENTORY

Similar sqlite3 libraries and code exist for other languages. 

For Java: see https://www.tutorialspoint.com/sqlite/sqlite_java.htm
For Python: see http://zetcode.com/db/sqlitepythontutorial/
