#include <stdlib.h>
#include <stdio.h>
#include "Stocks.h"


void printIterate(List * list, float amount, void (*evalPrint)(float amount, void *item));
void printAll(float amount, void *item);
void printClosingSharePrice(float amount, void *item);
void printPercentChange(float amount, void *item);

int main() {

	List * list = List_create();
	Stock * stock;
	float user_amount;
	int user_input; 
	char stockSymbol[13];
	char closing[13];
	char opening[13];
	char num[13];
	FILE *fp;

	fp = fopen("stocks", "r");

	if (fp == NULL) {
  		fprintf(stderr, "Can't open input file in.list!\n");
  		exit(1);
	}

	while (fscanf(fp, "%s %s %s %s", stockSymbol, closing, opening, num) != EOF) {
	  float closingSharePrice = atof(closing);
	  float openingSharePrice = atof(opening);
	  int numberOfShares = atoi(num);

	  stock = Stock_create(stockSymbol, closingSharePrice, openingSharePrice, numberOfShares);
	  List_push(list, stock);
	}

	while(1) {
		puts("welcome to fun C");
		puts("please choose from the following choices");
		puts("1. Search/print stock with closing price > some amount");
		puts("2. Search/print stock with percent change  > some amount");
		puts("3. List all the stocks using toString");
		puts("4. Quit and leave");
		printf("input: ");
		scanf("%d", &user_input);

		printf("\n");
		switch(user_input) {
			case 1:
				printf("Closing amount: ");
				scanf("%f", &user_amount);
				printf("\n");
				printIterate(list, user_amount, printClosingSharePrice);
				break;
			case 2:
				printf("Change percent amount: ");
				scanf("%f", &user_amount);
				printf("\n");
				printIterate(list, user_amount, printPercentChange);
				break;
			case 3:
				printIterate(list, 0.0, printAll);
				break;
			case 4:
				return 0;
		}
		printf("\n");
	}
}

void printIterate(List * list, float amount, void (*evalPrint)(float amount, void *item)){
	ListNode *curr = list->first;

	while (curr!=NULL)
	{
		evalPrint(amount, curr->value);
		curr = curr->next;
	}
}

void printAll(float amount, void *item){
	Stock *stock = (Stock *) item;
	stock->toString(stock);
}

void printClosingSharePrice(float amount, void *item){
	Stock *stock = (Stock *) item;
	if(stock->closingSharePrice > amount)
		stock->toString(stock);
}

void printPercentChange(float amount, void *item){
	Stock *stock = (Stock *) item;
	if(stock->getPercentChange(stock) > amount)
		stock->toString(stock);
}