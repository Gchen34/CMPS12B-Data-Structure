#Guangyang Chen
#gchen34@ucsc.edu
#PA5
#Makefile
FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
CSOURCES= README Makefile Dictionary.h DictionaryTest.c $(SOURCES)
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT  = submit cmps012b-pt.w18 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(CSOURCES)
	$(SUBMIT) $(CSOURCES)
