/**
 * SyntheticApp - This class acts as the about class.
 * @author Horatiu Lazu
 * @version 1.0.0.2
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;

public class About extends JFrame{
  private Image backgroundImg;
  
  private void fetchImages(){
    try{
      backgroundImg = ImageIO.read(new File("images/AboutBackground.png"));
    }
    catch(IOException e){
      JOptionPane.showMessageDialog(this, "Error: Could not find required image files!");
    }
  }
  
  public void paint(Graphics g){
    super.paint(g);
    fetchImages();
    
    g.drawImage(backgroundImg, 0, 20, null);
  }
  
  
  /** This is the class constructor, which sets up the GUI and adds the required buttons and labels. */
  public About(){
    super("About: Synthetic Division");
    Container a = new Container();
    JButton done = new JButton("Quit");
    
    done.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        //Execute when button is pressed
        dispose();
        setVisible(false);
      }
    });

    done.setBounds(68, 310, 115, 30);
    
    a.add(done);
    
    add(a);
    setSize(new Dimension(257, 390));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
    repaint();
    
  }
}