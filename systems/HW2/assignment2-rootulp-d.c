#include <stdio.h>
#include <stdlib.h>

//prototypes
void getString3(char **m); //note use of **m
void printString(char *);
char *findMatch(char *);
int getStart(char *text, char *start);

enum StateType {START, G, GC, GCA, GCAG};
enum Boolean {true, false};

int main(int argc, char *argv[]){
  char *line;
  char *result;
  char *c;
  getString3(&line);
  printString(line);
  result = findMatch(line);

  if (result == NULL){
    printf("Too bad no match\n");
  } else {
    printf("Yippee a match is found\n");
  }
}
 
//implementations of prototypes
void getString3(char **m){ //note use of **m
  int a=0; 
  char *k;
  char c;

  k =(char *)malloc(sizeof(char));
  printf("Enter your characters on one line, then hit <cr>: \n");
  printf("Input: ");
  while (1)  //infinite loop, very dangerous
   {
      c = getchar();
      if (c == '\n')
        break;
      *(k+a) = c;
      a++;
      k = realloc(k,a+1);
   }
  *(k+a) = '\0';
  *m = k;
}

void printString(char *g){
  int a=0;
  char *k;
  k = g;
  printf("Your Line: ");
  while (*(k+a) != '\0')
   {
     printf("%c",*(k+a));
     a++;
   }
   printf("\n");
}

char *findMatch(char *line){  
  int a = 0;
  int occurences = 0;
  char *g_index;
  char *k;
  k = line;
  int index = 0;
  enum StateType state = START;

  while (*(k+index) != '\0'){
    switch (state){
      case START:
        if (*(k+index) == 'G'){
          g_index = k + index; //index of the first G
          state = G;
        } else{
          state = START;
        }
        break;

      case G:
        if (*(k+index) == 'C'){
          state = GC;
        } else if (*(k+index) == 'G'){
          state = G;
        } else {
          state = START;
        }
        break;

      case GC:
        if (*(k+index) == 'A'){
          state = GCA;
        } else if (*(k+index) == 'G'){
          state = G;
        } else {
          state = START;
        }
        break;

      case GCA:
        if (*(k+index) == 'G'){
          occurences += 1;
          state = G;
        } else {
          state = START;
        }
        break;
    }
    index++;
  }

  if (occurences >= 1){
    printf("Number of matches: %d\n", occurences);
    return g_index;
  } else {
    return NULL;
  }
}