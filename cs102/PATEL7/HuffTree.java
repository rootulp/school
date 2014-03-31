// author: Rootul Patel & Nelson Demoraes
// file: HuffTree.java
// date: 3/29/14
// ps7
// HuffTree Interface

import java.io.*;
import java.util.*;

public interface HuffTree{
    public int getWeight();
    public char getCh();
    public HuffTree getLeft();
    public HuffTree getRight();
    public void makeBitPattern(int s, int r, SymTable<Integer, TableValue> st);
    public int compareTo(Object otherTree);
    public String toString();
}
