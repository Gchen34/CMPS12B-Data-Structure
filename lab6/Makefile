#Guangyang Chen 1518931
#gchen34@ucsc.edu
#CMPS12B
#Lab 6
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = List.java ListInterface.java ListClient.java ListIndexOutOfBoundsException.java ListTest.java
CLASSES    = List.class ListInterface.class ListClient.class List\$$Node.class ListIndexOutOfBoundsException.class ListTest.class
SUBMISSIONS= List.java ListInterface.java ListClient.java ListIndexOutOfBoundsException.java Makefile README ListTest.java

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

clean:
	rm -f $(CLASSES) $(JARFILE)

submit: $(SUBMISSIONS)
	submit cmps012b-pt.w18 lab6 $(SUBMISSIONS)
