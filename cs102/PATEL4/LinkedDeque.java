// file: LinkedDeque.java
// author: Rootul Patel
// date: February 9, 2014
//
// Linked Deque Implementation
// PS4

import java.util.NoSuchElementException;

public class LinkedDeque<T> implements Deque<T> {
    
    // Instance variables
    private int N;
    private Node<T> left;
    private Node<T> right;
    private class Node<T> {
        private T info;
        private Node next;
        private Node prev; 
    }
    
    // Constructor
    public LinkedDeque() {
        this.left = null;
        this.right = null;
        this.N = 0;
    }

    // isEmpty function
    public boolean isEmpty() { return this.N == 0; }  
    
    // size function
    public int size() { return N; }                   
    
    // toString function
    public String toString() {
        StringBuilder sb = new StringBuilder("The Deque = ");
        Node temp = this.left;
        while(temp != null) {
            sb.append(temp.info.toString() + " ");
            temp = temp.next;
        }
        return sb.toString();
    }
    
    // pushLeft function
    public void pushLeft(T info) {
        Node<T> temp = left;
        left = new Node<T>();
        left.info = info;
        if(temp != null) {
            temp.prev = left;
        }
        if(isEmpty()) {
            right = left;
        }
        else {
            left.next = temp;
        }
        this.N++;
    }
    
    // pushRight function
    public void pushRight(T info) {
        Node<T> temp = right;
        right = new Node<T>();
        right.info = info;
        if(temp != null) {
            temp.next = right;
        }
        if(isEmpty()) {
            left = right;
        }
        else {
            right.prev = temp;
        }
        this.N++;
    }
    
    // popLeft function
    public T popLeft() {
        if (isEmpty()) throw new NoSuchElementException("Deque Underflow");
        T info = left.info;
        left = left.next;
        left.prev = null;
        N--;
        if(isEmpty()) {
            right = null;
        }
        return info;
    }
    
    // popRight function
    public T popRight() {
        if (isEmpty()) throw new NoSuchElementException("Deque Underflow");
        T info = right.info;
        right = right.prev;
        right.next = null;
        N--;
        if(isEmpty()) {
            left = null;
        }
        return info;
    }
    
    // the MAIN!
    public static void main(String[] args) {
        Deque<String> dq = new LinkedDeque<String>();
        dq.pushLeft("1");
        dq.pushLeft("2");
        dq.pushRight("3");
        dq.pushRight("4");
        System.out.println(dq.toString());
        StdOut.println("pop left " + dq.popLeft());
        StdOut.println("pop right " + dq.popRight());
        System.out.println(dq.toString());
        System.out.println("deque size: " + dq.size());
        System.out.println("is the deque empty? " + dq.isEmpty());
    }
}

    