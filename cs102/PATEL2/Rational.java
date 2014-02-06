// file: Rational.java
// author: Rootul Patel
// date: January 26, 2014
//
// An API for rational numbers.
//

public interface Rational {

//  public RationalC(int numerator, int denominator);
    
    public int getNumerator();
    public int getDenominator();
    public Rational add(Rational other);
    public int compareTo(Rational other);
    public String toString();
}