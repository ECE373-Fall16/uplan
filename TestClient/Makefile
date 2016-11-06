CLASSPATH=./lib/commons-codec-1.3.jar:./lib/xmlrpc-2.0.1.jar:.

all: Client.class

client:
	@java -classpath $(CLASSPATH) Client

Client.class: Client.java
	javac -classpath $(CLASSPATH) Client.java

clean:
	rm *.class

