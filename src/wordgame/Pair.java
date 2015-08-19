
package wordgame;

import java.util.Objects;

/*
 * A generic class for Pairs
 * You cannot RUN this utility file.
 * No main method.
 */


public class Pair<T extends Comparable, S> implements Comparable<Pair>{
    
// ****** Instance variables ******

private T first;    private S second;  	

// We will define pair1 to be less than pair2 if its first element is less than the first element of pair2. 
// Thus the second element does not affect the ordering. 



// ****** Constructors ******
// 1. General constructor.
public Pair (T firstElement, S secondElement) 			
	{ first = firstElement;  second = secondElement; }

// 2. Default constructor.  
public Pair ()  { first = null;  second = null; }


// ****** Accessors or Getters ******

public T getFirst()  { return first;  }				
public S getSecond() { return second; }


// Mutators or Setters

public void setFirst(T newFirst)  { first = newFirst; }         
public void setSecond(S newSecond) { second = newSecond; }



// ****** Conversion ******

@Override
public String toString() {
    String result = "[" + this.getFirst() + ", " + this.getSecond()+"]";
    return result;
    
} // end of toString() method



// Compare "this" Pair to "that" Pair. 
@Override 
public int compareTo(Pair that) {
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;

    // this optimization is usually worthwhile, and can
    // always be added
    // if (this == aThat) return EQUAL;

    if ( this.first.compareTo(that.getFirst()) < 0 ) return BEFORE;
    else if ( this.first.compareTo(that.getFirst()) > 0 ) return AFTER;

   
    
    else {return EQUAL; } // This is not quite right. 
} // end of method






@Override
public boolean equals(Object object) {
    if (!(object instanceof Pair))  return false;
    if (object == this)  return true;
    
  Pair pair2 = (Pair) object;
  boolean firstElementsAreEqual  = this.getFirst().equals(pair2.getFirst());
  boolean secondElementsAreEqual = this.getSecond().equals(pair2.getSecond());

  return firstElementsAreEqual && secondElementsAreEqual;  
}  // end of equals method







  @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.first);
        hash = 73 * hash + Objects.hashCode(this.second);
        return hash;
    }  // end of hash code method  


} // end of generic class Pair




