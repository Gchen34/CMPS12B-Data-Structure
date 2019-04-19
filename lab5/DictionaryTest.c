#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
Dictionary A = newDictionary();
char* k;
char* v;
char* word1[] = {"a","b","c","d","e"};
char* word2[] = {"q","w","e","r","t"};
int i;

for(i=0; i<5; i++){
   insert(A, word1[i], word2[i]);
}
printDictionary(stdout, A);

for(i=0; i<5; i++){
   k = word1[i];
   v = lookup(A, k);
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
}

delete(A, "a");
delete(A, "e");
printDictionary(stdout, A);

for(i=0; i<5; i++){
   k = word1[i];
   v = lookup(A, k);
   printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
}

printf("%s\n", (isEmpty(A)?"true":"false"));
printf("%d\n", size(A));
makeEmpty(A);
printf("%s\n", (isEmpty(A)?"true":"false"));
printf("%d\n", size(A));

freeDictionary(&A);

return(EXIT_SUCCESS);
}
