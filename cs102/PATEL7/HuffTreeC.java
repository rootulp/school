// author: Rootul Patel & Nelson Demoraes
// file: HuffTreeC.java
// date: 3/29/14
// ps7
// HuffTreeC Implementation

import java.io.*;
import java.util.*;

public class HuffTreeC implements HuffTree, Comparable{
    //Instance Variables
    private int weight;
    private char ch;
    private HuffTree left;
    private HuffTree right;

    //Constructor
    public HuffTreeC(char ch, int weight, HuffTree left, HuffTree right){
        this.ch = ch;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public int getWeight(){ 
        return this.weight; 
    }
    
    public char getCh(){ 
        return this.ch; 
    }
    
    public HuffTree getLeft(){ 
        return this.left; 
    }
    
    public HuffTree getRight(){ 
        return this.right; 
    }

    public void makeBitPattern(int s, int r, SymTable<Integer, TableValue> st){
        if (getCh() == 0 && getLeft() != null) {
            this.getLeft().makeBitPattern(s * 2 | 0, r+1, st);
        }
        if (getCh() == 0 && getRight() != null) {
            this.getRight().makeBitPattern(s * 2 | 1, r+1, st );
        }
        if (getCh() != 0) {
            char key = getCh();
            TableValue val = (TableValue) st.get((int) key);
            val.setBits(new BitPatternC(s, r));
        }
    }

    public int compareTo(Object otherTree){
        HuffTree other = (HuffTree) otherTree;
        return this.getWeight() - other.getWeight();
    }
    
    public String toString(){
        String left;
        String right;
        if(this.ch != 0) return this.ch + ":" + this.weight;

        if (this.getLeft() == null) left = "";
        else left = this.getLeft().toString();

        if (this.getRight() == null) right = "";
        else right = this.getRight().toString();

        return "Node(" + this.weight + ", " + left + ", " + right + ")";
    }

}
