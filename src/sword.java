import java.awt.*;
import java.awt.geom.Ellipse2D;

public class sword {



    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Ellipse2D ell;
    public Image pic;

    //movement booleans
    public boolean space;


    public int speed;

    public sword(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 150;
        height = 150;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        ell = new Ellipse2D.Double(xpos + (width/2), ypos + (height/2), width, height);

    } // constructor

    public void swordAlive() {
        if (space) {
            isAlive = true;
        }
        if (!space) {
            isAlive = false;
        }
        rec = new Rectangle(xpos, ypos, width, height);
        ell = new Ellipse2D.Double(xpos + (width/2), ypos + (height/2), width, height);
    }
}
