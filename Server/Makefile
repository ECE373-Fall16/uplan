CLASSPATH=./lib/commons-codec-1.3.jar:./lib/xmlrpc-2.0.1.jar:./lib/sqlite-jdbc-3.14.2.1.jar:.

all: Server.class Driver.class

server:
	@java -classpath $(CLASSPATH) Server

driver:
	@java -classpath $(CLASSPATH) Driver

Server.class: Server.java
	javac -classpath $(CLASSPATH) Server.java

Client.class: Client.java
	javac -classpath $(CLASSPATH) Client.java

Driver.class: Driver.java
	javac -classpath $(CLASSPATH) Driver.java

Calendar.class: Calendar.java
	javac -classpath $(CLASSPATH) Calendar.java

DataBase.class: DataBase.java
	javac -classpath $(CLASSPATH) DataBase.java

clean:
	rm *.class

