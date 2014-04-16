// author: Rootul Patel & Nelson Demoraes
// file: HuffTreeC.java
// date: 4/6/14
// ps8
// HuffTreeC Implementation

import java.io.*;
import java.util.*;

public class HuffTreeC implements HuffTree, Comparable{
    //Instance Variables
    private int weight;
    private char ch;
    private HuffTree left;
    private HuffTree right;
    private HuffTree root;

    //Constructor 
    public HuffTreeC(char ch, int weight, HuffTree left, HuffTree right){
        this.ch = ch;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    //Getters
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
    
    //isLeaf
    public boolean isLeaf(){ 
        return (this.getCh() != 0); 
    }
    
    //checkLeaf
    public char checkLeaf(BinaryIn in){
        char tempCh = this.getCh();
        if (tempCh != 0) {
            return tempCh;
        }
        else if (in.readInt(1) == 0) {
            return this.getLeft().checkLeaf(in);
        }
        return this.getRight().checkLeaf(in);
    }

    //MakeBitPattern
    public void makeBitPattern(int s, int r, SymTable<Integer, TableValue> st){
        if (getCh() == 0 && getLeft() != null) {
            this.getLeft().makeBitPattern(s * 2 | 0, r+1, st);
        }
        if (getCh() == 0 && getRight() != null) {
            this.getRight().makeBitPattern(s * 2 | 1,r+1, st);
        }
        if (getCh() != 0) {
            char key = getCh();
            TableValue val = (TableValue) st.get((int) key);
            val.setBits(new BitPatternC(s, r));
        }
    }
    
    public int compareTo(Object otherTree) {
        HuffTree other = (HuffTree) otherTree;
        return this.getWeight() - other.getWeight();
    }
    
    public String toString() {
        String l;
        String r;
        if(this.ch != 0) {
            return this.ch + ":" + this.weight;
        }
        if (this.getLeft() == null) {
            l = "";
        }
        else l = this.getLeft().toString();
        if (this.getRight() == null) {
            r = "";
        }
        else r = this.getRight().toString();
        return "Node(" + this.weight + ", " + l + ", " + r + ")";
    }

    public void writeText(FileIO io, BinaryIn in) throws IOException{
        FileWriter out = io.openOutputFile();
        for (int i = 0; i < this.getWeight(); i++) {
            char letter = this.checkLeaf(in);
            out.write(letter);
        }
        out.close();
        System.out.println("Finished writing characters to output file");
    }
}