// author: Rootul Patel
// file: MergeTDNonrecursive
// date: 2/21/14

/*************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                http://algs4.cs.princeton.edu/22mergesort/words3.txt
 *   
 *  Sorts a sequence of strings from standard input using mergesort.
 *   
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *    
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *  
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *  
 *************************************************************************/

/**
 *  The <tt>Merge</tt> class provides static methods for right an
 *  array using mergesort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.util.*;
public class MergeTDNonrecursive {

    // This class should not be instantiated.
    private MergeTDNonrecursive() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];   // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    // sort function
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
       for (int blockSize=1; blockSize<a.length; blockSize*=2)
         for (int start=0; start<a.length; start+=2*blockSize)
            merge(a, aux, start, start+blockSize, start+2*blockSize);
    }
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    
    private static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        int low;
        int high;
        //Create two stacks
        Stack<Bounds> left = new Stack<Bounds>();
        Stack<Bounds> right = new Stack<Bounds>();
        Bounds last = new Bounds(a.length-1, 0);
        left.push(last);
        while (!left.isEmpty()){
            last = left.pop();
            low = last.getlow();
            high = last.gethigh();
            right.push(last);
            if (low < high){
               int mid = ((high-low)/2) + low;
               left.push(new Bounds(mid, low));
               left.push(new Bounds(high, mid+1));
            }
        }
        while (!right.isEmpty()){
            last = right.pop();
            low = last.getlow();
            high = last.gethigh();
            int mid = ((high-low)/2) + low;
            merge(a, aux, low, mid, high);
        }
        assert isSorted(a);
    }
    //Private Bounds
    private static class Bounds {
        private int high;
        private int low; 
        
        //Setter for Bounds
        Bounds(int high,int low){
            this.high = high;
            this.low = low;
        }
        
        //Getter for Bounds
        public int gethigh(){
            return this.high;
        }
        
        public int getlow(){
            return this.low;
        }
    }

   /***********************************************************************
    *  Helper right functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


   /***********************************************************************
    *  Index mergesort
    ***********************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }

  
    public static int[] indexSort(Comparable[] a) {
        int N = a.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++)
            index[i] = i;

        int[] aux = new int[N];
        sort(a, index, aux, 0, N-1);
        return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
       
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        In inFile = new In(args[0]);
        String[] a = inFile.readAllStrings();        
        Stopwatch sw = new Stopwatch();    
        MergeTDNonrecursive.sort(a);
        double seconds = sw.elapsedTime();
        System.out.println(seconds);
        //show(a);
    }
}