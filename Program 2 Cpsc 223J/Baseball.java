//****************************************************************************************************************************
//Program name: "Baseball".  This program shows a ball travelling along a diamond.  The speed of the ball is slected*
//by the user.  The action of moving the ball is displayed.  The user may choose the speed at which the ball travels.  *
//  Copyright (C) 2021 Nicholas Ayson.  All rights reserved.                  *
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

//Program information
  //Program name: Baseball
  //Programming language: Java
  //Files in this program: Baseball.java (main), Diamond_user_interface.java (UI frame), Diamond_panel.java (graphic panel), run.sh (Bash)
  //Date project began: Mar 1st, 2021
  //Date of last update: Mar 7, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.
  //Purpose: This program demonstrates a ball moving along a diamond at a user choice speed.
//
//This module
  //File name: Baseball.java
  //Purpose:  This file contains the class Baseball, which runs the main part of the program.

  // NOTE TO PROFESSOR: I did not do the deltax and deltay Computations in a seperate class but i put them in the motion clock if statements.
  // This is in the Diamond_user_interface 

import javax.swing.JFrame;
import java.awt.Dimension;

public class Baseball
{public static void main(String[] args)
    {Diamond_user_interface myframe = new Diamond_user_interface();
     myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myframe.setSize(1800,980); //frame declared to where I could see it biggest
     myframe.setResizable(false);


     myframe.setVisible(true);
    }//End of main
}
