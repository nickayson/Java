// #Author: Nicholas Ayson
// #Program: Sun and Earth
// Email: nick.ayson@csu.fullerton.edu


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
  final int frame_height = 980; //set to biggenst to where i could see it

  private String earthball_speed_pix_per_second;
  private double earthballspeedpixpersecond;// = 88.437; to change it to double;
  private double earthball_speed_pix_per_tic;


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
  private JButton start_button;
  private JButton pause_button;
  private JButton quit_button;
  //Inputs for the ball
  private JLabel earthspeed;
  private JTextField earth_speed_output;

  //Earth and sun panel
  private Earth_Sun_panel earthsunpanel;

  private DecimalFormat special_edition;
  private Timer refreshclock;
  private Timer earthclock;
  private Buttonhandlerclass buttonhandler;
  private Clockhandlerclass clockhandler;

  //set up refreshclock
  private final double refresh_clock_rate = 120.00;
  private int refresh_clock_delay_interval;
  //earth clock
  private final double motion_clock_rate = 99.873;
  private int earth_clock_delay_interval;
  private final double millisecondpersecond = 1000.0;
  private double earthdeltat;
  private double u, v;      //to get values of x and y of earth
  private boolean active = false;
  private boolean clocks_are_ticking = false;
  //location of earth labels
  private JLabel earthx;
  private JTextField earthxoutput;
  private JLabel earthy;
  private JTextField earthyoutput;
  //sun ball placed
  private double sunballcenterx = 900.0;
  private double sunballcentery = 450.0;

  private double earthorbitrad = 120.0; //earths orbit radius

  private double earthballcenterx = 900.0 + 120.0;
  private double earthballcentery = 450.0;

  public Game_user_interface()
  {
    //Mandatory first statement of a constructor
     super("223J FINAL");

     //Initialize the frame
     setLayout(new BorderLayout());
     setSize(frame_width,frame_height);
     setResizable(false);
     setVisible(false);

     //make handler for buttons
     buttonhandler = new Buttonhandlerclass();

     //Title panel
     title_panel = new JPanel();
     title_label = new JLabel("Earth by Nicholas Ayson");
     title_panel.setSize(title_panel_width,title_panel_height);
     title_panel.setBackground(java.awt.Color.pink);
     title_panel.setLayout(new FlowLayout());
     title_panel.add(title_label);
     add(title_panel, BorderLayout.NORTH);    //sets the titlepanel to the top

     //Middle Graphics Panel / richochet_panel
     earthsunpanel = new Earth_Sun_panel();
     add(earthsunpanel, BorderLayout.CENTER);      //sets graphics panel to middle

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
     quit_button = new JButton("Quit");
     quit_button.addActionListener(buttonhandler);
     //all inputs declared in order
     earthspeed = new JLabel("Input Earth Linear Speed(pix/sec)");
     earth_speed_output = new JTextField(8);
     //location of earth
     earthx = new JLabel("X Coordinates");
     earthxoutput = new JTextField(8);
     special_edition = new DecimalFormat("00.00");
     earthy = new JLabel("Y Coordinates");
     earthyoutput = new JTextField(8);
     special_edition = new DecimalFormat("00.00");

     control_panel.add(earthspeed);
     control_panel.add(earth_speed_output);
     control_panel.add(start_button);
     control_panel.add(earthx);
     control_panel.add(earthxoutput);
     control_panel.add(earthy);
     control_panel.add(earthyoutput);
     control_panel.add(pause_button);
     control_panel.add(quit_button);
     add(control_panel, BorderLayout.SOUTH);

     //create handler class for all clocks
     clockhandler = new Clockhandlerclass();

     //Set up the earth clock
     earth_clock_delay_interval = (int)Math.round(millisecondpersecond/motion_clock_rate);
     earthclock = new Timer(earth_clock_delay_interval,clockhandler);

     //Set up the refresh clock
     refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/refresh_clock_rate);
     refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

     earthsunpanel.earthsunvalues(earthballcenterx, earthballcentery, sunballcenterx, sunballcentery);  //puts balls in correct places

     setVisible(true);
   } //end of constructor

 private class Buttonhandlerclass implements ActionListener
 {
     public void actionPerformed(ActionEvent event)
     {
       //Convert the speed of the ball from units pix/sec to pix/tic
       earthball_speed_pix_per_second = earth_speed_output.getText();
       earthballspeedpixpersecond = Double.parseDouble(earthball_speed_pix_per_second);//step 1
       earthball_speed_pix_per_tic = earthballspeedpixpersecond/motion_clock_rate;//step 2


       earthdeltat = earthball_speed_pix_per_tic / earthorbitrad; //step 3 deltat done

       //need to comppute the location of the earth each time

       earthsunpanel.valuesneeded(earthdeltat, earthorbitrad); //pass values to other panel

       if(event.getSource() == start_button)
       {
         earthsunpanel.repaint();
         refreshclock.start();  //starts all the clocks
         earthclock.start();
         start_button.setEnabled(false);
         start_button.setLabel("Resume");   //sets the label to resume
         pause_button.setEnabled(true);
         active = true;
       }
       else if (event.getSource() == pause_button)
       {
         refreshclock.stop();
         earthclock.stop();   //stops all the clocks
         start_button.setEnabled(true);
         active = false;
       }
       else if(event.getSource() == quit_button)  //exit
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
     if(event.getSource() == refreshclock)
        {
          earthsunpanel.repaint();
        }
     else if(event.getSource() == earthclock)
        {
          earthsunpanel.earthmoveball();
          u = earthsunpanel.getxcenter_of_ball();
          v = earthsunpanel.getycenter_of_ball();
          earthxoutput.setText(special_edition.format(u));
          earthyoutput.setText(special_edition.format(v));
        }
     else
        System.out.printf("%s\n","There is a bug in one of the clocks.");
   }
 }
}//end of Game_user_interface
