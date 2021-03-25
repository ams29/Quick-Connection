run: compile
	java FrontEnd

compile: CS400Graph.java DataLoader.java FrontEnd.java GraphADT.java
	javac CS400Graph.java
	javac DataLoader.java
	javac FrontEnd.java
	javac GraphADT.java

test: TestClass.java
	javac -cp .:junit5.jar TestClass.java
	java -jar junit5.jar --class-path . --scan-classpath -details tree

clean:
	$(RM) *.class
