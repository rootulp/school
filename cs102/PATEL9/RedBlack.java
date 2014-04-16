// author: Rootul Patel
// file: RedBlack.java
// date: 4/12/14
// PS9

public interface RedBlack<Key extends Comparable<Key>, Value> {
    
    //Regular functions
    public boolean isEmpty();
    public int size();
    public boolean contains(Key key);
    public Value get(Key key);
    public RedBlack<Key, Value> put(Key key, Value val);
    public RedBlack<Key, Value> put(Key key, Value val, String s);
    public String toString();
    public String toStringTree();
    public RedBlack fix();
    
    //Getters and Setters
    public RedBlack getLeft();
    public RedBlack getRight();
    public boolean getColor();
    public void setLeft(RedBlack irb);
    public void setRight(RedBlack irb);
    public void setColor(boolean color);
    public void setSize(int n);
    public boolean isRed();    
}