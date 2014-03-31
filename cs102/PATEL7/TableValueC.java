// author: Rootul Patel & Nelson Demoraes
// file: TableValueC.java
// date: 3/29/14
// ps7
// TableValueC Implementation

import java.io.*;
import java.util.*;

public class TableValueC implements TableValue{
    
    //Instance Variables
    private int frequency;
    private BitPattern bits;

    //Constructor
    public TableValueC (int freq) {
        this.frequency = freq;
        this.bits = null;
    }
    
    //Getters
    public BitPattern getBits(){ 
        return this.bits; 
    }
    public int getFrequency() { 
        return this.frequency; 
    }
    
    //Setters
    public void setBits(BitPattern bit) { 
        this.bits = bit; 
    }
    public void setFrequency(int freq) { 
        this.frequency = freq; 
    }
}
