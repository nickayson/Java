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
  //File name: Diamond_panel.java
  //Purpose:  This file contains the class Diamond_panel, which is inherited to the JFrame.


  // NOTE TO PROFESSOR: I did not do the deltax and deltay Computations in a seperate class but i put them in the motion clock if statements.
  // This is in the Diamond_user_interface

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class Diamond_panel extends JPanel
{   private double diamond_panel_width;
    private double diamond_panel_height;

    private final double ballradius = 7.0;
    private final double balldiameter = 2.0 * ballradius;
    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x;
    private int ball_upper_corner_integer_y;

    private double a1 ;
    private double b1 ;
    private double a2 ;
    private double b2;
    private double length_line_segment1;
    private double deltax;                                 //Unit of incremental change in coordinates.
    private double deltay;                                 //Unit of incremental change in coordinates.

    private int i1,j1,i2,j2;    //integer coordinates of end points of line segment.
    private int k1,l1,k2,l2;
    private int u1,m1,u2,m2;
    private int t1,y1,t2,y2;
    private double x;
    private double y;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private double temporary;

    private double ballspeedpixpersecond = 88.437;
    private double ball_speed_pix_per_tic;



    public Diamond_panel()  //Constructor
    {
     setVisible(true);
     System.out.println("The constructor of the diamond panel has finished.");
    }//End of constructor

    public void paintComponent(Graphics graphicarea)
    {
      super.paintComponent(graphicarea);
      setBackground(Color.green);
      //draw the diamond
      graphicarea.drawLine(500, 400, 800, 100);
      graphicarea.drawLine(800, 100, 1100 , 400);
      graphicarea.drawLine(1100, 400, 900 ,700);
      graphicarea.drawLine(900, 700, 500, 400);
      graphicarea.drawLine(i1, j1, i2, j2);
      graphicarea.fillOval(ball_upper_corner_integer_x,ball_upper_corner_integer_y,(int)Math.round(balldiameter),(int)Math.round(balldiameter));

    }//end of method paintComponent

    public void initializeobjectsinpanel1(double startx, double starty, double endx, double endy, double deltax, double deltay)
    {
      a1 = startx;
      b1 = starty;
      a2 = endx;
      b2 = endy;
      i1 = (int)Math.round(startx);
      j1 = (int)Math.round(starty);
      i2 = (int)Math.round(endx);
      j2 = (int)Math.round(endy);
      x = deltax;
      y = deltay;
      distance_moved_in_one_tic = Math.sqrt(x*x + y*y);
      ball_center_x = a1; //Ball receives its starting coordinates.
      ball_center_y = b1;
      distance_center_of_ball_2_end_of_line_segment
          = Math.sqrt(Math.pow(ball_center_x - a2,2) + Math.pow(ball_center_y - b2,2));
    }//End of initializeobjectsinpanel


    public boolean moveball() //function that moves the ball
    {    successfulmove = true;
         if(distance_center_of_ball_2_end_of_line_segment > distance_moved_in_one_tic)
             {
              ball_center_x = ball_center_x + x;
              ball_center_y = ball_center_y + y;
             }
         else
             {
              ball_center_x = a2;
              ball_center_y = b2;
              successfulmove = false;
             }
             ball_upper_corner_x = ball_center_x - ballradius;
             ball_upper_corner_y = ball_center_y - ballradius;
             ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
             ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
             distance_center_of_ball_2_end_of_line_segment = Math.sqrt(Math.pow(ball_center_x - i2,2) + Math.pow(ball_center_y - j2,2));
         return successfulmove;
    }//End of moveball

    public double getxcenter_of_ball()
    {
     temporary = ball_center_x;
     return temporary;
    }

    public double getycenter_of_ball()
    {temporary = ball_center_y;
     return temporary;
    }//End of method getycenter_of_ball
}
