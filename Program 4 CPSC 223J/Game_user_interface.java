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
  //File name: Richochet_user_interface.java
  //Purpose:  This file contains the class Richochet_user_interface, which displays the UI.



import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.text.DecimalFormat;
import java.lang.Math;


public class Game_user_interface extends JFrame
{
  final int frame_width = 1800;
  final int frame_height = 980;

  private String mouseball_speed_pix_per_second;
  private double mouseballspeedpixpersecond;// = 88.437;
  private double mouseball_speed_pix_per_tic;

  private String catball_speed_pix_per_second;
  private double catballspeedpixpersecond;// = 88.437;
  private double catball_speed_pix_per_tic;

  private String direction;
  private double directionnum;
  private double directionnumrad;

  //title label objects
  private JPanel title_panel;
  private final int title_panel_width = frame_width;
  private final int title_panel_height = 50;
  private JLabel title_label;

  //Declare a control panel
  private JPanel control_panel;
  private final int control_panel_width = frame_width;
  private final int control_panel_height = 100;
  //all of the buttons to be declared in the control panel
  private JButton clear_button;
  private JButton start_button;
  private JButton pause_button;
  private JButton quit_button;
  //ball distance between
  private JLabel distance_between;
  private JTextField distance_between_text;
  //Inputs for the ball
  private JLabel catspeed;
  private JTextField cat_speed_output;
  private JLabel mousespeed;
  private JTextField mouse_speed_output;
  private JLabel directiondeg;
  private JTextField direction_output;

  //richochet_panel
  private Cat_Mouse_panel catmousepanel;

  private int act = 1; //starts at the first point

  private DecimalFormat special_edition;
  private Timer refreshclock;
  private Timer mouseclock;
  private Timer catclock;
  private Buttonhandlerclass buttonhandler;
  private Clockhandlerclass clockhandler;
  //set up refreshclock
  private final double refresh_clock_rate = 120;
  private int refresh_clock_delay_interval;
  private final double mouse_clock_rate = 99.873;
  private int mouse_clock_delay_interval;
  private final double cat_clock_rate = 99.873;
  private int cat_clock_delay_interval;
  private final double millisecondpersecond = 1000.0;
  private double mousedeltax;
  private double mousedeltay;
  private double catdeltax;
  private double catdeltay;
  private double u, v;
  private boolean active = false;
  private boolean clocks_are_ticking = false;

  public Game_user_interface()
  {
    //Mandatory first statement of a constructor
     super("Program 4");

     //Initialize the frame
     setLayout(new BorderLayout());
     setSize(frame_width,frame_height);
     setResizable(false);
     setVisible(false);

     //make handler for buttons
     buttonhandler = new Buttonhandlerclass();

     //Title panel
     title_panel = new JPanel();
     title_label = new JLabel("Nicholas Ayson CPSC 223J Assignment 4");
     title_panel.setSize(title_panel_width,title_panel_height);
     title_panel.setBackground(java.awt.Color.yellow);
     title_panel.setLayout(new FlowLayout());
     title_panel.add(title_label);
     add(title_panel, BorderLayout.NORTH);    //sets the titlepanel to the top

     //Middle Graphics Panel / richochet_panel
     catmousepanel = new Cat_Mouse_panel();
     add(catmousepanel, BorderLayout.CENTER);      //sets graphics panel to middle

     //Control panel
     control_panel = new JPanel();
     control_panel.setLayout(new FlowLayout());     //set as a flow layout for now to see how it looks
     control_panel.setBackground(java.awt.Color.pink);
     //all buttons declared in order
     start_button = new JButton("Start");
     start_button.addActionListener(buttonhandler);
     pause_button = new JButton("Pause");
     pause_button.setEnabled(false);    //pause button doesn't appear until after start button is pressed
     pause_button.addActionListener(buttonhandler);
     clear_button = new JButton("Clear");
     clear_button.addActionListener(buttonhandler);
     quit_button = new JButton("Quit");
     quit_button.addActionListener(buttonhandler);
     //all inputs declared in order
     catspeed = new JLabel("Cat Speed(pix/sec)");
     cat_speed_output = new JTextField(8);
     mousespeed = new JLabel("Mouse Speed(pix/sec)");
     mouse_speed_output = new JTextField(8);
     directiondeg = new JLabel("Mouse Direction");   //grab direction in degrees but convert to radians
     direction_output = new JTextField(8);
     //distance between two balls
     distance_between = new JLabel("Distance between");
     distance_between_text = new JTextField(8);
     special_edition = new DecimalFormat("00.00");


     control_panel.add(clear_button);
     control_panel.add(start_button);
     control_panel.add(pause_button);
     control_panel.add(quit_button);
     control_panel.add(distance_between);
     control_panel.add(distance_between_text);
     control_panel.add(catspeed);
     control_panel.add(cat_speed_output);
     control_panel.add(mousespeed);
     control_panel.add(mouse_speed_output);
     control_panel.add(directiondeg);
     control_panel.add(direction_output);
     add(control_panel, BorderLayout.SOUTH);




     //create handler class for all clocks
     clockhandler = new Clockhandlerclass();

     //Set up the mouse clock
     mouse_clock_delay_interval = (int)Math.round(millisecondpersecond/mouse_clock_rate);
     mouseclock = new Timer(mouse_clock_delay_interval,clockhandler);

     cat_clock_delay_interval = (int)Math.round(millisecondpersecond/cat_clock_rate);
     catclock = new Timer(cat_clock_delay_interval,clockhandler);

     //Set up the refresh clock
     refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refresh_clock_rate);
     refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);


     setVisible(true);
   } //end of constructor

 private class Buttonhandlerclass implements ActionListener
 {
     public void actionPerformed(ActionEvent event)
     {
       if(event.getSource() == start_button)
       {
         //Convert the speed of the ball from units pix/sec to pix/tic
         mouseball_speed_pix_per_second = mouse_speed_output.getText();
         mouseballspeedpixpersecond = Double.parseDouble(mouseball_speed_pix_per_second);
         mouseball_speed_pix_per_tic = mouseballspeedpixpersecond/mouse_clock_rate;

         //Convert the speed of the ball from units pix/sec to pix/tic
         catball_speed_pix_per_second = cat_speed_output.getText();
         catballspeedpixpersecond = Double.parseDouble(catball_speed_pix_per_second);
         catball_speed_pix_per_tic = catballspeedpixpersecond/cat_clock_rate;

         //get the direction degrees
         direction = direction_output.getText();
         directionnum = Double.parseDouble(direction);
         // directionnumrad = Math.toRadians(directionnum);

         //compute delatx and deltay and convert to radians
         mousedeltax = mouseball_speed_pix_per_tic*(Math.cos(Math.toRadians(directionnum)));
         mousedeltay = -(mouseball_speed_pix_per_tic*(Math.sin(Math.toRadians(directionnum))));

         catmousepanel.initializeobjectsinpanel(mousedeltax, mousedeltay, mouseball_speed_pix_per_tic, catball_speed_pix_per_tic);
         catmousepanel.repaint();
         refreshclock.start();
         catclock.start();
         mouseclock.start();
         start_button.setEnabled(false);
         start_button.setLabel("Resume");
         pause_button.setEnabled(true);
         active = true;
       }
       else if (event.getSource() == pause_button)
       {
         refreshclock.stop();
         mouseclock.stop();
         catclock.stop();
         start_button.setEnabled(true);
         active = false;
       }
       else if(event.getSource() == clear_button)
       {
         //everything clears
         mouse_speed_output.setText("");
         cat_speed_output.setText("");
         direction_output.setText("");
         distance_between_text.setText("");
         // richochetpanel.ball_center_x.setX(900);
         // richochetpanel.ball_center_y.setY(450);
         catmousepanel.repaint();    //makes the ball go back to original spot
         start_button.setEnabled(true);
       }
       else if(event.getSource() == quit_button)
       {
         System.exit(0);
       }
       else
       {
         System.out.printf("%s\n","An unknown error occurred in a button.");
       }
     }
 }//end of Buttonhandlerclass


 private class Clockhandlerclass implements ActionListener
 {
   public void actionPerformed(ActionEvent event)
   {
     boolean animation_continues = false;
     if(event.getSource() == refreshclock)
        {
          catmousepanel.repaint();
        }
     else if(event.getSource() == catclock)
        {
          catmousepanel.catmoveball();
          u = catmousepanel.getdistancebetween();
          distance_between_text.setText(special_edition.format(u));
        }
     else if(event.getSource() == mouseclock)
        {
          catmousepanel.mousemoveball();
        }
     else
        System.out.printf("%s\n","There is a bug in one of the clocks.");
   }
 }

 //The pause button must be able to alternate between stop all three clocks and start all three clock.  The following function can do exactly that.  You will need to declare clocks_are_ticking as a boolean.  This function is called by the pause button handler.
public void change_clocks()
{
  if(clocks_are_ticking)
  {
    refreshclock.stop();
    catclock.stop();
    mouseclock.stop();
  }
  else
  {
    refreshclock.start();
    catclock.start();
    mouseclock.start();
  }
  clocks_are_ticking = !clocks_are_ticking;
}

}//end of Game_user_interface
