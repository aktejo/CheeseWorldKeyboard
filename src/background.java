import java.awt.*;

public class background {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public int dy;                    //the speed of the scroll
    public Image pic;

    public background(int pxpos, int pypos, int pdy, Image pPic) {
        dy = pdy;
        height = 1400;
        width = 1000;
        xpos = pxpos;
        ypos = pypos;
        pic = pPic;
    }

    public void scroll() {
        ypos = ypos + dy;

        if (ypos == 0) {
            ypos = -700;
        }
    }
}
