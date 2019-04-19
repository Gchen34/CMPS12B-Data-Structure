//-----------------------------------------------------------------------------
//   Dictionary.c
//   Dictionary ADT implemented by linked list
//-----------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node head;
   Node tail;
   int numItems;
} DictionaryObj;


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D != NULL);
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
   return D;
}

//freeDictionary()
//destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL){
      if( !isEmpty(*pD) )makeEmpty(*pD);
         free(*pD);
      *pD = NULL;
   }
}

//isEmpty()
//returns 1 (true) if S is empty, 0 (false) otherwise
//pre: none
int isEmpty(Dictionary D){
   if( D == NULL ){
      fprintf(stderr, 
	      "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

//size()
//returns the size of D
//pre: none
int size(Dictionary D){
   return D->numItems;
}

//lookup()
//returns the value, or returns NULL msg if no such value exists
//pre: takes an int
char* lookup(Dictionary D, char* k){
    Node N = D->head;
    if( D == NULL ){
      fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
      exit(EXIT_FAILURE);
   }
   while(N != NULL){
      if(strcmp(N->key,k)== 0)
         return N->value;
      N = N->next;
   }
   return NULL;;
}

//insert()
//inserts new(k,v) pair into D
//pre:lookup(D,K)== NULL, no such a key in D
void insert(Dictionary D, char* k, char* v){
   if(D->numItems == 0){
      D->head = D->tail = newNode(k, v);
   }else{
      Node N;
      N = newNode(k, v);
      D->tail->next = N;
      D->tail = N;
    }
    D->numItems++;
}

//makeEmpty()
//resets D to the empty state
//pre: none
void makeEmpty(Dictionary D){
   if(D == NULL){
      fprintf(stderr, "Dictionary error: calling makeEmpty() on NULL Dictionary reference\n");
   exit(EXIT_FAILURE);
}
   Node c = D->head;
   Node d = D->head;
   while( c != NULL){
   c = c->next;
   freeNode(&d);
   d = c;
}
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
}


// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N = D->head;
   if( lookup(D,k) == NULL ){
       fprintf(stderr, "Dictionary error: key not found\n");
       exit(EXIT_FAILURE);
   }
   if(strcmp(N->key,k)==0){
      Node P = D->head;
      Node G = P->next;
      D->head = G;
      freeNode(&P);
   }else{
     while(N !=NULL && N->next != NULL){
        if(strcmp(N->next->key, k)==0){
        Node G = N;
        Node C = N->next;
        G->next = C->next;
        N=G;
        freeNode(&C);
        }
     N = N->next;
    }
  }
  D->numItems--;
}

// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->head; N!=NULL; N=N->next) fprintf(out, "%s %s \n" , N->key, N->value);
   fprintf(out, "\n");
}
