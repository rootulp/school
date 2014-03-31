// author: Rootul Patel & Nelson Demoraes
// file: SymTable.java
// date: 3/29/14
// ps7
// SymTable Interface

import java.io.*;
import java.util.*;

public interface SymTable<Key, Value> {
    public int size();
    public boolean isEmpty();
    public Value get(Key k);
    public Set<Key> getKeys();
    public Collection<Value> getVals();
    public void put(Key k, Value v);
    public boolean containsKey(Key k);
    public void write (BinaryOut writeFile);
    public void toStringCodes();
}
