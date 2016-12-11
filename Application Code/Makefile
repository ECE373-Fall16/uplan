all:
	javac -g *.java

start: update installJDK installJRE

driver: Driver.class Client.class rundriver

gui: GUIDriver.class Client.class rungui

uidriver: cleanui UIDriver.class runui

server: Server.class runserver

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

testscript:
	rm -f test/*.out
	./testscript.sh

clean:
	rm -f *.class test/*.out test/*.txt
