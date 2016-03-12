import java.io.*;
import java.util.*;

public class SyntheticConsole{
  private BufferedReader in;
   
  public SyntheticConsole(){
    try{
      in = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Welcome to the Synthetic Console! This application will visually demonstrate Synthetic Division, and is not limited to Ruffini's Law!\nDesigned by: Horatiu Lazu\n\nPress any key to continue.");
      in.read();
      while(true){
        System.out.println("**Main Menu**");
        System.out.println("1. Divide Synthetically");
        System.out.println("2. Save Previous Results");
        System.out.println("3. Exit");
        System.out.println("Please make your selection.");
        try{
          int select = Integer.parseInt(in.readLine());
          if (select == 1){
            divideSynthetically();
          }
          else if (select == 2){
            
          }
          else if (select == 3){
            
          }
          else{
            System.out.println("Invalid choice! Press any key to continue.");
            in.read();
          }
        }
        catch(NumberFormatException nF){
          System.out.println("Error: You have to enter a valid number!");
        }
      }
    }
    catch(IOException e){
      
    }
  }
  
  private void divideSynthetically(){
    try{
      ArrayList<Term> divident = new ArrayList<Term>();
      ArrayList<Term> divisor = new ArrayList<Term>();
      
      System.out.println("Please enter your equation.");
      divident = Utility.generateTerms(in.readLine());
      System.out.println("Please enter your divident.");
      divisor = Utility.generateTerms(in.readLine());
      
      Collections.sort(divident);
      Collections.sort(divisor);
      
      divident = Utility.fillTerms(divident, Utility.findGreatestExponent(divident));
      divisor = Utility.fillTerms(divisor, Utility.findGreatestExponent(divisor));
      
      if (Utility.findGreatestExponent(divident) < Utility.findGreatestExponent(divisor)){
        System.out.println("Notice: Dividing polynomial with a larger exponent on the base is unsupported / is in beta.");
      }
      
      if (Utility.findGreatestExponent(divisor) > 2){
        System.out.println("Error: You cannot divide by a non-quadratic in this version."); 
        divideSynthetically();
        return;
      }
      
      Solver.solveEquation(divident, divisor, false, false);
      System.out.println(Solver.getSolution());
      System.out.println("------");
      System.out.println("Press any key to continue.");
      in.read();
    }
    catch(IOException e){
      System.out.println("Error: Input is not valid. Please try again.");
      divideSynthetically();
      return;
    }
  }
  
  public static void main (String [] args){
    new SyntheticConsole();
  }
}