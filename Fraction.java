/**
 * Fraction - This class acts as the fraction.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
*/

import java.io.*;
import java.util.*;


public class Fraction{
 int numerator;
 int denominator;
 int wholeNum;
 
 public Fraction(int numerator, int denominator, int wholeNum){
  this.numerator = numerator;
  this.denominator = denominator;
  this.wholeNum = wholeNum;
 }
 
 public int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
 
 public void simplify(){
  
 }
 
 
 
 public double add(Fraction a){
   return 0;
 }
 
 public Fraction (double res){
  /* Do computations */ 
   
 }
 
 public double returnDouble(){
  return wholeNum + (numerator / denominator); 
 }
 
 public double getNumerator(){
  return numerator; 
 }
 
 public double getDenominator(){
  return denominator; 
 }
 
 public double getWholeNumber(){
  return wholeNum; 
 }
}