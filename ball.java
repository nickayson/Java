//****************************************************************************************************************************
//Program name: "Richochet".  This program shows a ball bouncing off of the walls of a panel.  The speed, direction, and refresh *
//rate of the ball is slected by the user.  The action of moving the ball is displayed.                                      *
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
  //Program name: Richochet
  //Programming language: Java
  //Files in this program: ball.java (main), Richochet_user_interface.java (UI frame), Richochet_panel.java (graphic panel), run.sh (Bash)
  //Date project began: Mar 20, 2021
  //Date of last update: Mar 28, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.
  //Purpose: This program demonstrates a ball moving and bouncing off the wall at a user choice speed and direction.
//
//This module
  //File name: Richochet_panel.java
  //Purpose:  This file contains the class ball, which displays the entire frame.




import javax.swing.JFrame;
import java.awt.Dimension;

public class ball
{
  public static void main(String[] args)
    {
     Richochet_user_interface myframe = new Richochet_user_interface();
     myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // myframe.setSize(1800,980); //frame declared to where I could see it biggest
     myframe.setResizable(false);
     myframe.setVisible(true);
    }//End of main
}
