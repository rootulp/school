#include <stdio.h>
#include <stdlib.h>
#include "string.h"

String *build() {
    char temp;
    int size = 0;

    String * build_str = (String *) malloc (sizeof(String));
    build_str -> start = (char *) malloc (sizeof(char));
    temp = getchar();

    while (temp != '\n') {
        *((build_str->start)+size) = temp;
        build_str -> start = (char *) realloc(build_str -> start, ++size);
        temp = getchar();
    }

    *((build_str -> start) + size) = '\0';
    return build_str;
}

void print(String *s) {
    printf ("%s\n", s->start);
}