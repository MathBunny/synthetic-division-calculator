/**
 * SyntheticApp - This class acts as the utility class.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
 */
import java.util.*;


public class Utility{
  
  /** This method clears the console screen*/
  public static void clearScreen(){
    for(int i = 0; i < 20; i++)
      System.out.println(); 
  }
  
  /** This method finds the greatest exponent.
    * @return int This returns the greatets exponent */
  public static int findGreatestExponent(ArrayList <Term> terms){
    int largest = 0;
    for(int i = 0; i < terms.size(); i++){
      if (terms.get(i).getPower() > largest)
        largest = terms.get(i).getPower();
    } 
    return largest;
  }
  
  /** This method outputs the terms. */
  public static void outputTerms(ArrayList<Term> terms, boolean diag){
    if (diag)
      System.out.println("Terms outputted: ");
    for(int i = 0; i < terms.size(); i++){
      System.out.print(terms.get(i).getCoefficient());
      if (terms.get(i).getPower() != 0){
        System.out.print("x^" + terms.get(i).getPower() + " ");
      }
      else{
        System.out.print(" ");
      }
    }
    System.out.print("\n");
  }
  
  public Utility(){}
}