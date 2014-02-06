// file: Stack.java
// author: Rootul Patel
// date: January 30, 2014
//
// Stack Interface
//

public interface Stack<T> {
    public void push(T element);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public int size();
}