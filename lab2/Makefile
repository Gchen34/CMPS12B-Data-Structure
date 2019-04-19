#Guangyang Chen, 1518931
#gchen34@ucsc.edu
#CMPS12B, Jan 24th
#This is Makefile

JAVASRC    = FileReverse.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = FileReverse
CLASSES    = FileReverse.java
JARFILE    = FileReverse
SUBMIT     = submit cmps012b-pt.w18 lab2

all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
