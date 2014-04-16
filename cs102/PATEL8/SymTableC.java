// author: Rootul Patel & Nelson Demoraes
// file: SymTableC.java
// date: 3/29/14
// ps7
// SymTableC Implementation

import java.io.*;
import java.util.*;

public class SymTableC<Key, Value> implements SymTable<Key, Value> {

    private Map<Key, Value> myHashMap = new HashMap<Key, Value>(); //HASH MAP (Python dictionary)

    //Constructor
    public SymTableC() {}

    public Value get(Key key){ 
        return myHashMap.get(key); 
    }
    
    public void put(Key key, Value value){ 
        myHashMap.put(key, value); 
    }
    
    public boolean containsKey(Key key){ 
        return myHashMap.containsKey(key);
    }
    
    public int size(){ 
        return myHashMap.size(); 
    }
        
    public void write (BinaryOut writeFile) {}

    public Set<Key> getKeys(){ 
        return myHashMap.keySet(); 
    }
    
    public Collection<Value> getVals(){ 
        return myHashMap.values(); 
    }
    
    public boolean isEmpty(){
        return myHashMap.isEmpty();
    }
    public void writeSTBits(BinaryOut of){
        of.write(this.size());

    }

    public String toString(){
        return myHashMap.toString();
    }

    public void toStringCodes(){
        Set<Key> STKeys = this.getKeys();
        System.out.print("STCodes: ");
        for (Key i : STKeys) {
            TableValue tv = (TableValue) this.get(i);
            Integer ii = (Integer) i;
            System.out.print((char)((int) ii) + ":" + (int) tv.getFrequency() + ":" + tv.getBits().getBit() + " ");
        }
    }
    
     public void toStringFreq(){
        Set<Key> STKeys = this.getKeys();
        System.out.print("STCodes: ");
        for (Key i : STKeys) {
            TableValue tv = (TableValue) this.get(i);
            Integer ii = (Integer) i;
            System.out.println((char)((int) ii) + ":" + (int) tv.getFrequency());
        }
    }
}
