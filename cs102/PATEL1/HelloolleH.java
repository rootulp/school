// file: HelloolleH.java
// author: Rootul Patel
// date: 1/16/2014
//
// This program prints a greeting and names reversed

public class HelloolleH {
    public static void main(String[] args) {
        String[] total;
        total = new String[10];
        int count = 0;
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            total[count] = name;
            count = count + 1;
        }
        System.out.print("Hello");
        for(int i=count-1; i>=0; i=i-1){
              System.out.print(" " + total[i]);
         }
    }
}