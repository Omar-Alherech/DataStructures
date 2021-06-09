#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

struct node
{
    struct node* next;
    int data;
};


struct node* insert(struct node* list, int value)
{
    //Put your code here
    struct node* previous = NULL;
    struct node* new_node = NULL;
    struct node* current = list;
    while(current != NULL)
    {
        if (current->data >= value){
            if (previous == NULL){
                new_node = malloc(sizeof(struct node));
                new_node->data = value;
                new_node->next = current;
                previous = new_node;
                list = new_node;
                break;
            }
            new_node = malloc(sizeof(struct node));
            new_node->data = value;
            new_node->next = current;
            previous->next = new_node;
            break;
        }
        if (current->next == NULL) {
            new_node = malloc(sizeof(struct node));
            new_node->data = value;
            current->next = new_node;
            new_node->next = NULL;
            current->next = new_node;
            break;
        }
        previous = current;
        current = current->next;
    }



    return list;

};

struct node* delete(struct node* list, int value)
{
    struct node* previous = NULL;
    struct node* cursor = list;
    while (cursor != NULL) {
        if (cursor->data == value) {
            if (previous == NULL) {
                cursor = cursor->next;
                free(list);
                return cursor;
            } else {
                previous->next = cursor->next;
                free(cursor);
                break;
            }
        } else {
            previous = cursor;
            cursor = cursor->next;
        }
    }
    return list;
};


int main()
{
    struct node* head;
    head = malloc(sizeof(struct node));
    head->data =0;
    head->next = malloc(sizeof(struct node));
    struct node* current = head-> next;
    for(int idx = 2; idx < 10; idx +=2)
    {
        current->data = idx;
        current->next = malloc(sizeof(struct node));
        current = current->next;
    }
    current->data = 10;
    current = head;
    current = delete(current, 0);
    current = insert(current, -3);
    current = insert(current, 3);
    current = delete(current, 10);
    current = insert(current, 12);
    current = delete(current, 2);
    current = delete(current, 7);

    while(current != NULL)
    {
        printf("found element number %d \n", current->data);
        head = current;
        current = current->next;
        free(head);
    }


}