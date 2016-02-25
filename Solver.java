/**
 * SyntheticApp - This class acts as the solving class.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
 */
import java.io.*;
import java.util.*;
import javax.swing.*;



public class Solver{
  
  /** @param q String This is the quotient */
  public static String q = "";
  /** @param r String This is the remainder */
  public static String r = "";
  
  /** This method outputs the board */
  private static void drawBoard(double [] [] board, double [] multiSide, double [][] res){
    System.out.println("CONSOLE SOLUTION: ");
    for(int i = 0; i < multiSide.length - 1; i++){
      System.out.print(multiSide[i] + " || ");
      for(int x = 0; x < board.length; x++){
        System.out.print(board[x][i] + " | "); 
      }
      System.out.println();
    }
    System.out.println("***************************************");
    for(int i = 0; i < 2; i++){
      System.out.print("\t|| ");
      for(int x = 0; x < board.length; x++){
        System.out.print(res[x][i] + " "); 
      } 
      System.out.println();
    }
  }
  
  /** This method outputs the answer */
  private static void printAns(double [] [] board, double [] multiSide, double [][] res){
    
    try{
      PrintWriter out = new PrintWriter(new FileWriter(SyntheticApp.name + ".txt"));
      out.println("Horatiu Lazu - Synthetic Division Table");
      out.println("Command: Divide " + SyntheticApp.div1 + " by: " + SyntheticApp.div2 + ".");
      for(int i = 0; i < multiSide.length - 1; i++){
        out.print(multiSide[i] + " || ");
        for(int x = 0; x < board.length; x++){
          out.print(board[x][i] + " | "); 
        }
        out.println();
      }
      out.println("***************************************");
      for(int i = 0; i < 2; i++){
        out.print("\t\t|| ");
        for(int x = 0; x < board.length; x++){
          out.print(res[x][i] + " "); 
        } 
        out.println();
      }
      out.close();
    }
    catch(IOException e){
      
    }
  }
  
  
  /** This outputs the array for the table */
  public static Object [] [] getTableData(double [] [] board, double [] multiSide, double [] [] res){
    Object [] [] retArr = new Object[ board.length + 2][board[0].length + res[0].length +2];
    for(int i = 0; i < retArr[0].length - 2; i++)
      retArr[1][i] = '*';
    for(int i = 0; i < retArr.length; i++){
      retArr[i][1] = '*';
    }
    for(int i = 0; i < retArr[0].length; i++){
      retArr[1][i] = '*';
    }
    
    for(int i = 1; i < multiSide.length; i++){
      retArr[0][i] = multiSide[i-1];
    }
    
    for(int i = 2; i < retArr.length; i++){
      retArr[i][0] = board[i - 2][0]; //error here?
    }
    for(int i = 2; i < retArr.length; i++){
      for(int x = 1; x < board[0].length; x++){
        retArr[i][x + 1] = board[i - 2][x];
      }
    }
    for(int x = 0; x < 2; x++){
      for(int i = 2; i < retArr.length; i++){
        retArr[i][x + board[0].length + 2] = res[i - 2][x];
      }
    }
    //outputObjectArray(retArr);
    SyntheticApp.addNewTable(retArr);
    return retArr;
    
  }
  
  /* This method returns if the divident is monic 
   * @return boolean if it is monic */
  private static boolean isMonic(ArrayList<Term> divident){
    if (divident.get(0).getCoefficient() == 1)
      return true;
    return false;
  }
  /** This method outputs the answer of the program. */
  
  public static void outputAnswer(double [] [] res, int cutOff){
    int degree = res.length - cutOff; //plus 1?
    System.out.print("p(x) = ");
    for(int i = degree; (i - cutOff) >= 0; i--){
      if (i < degree)
        System.out.print(" +");
      System.out.print(res[degree -i][1] + "x^" + (i - cutOff)); 
    }
    System.out.print("\nr(x) = ");
    for(int i = cutOff+1; i <= res.length - 1; i++){
      if (i > cutOff + 1)
        System.out.print(" +");
      System.out.print(res[i][0] + "x^" + (res.length - i - 1) );
    }
    System.out.println();
    
  }
  
  /** This method will take an equation and then generate the table by dividing it by a quadratic */
  
  public static Object [] [] solveByDividingQuadratic(ArrayList<Term> divident, ArrayList<Term> divisor){
    
    double [] topBoard = new double [divident.size()];
    for(int tp = 0; tp < divident.size(); tp++){
      topBoard[tp] = divident.get(tp).getCoefficient();
    }
    double [] sideBoard = new double[divisor.size() - 1];
    for(int sB = divisor.size() - 2; sB >= 0; sB--){
      sideBoard[sB] = divisor.get(sB).getCoefficient() * -1; 
    }
    
    double [] [] resBoard = new double[divident.size()][2];
    double [] [] work = new double[divident.size()][divisor.size()-1];
    
    boolean isDivisorMonic = isMonic(divisor);
    
    for(int qq = 0; qq < divident.size(); qq++){
      double sum = 0;
      sum += topBoard[qq];
      
      for(int i = 0; i < work[qq].length; i++){
        sum += work[qq][i];
      }
      resBoard[qq][0] = sum;
      if (isDivisorMonic){
        resBoard[qq][1] = sum / divisor.get(0).getCoefficient();
        sum = resBoard[qq][1];
      }
      double proc = sum;
      for(int i = qq; i < divident.size(); i++){
        for(int y = sideBoard.length - 1; y>= 0; y--){
          try{
            work[qq][y] = proc * sideBoard[y];
          }
          catch(IndexOutOfBoundsException e){
          }
        }
      }
    }
    Object [] [] finalAns = new Object[2 + divident.size()][sideBoard.length + 3];
    finalAns[0][0] = '*';
    finalAns[1][0] = '*';
    for(int i = 2; i < finalAns.length; i++){
      finalAns[i][0] = topBoard[i-2];
    }
    for(int i = sideBoard.length; i > 0; i--){ 
      finalAns[0][i] = sideBoard[i-1];
    }
    for(int i = 0; i < sideBoard.length + 1; i++)
      finalAns[1][i] = '*';
    for(int i = 0; i < finalAns.length; i++)
      finalAns[i][sideBoard.length + 1] = '*';
    for(int i = 3; i < finalAns.length; i++){
      for(int x = 0; x < 2; x++){
        finalAns[i][x] = resBoard[i - 3][x];
      }
    }
    
    for(int i = 0; i < finalAns[0].length; i++){
      for(int x = 0; x < finalAns.length; x++){
        System.out.print(finalAns[x][i] + " ");
      }
      System.out.println();
    }
    
    
    
    return finalAns;
    
    // for(int qq  = 0; qq < 
  }
  
  /** This method specializes into solving quadratics quickly.
    */
  public static void solveByQuadratic(ArrayList<Term> divident, ArrayList <Term> divisor){
    System.out.println("Solving");
    
    /* Let's keep this simple. We just need an array for the board, side panel and then one for the base. */
    double [][] board = new double[divident.size()][divisor.size()];
    for(int i = 0; i < board.length; i++){ /*Load the coefficients into the table. */
      board[i][0] = divident.get(i).getCoefficient(); 
    }
    /* Side panel */
    double [] sidePanel = new double[divisor.size()];
    for(int i = divisor.size() - 1; i > 0; i--){
      sidePanel[i] = divisor.get(i).getCoefficient() * -1; //times -1? 
    }
    /* Now for the base */
    double [][] base = new double[divident.size()][2];
    /* Now let's go through the table*/
    for(int qq = 0; qq < divident.size(); qq++){
      double sum = 0;
      for(int qp = 1; qp < divisor.size(); qp++){
        sum += board[qq][qp];
      }
      base[qq][0] = sum; //time to do monmonic thing AFTER...
      
      for(int xx = qq + 1; xx < divident.size(); xx++){
        for(int y = divisor.size(); y > 0; y--){
          try{
            board[xx][y] = base[qq][0] * sidePanel[y];
          }
          catch(ArrayIndexOutOfBoundsException e){
            
          }
        }
      }
    }
    
    SyntheticApp.updateTableEntries(board, sidePanel, base, 0xFFFFFFF);
    //JOptionPane.showMessageDialog (null, "Made it", "Error: Blank field", JOptionPane.WARNING_MESSAGE); 
    
    Solver.drawBoard(board, sidePanel, base);
    
  }
  
  /** This method SOLVES the equations! */
  public static void solveEquation(ArrayList<Term> divident, ArrayList<Term> divisor, boolean shouldPrint){
    /* Time to get the coefficients and set up the arrays. */
    double [] [] board = new double [divident.size()][divisor.size()];
    if (board[0].length == 0) //RETURN IF THE ARRAY HAS NOTHING IN IT
      return;
    /* Let's fill the top of the board with the appropriate initial values. */
    for(int i = 0; i < divident.size(); i++){
      //System.out.println("COEFF: " + divident.get(i).getCoefficient());
      board[i][0] = divident.get(i).getCoefficient();
    }
    boolean monic = isMonic(divident);
    
    double [] multiSide = new double[divisor.size() + 1];
    /* Populate left most table. Update the monic restriction for flipping all values, too. */
    for(int i = divisor.size() - 1; i >0; i--){
      multiSide[divisor.size() - i] = ((monic) ? (divisor.get(i).getCoefficient() * -1) : (divisor.get(i).getCoefficient() * -1.0));  //times *-1
    }
    
    double [][] res = new double[divident.size()][2];
    double leadingCoef = divisor.get(0).getCoefficient();
    
    /* Now to go sequentially through the top list. And do the appropriate things. */
    for(int qq = 0; qq < divident.size(); qq++){
      /* Go down the table, adding every value. */
      double sum = 0;
      for(int p = 0; p < divisor.size(); p++){ //minus 2? //-1
        sum += board[qq][p];
      }
      res[qq][0] = sum;
      if (divident.get(0).getPower() - qq < divisor.get(0).getPower()){} //nothing.
      else{
        res[qq][1] = res[qq][0]/leadingCoef;
        /* Now to populate the rest of the board. */
        int x = qq+1; //wrong.;
        int y = divisor.size() - 1;
        while(true){
          if (y <= 0 || x > divident.size() - 1) //<= ?? // -2 or -1 //<= 0
            break;
          board[x][y] = res[qq][1] * multiSide[y];
          if (board[x][y] == 0){
            System.out.println(res[qq][1] + " this one"); 
          }
          x++;
          y--;
        }
      }
    }
    outputPxRx(Utility.findGreatestExponent(divident), Utility.findGreatestExponent(divisor), res);
    drawBoard(board, multiSide, res);
    
    getTableData(board, multiSide, res);
    if (shouldPrint)
      printAns(board, multiSide, res);
    //outputAnswer(res, divident.get(0).getPower() - divisor.get(0).getPower());
    
  }
  
  /** Insane amount of ternary operators.
    * Essentially outputs the solution */
  public static void outputPxRx(int expOne, int expTwo, double [][] res){
    String quot = "";
    String rem = "";
    int currEx = expOne - expTwo;
    int exp = res.length - (expOne - expTwo) - 2; //x -1
    for(int i = 0; i < res.length; i++){
      if (res[i][1] == 0 && res[i][0] != 0){
        if (currEx >= 0){
          quot += ((res[i][0] > 0 && i != 0) ? ("+") :  "") + ((res[i][0] != 1 && res[i][0] != -1) ? (res[i][0]) : (res[i][0] == -1 && currEx != 0) ? ("-") : (res[i][0] == 1 && currEx == 0) ? (1) : ("")) + (((currEx != 0 && currEx != 1)) ? ("x^" + (currEx)) : (currEx == 1) ? ("x") : ("")) + " ";
          currEx--;
        }
        else{
          rem += ((res[i][0] > 0 && i != 0) ? ("+") :  "") + ((res[i][0] != 1 && res[i][0] != -1) ? (res[i][0]) : (res[i][0] == -1 && exp != 0) ? ("-") : (res[i][0] == 1 && exp == 0) ? (1) : ("")) + ((exp != 0 && exp != 1) ? ("x^" + exp) : (exp == 1) ? ("x") : ("")) + " ";
          exp--;
        }
      }
      else{
        if (res[i][1] != 0){
          if (currEx >= 0){
            quot += ((res[i][1] > 0 && i != 0) ? ("+") : "") + ((res[i][1] != 1 && res[i][1] != -1) ? (res[i][1]) : (res[i][1] == -1 && currEx != 0) ? ("-") : (res[i][1] == 1 && currEx == 0) ? (1) : ("")) + ((currEx != 0 && currEx != 1) ? ("x^" + currEx) : (currEx == 1) ? ("x") : ("")) + " ";
            currEx--;
          }
          else{
            rem += ((res[i][1] > 0 && i != 0) ? ("+") :  "") + ((res[i][1] != 1 && res[i][1] != -1) ? (res[i][1]) : (res[i][1] == -1 && exp != 0) ? ("-") : (res[i][1] == 1 && exp == 0) ? (1) : ("")) + ((exp != 0 && exp!= 1) ? ("x^" + exp) : (exp == 1) ? ("x") : ("")) + " ";
            exp--;
          }
        }
      }
    }
    JOptionPane.showMessageDialog (null, "Solution: Q(x) = " + quot + "\nR(x) = " + ((rem.equals("")) ? ("None") : (rem)), "Solution", JOptionPane.WARNING_MESSAGE);
  }
}