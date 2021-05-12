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
  //Date of last update: May 12, 2021
  //Status: Ready for public posting.  The program was tested significantly and did very well.
  //Purpose: This program demonstrates a ball moving and bouncing off the wall at a user choice speed while another object chases it.
//
//This module
  //File name: Cat_Mouse_panel.java
  //Purpose:  This file contains the class Cat_Mouse_panel, which has the graphic panel which moves both balls.
  //extra: The cat function for the second ball moves at a curve to catch the mice.

  //Speed works correctly for both
  //double speed and same for twins good
  //collision is sum of 2 radii
  //walls are not damaged
  //frame size is minimum for my monitor



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;
import java.lang.reflect.*;
import java.lang.*;

public class Cat_Mouse_panel extends JPanel
{   private final int graphic_panel_width = 1800;
    private final int graphic_panel_height = 870;

    private final double mouseballradius = 7.0;
    private final double mouseballdiameter = 14.0;
    private double mouseball_center_x;
    private double mouseball_center_y;

    private final double catballradius = 10.0;
    private final double catballdiameter = 2.0 * catballradius;
    private double catball_center_x;
    private double catball_center_y;

    private double dtop;
    private double dleft;
    private double dbottom;
    private double dright;
    private double deltax;                                 //Unit of incremental change in coordinates.
    private double deltay;                                 //Unit of incremental change in coordinates.

    private double direction;

    private double catspeed;
    private double mousespeed;

    private double a = 2.0;

    private double mdx;
    private double mdy;
    private double mdxnew;
    private double mdynew;
    private double cdx;
    private double cdy;
    private double cdxnew;
    private double cdynew;
    private double D;
    private double gap;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private double temporary;

    private Game_user_interface gameuserinterface;

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
      graphicarea.fillOval((int)Math.round(mouseball_center_x - mouseballradius),(int)Math.round(mouseball_center_y - mouseballradius),(int)Math.round(mouseballdiameter),(int)Math.round(mouseballdiameter));
      //draw catball
      graphicarea.setColor(Color.white);
      graphicarea.fillOval((int)(catball_center_x - catballradius), (int)(catball_center_y - catballradius),(int)(catballdiameter),(int)(catballdiameter));


    }//end of method paintComponent

    public void catmousevalues(double mouseballcenterx, double mouseballcentery, double catballcenterx, double catballcentery)
    {
      mouseball_center_x = mouseballcenterx;
      mouseball_center_y = mouseballcentery;
      catball_center_x = catballcenterx;
      catball_center_y = catballcentery;
    }

    public void valuesneeded(double mousedx, double mousedy, double mouseball_speed_pix_per_tic, double catball_speed_pix_per_tic)
    {
      mdx = mousedx;
      mdy = mousedy;
      catspeed = catball_speed_pix_per_tic;
      mousespeed = mouseball_speed_pix_per_tic;
    }//End of initializeobjectsinpanel


    public void mousemoveball() //function that moves the ball
    {
      //Compute the distance between the center of cat and the center of mouse.  Let call that distance D.
      D = Math.sqrt(Math.pow((mouseball_center_x - catball_center_x), 2)+ Math.pow((mouseball_center_y - catball_center_y),2));

      //Compute the physical gap between the two animals
      gap = D - catballradius - mouseballradius;

      //Check for collision with the other animal
      if(gap <= 0.0)
      {
        gameuserinterface.change_clocks();
      }

      else if(mousespeed >= gap) //The mouse is so close to the cat that a collision will occur in the next step
      {//In this case a new special pair of increments are computed: Δx_mouse_new & Δy_mouse_new for one time use.
        mdxnew = (mouseball_center_x - catball_center_x) * gap / D;
        mdynew = (mouseball_center_y - catball_center_y) * gap / D;
        mouseball_center_x += mdxnew;
        mouseball_center_y += mdynew;
      }
      //Check north wall for possible collision
      if (mouseball_center_y - mouseballradius < 0)
      {
        mouseball_center_y = mouseballradius;
        mdy = -mdy; //Change sign
      }
      else if (mouseball_center_y + mouseballradius > graphic_panel_height)
      {
        mouseball_center_x = graphic_panel_height - mouseballradius;
        mdy = -mdy;
      }
      //Check west wall for possible collision
      if(mouseball_center_x - mouseballradius < 0)
      {
        mouseball_center_x = mouseballradius;
        mdx = -mdx; //Change sign
      }
      else if(mouseball_center_x + mouseballradius > graphic_panel_width)
      {
        mouseball_center_x = graphic_panel_width - mouseballradius;
        mdx = -mdx;
      }
      //Check south wall for possible collision
      else if(graphic_panel_height - mouseball_center_y - mouseballradius < mdy)
      {
        mouseball_center_y = graphic_panel_height - mouseballradius;
        mdy = -mdy; //Change sign
      }
      //Check east wall for possible collision
      else if(graphic_panel_width - mouseball_center_x - mouseballradius < mdx)
      {
        mouseball_center_x = graphic_panel_width - mouseballradius;
        mdx = -mdx; //Change sign
      }
      else //There is no collison pending, therefore make an ordinary move
      {
        mouseball_center_x += mdx;
        mouseball_center_y += mdy;
      }
      repaint();
    }//End of moveball mouse

    public void catmoveball()
    {

      //Compute the distance between the center of cat and the center of mouse.  Let call that distance D.
      D = Math.sqrt(Math.pow((mouseball_center_x - catball_center_x), 2)+ Math.pow((mouseball_center_y - catball_center_y),2));

      cdx = ((mouseball_center_x - catball_center_x)*catspeed)/D;
      cdy = ((mouseball_center_y - catball_center_y)*catspeed)/D;


      //Compute the physical gap between the two animals
      gap = D - catballradius - mouseballradius;

      if(gap <= 0.0)
      {
        gameuserinterface.change_clocks();
      }

      //Check to see if the gap is so small that a single step forward will collide with the mouse
      if(catspeed <= gap)
      {//The next step will not cause a collision; therefore, make an ordinary step forward.
        catball_center_x += cdx;
        catball_center_y += cdy;
      }
      else
      {//The cat is very close to the mouse and a collision will occur in the next move.
        cdxnew = (mouseball_center_x - catball_center_x) * gap / D;
        cdynew = (mouseball_center_y - catball_center_y) * gap / D;
        catball_center_x += cdxnew;
        catball_center_y += cdynew;
      }
      repaint();
    }

    public double getdistancebetween()
    {
     temporary = Math.sqrt(Math.pow((mouseball_center_x - catball_center_x), 2)+ Math.pow((mouseball_center_y - catball_center_y),2));
     return temporary;
    }

}
