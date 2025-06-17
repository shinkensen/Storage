import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.HashMap;

public class Methods
{
    /**********************************************
     ******** Methods to use in your code *********
     **********************************************/
    /*
     * List of methods
     *    
     *      drawButtonPressed(Graphics g, int width, int height,int[] mouse)
     *      drawGrid(Graphics g, int width, int height)
     */
     
     
        
    //This shows all the buttons being pressed on the keyboard, mouse, and mouse position
    public static void drawButtonPressed(Graphics2D g2d, int width, int height,int mouseX, int mouseY)
    {
        //space
        
        
    }
    //This draws the grid on the screen.  It is in the student file.
    public static void drawGrid(Graphics2D g2d, int width, int height)
        {
            g2d.setStroke(new BasicStroke(1));
            g2d.setFont(new Font("Helvetica", Font.PLAIN, 10));
            g2d.setColor(new Color(75,75,75));
            
            for (int x = 0; x<=width; x+= 20)
            {
                g2d.setColor(new Color(75,75,75));
                g2d.drawLine(x, 0, x, 600); 
                g2d.rotate(Math.toRadians(90),x,0);
                g2d.setColor(new Color(175,175,175));
                g2d.drawString(""+x,x,-1);
                g2d.rotate(Math.toRadians(-90),x,0);
            }
            for (int y = 0; y<=height; y+= 20)
            {
                g2d.setColor(new Color(75,75,75));
                g2d.drawLine(0,y,800,y); 
                g2d.setColor(new Color(175,175,175));
                g2d.drawString(""+y,1,y-1);
            }
            g2d.setColor(new Color(175,175,175));
            for (int x = 0; x<=800; x+= 100)
                g2d.drawLine(x, 0, x, 600);
            for (int y = 0; y<=600; y+= 100)
                g2d.drawLine(0, y, 800, y); 
        }
}
