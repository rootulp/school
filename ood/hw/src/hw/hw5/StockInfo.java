package hw.hw5;

import java.util.*;
import java.io.*;

public class StockInfo {
    private Map<String, String> info = new HashMap<String, String>();

    public StockInfo(String filename) {
        try {
            InputStream is = new FileInputStream(filename);
            Scanner sc = new Scanner(is);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(" ", 2);
                info.put(words[0], words[1]);
            }
            sc.close();
        } catch (IOException ex) {
        }
    }

    public String get(String key) {
        return info.get(key);
    }
}
