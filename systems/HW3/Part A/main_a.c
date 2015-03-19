#include <stdio.h>
#include "string.h"

enum Statetype {IN, OUT};

char * fsm (String *text, String *pattern) {
    int i = 0;
    int j = 0;
    int start;
    enum Statetype State = OUT;
    
    while (*(text->start + i) != '\0') {
        switch (State) {

            case IN:
                if (*(text->start + i) == *(pattern->start + j)) {
                    j++;
                    if (*(pattern->start+j) == '\0') {
                        return (text->start + start);
                    }
                } else if (*(pattern->start) == *(text->start + i)) {
                    j = 1;
                    start = i;
                    State = IN;
                } else {
                    j = 0;
                    State = OUT;
                } break;

            case OUT:
                if (*(text->start + i) == *(pattern->start + j)) {
                    j++;
                    State = IN;
                    start = i;
                    if (*(pattern->start+j) == '\0') {
                        return (text->start + start);
                    }
                }
                break;
        }
        i++;
    }
    return NULL;
}

int main() {

    puts("Input pattern:");
    String *pattern = build();

    puts("Input text:");
    String *text = build();

    char * startHere;
    startHere = fsm(text, pattern);
    if (startHere == NULL) {
        puts("Pattern Not Found!");
    } else {
        puts("Pattern Found!");
    }
}
