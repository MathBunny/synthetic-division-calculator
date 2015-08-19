/**
 * SyntheticApp - This class acts as the runner and extends JPanel for the app.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;


public class SyntheticApp extends JPanel implements ActionListener{
  
  /** @param JTable table This variable stores the table */
  public static JTable table;
  /** @param JScrollPane scrollPane This variable stores the scroll pane for the JTable */
  static JScrollPane scrollPane;
  /** @param co Container This variable stores the container for the program. */
  static Container co;
  /** @param a Container This stores a temporary container */
  static Container a;
  /** @param double [] [] data This stores the data */
  static double  [] [] data;
  /** @param JFrame j This stores the JFrame */
  static final JFrame j = new JFrame("Synthetic Division - Programmed by: Horatiu Lazu");
  /** @param boolean exists This boolean stores if the variable exists */
  static boolean exists = false;
  /** @param name String This stores the file name. */
  static String name = ""; /* File name */
  /** @param div1 String This stores division 1. */
  static String div1;
  /** @param div2 String this stores division 2.*/
  static String div2;
  
  /** This method is the main mthod */
  public static void main (String [] args){
    JOptionPane.showMessageDialog (null, "Welcome to Synthetic Division Calculator, made by Horatiu Lazu.\nTo begin, entered the simplified version of the expression in the appropriate textboxes." + 
                                   "\nYou will separate terms by a space, followed by the + or - like so: x^2 +4x +4\nEnjoy!", "Welcome: Instructions", JOptionPane.WARNING_MESSAGE);
    new SyntheticApp();
  }
  
  /** This method flips the array */
  public static Object [] []  flipArr(Object [] [] gArr){
    Object [] [] ret = new Object[gArr[0].length][gArr.length];
    for(int x = 0; x < gArr.length; x++){
      for(int y = 0; y < gArr[0].length; y++){
        ret[y][x] = (gArr[x][y] == null) ? ('-') : (gArr[x][y]);
      }
    }
    return ret;
  }
  
  /** This method removes the existing table */
  public static void removeExist(){
    a.remove(co);
  }
  
  /** This method adds a new table */
  public static void addNewTable(Object [][] arr){
    if (exists){
      removeExist();
    }
    exists = true;
    //Object [] [] arr = flipArr(arr2);
    String [] arrS = new String[arr[0].length];
    for(int i = 0; i < arr[0].length; i++){
      arrS[i] = ((i == 0) ? ("Divident Coefficients") : (i == 1) ? ("*") : (i < arr[0].length -2) ? ("Coefficient: " + (i - 1)) : (i == arr[0].length -2) ? ("Res Coef w/o Monic") : ("Res Coef w/ Monic: "));
      
    }
    table = new JTable(arr, arrS);
    
    scrollPane = new JScrollPane(table);
    table.setVisible(true);
    scrollPane.setVisible(true);
    
    table.repaint();
    table.validate();
    
    co = new Container();
    co.add(scrollPane);
    co.setBounds(7, 90, 600, 300);
    scrollPane.setBounds(7, 10, 600, 300);
    co.setVisible(true);
    co.repaint();
    co.validate();
    a.add(co);
    
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setBorder(null);
    
    co.repaint();
    co.validate();
    
    j.repaint();
  }
  
  /** This method updates the table entries */
  public static void updateTableEntries(double [] [] initiBoard, double [] leftPane, double [][] resBoard, int focalPoint){
    Object [] [] finalTable = new Object[2 + initiBoard.length][leftPane.length+3];
    //seperate by *
    for(int i = 1; i < leftPane.length; i++){
      finalTable[0][i] = leftPane[i] + "";
    }
    
    for(int i = 0; i < leftPane.length; i++){
      finalTable[1][i] = "*";
    }
    for(int i = 1; i < initiBoard.length; i++){
      for(int x = 0; x < initiBoard[0].length; x++){
        finalTable[2 + i][x] = initiBoard[i][x] + "";
      }
    }
    
    for(int i = 0; i < initiBoard.length; i++){
      finalTable[i][initiBoard[0].length] = "*"; 
    }
    for(int i = 0; i < initiBoard[0].length; i++){
      finalTable[1][i] = "*";
    }
    for(int i = 2; i < resBoard.length + 2; i++){
      finalTable[i][finalTable[0].length-1] = resBoard[i-2];
    }
    //done
    
    
    String [] columns = new String[2 + initiBoard.length];
    columns[0] = "LC";
    columns[1] = "*";
    for(int i = 0; i < initiBoard.length; i++){
      columns[2 + i] = "C" + (i+1); 
    }
    table.setFillsViewportHeight(true);
    table.repaint();
    table.revalidate();
    scrollPane.repaint();
    scrollPane.validate();
    co.repaint();
    co.validate();
    
    j.validate();
    j.repaint();
  }
  
  
  
  /** This method overrides ActionPerformed */
  public void actionPerformed(ActionEvent e){
    
  }
  
  /** This method exports the data */
  public  void export(String divs, String divi){
    if (divs.equals("") || divi.equals("")){
      JOptionPane.showMessageDialog (null, "Error: You cannot leave a field blank!", "Error: Blank field", JOptionPane.WARNING_MESSAGE); 
    }
    else{
      div1 = divs;
      div2 = divi;
      ArrayList<Term> divident = new ArrayList<Term>();
      ArrayList<Term> divisor = new ArrayList<Term>();
      divident = generateTerms(divs);
      divisor = generateTerms(divi);
      Collections.sort(divident);
      Collections.sort(divisor);
      divident = fillTerms(divident, Utility.findGreatestExponent(divident));
      divisor = fillTerms(divisor, Utility.findGreatestExponent(divisor));
      
      
      if (Utility.findGreatestExponent(divisor) > 2){
        JOptionPane.showMessageDialog (null, "Error: You cannot divide by a non-quadratic in this version.", "Error: Divisor degree is too large", JOptionPane.WARNING_MESSAGE); 
        return;
      }
      name=   JOptionPane.showInputDialog ("Please enter your desired filename." ); 
      
      Solver.solveEquation(divident, divisor, true);
    }
  }
  
  /** This method generates the constructor & JFrame */
  public SyntheticApp(){
    
    j.setSize(new Dimension(640, 550));
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    j.setVisible(true);
    a = new Container();
    a.setLayout(null); //no layout
    
    JMenuBar jM = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu help = new JMenu("Help");
    
    
    JMenuItem newE = new JMenuItem("Export Solution", KeyEvent.VK_N);
    JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
    JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
    final JTextField dividendJ = new JTextField();
    final JTextField divisorJ = new JTextField();
    
    newE.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        /* Do magic here */
        export(dividendJ.getText(), divisorJ.getText());
        
      }
    });
    
    about.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        new About();
      }
    });
    
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
        
      }
    });
    
    file.add(newE);
    file.add(exit);
    help.add(about);
    
    jM.add(file);
    jM.add(help);
    
    j.setJMenuBar(jM);
    
    
    
    JButton b1 = new JButton("Generate Table");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        if (dividendJ.getText().equals("") || divisorJ.getText().equals("")){
          JOptionPane.showMessageDialog (null, "Error: You cannot leave a field blank!", "Error: Blank field", JOptionPane.WARNING_MESSAGE); 
          
        }
        else{
          ArrayList<Term> divident = new ArrayList<Term>();
          ArrayList<Term> divisor = new ArrayList<Term>();
          
          divident = generateTerms(dividendJ.getText());
          divisor = generateTerms(divisorJ.getText());
          
          Collections.sort(divident);
          Collections.sort(divisor);
          
          divident = fillTerms(divident, Utility.findGreatestExponent(divident));
          divisor = fillTerms(divisor, Utility.findGreatestExponent(divisor));
          
          if (Utility.findGreatestExponent(divident) < Utility.findGreatestExponent(divisor)){
            JOptionPane.showMessageDialog (null, "Notice; Dividing polynomial with a larger exponent on the base is unsupported / is in beta.", "Notice: Invalid operation.", JOptionPane.WARNING_MESSAGE);
          }
          
          if (Utility.findGreatestExponent(divisor) > 2){
            JOptionPane.showMessageDialog (null, "Error: You cannot divide by a non-quadratic in this version.", "Error: Divisor degree is too large", JOptionPane.WARNING_MESSAGE); 
            return;
          }
          
          Solver.solveEquation(divident, divisor, false);
        }
      }
    });
    JButton b2 = new JButton("Export Result");
    
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        //Execute when button is pressed
        export(dividendJ.getText(), divisorJ.getText());
      }
    });
    
    JButton b3 = new JButton("Instructions");
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        //Execute when button is pressed
        JOptionPane.showMessageDialog (null, "Instructions: Enter the divident in the textbox, in the format as:\n" 
                                         + "x^2 +3x +4 - using spaces between the terms and no space between the positive or negative.", "Instructions", JOptionPane.WARNING_MESSAGE);
        
      }
    });
    
    JLabel div = new JLabel("Enter the dividend:");
    JLabel div2 = new JLabel("Enter the divisor:");
    
    a.add(b1);
    a.add(b2);
    a.add(b3);
    a.add(div);
    a.add(div2);
    a.add(dividendJ);
    a.add(divisorJ);
    
    
    
    Insets insets = a.getInsets();
    Dimension size = b1.getPreferredSize();
    b1.setBounds(250 + insets.left, 470 + insets.top,
                 size.width, size.height);
    size = b2.getPreferredSize();
    b2.setBounds(10 + insets.left, 470 + insets.top,
                 size.width, size.height);
    size = b3.getPreferredSize();
    b3.setBounds(515 + insets.left, 470 + insets.top,
                 size.width, size.height);
    div.setBounds(7, 20, 200, 30);
    div2.setBounds(7, 50, 200, 30);
    dividendJ.setBounds(220, 20, 400, 30);
    divisorJ.setBounds(220, 50, 400, 30);
    j.add(a);
    j.repaint();
    j.validate();
  }
  
  /** This method will fill the term with empty values like 0x^0 */
  public ArrayList<Term> fillTerms(ArrayList<Term> terms, int greatestExponent){
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
  
  /** This method will generate terms based off of string input */
  public ArrayList<Term> generateTerms(String input){
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
      ret.add(new Term(Integer.parseInt(exponentS), (isNegative) ? (Double.parseDouble(coefficientS) * -1) : (Double.parseDouble(coefficientS))));
    }
    return ret;
  }
  
}