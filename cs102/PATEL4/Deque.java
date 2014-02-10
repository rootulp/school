// file: Deque.java
// author: Rootul Patel
// date: February 9, 2014
//
// Deque Interface
// PS4

public interface Deque<T> {

  public void pushLeft(T item);
  public void pushRight(T item);
  public T popLeft();
  public T popRight();
  public int size();
  public boolean isEmpty();
  public String toString();
}