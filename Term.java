/**
 * Term - This class acts as template for a term.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
*/


public class Term implements Comparable<Term>{
 /* 2x^2
    power = 2
    coefficient = 2 */
 /** @param power int This variable stores the power. */
 private int power = 0x3ff;
 /** @param coefficient int This variable stores the coeff */
 private double coefficient = 0x3ff;
 
 /** This method returns and compares an item.
   * @return int The return val. */
 public int compareTo(Term o){
  if (o.getPower() < power){
   return 0; 
  } 
  return 1;
 }
 /** This method returns the coefficient.
   * @return double Coefficient */
 public double getCoefficient(){
  return coefficient; 
 }
 /** This method returns the power.
   * @return int This is the power */
 public int getPower(){
  return power; 
 }
 
 /** This method is the class constructor */
 public Term(int power, double coefficient){
  this.power = power;
  this.coefficient = coefficient;
 }
}