#include <stdlib.h>
#define Thread

// Declare Temp
struct Temp;

// Define Temp
typedef struct Temp {
	int id;         
	int seed;
}Temp;

// Declare function
void insertTemp(Temp *temp);