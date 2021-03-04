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
  //File name: Diamond_user_interface.java
  //Purpose:  This file contains the class Diamond_user_interface, which displays the UI.

// NOTE TO PROFESSOR: I did not do the deltax and deltay Computations in a seperate class but i put them in the motion clock if statements.
// This is in the Diamond_user_interface 

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


public class Diamond_user_interface extends JFrame
{
  final int frame_width = 900;
  final int frame_height = 600;

  private String ball_speed_pix_per_second;
  private double ballspeedpixpersecond;// = 88.437;
  private double ball_speed_pix_per_tic;

  //title label objects
  private JPanel title_panel;
  private final int title_panel_width = frame_width;
  private final int title_panel_height = 50;
  private JLabel title_label;

  //Declare a control panel
  private JPanel control_panel;
  private final int control_panel_width = frame_width;
  private final int control_panel_height = 100;
  private JButton initialize_button;
  private JButton start_button;
  private JButton pause_button;
  private JLabel speed;
  private JButton quit_button;
  private JTextField speed_output;

  //diamond_panel
  private Diamond_panel diamondpanel;
  private final int diamond_panel_width = frame_width;
  private final int diamond_panel_height = frame_height - title_panel_height - control_panel_height;

  //Declare the line segment properties.
  private double startxnumeric1;
  private double startynumeric1;
  private double endxnumeric1;
  private double endynumeric1;
  private double startxnumeric2;
  private double startynumeric2;
  private double endxnumeric2;
  private double endynumeric2;
  private double startxnumeric3;
  private double startynumeric3;
  private double endxnumeric3;
  private double endynumeric3;
  private double startxnumeric4;
  private double startynumeric4;
  private double endxnumeric4;
  private double endynumeric4;
  private double length_line_segment1;

  private int whatpoint = 1; //starts at the first point

  private Timer refreshclock;
  private Timer motionclock;
  private Buttonhandlerclass buttonhandler;
  private Clockhandlerclass clockhandler;
  private final double refresh_clock_rate = 120.47;
  private int refresh_clock_delay_interval;
  private final double motion_clock_rate = 99.873;
  private int motion_clock_delay_interval;
  private final double millisecondpersecond = 1000.0;
  private double deltax;
  private double deltay;
  private double u, v;
  private boolean active = false;

  public Diamond_user_interface()
  {
    //Mandatory first statement of a constructor
     super("Program 2");

     //Initialize the frame
     setLayout(new BorderLayout());
     setSize(frame_width,frame_height);
     setResizable(false);
     setVisible(false);

     //make handler for buttons
     buttonhandler = new Buttonhandlerclass();

     //Title panel
     title_panel = new JPanel();
     title_label = new JLabel("Nicholas Ayson Diamond Animation");
     title_panel.setSize(title_panel_width,title_panel_height);
     title_panel.setBackground(java.awt.Color.yellow);
     title_panel.setLayout(new FlowLayout());
     title_label.setForeground(java.awt.Color.red);  //The text of the label will be red.
     title_panel.add(title_label);
     add(title_panel, BorderLayout.NORTH);

     //Middle Graphics Panel / diamond_panel
     diamondpanel = new Diamond_panel();
     diamondpanel.setSize(diamond_panel_width, diamond_panel_height);
     add(diamondpanel, BorderLayout.CENTER);

     //Control panel
     control_panel = new JPanel();
     control_panel.setLayout(new GridLayout(1,5));
     start_button = new JButton("Start");
     start_button.setPreferredSize(new Dimension (40, 40)); //make the buttons bigger
     start_button.addActionListener(buttonhandler);
     pause_button = new JButton("Pause");

     pause_button.setEnabled(false);
     pause_button.addActionListener(buttonhandler);
     speed = new JLabel("Speed(pix/sec)");
     speed_output = new JTextField(8);
     quit_button = new JButton("Exit");
     quit_button.addActionListener(buttonhandler);
     control_panel.add(start_button);
     control_panel.add(pause_button);
     control_panel.add(speed);
     control_panel.add(speed_output);
     control_panel.add(quit_button);
     add(control_panel, BorderLayout.SOUTH);

     //create handler class for all clocks
     clockhandler = new Clockhandlerclass();

     //Set up the refresh clock
     refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refresh_clock_rate);
     refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

     //Set up the motion clock
     motion_clock_delay_interval = (int)Math.round(millisecondpersecond/motion_clock_rate);
     motionclock = new Timer(motion_clock_delay_interval,clockhandler);

     setVisible(true);
   } //end of constructor

 private class Buttonhandlerclass implements ActionListener
 {
     public void actionPerformed(ActionEvent event)
     {
       ball_speed_pix_per_second = speed_output.getText();
       ballspeedpixpersecond = Double.parseDouble(ball_speed_pix_per_second);
       //Convert the speed of the ball from units pix/sec to pix/tic
       ball_speed_pix_per_tic = ballspeedpixpersecond/motion_clock_rate;

       //declaration of all the points start and ends
       startxnumeric1 = 900.0;
       startynumeric1 = 700.0;
       endxnumeric1 = 1100.0;
       endynumeric1 = 400.0;
       startxnumeric2 = 1100.0;
       startynumeric2 = 400.0;
       endxnumeric2 = 800.0;
       endynumeric2 = 100.0;
       startxnumeric3 = 800.0;
       startynumeric3 = 100.0;
       endxnumeric3 = 500.0;
       endynumeric3 = 400.0;
       startxnumeric4 = 500.0;
       startynumeric4 = 400.0;
       endxnumeric4 = 900.0;
       endynumeric4 = 700.0;

       if(event.getSource() == start_button )
       {
         diamondpanel.repaint();
         refreshclock.start();
         motionclock.start();
         start_button.setEnabled(false);
         pause_button.setEnabled(true);
         active = true;
       }
       else if (event.getSource() == pause_button)
       {
         refreshclock.stop();
         motionclock.stop();
         start_button.setEnabled(true);
         active = false;
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
          diamondpanel.repaint();
        }
     else if(event.getSource() == motionclock)
        {
          animation_continues = diamondpanel.moveball();
          if(!animation_continues)
            {
              if(whatpoint == 0)
              {
                whatpoint++;
              }
              else if (whatpoint == 1) //declare the deltas for the distance of the first point
              {
                length_line_segment1 = Math.sqrt(Math.pow((endxnumeric1-startxnumeric1),2) + Math.pow((endynumeric1-startynumeric1),2));
                deltax = ball_speed_pix_per_tic*(endxnumeric1 - startxnumeric1)/length_line_segment1;
                deltay = ball_speed_pix_per_tic*(endynumeric1 - startynumeric1)/length_line_segment1;
                diamondpanel.initializeobjectsinpanel1(startxnumeric1, startynumeric1, endxnumeric1, endynumeric1, deltax, deltay);
                whatpoint++;
              }
              else if (whatpoint == 2)    //declare the deltas for the distance of the second point
              {
                length_line_segment1 = Math.sqrt(Math.pow((endxnumeric2-startxnumeric2),2) + Math.pow((endynumeric2-startynumeric2),2));
                deltax = ball_speed_pix_per_tic*(endxnumeric2 - startxnumeric2)/length_line_segment1;
                deltay = ball_speed_pix_per_tic*(endynumeric2 - startynumeric2)/length_line_segment1;
                diamondpanel.initializeobjectsinpanel1(startxnumeric2, startynumeric2, endxnumeric2, endynumeric2, deltax, deltay);
                whatpoint++;
              }
              else if (whatpoint == 3)  //declare the deltas for the distance of the third point
              {
                length_line_segment1 = Math.sqrt(Math.pow((endxnumeric3-startxnumeric3),2) + Math.pow((endynumeric3-startynumeric3),2));
                deltax = ball_speed_pix_per_tic*(endxnumeric3 - startxnumeric3)/length_line_segment1;
                deltay = ball_speed_pix_per_tic*(endynumeric3 - startynumeric3)/length_line_segment1;
                diamondpanel.initializeobjectsinpanel1(startxnumeric3, startynumeric3, endxnumeric3, endynumeric3, deltax, deltay);
                whatpoint++;
              }
              else if (whatpoint == 4)    //declare the deltas for the distance of the fourth point
              {
                length_line_segment1 = Math.sqrt(Math.pow((endxnumeric4-startxnumeric4),2) + Math.pow((endynumeric4-startynumeric4),2));
                deltax = ball_speed_pix_per_tic*(endxnumeric4 - startxnumeric4)/length_line_segment1;
                deltay = ball_speed_pix_per_tic*(endynumeric4 - startynumeric4)/length_line_segment1;
                diamondpanel.initializeobjectsinpanel1(startxnumeric4, startynumeric4, endxnumeric4, endynumeric4, deltax, deltay);
                whatpoint++;
              }
              else if (whatpoint == 5)      //stop the clock after it reaches the first
              {
                motionclock.stop();
                refreshclock.stop();
              }
            }
        }//End of if(event.getSource() == motionclock)
     else
        System.out.printf("%s\n","There is a bug in one of the clocks.");
   }
 }

}//end of Diamond_user_interface
