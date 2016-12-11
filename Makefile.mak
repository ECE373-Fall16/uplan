all:
	javac -g *.java

testscript:
	rm -f test/*.out
	./testscript.sh

clean:
	rm -f *.class test/*.out test/*.txt
