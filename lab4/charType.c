#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w);
int main(int argc, char* argv[]){
	FILE* in; 
	FILE* out;
	char* line;
	char* alphabetic;
	char* digit;
	char* punct;
	char* whitespace;

if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }
if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }
if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
alphabetic = calloc(MAX_STRING_LENGTH+1, sizeof(char));
digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
punct = calloc(MAX_STRING_LENGTH+1, sizeof(char));
whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char));
assert(line!=NULL && alphabetic!=NULL && digit!=NULL && punct!=NULL && whitespace!=NULL);

int lineNum = 0;
while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
	lineNum++;
	extract_chars(line, alphabetic, digit, punct, whitespace);

	fprintf(out, "line %d contains: \n", lineNum);
	
	if(strlen(alphabetic)>1){
	fprintf(out,"%d alphabetic characters: %s\n",(int)strlen(alphabetic),alphabetic);
		}
		else{
	fprintf(out,"%d alphabetic character: %s\n",(int)strlen(alphabetic),alphabetic);
		}
		if(strlen(digit)>1){
	fprintf(out,"%d numeric characters: %s\n",(int)strlen(digit),digit);
		}
		else{
	fprintf(out,"%d numeric character: %s\n",(int)strlen(digit),digit);
		}
		if(strlen(punct)>1){
	fprintf(out,"%d punctuation characters: %s\n",(int)strlen(punct),punct);
		}
		else{
	fprintf(out,"%d punctuation character: %s\n",(int)strlen(punct),punct);
		}
		if(strlen(whitespace)>1){
	fprintf(out,"%d whitespace characters: %s\n",(int)strlen(whitespace),whitespace);
		}
		else{
	fprintf(out,"%d whitespace character: %s\n",(int)strlen(whitespace),whitespace);
		} 
 }

//free's the heap memory after while loop
free(line);
free(alphabetic);
free(digit);
free(punct);
free(whitespace);
// close input and output files
return EXIT_SUCCESS;
}

//extract chars
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	int i=0, j=0, k=0, l=0, m=0;
	while(s[i] != '\0' && i<MAX_STRING_LENGTH){
		if( isupper((int)s[i])){
			a[j]=s[i];
			j++;
		}
		else if( isalpha((int)s[i])){
			a[j] =s[i];
			j++;
		}
		else if( isdigit((int)s[i])){
			d[k] = s[i];
			k++;
		}
		else if(ispunct((int)s[i])){
			p[l]=s[i];
			l++;
		}
		else{
			w[m]=s[i];
			m++;
		}
		i++;
	}
	a[j]='\0';
	d[k]='\0';
	p[l]='\0';
	w[m]='\0';	
}	
