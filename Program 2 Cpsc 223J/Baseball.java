import javax.swing.JFrame;
import java.awt.Dimension;

public class Baseball
{public static void main(String[] args)
    {Diamond_user_interface myframe = new Diamond_user_interface();
     myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myframe.setSize(1700,900);
     myframe.setResizable(false);


     myframe.setVisible(true);
    }//End of main
}
