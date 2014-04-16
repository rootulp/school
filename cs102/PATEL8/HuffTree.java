// author: Rootul Patel & Nelson Demoraes
// file: HuffTree.java
// date: 4/6/14
// ps8
// HuffTree Interface

import java.io.*;
import java.util.*;

public interface HuffTree{
    public int getWeight();
    public char getCh();
    public char checkLeaf(BinaryIn in);
    public HuffTree getLeft();
    public HuffTree getRight();
    public void writeText(FileIO io, BinaryIn in) throws IOException;
    public void makeBitPattern(int s, int r, SymTable<Integer, TableValue> st);
    public boolean isLeaf();
}