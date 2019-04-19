#	Makefile for FileReverse.c with macros

FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c 
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w18 lab3

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit : $(SOURCES) README Makefile
	$(SUBMIT) $(SOURCES) README Makefile
