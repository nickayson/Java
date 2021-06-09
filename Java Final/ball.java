// #Author: Nicholas Ayson
// #Program: Sun and Earth
// Email: nick.ayson@csu.fullerton.edu


import javax.swing.JFrame;
import java.awt.Dimension;

public class ball
{
  public static void main(String[] args)
    {
     Game_user_interface myframe = new Game_user_interface();
     myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // myframe.setSize(1800,980); //frame declared to where I could see it biggest
     myframe.setResizable(false);
     myframe.setVisible(true);
    }//End of main
}
