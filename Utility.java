/**
 * SyntheticApp - This class acts as the utility class.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
 */
import java.util.*;
import javax.swing.*;


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
  
  /** This method will fill the term with empty values like 0x^0 
      @return ArrayList<Term> These are the resultant terms in ArrayList form
  */
  public static ArrayList<Term> fillTerms(ArrayList<Term> terms, int greatestExponent){
    ArrayList<Term> ret = new ArrayList<Term> ();
    
    int currentEx = greatestExponent;
    for(int i = 0; i < terms.size(); i++){
      if (terms.get(i).getPower() != currentEx){
        ret.add(new Term(currentEx, 0));  //add new 0 ^ x
        i--;
      } 
      else
        ret.add(terms.get(i));
      //i++;
      currentEx--;
      if (currentEx < 0){
        break; //do you need to do this?
      }
    }
    return ret;
  }
  
  /** This method will generate terms based off of string input
    @return ArrayList<Term> These are the terms returned from the generatino
   */
  public static ArrayList<Term> generateTerms(String input){
    StringTokenizer st = new StringTokenizer(input);
    ArrayList<Term> ret = new ArrayList<Term>();
    while(st.hasMoreTokens()){
      String temp = st.nextToken();
      String coefficientS = "";
      String exponentS = "";
      boolean isNegative = false;
      boolean foundX = false;
      if (temp.charAt(0) == '-'){
        isNegative = true;
      }
      boolean doneNumerical = false;
      for(int i = (isNegative) ? (1) : (0); i < temp.length(); i++){
        if (doneNumerical){
          if (temp.charAt(i) <= '9' && temp.charAt(i) >= 0){
            exponentS += temp.charAt(i); 
          }
        }
        else{
          if (temp.charAt(i) > '9' || temp.charAt(i) < '0' && (temp.charAt(i) != '+') && temp.charAt(i) != '.'){
            doneNumerical = true;
            if (temp.charAt(i) == 'x' || temp.charAt(i) == 'X'){
              foundX = true; 
            }
          }
          else{
            if (temp.charAt(i) != '+')
              coefficientS += temp.charAt(i); 
          }
        }
      }
      exponentS = ((exponentS.equals("") && foundX) ? ("1") : ((exponentS.equals("") && !foundX) ? ("0") : (exponentS)));
      coefficientS = ((coefficientS.equals("") && foundX) ? ("1") : ((coefficientS.equals("") && !foundX) ? ("0") : (coefficientS)));
      try{
        ret.add(new Term(Integer.parseInt(exponentS), (isNegative) ? (Double.parseDouble(coefficientS) * -1) : (Double.parseDouble(coefficientS))));
      }
      catch(NumberFormatException e){
        JOptionPane.showMessageDialog (null, "Error: Invalid formatting! Please check your formatting.", "Invalid Formatting", JOptionPane.WARNING_MESSAGE);
      }
    }
    return ret;
  }
  
  public Utility(){}
}