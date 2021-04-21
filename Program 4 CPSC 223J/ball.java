//****************************************************************************************************************************
//Program name: "Cat and Mouse".  This program shows a ball bouncing off of the walls of a panel and another object chaising that object.
//  The speed, direction are given by user. The action of moving the ball is displayed.                                      *
//  Copyright (C) 2021 Nicholas Ayson.  All rights reserved.                                                                 *
//                                                                                                                           *
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.  This program is distributed in the hope that it will be useful,   *
//but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   *
//the GNU General Public License for more details.  A copy of the GNU General Public License v3 is available here:           *
//<https://www.gnu.org/licenses/>.                                                                                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author: Nicholas Ayson
//Email: nick.ayson@csu.fullerton.edu

//built on: Tuffix 2020

//Program information
  //Program name: Cat and Mouse
  //Programming language: Java
  //Files in this program: ball.java (main), Game_user_interface.java (UI frame), Cat_Mouse_panel.java (graphic panel), run.sh (Bash)
  //Date project began: April 13, 2021
  //Date of last update: April 25, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.
  //Purpose: This program demonstrates a ball moving and bouncing off the wall at a user choice speed while another object chases it.
//
//This module
  //File name: ball.java
  //Purpose:  This file contains the class ball, which displays the entire frame.

  //NOTE to the Professor: similar to the last project the Game accepts most values like 60.0, 97.4, 78.9, etc.
  // for some reason the code does not accept certain values like 45 and 30 and gets stuck while still moving at the top on the north wall
  // even though i am using the if values given to me via the skeleton code given to us.




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
