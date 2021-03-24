import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class Richochet_panel extends JPanel
{   private final int richochet_panel_width = 900;
    private final int richochet_panel_height = 450;

    private final double ballradius = 7.0;
    private final double balldiameter = 2.0 * ballradius;
    private double ball_center_x = 900;
    private double ball_center_y = 450;

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

      //for east side to move to touch panel
      if(richochet_panel_width - ball_center_x - ballradius < x)
      {
        ball_center_x = richochet_panel_width - ballradius;
      }
      else if (ball_center_x + ballradius > richochet_panel_width)
      {
        x = -x;
      }

      //for south side to touch panel
      else if(richochet_panel_height - ball_center_y - ballradius < y)
      {
        ball_center_y = richochet_panel_height - ballradius;
      }
      //if the ball center touches the bottom of panel change direction
      else if (ball_center_y + ballradius > richochet_panel_height)
      {
        y = -y;
      }

      //north wall to touch panel
      else if (ball_center_y - ballradius < y)
      {
        ball_center_y = ballradius;
      }
      //if the ball center touches the top of panel change direction
      else if (ball_center_y - ballradius <= 0)
      {
        y = -y;
      }

      //west wall to touchpanel
      else if(ball_center_x - ballradius < x)
      {
        ball_center_x = ballradius;
      }
      //if the ball center touches the left of panel change direction
      else if (ball_center_x - ballradius <= 0)
      {
        x = -x;
      }
      else{successfulmove = false;}

      //move ball
      ball_center_x = ball_center_x + x;
      ball_center_y = ball_center_y + y;

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
