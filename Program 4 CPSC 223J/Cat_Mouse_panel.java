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

public class Cat_Mouse_panel extends JPanel
{   private final int graphic_panel_width = 1800;
    private final int graphic_panel_height = 870;

    private final double mouseballradius = 7.0;
    private final double mouseballdiameter = 2.0 * mouseballradius;
    private double mouseball_center_x = 900.0;
    private double mouseball_center_y = 450.0;

    private final double catballradius = 10.0;
    private final double catballdiameter = 2.0 * catballradius;
    private double catball_center_x = 10.0;
    private double catball_center_y = 10.0;

    private double dtop;
    private double dleft;
    private double dbottom;
    private double dright;
    private double deltax;                                 //Unit of incremental change in coordinates.
    private double deltay;                                 //Unit of incremental change in coordinates.

    private double catspeed;

    private double a = 2.0;

    private double mdx;
    private double mdy;
    private double cdx;
    private double cdy;
    private double bigd;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private double temporary;

    public Cat_Mouse_panel()  //Constructor
    {
     setVisible(true);
     System.out.println("The constructor of the richochet panel has finished.");
    }//End of constructor

    public void paintComponent(Graphics graphicarea)
    {
      super.paintComponent(graphicarea);
      setBackground(Color.green);
      //draw mouseball
      graphicarea.fillOval((int)(mouseball_center_x - mouseballradius), (int)(mouseball_center_y - mouseballradius),(int)(mouseballdiameter),(int)(mouseballdiameter));
      //draw catball
      graphicarea.setColor(Color.white);
      graphicarea.fillOval((int)(catball_center_x - catballradius), (int)(catball_center_y - catballradius),(int)(catballdiameter),(int)(catballdiameter));


    }//end of method paintComponent

    public void initializeobjectsinpanel(double mousedeltax, double mousedeltay, double catball_speed_pix_per_tic)
    {
      mdx = mousedeltax;
      mdy = mousedeltay;
      catspeed = catball_speed_pix_per_tic;
      bigd = Math.sqrt((Math.pow(catball_center_x - mouseball_center_x, 2)) + (Math.pow(catball_center_y - mouseball_center_y, 2)));
      cdx = ((mouseball_center_x - catball_center_x)*catspeed)/bigd;
      cdy = ((mouseball_center_y - catball_center_y)*catspeed)/bigd;
    }//End of initializeobjectsinpanel


    public boolean mousemoveball() //function that moves the ball
    {
      successfulmove = true;
      //move cat
      catball_center_x = catball_center_x + cdx;
      catball_center_y = catball_center_y + cdy;
      //move ball
      mouseball_center_x = mouseball_center_x + mdx;
      mouseball_center_y = mouseball_center_y + mdy;

      //if the ball center touches the top of panel change direction
      if (mouseball_center_y - mouseballradius <= 0)
      {
        mdy = -mdy;
      }
      //if ball center touches left sdie of panel
      if (mouseball_center_x - mouseballradius <= 0)
      {
        mdx = -mdx;
      }
      //if the ball center touches the bottom of panel change direction
      if (mouseball_center_y + mouseballradius > graphic_panel_height)
      {
        mdy = -mdy;
      }
      //for east side to move to touch panel
      if (mouseball_center_x + mouseballradius > graphic_panel_width)
      {
        mdx = -mdx;
      }



      if(graphic_panel_width - mouseball_center_x - mouseballradius < mdx)
      {
        mouseball_center_x = graphic_panel_width - mouseballradius;
      }
      //for south side to touch panel
      if(graphic_panel_height - mouseball_center_y - mouseballradius < mdy)
      {
        mouseball_center_y = graphic_panel_height - mouseballradius;
      }
      //north wall to touch panel
      if (mouseball_center_y - mouseballradius < mdy)
      {
        mouseball_center_y = mouseballradius;
      }
      //west wall to touchpanel
      if(mouseball_center_x - mouseballradius < mdx)
      {
        mouseball_center_x = mouseballradius;
      }
      // else{successfulmove = false;}

      return successfulmove;
    }//End of moveball

    public double getxcenter_of_ball()
    {
     temporary = mouseball_center_x;
     return temporary;
    }

    public double getycenter_of_ball()
    {
      temporary = mouseball_center_y;
     return temporary;
    }//End of method getycenter_of_ball
}
