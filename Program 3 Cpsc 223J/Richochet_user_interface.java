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


public class Richochet_user_interface extends JFrame
{
  final int frame_width = 900;
  final int frame_height = 600;

  private String ball_speed_pix_per_second;
  private double ballspeedpixpersecond;// = 88.437;
  private double ball_speed_pix_per_tic;

  private String ballrefreshrate;
  private double ballrefreshdouble;

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
  //ball location Labels
  private JLabel ball_location;
  private JLabel xequ;
  private JLabel yequ;
  private JTextField xlocation;
  private JTextField ylocation;
  //Inputs for the ball
  private JLabel refresh;
  private JTextField refresh_output;
  private JLabel speed;
  private JTextField speed_output;
  private JLabel directiondeg;
  private JTextField direction_output;

  //richochet_panel
  private Richochet_panel richochetpanel;
  private final int richochet_panel_width = frame_width;
  private final int richochet_panel_height = frame_height - title_panel_height - control_panel_height;

  private int act = 1; //starts at the first point

  private DecimalFormat special_edition;
  private Timer refreshclock;
  private Timer motionclock;
  private Buttonhandlerclass buttonhandler;
  private Clockhandlerclass clockhandler;
  // private final double refresh_clock_rate = 120.47;
  private int refresh_clock_delay_interval;
  private final double motion_clock_rate = 99.873;
  private int motion_clock_delay_interval;
  private final double millisecondpersecond = 1000.0;
  private double deltax;
  private double deltay;
  private double u, v;
  private boolean active = false;

  public Richochet_user_interface()
  {
    //Mandatory first statement of a constructor
     super("Program 3");

     //Initialize the frame
     setLayout(new BorderLayout());
     setSize(frame_width,frame_height);
     setResizable(false);
     setVisible(false);

     //make handler for buttons
     buttonhandler = new Buttonhandlerclass();

     //Title panel
     title_panel = new JPanel();
     title_label = new JLabel("Nicholas Ayson CPSC 223J Assignment 3");
     title_panel.setSize(title_panel_width,title_panel_height);
     title_panel.setBackground(java.awt.Color.yellow);
     title_panel.setLayout(new FlowLayout());
     title_panel.add(title_label);
     add(title_panel, BorderLayout.NORTH);    //sets the titlepanel to the top

     //Middle Graphics Panel / richochet_panel
     richochetpanel = new Richochet_panel();
     richochetpanel.setSize(richochet_panel_width, richochet_panel_height);
     add(richochetpanel, BorderLayout.CENTER);      //sets graphics panel to middle

     //Control panel
     control_panel = new JPanel();
     control_panel.setLayout(new FlowLayout());     //set as a flow layout for now to see how it looks
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
     refresh = new JLabel ("Refresh rate(Hz)");
     refresh_output = new JTextField(8);
     speed = new JLabel("Speed(pix/sec)");
     speed_output = new JTextField(8);
     directiondeg = new JLabel("Direction(Degrees)");   //grab direction in degrees but convert to radians
     direction_output = new JTextField(8);
     //location
     ball_location = new JLabel("Ball Location");
     xequ = new JLabel("X = ");
     xlocation = new JTextField(8);
     yequ = new JLabel("Y = ");
     ylocation = new JTextField(8);
     special_edition = new DecimalFormat("00.00");      //displays output in .00
     xlocation.setHorizontalAlignment(JTextField.CENTER);
     ylocation.setHorizontalAlignment(JTextField.CENTER);

     control_panel.add(clear_button);
     control_panel.add(start_button);
     control_panel.add(pause_button);
     control_panel.add(quit_button);
     control_panel.add(ball_location);
     control_panel.add(xequ);
     control_panel.add(xlocation);
     control_panel.add(yequ);
     control_panel.add(ylocation);

     control_panel.add(refresh);
     control_panel.add(refresh_output);
     control_panel.add(speed);
     control_panel.add(speed_output);
     control_panel.add(directiondeg);
     control_panel.add(direction_output);
     add(control_panel, BorderLayout.SOUTH);

     //create handler class for all clocks
     clockhandler = new Clockhandlerclass();

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

       ballrefreshrate = refresh_output.getText();
       ballrefreshdouble = Double.parseDouble(ballrefreshrate);
       //Set up the refresh clock
       refresh_clock_delay_interval = (int)Math.round(millisecondpersecond/ballrefreshdouble);
       refreshclock = new Timer(refresh_clock_delay_interval,clockhandler);

       direction = direction_output.getText();
       directionnum = Double.parseDouble(direction);
       directionnumrad = Math.toRadians(directionnum);


       if(event.getSource() == start_button)
       {
         deltax = ball_speed_pix_per_tic*(Math.cos(directionnumrad));
         deltay = ball_speed_pix_per_tic*(Math.sin(directionnumrad));
         richochetpanel.initializeobjectsinpanel(deltax, deltay, ball_speed_pix_per_tic);
         richochetpanel.repaint();
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
       else if(event.getSource() == clear_button)
       {
         refresh_output.setText("");
         speed_output.setText("");
         direction_output.setText("");
         xlocation.setText("");
         ylocation.setText("");
         richochetpanel.initializeobjectsinpanel(deltax, deltay, ball_speed_pix_per_tic);
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
          richochetpanel.repaint();
        }
     else if(event.getSource() == motionclock)
        {
          animation_continues = richochetpanel.moveball();
          u = richochetpanel.getxcenter_of_ball();
          v = richochetpanel.getycenter_of_ball();
          xlocation.setText(special_edition.format(u));
          ylocation.setText(special_edition.format(v));
          if(!animation_continues)
            {
              motionclock.stop();
              refreshclock.stop();
            }
        }//End of if(event.getSource() == motionclock)
     else
        System.out.printf("%s\n","There is a bug in one of the clocks.");
   }
 }

}//end of Diamond_user_interface
