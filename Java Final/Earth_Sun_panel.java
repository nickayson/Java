// #Author: Nicholas Ayson
// #Program: Sun and Earth
// Email: nick.ayson@csu.fullerton.edu

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.text.DecimalFormat;
import java.lang.reflect.*;
import java.lang.*;

public class Earth_Sun_panel extends JPanel
{   private final int graphic_panel_width = 1800;
    private final int graphic_panel_height = 870;

    private final double earthballradius = 7.0;
    private final double earthballdiameter = 14.0;
    private double earthball_center_x;
    private double earthball_center_y;

    private final double sunballradius = 40.0;
    private final double sunballdiameter = 2.0 * sunballradius;
    private double sunball_center_x;
    private double sunball_center_y;

    private double deltax;                                 //Unit of incremental change in coordinates.
    private double deltay;                                 //Unit of incremental change in coordinates.

    private double R;

    private double a = 2.0;

    private double edt;
    private double distance_center_of_ball_2_end_of_line_segment;
    private double distance_moved_in_one_tic;
    private boolean successfulmove = true;
    private double temporary;

    private double t = 0.0;

    public Earth_Sun_panel()  //Constructor
    {
     setVisible(true);
     System.out.println("The constructor of the richochet panel has finished.");
    }//End of constructor

    public void paintComponent(Graphics graphicarea)
    {
      super.paintComponent(graphicarea);
      setBackground(Color.gray);
      //draw earthball
      graphicarea.setColor(Color.blue);
      graphicarea.fillOval((int)Math.round(earthball_center_x - earthballradius),(int)(earthball_center_y - earthballradius),(int)(earthballdiameter),(int)(earthballdiameter));
      //draw sunball
      graphicarea.setColor(Color.yellow);
      graphicarea.fillOval((int)(sunball_center_x - sunballradius), (int)(sunball_center_y - sunballradius),(int)(sunballdiameter),(int)(sunballdiameter));
    }//end of method paintComponent

    public void earthsunvalues(double earthballcenterx, double earthballcentery, double sunballcenterx, double sunballcentery)
    {
      earthball_center_x = earthballcenterx;
      earthball_center_y = earthballcentery;
      sunball_center_x = sunballcenterx;
      sunball_center_y = sunballcentery;
    }

    public void valuesneeded(double earthdeltat, double earthorbitrad)
    {
      edt = earthdeltat;
      R = earthorbitrad;
    }//End of initializeobjectsinpanel


    public void earthmoveball() //function that moves the ball
    {
      t = t + edt;   //t + deltat
      earthball_center_x = sunball_center_x + (Math.cos(t) * R);
      earthball_center_y = sunball_center_y - (Math.sin(t) * R);

      repaint();
    }//End of moveball earth


    public double getxcenter_of_ball()
    {
     temporary = earthball_center_x;
     return temporary;
    }

    public double getycenter_of_ball()
    {
      temporary = earthball_center_y;
     return temporary;
    }//End of method getycenter_of_ball

}
