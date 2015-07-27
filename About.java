/**
 * SyntheticApp - This class acts as the about class.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
*/

import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.geom.*;
import java.math.*;

import javax.swing.*;
import java.awt.Event;
import java.awt.event.*;




public class About{

  /** This is the class constructor */
 public About(){
  final JFrame j = new JFrame("About: Synthetic Divisor");
  Container a = new Container();
  JButton done = new JButton("Quit");
  JLabel dev = new JLabel("Application developed by: Horatiu Lazu");
  JLabel dev2 = new JLabel("Copyright: 2015 Horatiu Incorporated");
  JLabel dev3 = new JLabel("Version: 1.0.0.2");
  JLabel dev4 = new JLabel("Purpose: Demonstrate Synthetic Division");
  
  done.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
       //Execute when button is pressed
       j.dispose();
       j.setVisible(false);
      }
  });
  
  Insets insets = a.getInsets();
  Dimension size = done.getPreferredSize();

  dev.setBounds(7, 5, 300, 20);
  dev2.setBounds(7, 25, 300, 20);
  dev3.setBounds(7, 45, 300, 20);
  dev4.setBounds(7, 65, 300, 20);
  done.setBounds(90, 100, 200, 30);
  
  a.add(dev);
  a.add(dev2);
  a.add(dev3);
  a.add(dev4);
  a.add(done);
  
  j.add(a);
  j.setSize(new Dimension(400, 150));
  j.setDefaultCloseOperation(0xffffffff * 0 + JFrame.EXIT_ON_CLOSE);
  j.setVisible(true);
  
  
 }
}