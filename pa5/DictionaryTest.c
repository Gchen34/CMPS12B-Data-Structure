#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   printDictionary(stdout,A);
   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%s\n", size(A));

   char* k;
   char* v;
   char* word1[] = {"a","b","c","d","e","f"};
   char* word2[] = {"Mon","Tue","Wed","Thu","Fri","Sat"};
   int i;

   for(i=0; i<6; i++){
   insert(A, word1[i], word2[i]);
   }
   printDictionary(stdout, A);
   
      for(i=0; i<6; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }
   delete(A, "a");
   insert(A, "a", "Sun");
   printDictionary(stdout, A);
   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A);
   
   return(EXIT_SUCCESS);
}