#include "LinkedList.h"
#include "Thread.h"

#define MAX_SENSORS 25

// Declare globals
List *list;
int sensors;
int cycles;

// Thread stuffs
pthread_mutex_t lock;
pthread_cond_t self;

// Random variables
int btime;
long ltime;
  
int main(int argc,char *argv[]){
	
	// Check for two arguments
	if(argc != 2) {
    	printf("You did not enter two arguments\n\n");
     	exit(0);
    } else {
    	sensors = atoi(argv[1]);
    	printf("You entered %d sensors\n", sensors);
    	printf("Running for 10 cycles\n\n", sensors);
	}

    // Initialize vars and pointers
    list = List_create();
    cycles = 0;
    Temp *temp;
    int sensor_index;
    pthread_t sensor_thread[MAX_SENSORS];
    
    pthread_mutex_init(&lock, NULL);
    pthread_cond_init(&self, NULL);
   
    for (sensor_index = 0; sensor_index < sensors; sensor_index++) {
    	// Random stuff
    	ltime = time(NULL);
    	btime = (unsigned) ltime/2;

    	// Temp stuff
    	temp = (Temp *)malloc(sizeof(Temp));
    	temp->id = sensor_index;
    	temp->seed = btime;

    	// Sleep
    	sleep(1);
    
    	// Call insertTemp
	    if (pthread_create(&(sensor_thread[sensor_index]), NULL, (void *)insertTemp, temp) !=0) {
	    	perror("pthread_create");
	    	exit(1);
	    }
	}

	for (sensor_index = 0; sensor_index < sensors; sensor_index++) {	
		pthread_join(sensor_thread[sensor_index], NULL);
	}
	return 0;
}
void insertTemp(Temp *temp) {

	Temp *new_temp;
	int cycle_index = 0;
	int rand_temp;
	srand(temp->seed);

	while (cycle_index < 10) {
		
		// Generate rand temp between -30 and 100 degrees
		rand_temp = (rand() % 130) - 30;
		new_temp = (Temp *)malloc(sizeof(Temp));
		new_temp->id = temp->id;
		new_temp->seed = rand_temp;

		printf("%d. Sensor is adding %d to Linked List\n", temp->id, rand_temp);
		pthread_mutex_lock(&lock);
		List_push(list, new_temp);
		cycles++;
		
		while ( cycles < ((cycle_index + 1) * sensors) ) {
			pthread_cond_wait(&self, &lock);

			// Iterate and average
			int total = 0;
			ListNode *curr;
			Temp *curr_temp;
			curr = list->first;
			
			list = List_create(); 
			while (curr != NULL) {
				curr_temp = curr->value;
				total += curr_temp->seed;
				curr = curr->next;
			}

			// Check if total == 0 to avoid garbage total / average values
			if (total != 0) {
				printf("Linked List Total is: %d\n", total);
				printf("Linked List Average is: %d\n\n", (total/sensors));
			}

		}
		
		pthread_mutex_unlock(&lock);
		pthread_cond_broadcast(&self);

		// Sleep and Increment
		sleep(1);
		cycle_index++;
	}
	pthread_exit(NULL);
}