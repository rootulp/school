// file: Deque.java
// author: Rootul Patel
// date: February 9, 2014
//
// LinkedDeque Implementation
// PS4

public class LinkedDeque<T> implements Deque<T> {
    
    // Instance variables
    //
    private Node front;
    private Node back;
    private int N;
    
    public LinkedDeque() {
        this.front = null;
        this.back = null;
        this.N = 0;
    }
        
    private class Node {
        private T info;
        private Node next;
        
        private Node(T info) {
            this.info = info;
            this.next = null;
        }
        private T getInfo()    { return this.info; }
        private Node getNext() { return this.next; }
    }
    
    public void pushRight(T info) {
        Node temp = new Node(info);
        if(this.isEmpty())
            this.front = temp;
        else
            this.back.next = temp;
        this.back = temp;
        this.N++;
    }
    
    public void pushLeft(T info) {
        Node temp = new Node(info);
        if(this.isEmpty())
            this.back = temp;
        else
            this.front.next = temp;
        this.front = temp;
        this.N++;
    }
    
    public T popLeft() {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException("Queue is empty.");
        T frontValue = this.front.getInfo();
        this.front = this.front.getNext();
        this.N--;
        if(this.isEmpty())
            this.back = null;
        return frontValue;
    }
    
    public T popRight() {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException("Queue is empty.");
        T backValue = this.back.getInfo();
        this.back = this.back.getNext();
        this.N--;
        if(this.isEmpty())
            this.front = null;
        return backValue;
    }
       
    public String toString() {
        StringBuilder sb = new StringBuilder("Deque = ");
        Node temp = this.front;
        while(temp != null) {
            sb.append(temp.getInfo().toString() + " ");
            temp = temp.getNext();
        }
        return sb.toString();
    }
    
    public boolean isEmpty() { return this.N == 0; }
    public int size()        { return this.N; }
    
    public static void main(String[] args) {
     
        Deque<String> qs = new LinkedDeque<String>();
        qs.pushLeft("1");
        qs.pushLeft("2");
        qs.pushRight("3");
        qs.pushRight("4");
        System.out.println(qs.toString());
        System.out.println(qs.size());
    }
}

    