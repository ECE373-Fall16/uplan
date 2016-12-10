CLASSPATH=./lib/commons-codec-1.3.jar:./lib/xmlrpc-2.0.1.jar:./lib/sqlite-jdbc-3.14.2.1.jar:./lib/rt.jar:.

all: Server.class UIDriver.class Driver.class

start: update installJDK installJRE

driver: cleandriver Driver.class rundriver

gui: GUIDriver.class rungui

uidriver: cleanui UIDriver.class runui

server: cleanserver cleandb Server.class Client.class runserver

db: InitializeDB.class rundb

cleanall: cleanc cleanserver cleanui cleandb cleandriver

runserver:
	@java -classpath $(CLASSPATH) Server

rundriver:
	@java -classpath $(CLASSPATH) Driver

rundb:
	@java -classpath $(CLASSPATH) InitializeDB

rungui:
	@java -classpath $(CLASSPATH) GUIDriver

runui:
	@java -classpath $(CLASSPATH) UIDriver

Server.class: Server.java
	javac -classpath $(CLASSPATH) Server.java

Client.class: Client.java
	javac -classpath $(CLASSPATH) Client.java

Driver.class: Driver.java
	javac -classpath $(CLASSPATH) Driver.java

GUIDriver.class: GUIDriver.java
	javac -classpath $(CLASSPATH) GUIDriver.java

Calendar.class: Calendar.java
	javac -classpath $(CLASSPATH) Calendar.java

DataBase.class: DataBase.java
	javac -classpath $(CLASSPATH) DataBase.java

InitializeDB.class: InitializeDB.java
	javac -classpath $(CLASSPATH) InitializeDB.java

UIDriver.class: UIDriver.java
	javac -classpath $(CLASSPATH) UIDriver.java

cleanc:
	rm Client.class

cleanserver:
	rm Server.class

cleanui:
	rm UIDriver.class HomePage.class

cleandb:
	rm DataBase.class

cleandriver:
	rm Driver.class

clean:
	rm *.class

rmdb:
	rm *.db

update:
	sudo apt-get update

installJRE:
	sudo apt-get install default-jdk

installJDK:
	sudo apt-get install default-jre

