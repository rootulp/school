// file: ResizingArrayDeque.java
// author: Rootul Patel
// date: February 9, 2014
//
// ResizingArrayDeque Implementation
// PS4

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class ResizingArrayDeque<T> implements Deque<T> {
    
    private T[] a = (T[])new Object[6];
    private int N = 0;

    public boolean isEmpty(){  //Simple isEmpty function
        return N == 0;
    }

    public int size(){         //Return N for size
        return N;
    }
                                //Resize function copied from Sedgewig and Wayne
    private void resize(int capacity) {
        assert capacity >= N;  
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    public void pushRight(T item) {
                                //If array becomes too big, resize to double the size
        if (N == a.length-1) resize(2*a.length);   
        a[N] = item;            //Set element in rightmost position to item 
        N = N+1;                //Increment N
    }
    
    public void pushLeft(T item) {
                                 //If array becomes too big, resize to double the size
        if (N == a.length-1) resize(2*a.length);    
        for (int i=N; i>-1;i--){ 
            a[i+1] = a[i];
        }
        a[0] = item;             //Set element in leftmost position to item
        N = N+1;                 //Increment N
        }
    
    public T popRight() {
                                 //Test if is empty and throw exception
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");  
        T item = a[N-1];
        a[N-1] = null;                              
        N = N-1;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    
    public T popLeft() {
                                 //Test if is empty and throw exception
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");      
        T temp = a[0];           //Set temp variable as leftmost element
        for(int i=1; i<N; i++){  //Move Array one unit to the left
            a[i-1]=a[i];      
        }
        N = N-1;                 //Decrement N
        if (N > 0 && N == a.length/4) resize(a.length/2);
        a[N] = null;             //Set array value of N to null
        return temp;             
    }
    
    public String toString(){
        return Arrays.toString(a);
    }
    
    public static void main(String[] args) {
                                  //Create deque with test values
        ResizingArrayDeque<String> dq = new ResizingArrayDeque<String>();
        dq.pushLeft("3");
        dq.pushLeft("2");
        dq.pushLeft("1");
        dq.pushRight("4");
        dq.pushRight("5");
                                  //Print out original deque
        StdOut.println("Original Deque " + dq.toString());
                                  //Fill up deque forcing a resize
        dq.pushRight("6");
                                  //Print out resized deque
        StdOut.println("Resized Deque " + dq.toString());
                                  //Pop some values
        StdOut.println("pop left " + dq.popLeft());
        StdOut.println("pop right " + dq.popRight());
        StdOut.println("pop left " + dq.popLeft());
                                  //Print out new deque
        StdOut.println(dq.toString());
                                 //Print out size and is empty
        StdOut.println("Size: " + dq.size());
        StdOut.println("Is this Deque Empty? " + dq.isEmpty());   
    }
}
        