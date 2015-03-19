#include <stdlib.h>
#include "LinkedList.h"

List *List_create()
{
    return calloc(1, sizeof(List));
}

void List_push(List *list, void *value)
{
    ListNode *node = calloc(1, sizeof(ListNode));

    node->value = value;

    if(list->last == NULL) {
        list->first = node;
        list->last = node;
    } else {
        list->last->next = node;
        node->prev = list->last;
        list->last = node;
    }

    list->count++;

error:
    return;
}

void *List_pop(List *list)
{
    ListNode *node = list->last;
    return node != NULL ? List_remove(list, node) : NULL;
}

void List_unshift(List *list, void *value)
{
    ListNode *node = calloc(1, sizeof(ListNode));

    node->value = value;

    if(list->first == NULL) {
        list->first = node;
        list->last = node;
    } else {
        node->next = list->first;
        list->first->prev = node;
        list->first = node;
    }

    list->count++;

error:
    return;
}

void *List_shift(List *list)
{
    ListNode *node = list->first;
    return node != NULL ? List_remove(list, node) : NULL;
}

void *List_remove(List *list, ListNode *node)
{
    void *result = NULL;

    if(node == list->first && node == list->last) {
        list->first = NULL;
        list->last = NULL;
    } else if(node == list->first) {
        list->first = node->next;
        list->first->prev = NULL;
    } else if (node == list->last) {
        list->last = node->prev;
        list->last->next = NULL;
    } else {
        ListNode *after = node->next;
        ListNode *before = node->prev;
        after->prev = before;
        before->next = after;
    }

    list->count--;
    result = node->value;
    free(node);

error:
    return result;
}

void *List_forward_traverse(List *list)
{
    ListNode *curr = NULL;
    printf("FIRST -> ");
    for (curr = list->first; curr != NULL; curr = curr->next){
        printf ("%s -> ", curr->value);
    }
    printf ("LAST\n");
}

void *List_backward_traverse(List *list)
{
    ListNode *curr = NULL;
    printf("LAST -> ");
    for (curr = list->last; curr != NULL; curr = curr->prev){
        printf ("%s -> ", curr->value);
    }
    printf ("FIRST\n");
}

void *List_node_at(List *list, int index)
{
    if (index <= list->count) {
        int curr_index;
        ListNode *curr = list->first;

        for (curr_index = 1; curr_index != index; curr_index++){
            curr = curr->next;
        }

        return curr->value;
    }
}