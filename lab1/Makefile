#------------------------------------------------------------------------------ #Guangyang Chen
#gchen34
#CMPS12B
#Jan 17th, 2018
#This is lab1, introducing the use of makefile.
#README: a file lists contents in this lab
#HelloUser.java: Prints greeting to student, then prints out some environment information.
#HelloUser2.java: Prints greeting.
#Makefile: manage files that make it easy to make some changes.
##------------------------------------------------------------------------------
JAVASRC = HelloUser.java HelloUser2.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = HelloUser2
CLASSES = HelloUser.class HelloUser2.class
JARFILE = Hello
SUBMIT = submit cmps012b-pt.w18 lab1

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
	
