// author: Rootul Patel
// file: EmptyC.java
// date: 4/12/14
// PS9

public class EmptyC<Key extends Comparable<Key>, Value> implements RedBlack<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private boolean color = BLACK;
    
    //Setters & Getters
    public void setLeft(RedBlack rbtree) {}
    public void setRight(RedBlack rbtree){}
    public void setColor(boolean color) {}
    public void setSize(int n){}
    public EmptyC getRight(){
        return this;
    }
    public EmptyC getLeft(){
        return this;
    }
    public boolean getColor() { 
        return this.color; 
    }

    public boolean isEmpty() { 
        return true; //Empty always empty
    }

    public int size() { 
        return 0; 
    }

    public boolean contains(Key key) { 
        return false; 
    }

    public Value get(Key key) { 
        return null; 
    }

    public RedBlack<Key, Value> put(Key key, Value val, String s) {
        return new RedBlackC(key, val, RED, new EmptyC<Key, Value>(), new EmptyC<Key, Value>());
    }

    public RedBlack<Key, Value> put(Key key, Value val) {
        return new RedBlackC(key, val, RED, new EmptyC<Key, Value>(), new EmptyC<Key, Value>());
    }

    public String toString() { 
        return " -- ";
    }

    public boolean isRed(){
        return false;
    }

    public RedBlack fix(){
        return this;
    }
    public String toStringTree() { 
        System.out.print(" -- "); 
        return "";
    }
}