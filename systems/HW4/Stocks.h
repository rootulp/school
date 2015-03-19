#include <stdlib.h>
#include "LinkedList.h"

struct stock;

typedef  struct Stock{
	char * stockSymbol;
    float closingSharePrice;
    float openingSharePrice;
    int numberOfShares;
    float (* getPrice) (void * S);
    float (* getTotalDollarAmount)(void * S);
    float (* getPercentChange)(void * S);
    char * (* toString)( void * S);
} Stock;

Stock * Stock_create(char *name, float closingSharePrice, float openingSharePrice, int numberOfShares);
