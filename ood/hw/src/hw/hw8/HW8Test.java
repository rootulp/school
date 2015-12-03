package hw.hw8;

import java.io.*;
import java.util.*;

public class HW8Test {
    private static List<List<String>> allrows = new ArrayList<List<String>>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileInputStream("text.csv"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            processLine(line);
        }
        sc.close();
        for (List<String> row : allrows) {
            for (String value : row)
                System.out.print(value + " ");
            System.out.println();
        }
    }

    private static void processLine(String line) {
        CSVMachine m = new CSVMachine();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            m.processChar(c);
        }
        List<String> row = m.getRow();
        allrows.add(row);
    }
}
