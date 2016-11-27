#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('inventory.db')

print "Opened database successfully";

conn.execute('''CREATE TABLE INVENTORY(
         ITEM_NUMBER INT PRIMARY KEY NOT NULL,
         QUANTITY INT NOT NULL,
         TITLE TEXT NOT NULL,
         TOPIC TEXT NOT NULL,
         COST DOUBLE NOT NULL);''')
print "Table created successfully";


conn.execute("INSERT INTO INVENTORY VALUES (53477, 1, 'Achieving Less Bugs in your Code', 'software systems', 19.99)");

conn.execute("INSERT INTO INVENTORY VALUES (53573, 2, 'Software Design for Dummies', 'software systems', 29.99)");

conn.execute("INSERT INTO INVENTORY VALUES (12365, 1, 'Surviving College', 'college life', 39.99)");

conn.execute("INSERT INTO INVENTORY VALUES (12498, 3, 'Cooking for the Impatient Undergraduate', 'college life', 49.99)");

conn.commit()
print "Records created successfully";

cursor = conn.execute("SELECT * from INVENTORY WHERE TOPIC='college life'");
for row in cursor:
   print "ITEM_NUMBER = ", row[0]
   print "QUANTITY = ", row[1]
   print "TITLE = ", row[2]
   print "TOPIC = ", row[3]
   print "COST = ", row[4], "\n"

print "Operation done successfully";


