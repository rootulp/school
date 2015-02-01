/* Hello World program */

#include<stdio.h>

main()
{
  int x;
  x = 5;

  int *p;
  p = &x;

  printf("x variable: %x", x);
  printf("\n");
  printf("x variable location: %p", p);
  printf("\n");
}