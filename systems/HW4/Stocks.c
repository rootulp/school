#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Stocks.h"

float getTotalDollarAmount(void * S) {
	return (((Stock*)S)->closingSharePrice) * (((Stock*)S)->numberOfShares);
}

float getPercentChange(void * S) {
	float closing = (((Stock*)S)->closingSharePrice);
	float opening = (((Stock*)S)->openingSharePrice);
	return ((closing - opening) / opening);
}

float getPrice(void * S) {
	float closing = (((Stock*)S)->closingSharePrice);
	return closing;
}

void toString(void * S) {
	Stock * stock = (Stock*)S;
	printf("%s, Closing: %f, Opening: %f, Num %d, Change: %f, Total: %f \n", stock->stockSymbol, stock->closingSharePrice, stock->openingSharePrice, stock->numberOfShares, stock->getPercentChange(stock), stock->getTotalDollarAmount(stock));
}

Stock * Stock_create(char *name, float closingSharePrice, float openingSharePrice, int numberOfShares){
	int nameLen = strlen(name);
	Stock *stock = (Stock *) malloc (sizeof(Stock));
	stock->stockSymbol = (char *) malloc(sizeof(char)*nameLen+1);
	strcpy(stock->stockSymbol, name);
	stock->closingSharePrice = closingSharePrice;
	stock->openingSharePrice = openingSharePrice;
	stock->numberOfShares = numberOfShares;
	stock->getPrice = getPrice;
	stock->getTotalDollarAmount = getTotalDollarAmount;
	stock->getPercentChange = getPercentChange;
	stock->toString = toString;
}


