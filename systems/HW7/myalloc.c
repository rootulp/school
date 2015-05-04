// Rootul Patel
// Computer Systems HW7
// Due: 5/3/15
// Prof. Signorile

#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <signal.h>
#include "myalloc.h"

void *myalloc2(int size);
void coalesce();
void mark(int *p);
void sweep(int *p);

//helper functions
int *isPtr(int *p);
int blockMarked(int *p);
void markBlock(int *p);
void unmarkBlock(int *p);
int blockAllocated(int *p);
void allocateBlock(int *p);
void unallocateBlock(int *p);
int length(int *p);
int *nextBlock(int *p);
void printblock(int *p);

int *start;
int sizeOfHeap;

void myallocinit(int size) {       
  int blocklen = ((size + 3) / 8 + 1) * 8;
  sizeOfHeap = blocklen;
  start = (int *)malloc(sizeOfHeap + 8); 
  if (start == NULL) {
    printf("Cannot init that much space\n");
    exit(1);
  }
  start++;
  *start = blocklen;
  start++;
  *(nextBlock(start)) = 0;
}

void printallocation() {
  int size = *(start - 1), *temp = start;
  while (length(temp)) {
    printblock(temp);
    temp = nextBlock(temp);
  }
}

void printblock(int *p) {
  printf("Block: %p \tSize: %d \tAllocated: %d \tMarked: %d\n", p, length(p), blockAllocated(p), blockMarked(p));
}

void *myalloc(int size) {
  void *myalloc(int size);
  void *ptr = myalloc2(size);
  if (ptr == NULL) {
    coalesce();
    ptr = myalloc2(size);
  }
  return ptr;
}

void *myalloc2(int size) {
  int blockLen = ((size + 3) / 8 + 1) * 8;
  int *temp = start, oldSize = *(start - 1), newSize = 0;
  while(oldSize != 0) {
    if (!blockAllocated(temp) && oldSize >= blockLen) {
      *(temp - 1) = blockLen;
      allocateBlock(temp);
      newSize = oldSize - blockLen;
      if (newSize > 0) {
        *(nextBlock(temp) - 1) = newSize;
      }
      return temp;
    }
    temp = nextBlock(temp);
    oldSize = *(temp - 1);
  }
  fprintf(stderr, "No space for allocation.\n");
  return NULL;
}

void myfree(void *p) {
  unallocateBlock(p); 
}

void coalesce() {
  int *ptr = start;
  int *nextptr; 
  while (length(ptr)) {
    nextptr = nextBlock(ptr);
    if (blockAllocated(ptr) || blockAllocated(nextptr) || *(nextptr - 1) == 0) {
      ptr = nextBlock(ptr);
      continue;
    }
    *(ptr - 1) += length(nextptr);
  }
}

void mygc() {
  unsigned long stack_bottom;
  int **max =  (int **) GC_init();
  int* q;
  int **p = &q;
  while (p < max) {
    mark(*p);
    p++;
  }
  sweep(q);
  coalesce();  
}

void mark(int *p) {
  int i = 0, size;
  int *ptr;
  ptr = isPtr(p);
  if (ptr != NULL && blockAllocated(ptr)) {
    if (!blockMarked(ptr)) {
      markBlock(ptr);
    }
    size = length(ptr)/ 4;
    while (++i < size) {
      mark(ptr+i);
    }
  }
}

void sweep(int *ptr) {
  ptr = start;
  while (length(ptr)) {
    if (blockAllocated(ptr) && !blockMarked(ptr)) {
      myfree(ptr);
    } else if (blockMarked(ptr)) {
      unmarkBlock(ptr);
    }
    ptr = nextBlock(ptr);
  }
}

int *isPtr(int *p) {
  int *ptr = start;
  if (ptr <= p && p <= (ptr+sizeOfHeap)) {
    return p;
  } else {
    return NULL;
  } 
}

int blockMarked(int *p) {
  return *(p - 1) != (*(p - 1) & ~0x2);
}

void markBlock(int *p) {
  *(p - 1) = (*(p - 1) | 0x2);
}

void unmarkBlock(int *p) {
  *(p - 1) = (*(p - 1) & ~0x2);
}

int blockAllocated(int *p) {
  return *(p - 1) != (*(p - 1) & ~0x1);
}

void allocateBlock(int *p) {
  *(p - 1) = (*(p-1) | 0x1);
}

void unallocateBlock(int *p) {
  *(p - 1) = (*(p-1) & ~0x1);
}

int *nextBlock(int *p) {
  return (p + length(p) / 4); 
}

int length(int *p) {
  return (*(p - 1) & ~0x7);
}