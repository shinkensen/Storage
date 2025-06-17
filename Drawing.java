import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class Drawing {
    public boolean drawGrid = true;
    public String[] directions;
    public String windowTitle;
    public abstract void drawPerFrame(Graphics2D g2d);
}
