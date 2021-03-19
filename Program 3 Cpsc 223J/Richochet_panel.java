import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class Richochet_panel extends JPanel
{   private double richochet_panel_width;
    private double richochet_panel_height;

    private final double ballradius = 7.0;
    private final double balldiameter = 2.0 * ballradius;
    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x = 900;
    private int ball_upper_corner_integer_y = 490;

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
      graphicarea.fillOval(ball_upper_corner_integer_x,ball_upper_corner_integer_y,(int)Math.round(balldiameter),(int)Math.round(balldiameter));

    }//end of method paintComponent

    public void initializeobjectsinpanel(double deltax, double deltay, double ball_speed_pix_per_tic)
    {

      x = deltax;
      y = deltay;
      ballspeed = ball_speed_pix_per_tic;
      distance_moved_in_one_tic = Math.sqrt(x*x + y*y);
      ball_center_x = 900.0; //Ball receives its starting coordinates.
      ball_center_y = 490.0;
      ball_upper_corner_x = ball_center_x - ballradius;   //Translate from center to upper left corner.
      ball_upper_corner_y = ball_center_y - ballradius;   //Ditto
      ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);  //Round to nearest int
      ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);  //Ditto
    }//End of initializeobjectsinpanel


    public boolean moveball() //function that moves the ball
    {
      successfulmove = true;
      dtop = ball_center_y - ballradius;
      dbottom = richochet_panel_height - ball_center_y - ballradius;
      dleft = ball_center_x - ballradius;
      dright = richochet_panel_width - ball_center_x - ballradius;
      ball_center_x = ball_center_x + x;
      ball_center_y = ball_center_y + y;
      if(dright < ballspeed)
      {
        ball_center_x = richochet_panel_width - ballradius;
        x = -x;
      }
      if(dtop < ballspeed)
      {
        ball_center_y = ballradius;
        y = -y;
      }
      if(dleft < ballspeed)
      {
        ball_center_x = ballradius;
        x = -x;
      }
      if(dbottom < ballspeed)
      {
        ball_center_y = richochet_panel_height - ballradius;
        y = -y;
      }
      ball_upper_corner_x = ball_center_x - ballradius;
      ball_upper_corner_y = ball_center_y - ballradius;
      ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
      ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
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
