// file: RationalC.java
// author: Rootul Patel
// date: January 26, 2014
//
// An implementation of a simple rational number ADT.
//

public class RationalC implements Rational {

//  public RationalC(int numerator, int denominator);

    // Instance variables.
    private int Numerator;
    private int Denominator;
    private int n;
    private int d;
    private int t;
    private int gcd;
    
    // A constructor.
    public RationalC(int numerator, int denominator) {
        reduce(numerator,denominator);
    }
    
    // Private Helper Function: Reduce
    private void reduce(int numerator, int denominator) { 
        int n = numerator; 
        int d = denominator; 
        while (d != 0) { 
            int t = d; 
            d = n % d; 
            n = t; 
        } 
        int gcd = n; 
        numerator /= gcd; 
        denominator /= gcd;
        this.Numerator = numerator;
        this.Denominator = denominator;
    }
    
    // Get Numerator
    public int getNumerator() {
        return this.Numerator;
    }
    
    // Get Denominator
    public int getDenominator() {
        return this.Denominator;
    }
    
    // Add
    public Rational add(Rational other) {  
        n = (this.getNumerator() * other.getDenominator()) + (other.getNumerator() * this.getDenominator());
        d = this.getDenominator() * other.getDenominator();
        return new RationalC(n, d);
    }
    
    // Compare To
    public int compareTo(Rational other) {
        return ((this.getNumerator() * other.getDenominator()) - (this.getDenominator() * other.getNumerator()));
    }
    
    // toString
    public String toString() {
        return "The rational number is (" + getNumerator() + "/" + getDenominator() + ")";
    }
    
    // Main
    public static void main(String[] args) {
        Rational a = new RationalC(4,6);
        Rational b = new RationalC(1,4);
        System.out.println(a.getNumerator());   //Should print 2
        System.out.println(a.getDenominator()); //Should print 3
        System.out.println(a.compareTo(b));     //Should print positive number
        System.out.println(a.add(b));           //Should print (11/12)
    }   
}
