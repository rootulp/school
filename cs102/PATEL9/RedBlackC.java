// author: Rootul Patel
// file: RedBlackC.java
// date: 4/12/14
// PS9

public class RedBlackC<Key extends Comparable<Key>, Value> implements RedBlack<Key, Value> {
    
    //Instance Variables
    private int N;
    private Key key;
    private Value val;
    private RedBlack<Key, Value> left;
    private RedBlack<Key, Value> right;
    private boolean color;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //Constructor
    public RedBlackC(Key key, Value val, boolean color, RedBlack left, RedBlack right){
        this.val = val;
        this.key = key;
        this.color = color;
        this.left = left;
        this.right = right;
        this.N = this.left.size() + this.right.size() + 1;
    }
    
    //Getters & Setters
    public RedBlack getLeft(){return this.left;}
    public RedBlack getRight(){return this.right;}
    public boolean getColor() {return this.color;}
    public boolean isRed(){return this.color == RED;}
    public void setLeft(RedBlack rbtree){this.left = rbtree;}
    public void setRight(RedBlack rbtree){this.right = rbtree;}
    public void setColor(boolean color) {this.color = color;}
    public void setSize(int n){this.N = n;}

    public RedBlack<Key, Value> put(Key key, Value val){
        RedBlack tree = put(key,val, "");
        tree.setColor(BLACK);
        return tree;
    }

    public boolean isEmpty(){
        return false; //EmptyC returns true
    }

    public int size(){
        return this.N; //Calculates N based off subtree's Ns
    }

    public boolean contains(Key key){
        return this.get(key) != null; //If get returns something, tree contains it
    }

    public Value get(Key key){
        int comp = this.key.compareTo(key);
        if (comp < 0) return this.right.get(key); //Recursively walks down the tree
        if (comp > 0) return this.left.get(key);
        return this.val;
    }

    public RedBlack<Key, Value> put(Key key, Value val, String s){
        int comp = this.key.compareTo(key);
        if (comp == 0) //Creates new tree at current position
            return new RedBlackC<Key, Value>(key, val, this.color, this.left, this.right);
        else if (comp < 0) //Recursively walks down the tree and fixes on way back upir
            return new RedBlackC<Key, Value>(this.key, this.val, this.color, this.left, this.right.put(key, val,"")).fix();
        else
            return new RedBlackC<Key, Value>(this.key, this.val, this.color, this.left.put(key, val,""), this.right).fix();
    }

    public RedBlack fix(){ //Performs rotates and flips to fix tree
        if (this.getRight().isRed() && !this.getLeft().isRed()) { return this.rotateLeft().fix(); }
        if (this.getLeft().isRed()  &&  this.getLeft().getLeft().isRed())  return this.rotateRight().fix();
        if (this.getLeft().isRed()  &&  this.right.isRed())  return this.flipColors().fix();
        else return this;
    }
    
    private RedBlack rotateLeft(){ //left rotation
        RedBlack<Key, Value> rt = this.getRight();
        this.setRight(rt.getLeft());
        rt.setLeft(this);
        rt.setColor(this.getColor());
        this.setColor(RED);
        rt.setSize(this.N);
        this.N = this.left.size() + this.right.size() + 1;
        return rt;
    }
    
    private RedBlack rotateRight(){ //right rotation
        RedBlack<Key, Value> lt = this.getLeft();
        this.setLeft(lt.getRight());
        lt.setRight(this);
        lt.setColor(this.getColor());
        this.setColor(RED);
        lt.setSize(this.N);
        this.N = this.getLeft().size() + this.getRight().size() + 1;
        return lt;

    }
    
    private RedBlack flipColors(){ //flip colors
        this.color = !this.color;
        this.getLeft().setColor(!this.getLeft().getColor());
        this.getRight().setColor(!this.getRight().getColor());
        return this;
    }

    public String toStringTree(){ //toString Stuff
        System.out.print(" " + this.key + ":" + this.val + ":" + this.showColor());
        System.out.print(" <--(LT");
            this.getLeft().toStringTree();
        System.out.print(" RT");
            this.getRight().toStringTree();
        System.out.print(")");
        return "";
    }

    public String toString(){
        return this.getLeft().toString() + this.key + ":" + this.val + ":" + this.showColor() + this.getRight().toString();
    }
    
    private String showColor(){
        if (getColor()) return "R";
        else return "B";
    }

    //The MAIN!
    public static void main (String[] args){
        RedBlack<String, Integer> rbtree = new EmptyC<String, Integer>();
        System.out.println("Is tree empty? " + rbtree.isEmpty());
        System.out.println("Putting F-L-O-R-I-D-A");
        rbtree = rbtree.put("F", 1);
        rbtree = rbtree.put("L", 2);
        rbtree = rbtree.put("O", 3);
        rbtree = rbtree.put("R", 4);
        rbtree = rbtree.put("I", 5);
        rbtree = rbtree.put("D", 6);
        rbtree = rbtree.put("A", 7);
        System.out.print("Contents of Tree:");
        System.out.println(rbtree.toString());
        System.out.print("Entire Tree:");
        System.out.println(rbtree.toStringTree());
        System.out.print("Get Value for 'F':");
        System.out.println(rbtree.get("F"));
        System.out.println("Is tree empty? " + rbtree.isEmpty());
    }
}