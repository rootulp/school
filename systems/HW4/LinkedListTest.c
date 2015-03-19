#include <stdlib.h>
#include "LinkedList.h"

static List *list = NULL;
char *test1 = "junk info";
char *test2 = "1";
char *test3 = "pop it";
char *test4 = "shift it";
char *test5 = "3";
char *test6 = "5";
char *test7 = "7";

int main (){
	list = List_create();

	List_push(list, test1);
	List_push(list, test2);
	List_push(list, test3);
	List_unshift(list, test4);

	char *remove_val = List_remove(list, list->first->next);
    printf ("remove val should be junk info: %s\n", remove_val);

    char *pop_val = List_pop(list);
    printf ("pop val should be pop it: %s\n", pop_val);

    char *shift_val = List_shift(list);
    printf ("shift val should be shift it: %s\n", shift_val);

    List_push(list, test5);
	List_push(list, test6);
	List_push(list, test7);

    List_forward_traverse(list);
    List_backward_traverse(list);

    char *node2_val = List_node_at(list, 2);
    printf ("val in node 2 is: %s\n", node2_val);

	return 0;
}