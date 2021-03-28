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
  //Purpose:  This file contains the class Richochet_panel, which has the graphic panel which moves the ball.



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class Richochet_panel extends JPanel
{   private final int richochet_panel_width = 1800;
    private final int richochet_panel_height = 870;

    private final double ballradius = 7.0;
    private final double balldiameter = 2.0 * ballradius;
    private double ball_center_x = 900.0;
    private double ball_center_y = 450.0;

    private double dtop;
    private double dleft;
    private double dbottom;
    private double dright;
    private double deltax;                                 //Unit of incremental change in coordinates.
    private double deltay;                                 //Unit of incremental change in coordinates.

    private double x;
    private double y;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private double temporary;

    private double ballspeed;



    public Richochet_panel()  //Constructor
    {
     setVisible(true);
     System.out.println("The constructor of the richochet panel has finished.");
    }//End of constructor

    public void paintComponent(Graphics graphicarea)
    {
      super.paintComponent(graphicarea);
      setBackground(Color.green);
      //draw ball
      graphicarea.fillOval((int)(ball_center_x - ballradius), (int)(ball_center_y - ballradius),(int)(balldiameter),(int)(balldiameter));

    }//end of method paintComponent

    public void initializeobjectsinpanel(double deltax, double deltay)
    {
      x = deltax;
      y = deltay;
      distance_moved_in_one_tic = Math.sqrt(x*x + y*y); // magnitude of speed
    }//End of initializeobjectsinpanel


    public boolean moveball() //function that moves the ball
    {
      successfulmove = true;
      // dtop = ball_center_y - ballradius;
      // dbottom = ball_center_y + ballradius;
      // dleft = ball_center_x - ballradius;
      // dright = ball_center_x + ballradius;
      //move ball
      ball_center_x = ball_center_x + x;
      ball_center_y = ball_center_y + y;

      //if the ball center touches the top of panel change direction
      if (ball_center_y - ballradius <= 0)
      {
        y = -y;
      }
      //if ball center touches left sdie of panel
      if (ball_center_x - ballradius <= 0)
      {
        x = -x;
      }
      //if the ball center touches the bottom of panel change direction
      if (ball_center_y + ballradius > richochet_panel_height)
      {
        y = -y;
      }
      //for east side to move to touch panel
      if (ball_center_x + ballradius > richochet_panel_width)
      {
        x = -x;
      }



      if(richochet_panel_width - ball_center_x - ballradius < x)
      {
        ball_center_x = richochet_panel_width - ballradius;
      }
      //for south side to touch panel
      if(richochet_panel_height - ball_center_y - ballradius < y)
      {
        ball_center_y = richochet_panel_height - ballradius;
      }
      //north wall to touch panel
      if (ball_center_y - ballradius < y)
      {
        ball_center_y = ballradius;
      }
      //west wall to touchpanel
      if(ball_center_x - ballradius < x)
      {
        ball_center_x = ballradius;
      }
      // else{successfulmove = false;}

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
