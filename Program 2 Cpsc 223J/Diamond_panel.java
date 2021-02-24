import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;

public class Diamond_panel extends JPanel
{   private double diamond_panel_width;
    private double diamond_panel_height;

    private final double ballradius = 7.0;
    private final double balldiameter = 2.0 * ballradius;
    private double ball_center_x;
    private double ball_center_y;
    private double ball_upper_corner_x;
    private double ball_upper_corner_y;
    private int ball_upper_corner_integer_x;
    private int ball_upper_corner_integer_y;
    private double a1,b1,a2,b2; //double coordinates of end points of line segment.
    private int i1,j1,i2,j2;    //integer coordinates of end points of line segment.
    private double x;
    private double y;
    private double distancetoend;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private boolean showline = false;
    private double temporary;


    public Diamond_panel()  //Constructor
    {
     setVisible(true);
     System.out.println("The constructor of the diamond panel has finished.");
    }//End of constructor

    public void paintComponent(Graphics graphicarea)
    {
      super.paintComponent(graphicarea);
      setBackground(Color.green);
      Graphics2D g2D = (Graphics2D) graphicarea;
      if (showline)
      g2D.drawLine(500, 400, 800, 100);
      g2D.drawLine(800, 100, 1100 , 400);
      g2D.drawLine(1100, 400, 900, 700);
      g2D.drawLine(900, 700, 500, 400);
      g2D.fillOval(ball_upper_corner_integer_x, ball_upper_corner_integer_y,(int)Math.round(balldiameter),(int)Math.round(balldiameter));

    }//end of method paintComponent

    public void initializeobjectsinpanel(double startx, double starty, double endx, double endy, double deltax, double deltay)
    {
      x = deltax;
      y = deltay;
      distance_moved_in_one_tic = Math.sqrt(x*x + y*y);  //Hypotenuse in right triangle with legs δx and δy.
      showline = true;
      ball_center_x = 900; //Ball receives its starting coordinates.
      ball_center_y = 700;
      ball_upper_corner_x = ball_center_x - ballradius;   //Translate from center to upper left corner.
      ball_upper_corner_y = ball_center_y - ballradius;   //Ditto
      ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);  //Round to nearest int
      ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);  //Ditto
      distancetoend
                    = Math.sqrt(Math.pow(ball_center_x - a2,2) + Math.pow(ball_center_y - b2,2));
    }//End of initializeobjectsinpanel

    public boolean moveball()
    {    successfulmove = true;
         if(distancetoend > distance_moved_in_one_tic)
             {//This is the case where the destination is further away than a single step can accomplish.
               ball_center_x += x;
               ball_center_y += y;
             }
         else
             {//This is the case where the ball needs exactly one short hop to reach its destination.
               ball_center_x = a2;
               ball_center_y = b2;
               successfulmove = false;
             }
             ball_upper_corner_x = ball_center_x - ballradius;
             ball_upper_corner_y = ball_center_y - ballradius;
             ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
             ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);
             distancetoend = Math.sqrt(Math.pow(ball_center_x - i2,2) + Math.pow(ball_center_y - j2,2));
         return successfulmove;
    }//End of moveball

    public double getxcenter_of_ball()
    {//If I used one statement "return temp" then the program froze, but if I use a temporary variable then it works.
     temporary = ball_center_x;
     return temporary;
    }

    public double getycenter_of_ball()
    {temporary = ball_center_y;
     return temporary;
    }//End of method getycenter_of_ball
}
