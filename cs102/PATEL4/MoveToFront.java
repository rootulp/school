// file: MoveToFront.java
// author: Rootul Patel
// date: February 9, 2014
//
// MoveToFront Implementation
// PS4 Bonus - MoveToFront

import java.util.NoSuchElementException;

public class MoveToFront {
   
    // Instance Variables & Declaring stuff
    private Node first;
    private static class Node {
        private char item;
        private Node next;
        Node(char item, Node next) { this.item = item; this.next = next; }
    }

    // Is Empty Function
    public boolean isEmpty() { return (first == null); }

    // Delete node with given char if it exists
    // Return Node if true
    private Node delete(Node x, char item) {
        if (x == null) return null;
        if (x.item == item) return x.next;
        x.next = delete(x.next, item);
        return x;
    }

    // Test to see if linked list contains char already
    // Returns location if true
    private int test(char item) {
        int i = 1;
        for (Node x = first; x != null; x = x.next) {
            if (x.item == item) return i;
            i++;
        }
        return 0;
    }

    // Push item to  front
    int push(char item) {
        int location = test(item);
        first = delete(first, item);
        first = new Node(item, first);
        return location;
    }

    // toString function
    public String toString() {
        StringBuilder sb = new StringBuilder("The Linked List = ");
        Node temp = this.first;
        while(temp != null) {
            sb.append(Character.toString(temp.item) + " ");
            temp = temp.next;
        }
        return sb.toString();
    }

    // The MAIN!
    public static void main(String[] args) {
        MoveToFront ll = new MoveToFront();
        String s = "ABCADEF";
        Character a = new Character('A');
        Character b = new Character('B');
        Character c = new Character('C');
        Character d = new Character('D');
        ll.push(a);
        System.out.println("Push A");
        ll.push(b);
        System.out.println("Push B");
        ll.push(c);
        System.out.println("Push C");
        ll.push(a);
        System.out.println("Push A");
        ll.push(d);
        System.out.println("Push D");
        System.out.println(ll.toString());
    }

}

    