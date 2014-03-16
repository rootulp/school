// author: Rootul Patel
// file: MaxPQC
// date: 3/12/14
// MaxPQC ADT
// PS6

import java.util.*; 
 
public class MaxPQC<Key extends Comparable<Key>> implements MaxPQ<Key>{ 
    
    //Instance Variables 
    private int N; 
    private Node root; 
    private Node current; 
 
    //Create Node with 4 fields of info
    private class Node { 
        private Key info; 
        private Node left; 
        private Node right; 
        private Node parent; 
 
        //Set Info for Node
        private Node(Key info) { 
            this.info = info; 
            this.left = null; 
            this.right = null; 
            this.parent = null; 
        } 
 
        //Getters and Setters for Node
        private Key getInfo() {return this.info;} 
        private Node getLeft() {return this.left;} 
        private Node getRight() {return this.right;} 
        private Node getParent() {return this.parent;} 
        private void setInfo(Key i) {this.info = i;} 
        private void setLeft(Node n) {this.left = n;} 
        private void setRight(Node n) {this.right = n;} 
        private void setParent(Node n) {this.parent = n;} 
    } 
 
    // Constructor for PQC 
    public MaxPQC(){ 
        this.root = null; 
        this.current = this.root; 
        this.N = 0; 
    } 
    
    //Find function
    private void find(int nodeNum) {
        Deque<Integer> path = new LinkedList<Integer>();                  //Create deque to store path
        this.current = this.root; 
        int start = nodeNum/2; 
        while (start > 1) { 
            if (start <= 2) {path.push(start-2);start = start/2;}
            else {path.push(start%2);start = start/2;}                    //Pushes 0 or 1 depending on Left or Right1
        } 
        while (path.size() > 0 ){ 
            if (path.pop() == 0) {this.current = this.current.getLeft();} //0 = left
            else {this.current = this.current.getRight();}                //1 = right
        }
    }
 
    private void swim(Node nodeSwim) { 
        if (nodeSwim.getParent() != null && nodeSwim.getInfo().compareTo(nodeSwim.getParent().getInfo()) > 0) { 
            swap(nodeSwim, nodeSwim.getParent());
            swim(nodeSwim.getParent());
        } 
    } 
 
    private void sink(Node nodeSink) { 
        if (nodeSink.getLeft() != null) { //Check for left node
            Key big;
            if (nodeSink.getRight()==null || nodeSink.getLeft().getInfo().compareTo(nodeSink.getRight().getInfo()) > 0){ 
                big = nodeSink.getLeft().getInfo(); //Left is bigger
                if (nodeSink.getInfo().compareTo(big) < 0) { 
                    swap(nodeSink, nodeSink.getLeft()); 
                    sink(nodeSink.getLeft()); 
                } 
            } 
            else { 
                big = nodeSink.getRight().getInfo(); //Right is bigger
                if (nodeSink.getInfo().compareTo(big) < 0) {
                    swap(nodeSink, nodeSink.getRight()); 
                    sink(nodeSink.getRight()); 
                } 
            } 
        } 
    } 
 
    private void swap(Node node1, Node node2) { 
        Key node1Info = node1.getInfo(); 
        Key node2Info = node2.getInfo();
        node1.setInfo(node2Info); 
        node2.setInfo(node1Info); 
    } 
 
    public Key delMax(){ 
        if (this.root == null)                                  //If empty throw exception
            throw new NullPointerException("TREE UNDERFLOW"); 
        Node last = null;                                       //The last added node 
        Key rootInfo = this.root.getInfo();                     //Return this 
        find(this.N);                                           //Find last Node
        if (this.current.getRight() != null) last = this.current.getRight(); 
        else if (this.current.getLeft() != null)last = this.current.getLeft(); 
        if (last != null) { 
            swap(this.root, last); 
            if (this.N % 2 == 0) {this.current.setLeft(null);} //Delete the Max
            else {this.current.setRight(null);} 
            last.setParent(null); 
            sink(this.root); 
        } 
        else this.root = null; 
        N = N-1;              //N goes down by one
        return rootInfo; 
    } 
 
    public void insert(Key key){         //Insert
        Node nodeInsert = new Node(key); 
        N = N+1;                        //N goes up by one
        if (this.root == null) {        //If tree is empty
            this.root = nodeInsert; 
        } 
        else { 
            find(this.N);               //Find last Node
            nodeInsert.setParent(this.current); 
            if (this.N%2 == 0) nodeInsert.getParent().setLeft(nodeInsert); 
            else nodeInsert.getParent().setRight(nodeInsert); 
        } 
        swim(nodeInsert);                    //SWIM!
    } 
 
    public boolean isEmpty(){return N == 0;} //Is Empty?
    public int size(){return N;}             //Size
 
    public String toString(){                //To String function
        System.out.print("The Tree is: "); 
        print(this.root);                    //Recursively prints
        System.out.println(); 
        return "Done"; 
    } 
 
    private void print(Node parent){ //Recursively Prints Info
        System.out.print(parent.getInfo() + ""); 
        if (parent.getRight() != null && parent.getLeft() != null){ 
            System.out.print("("); 
            print(parent.getLeft()); //Node levels are differentiated by paranthesis
            System.out.print(","); 
            print(parent.getRight()); 
            System.out.print(")"); 
        } 
        else if (parent.getLeft() != null) { 
            System.out.print("("); 
            print(parent.getLeft()); 
            System.out.print(")");
        } 
    } 
 
    public static void main(String[] args) { 
        MaxPQ<Integer> test = new MaxPQC<Integer>(); 
        System.out.println("Is Tree Empty? " + test.isEmpty()); 
        System.out.println("Insert Numbers 1-9"); 
        int i = 1;
        while (i<10){test.insert(i);i+=1;}
        System.out.println(test.toString()); 
        System.out.println("Size = " + test.size());
        System.out.println("Insert 99 & 100"); 
        test.insert(99);
        test.insert(100); 
        System.out.println(test.toString()); 
        System.out.println("Delete Max = " + test.delMax()); 
        System.out.println(test.toString()); 
        System.out.println("Is Tree Empty? " + test.isEmpty());   
    } 
}