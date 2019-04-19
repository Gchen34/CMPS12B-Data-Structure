#Guangyang Chen gchen34@ucsc.edu
#Lab4
PROGRAM_NAME = charType
FLAGS 	= -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN 	= charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

submit :
	submit cmps012b-pt.w18 lab4 Makefile README charType.c
