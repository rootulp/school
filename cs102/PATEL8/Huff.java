// author: Rootul Patel & Nelson Demoraes
// file: Huff.java
// date: 3/29/14
// ps7
// Huff Implementation

import java.io.*;
import java.util.*;

public class Huff {
    //Instance Variables
    public static boolean DEBUG = true; //DEBUG from FileIO
    private String input; //Input
    private FileIO io = new FileIOC(); //FileIO
    private SymTable<Integer, TableValue> st = new SymTableC<Integer, TableValue>(); //Symbol Table of type Integers and Values
    private PriorityQueue<HuffTree> HuffPQ; //MinPQ of type Hufftrees
    private static int Header = 0x0BC0; //Header 
    
    public Huff(String input) {
        this.input = input;
    }

    private void huffStart() throws IOException { //Main Function
        this.makeTable();
        HuffTree tree = this.makeHTree();
        makeBinary(tree);
        //st.toStringCodes();
        //System.out.println("Tree: " + tree.toString());
    }

    private void makeTable() throws IOException{ //Makes Symbol Table
        FileReader inputFile = io.openInputFile(this.input);
        int temp = 0;
        int freq;
        while (temp!= -1){ //Temp only ='s -1 when file is done reading
            temp = inputFile.read();
            if (temp!= -1){ //To catch -1
                if (st.containsKey(temp)){
                    freq = st.get(temp).getFrequency();
                    st.get(temp).setFrequency(freq + 1); //Increment frequencey if found
                }
                else{
                    st.put(temp, new TableValueC(1)); //Else input with frequency = 1
                }
            }
        }
        inputFile.close();
    }

    private HuffTree makeHTree() {
        Set<Integer> STKeys = st.getKeys();
        PriorityQueue<HuffTree> tempQueue = new PriorityQueue<HuffTree>();
        for (Integer i : STKeys) {
            int freq = st.get(i).getFrequency();
            char c = (char) i.intValue();
            tempQueue.add(new HuffTreeC(c, freq, null, null));
        }
        while (tempQueue.size() > 1) { //Combine all individual trees into one
            HuffTree p1 = tempQueue.poll();
            HuffTree p2 = tempQueue.poll();
            int tempWeight = p1.getWeight() + p2.getWeight(); //temp weight is sum of two previous trees
            tempQueue.add(new HuffTreeC((char) 0, tempWeight, p1, p2)); //Add one tree back that is the two previous combined
        }
        return tempQueue.poll(); //Return final huff Tree

    }
    
    private void makeBinary(HuffTree tree) throws IOException {
        tree.makeBitPattern(0, 0, this.st);
        FileReader inputFile = io.openInputFile(this.input);
        BinaryOut outFile = io.openBinaryOutputFile();
        outFile.write(this.Header,16); //Write the header number
        Set<Integer> STKeys = this.st.getKeys();
        outFile.write(STKeys.size()); //Write the size of the symbol table
        for (Integer i : STKeys) { //Print the symbol table and frequencies
            TableValue tv = (TableValue) this.st.get(i);
            char c = (char)((int) i);
            int freqW = tv.getFrequency();
            outFile.write(c);
            outFile.write(freqW);
        }
        inputFile.close();
        FileReader fin = io.openInputFile(this.input);
        int temp = 0; 
        int freq;
        while (temp!= -1){ //Temp only ='s -1 when file is done reading
            temp = fin.read();
            if (temp!= -1){ //Catch -1
                TableValue bits = st.get(temp);
                BitPattern bp = bits.getBits();
                outFile.write(bp.getBit(), bp.getLength()); //Write the bitpatterns
            }
        }
        outFile.close();
        fin.close();
    }
 
    //The MAIN!!!    
    public static void main(String[] args) throws IOException {
        new Huff(args[0]).huffStart();
    }
}