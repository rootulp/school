#include "definitions.h"

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
  int size=0;
  line = NULL;
  line = realloc(line,sizeof(char));
  c = fgetc(fp);
  if (test_eof(c))
    return NULL;
  while (!test_eol(c)){
    *(line+size) = c;
    size++;
    line = realloc(line,sizeof(char)* (size+1));
    c = fgetc(fp);
  }
  *(line+size) = '\0';
  return line;
}


void printLine(char *line){
  int i=0;
  while (*(line+i) != '\0'){
    printf("%c",*(line+i));
    i++;
  }
  printf("\n");
}

String * makeString(char * s)
{
  String *new = (String *) malloc (sizeof(String));
  new->stng = (char *) malloc(sizeof(char) * strlen(s));
  strcpy(new->stng,s);
  new->len = strlen(new->stng);
  return new;
}

void printString(String *s){
  char * c;
  int i=0;
  c = s->stng;
  
  while (*(c+i) != '\0'){
    printf("%c",*(c+i));
    i++;
  }
  printf("\n");
}


int stringLength(String *s)
{
  return s->len;
}

int string_indexOf(String *s, String *sub, int from)
{
  char *p = strstr(s->stng+from, sub->stng);
  if (p == NULL) {
    return -1;
  } else {
    return (int)p - (int)s->stng;
  } 
}

String *string_substring(String *s, int from, int to)
{
  int n = to - from;
  char *chars = (char *) malloc ((n+1) * sizeof(char));
  strncpy(chars, s->stng + from, n);
  chars[n] = '\0';
  return makeString(chars);
}

int string_split(String *s, String *delim, String **array, int n)
{
  int i = 0;
  int from = 0;
  int to;

  for(i=0; i<n; i++) {
    to = string_indexOf(s, delim, from);
    if (to == -1) break;
    array[i] = string_substring(s, from, to);
    from = to + stringLength(delim);
  }
  array[i] = string_substring(s, from, stringLength(s));
  return i + 1;
}

stringContainer_t * insertStringIntoLinkedList(stringContainer_t * head, String * newString){
  stringContainer_t *q;
  stringContainer_t * newContainer;
  newContainer = (stringContainer_t *) malloc(sizeof(stringContainer_t *));
  newContainer->str = newString;
  newContainer->next = NULL;
  if (head == NULL){
    head = newContainer;
    return head;
  }
  q = head;
  while (q->next != NULL){
    q=q->next;
  }
  q->next = newContainer;
  return head;
}

void displayStringList(stringContainer_t * head){
  String *r;
  stringContainer_t * temp;
  if (head == NULL){
    puts("No strings in the list");
    return;
  }
  temp = head;  
  puts("The list of lines are:");
  while (temp != NULL){
    r = temp->str;
    printString(r);
    temp=temp->next;
  }
  return;
}

word_t * createWord(char * w){
    word_t *temp;
    int size;
    char *wordText;
    temp = (word_t *)malloc(sizeof(word_t));
    temp->length = strlen(w);
    temp->freq = 1;
    temp->next = NULL;
    temp->lines=NULL;
    temp->lineCount=0;
    temp->w = (char *)malloc(sizeof(char)*temp->length+1);
    strcpy(temp->w, w);
    return temp;
}

void printWord(word_t *m){
  int i=0;
  char *c;
  c = m->w;
  printf("%s\t Freq: %d\t Lines: ",c, m->freq);
  printLines(m->lines, m->lineCount);
  printf("\n");
}

void displayWordList(word_t * head){
  word_t *r;
  word_t * temp;
  if (head == NULL){
    puts("No words in this list");
    return;
  }
  temp = head;

  while (temp != NULL){
    r = temp;
    printWord(r);
    temp=temp->next;
  }
  printf("\n");
  return;
}

word_t * insertWordIntoLinkedList(word_t *head, word_t *word, int line){
    word_t *temp, *newWord;
    char *wordChars = word->w;
    int *linep;
    if (head == NULL){
      newLine(&word, line);
      word->next = NULL;
      head = word;
      return head;
    }
    temp = head;
    while (temp->next != NULL)
    {
      if (strcmp(word->w, temp->w)==0)
      {
          temp->freq++;
          if (temp->lines==NULL || hasLine(temp, line)==0){
              newLine(&temp, line);
          }
          return head;
      }
      temp=temp->next;
    }
    if (strcmp(word->w, temp->w)==0)
    {
        temp->freq++;
        if (temp->lines==NULL || hasLine(temp, line)==0){
            newLine(&temp, line);
        }
        return head;
    }
    newWord = (word_t *)malloc(sizeof(word_t));
    newWord = word;
    newLine(&newWord, line);
    newWord->next = NULL;
    temp->next = newWord;
    return head;
}

int hasLine(word_t *word, int line) {
    int i=0, *w = word->lines;
    while (i<word->lineCount)
    {
        if (*(w+i)==line)
          return 1;
        i++;
    }
    return 0;
}

void printLines(int *lines, int numLines){
    int i=0;
    while (i<numLines)
    {
        printf("%d ", *(lines+i));
        i++;
    }
}

void newLine(word_t **word, int line) {
    (*word)->lineCount++;
    (*word)->lines = (int *)realloc((*word)->lines, (*word)->lineCount);
    *((*word)->lines + (*word)->lineCount-1)=line;
}

word_t * findMostFrequentWord(word_t *head){
    word_t *tmp=head;
    word_t *most=head;
    while (tmp!=NULL)
    {
        if (most->freq < tmp->freq){
            most = tmp;
        }
        tmp=tmp->next;
    }
    return most;
}
