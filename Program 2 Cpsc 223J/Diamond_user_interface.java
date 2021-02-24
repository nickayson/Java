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
  final int frame_height = 500;

  private double ball_speed_pix_per_second;    //You can manually change the speed and re-compile the program.
  private double ball_speed_pix_per_tic;

  //title label objects
  private JPanel title_panel;
  private final int title_panel_width = frame_width;
  private final int title_panel_height = 50;
  private JLabel title_label;

  //Declare a control panel
  private JPanel control_panel;
  private final int control_panel_width = frame_width;   //pixels
  private final int control_panel_height = 100;
  private JButton start_button;
  private JButton pause_button;
  private JLabel speed;
  private JButton quit_button;
  private JTextField speed_output;

  //diamond_panel
  private Diamond_panel diamondpanel;
  private final int diamond_panel_width = frame_width;
  private final int diamond_panel_height = frame_height - title_panel_height - control_panel_height;

  //Declare the line segment properties.  [Notice how we use type double whenever possible.
  //There are a few occasions when you cannot avoid type int.
  private String startxstring;
  private double startxnumeric;
  private String startystring;
  private double startynumeric;
  private String endxstring;
  private double endxnumeric;
  private String endystring;
  private double endynumeric;
  private double length_line_segment;

  private Timer refreshclock;
  private Timer motionclock;
  private Buttonhandlerclass buttonhandler;
  private Clockhandlerclass clockhandler;
  private final double refresh_clock_rate = 120.47;   //Hz.  This is often called frames per second.
  private int refresh_clock_delay_interval;          //This value will be computed
  private final double motion_clock_rate = 99.873;   //Hz.  How many times the moving object will update its position in each second.
  private int motion_clock_delay_interval;           //This number will be computed by the constructor
  private final double millisecondpersecond = 1000.0;
  private double deltax;                                 //Unit of incremental change in coordinates.
  private double deltay;                                 //Unit of incremental change in coordinates.
  private double u, v;                               //Two temporary variables for holding xy coordinates.
  private boolean active = false;                    //True = program running; False = program stopped.

  public Diamond_user_interface()
  {
    //Mandatory first statement of a constructor
     super("Program 2");

     //Initialize the frame
     setLayout(new BorderLayout());
     setSize(frame_width,frame_height);
     setResizable(false);
     setVisible(false);

     //Convert the speed of the ball from units pix/sec to pix/tic
     ball_speed_pix_per_tic = ball_speed_pix_per_second/motion_clock_rate;

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
     start_button.addActionListener(buttonhandler);
     pause_button = new JButton("Pause");
     pause_button.setEnabled(false);
     pause_button.addActionListener(buttonhandler);
     speed = new JLabel("Speed(pix/sec)");
     speed_output = new JTextField(8);
     ball_speed_pix_per_tic.getText(speed_output);
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
       if(event.getSource() == start_button )
       {
         refreshclock.start();
         motionclock.start();
         deltax = ball_speed_pix_per_tic*(endxnumeric - startxnumeric)/length_line_segment;
         deltay = ball_speed_pix_per_tic*(endynumeric - startynumeric)/length_line_segment;
         diamondpanel.repaint();
         pause_button.setEnabled(true);
       }
       else if (event.getSource() == pause_button)
       {
         refreshclock.stop();
         motionclock.stop();
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
              motionclock.stop();
              refreshclock.stop();
            }
        }//End of if(event.getSource() == motionclock)
     else
        System.out.printf("%s\n","There is a bug in one of the clocks.");
   }
 }

}//end of Diamond_user_interface
