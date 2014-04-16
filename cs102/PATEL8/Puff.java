// author: Rootul Patel & Nelson Demoraes
// file: Puff.java
// date: 4/6/14
// ps8
// Puff Implementation

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;


public class Puff {
    //Instance Variables
    public static boolean DEBUG = true; //DEBUG from FileIO
    private BinaryIn input; //Input
    private FileIO io = new FileIOC(); //FileIO
    private static final int Header = 0x0BC0; //Header Number
    private SymTable<Integer, TableValue> st = new SymTableC<Integer, TableValue>(); //Symbol Table of type Integers and Values

    private Puff(String input){
        this.input = io.openBinaryInputFile(input);
    }

    private void puffStart() throws IOException, DataFormatException{
        checkHeader(input);
        int tableSize = input.readInt();
        System.out.println("Table Size: " + tableSize);
        makePuffTable(tableSize);
        HuffTree tree = makeHTree();
        tree.writeText(this.io, this.input);
    }

    private boolean checkHeader(BinaryIn inputFile) throws DataFormatException {
        int testHeader = inputFile.readShort();
        if (testHeader == this.Header) return true;
        else throw new DataFormatException("Incorrect File Type");
    }

    private void makePuffTable(int tableSize){
        for (int i = 0; i < tableSize; i++) {
            st.put((int) this.input.readByte(), new TableValueC(this.input.readInt()));
        }
        st.toStringFreq();
    }

    private HuffTree makeHTree() {
        Set<Integer> STKeys = st.getKeys();
        PriorityQueue<HuffTree> tempQueue = new PriorityQueue<HuffTree>();
        for (Integer i : STKeys) {
            int freq = st.get(i).getFrequency();
            char c = (char) i.intValue();
            tempQueue.add(new HuffTreeC(c, freq, null, null));
        }
        while (tempQueue.size() > 1) {
            HuffTree p1 = tempQueue.poll();
            HuffTree p2 = tempQueue.poll();
            int tempWeight = p1.getWeight() + p2.getWeight();
            tempQueue.add(new HuffTreeC((char) 0, tempWeight, p1, p2));
        }
        return tempQueue.poll();
    }

    public static void main(String[] args) throws IOException, DataFormatException{
        new Puff(args[0]).puffStart();
    }
}