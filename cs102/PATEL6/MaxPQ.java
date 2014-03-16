// author: Rootul Patel
// file: MaxPQ
// date: 3/12/14
// MaxPQ API
// PS6

public interface MaxPQ<Key extends Comparable<Key>> {
    public Key delMax();
    public void insert(Key key);
    public boolean isEmpty();
    public int size();
    public String toString();
}