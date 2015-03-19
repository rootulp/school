#include <stdio.h>
#include <stdlib.h>

typedef struct {
	char * start;
    int length;  
} String;

String *build();
void print(String *s);
