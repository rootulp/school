#include <stdio.h>
#include <unistd.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include "main.h"

#define TRUE 1
#define FALSE 0

int test_eol(char a){
  if (a == '\n')
    return TRUE;
  else
    return FALSE;
}

int test_eof(char a){
  if (a == EOF)
    return TRUE;
  else
    return FALSE;
}

char * returnLine(FILE *fp){
  char c;
  char *line;
  int size = 0;
  line = NULL;
  line = realloc(line, sizeof(char));
  c = fgetc(fp);
  if (test_eof(c)) {
    return NULL;
  } else {
    while (!test_eol(c)) {
      *(line + size) = c;
      size++;
      line = realloc(line, sizeof(char) * (size + 1));
      c = fgetc(fp);
    }
  }
  *(line + size) = '\0';
  return line;
}

int main(int argc, char* argv[]) {
  int pipe_1[2];
  int pipe_2[2];
  int rc1;
  int rc2;
  int pid;
  int pid2;
  int status;
  char read_buffer_1[150];
  char read_buffer_2[150];
  char *line;
  FILE *fp;
  FILE *fpout;

  // Check for errors
  rc1 = pipe(pipe_1);
  if (rc1 == -1) {
    perror("Error: pipe_1");
    exit(1);
  }

  rc2 = pipe(pipe_2);
  if (rc2 == -1) {
    perror("Error: pipe_2");
    exit(1);
  }

  fp = fopen("input.txt", "r");
  if (fp == NULL) {
    perror("Error: input.txt");
    return(-1);
  }

  fpout = fopen("output.txt", "wb");
  if (fpout == NULL) {
    perror("Error: output.txt");
    return(-1);
  }

  line = returnLine(fp);

  while(line != NULL) {
    pid = fork();
    if (pid != 0) {
      printf("Process 1\n");
      printf("Reading from input file: '%s'\n", line);
      printf("Writing to pipe 1: '%s'\n", line);
      rc1 = write(pipe_1[1], line, strlen(line) + 1);
      if (rc1 == -1) {
        perror("Error: writing to pipe 1");
        close(pipe_1[1]);
        exit(1);
      }
      waitpid(pid, &status, 0);
      exit(1);
    } else {
      printf("Process 2\n");
      rc1 = read(pipe_1[0], read_buffer_1, sizeof(read_buffer_1));
      printf("Reading from pipe 1: '%s'\n", read_buffer_1);

      for (int i = 0; i < strlen(read_buffer_1); i++) {
        int test1 = islower(read_buffer_1[i]);
        int test2 = isupper(read_buffer_1[i]);
        if ((test1 != 0) && (test2 == 0)) {
          read_buffer_1[i] = toupper(read_buffer_1[i]);
        } else if ((test1 == 0) && (test2 != 0)) {
          read_buffer_1[i] = tolower(read_buffer_1[i]);
        } else {
          read_buffer_1[i] = read_buffer_1[i];
        }
      }

      rc2 = write(pipe_2[1], read_buffer_1, sizeof(read_buffer_1));
      printf("Writing to pipe 2: '%s'\n", read_buffer_1);

      pid2 = fork();
      if (pid2 != 0) {
        waitpid(pid2, &status, 0);
      } else {
        printf("Process 3\n");
        rc2 = read(pipe_2[0], read_buffer_2, sizeof(read_buffer_2));
        printf("Reading from pipe 2: '%s'\n", read_buffer_2);
        printf("Writing to output file: '%s'\n\n", read_buffer_2);

        strcat(read_buffer_2, "\n");
        fputs(read_buffer_2, fpout);
        exit(0);
      }
    }
    line = returnLine(fp);
  }

  return 0;
}
