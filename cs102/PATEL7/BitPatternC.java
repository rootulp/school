// author: Rootul Patel & Nelson Demoraes
// file: BitPatternC.java
// date: 3/29/14
// ps7
// BitPatternC Implementation

import java.io.*;
import java.util.*;

public class BitPatternC implements BitPattern {
    
    //Instance Variables 
    private int bits;
    private int size;

    public BitPatternC() { 
        this.bits = 0;
        this.size = 0;
    }
    
    //Constructor
    public BitPatternC(int bits, int size) {
        this.bits = bits;
        this.size = size;
    }
    
    public int getLength(){
        return this.size; 
    }

    public int getBit(){ 
        return this.bits; 
    }

    public void setBit(int bits){ 
        this.bits = bits; 
    }
}
