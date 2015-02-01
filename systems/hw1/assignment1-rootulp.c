// Rootul Patel
// Computer Systems
// Sig
// HW1

// Using a FSA (really a DFA) to find and replace a pattern
// getLine and printLine were written by Sig

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int getLine(char *line);
void printLine(char *line, int size);
int parse(char *input, int inputSize, char *output);
enum Statetype {START, SLASH, MIDCOMMENT, STAR, QUOTE};
enum Boolean {true,false};

int main(){
  char input[80];
  char output[80];
  int inputSize = 0;
  int outputSize = 0;

  printf("Input:\t");
  inputSize = getLine(input);
  outputSize = parse(input, inputSize, output);
  printf("Output:\t");
  printLine(output, outputSize);

  return 0;
}

//int getLine(char line[])
int getLine(char *line){
  char *a,b;
  int i,size=0;
  a = line;
  b = getchar();
  while( b != '\n' && size < 80){
    *(a+size) = b;
    size++;
    b = getchar();
  }
  return size;
}

//void printLine(char line[], int size)
void printLine(char *line, int size){
  char *a;
  int i;
  a = line;
  for (i=0;i<size;i++)
    printf("%c",*(a+i));
  printf("\n");
}

//int parse(char input[], int inputSize, char output[])
//Take in a char array
//Copy over characters that aren't in comments into output array
//Copy over comments if they are inside a quote block
//Print error if input string ends mid comment
//Return size of output array.
int parse(char *input, int inputSize, char *output){
  
  enum Statetype state = START; // start state
  enum Boolean error = false; // error flag
  int inputIndex = 0; // used to index through input array
  int outputIndex = 0; // used to index through output array

  while (inputIndex < inputSize){
  
    switch (state) {

      case START:
        *(output + outputIndex) = *(input + inputIndex); // copy over current char
        outputIndex++; // increment output index

        if (*(input + inputIndex) == '"'){
          state = QUOTE;
        } else if (*(input + inputIndex) == '/'){
          state = SLASH;
        } else {
          state = START;
        }
        break;

      case SLASH:
        
        if (*(input + inputIndex) == '*'){
          error = true; // set error flag to true because we are about to be midcomment
          *(output + outputIndex -1) = ' '; // set previous char(/) to empty space
          state = MIDCOMMENT;
        } else if (*(input + inputIndex) == '/'){
          *(output + outputIndex) = *(input + inputIndex);
          outputIndex++;
          state = SLASH;
        } else {
          *(output + outputIndex) = *(input + inputIndex);
          outputIndex++;
          state = START;
        }
        break;

      case MIDCOMMENT:
        if (*(input + inputIndex) == '*'){
          state = STAR;
        } else {
          state = MIDCOMMENT;
        }
        break;

      case STAR:
        if (*(input + inputIndex) == '/'){
          error = false; // reset error flag to false
          state = START;
        } else if (*(input + inputIndex) == '*') {
          state = STAR;
        } else{
          state = MIDCOMMENT;
        }
        break;

      case QUOTE:
        *(output + outputIndex) = *(input + inputIndex);
        outputIndex++;

        if (*(input + inputIndex) == '"'){
          state = START;
        } else {
          state = QUOTE;
        }
        break;

    }
    
    inputIndex++; // increment input index

  }

  if (error == true) // check error flag
   printf("Error: unterminated comment\n");

  return outputIndex; // return size of output array
}