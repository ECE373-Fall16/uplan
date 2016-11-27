CLASSPATH=.:./lib/sqlite-jdbc-3.14.2.1.jar

all: InitializeDB.class

run:
	@java -classpath $(CLASSPATH) InitializeDB

InitializeDB.class: clean InitializeDB.java
	javac -classpath $(CLASSPATH) InitializeDB.java

clean:
	rm -f *.class *.db
