// author: Rootul Patel & Nelson Demoraes
// file: TableValue.java
// date: 3/29/14
// ps7
// TableValue Interface

import java.io.*;
import java.util.*;

public interface TableValue {
    //Getters
    public BitPattern getBits(); 
    public int getFrequency();
    //Setters
    public void setBits(BitPattern bit); 
    public void setFrequency(int freq);
}