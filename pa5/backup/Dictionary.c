//-----------------------------------------------------------------------------
// Dictionary.c
// the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101;
// private types and functions ------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node *table;
   int numItems;
} DictionaryObj;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
 int sizeInBits = 8*sizeof(unsigned int);
 shift = shift & (sizeInBits - 1);
 if ( shift == 0 )
 return value;
 return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
 unsigned int result = 0xBAE86554;
 while (*input) {
 result ^= *input++;
 result = rotate_left(result, 5);
 }
 return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
 return pre_hash(key)%tableSize;
}

//findKey()
Node findKey(Node R, char* k){
   
   if( R==NULL || strcmp(k, R->key)==0 ){ 
      return R;
   }
    else return findKey(R->next, k);
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->table = calloc(tableSize,sizeof(Node));
   assert(D->table != NULL);
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if ( pD!=NULL && *pD!=NULL ){
	  if(isEmpty(*pD)==0)
      makeEmpty(*pD);
      free((*pD)->table);
	  free(*pD);
      *pD = NULL;
   }
}

//isEmpty
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   int i = hash(k);
   Node N = findKey(D->table[i], k);
   return ( N==NULL ? NULL : N->value );
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
	int i = hash(k);
	if(findKey(D->table[i],k)!=NULL){
		 fprintf(stderr, 
         "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
	}
	Node N = newNode(k,v);
	N->next = D->table[i];
	D->table[i]=N;
	N=NULL;
	D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N, P;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   int i = hash(k);
   N = findKey(D->table[i], k); //Node at hash index
   P = D->table[i]; //Head at index
   if( N==NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
   if(D->table[i]->next == N->next){ //Pointer = head node
	   D->table[i] = D->table[i]->next; //Head node becomes next
   } else {
	   for(; P->next!=N; P=P->next); //Another case: P node == N
	   P->next=N->next; //P node points to the node after N
   }
	N->next = NULL;
	freeNode(&N);
	N = NULL;
	D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
	Node N;
	if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i=0; i<tableSize; i++){
	   while(D->table[i]!=NULL){
		   N = D->table[i];
		   D->table[i]=N->next; //next
		   freeNode(&N);
		   N = NULL;
	   }
   }
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   Node N;
   for(int i=0; i<tableSize; i++){
	   N = D->table[i];
	   while(N!=NULL){
		fprintf(out, "%s %s\n", N->key, N->value);
		N = N->next; //Next node
	   }
   }
}


















