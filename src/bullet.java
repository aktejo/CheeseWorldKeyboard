import java.awt.*;

public class bullet {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public boolean hasBeenShot = false;
    public int x;
    public int y;
    public double angle;
    public int bulletDestX;
    public int bulletDestY;
    public int bulletStartingX;
    public int bulletStartingY;

    public bullet(int pXpos, int pYpos, int pDx, int pDy, Image picP) {
        xpos = pXpos;
        ypos = pYpos;
        dx =pDx;
        dy =pDy;
        width = 100;
        height = 100;
        isAlive = false;
        pic = picP;

    } // constructor

    public void move() {
        x = (int)(Math.random()*400);
        y = (int)(Math.random()*400);
        if (xpos < (0-width) || xpos > 1000 || ypos < (0-height) || ypos > 700) {
//                xpos = cannon[i].xpos;
//                ypos = cannon[i].ypos;
//                dx = cannon[i].dx;
//                dy = cannon[i].dy;

            xpos = -100;
            ypos = -100;
            dx = 0;
            dy = 0;
            isAlive = false;

        }
        if (x == y && isAlive == false) {
            xpos = bulletStartingX;
            ypos = bulletStartingY;
            dx = 5;
            dy = 5;
            isAlive = true;
/*            if (user.dx > 0 && user.dy > 0) {
                bulletDestX = user.xpos + 10;
                bulletDestY = user.ypos + 10;
            } else if (user.dx < 0 && user.dy > 0) {
                bulletDestX = user.xpos - 10;
                bulletDestY = user.ypos + 10;
            } else if (user.dx > 0 && user.dy < 0) {
                bulletDestX = user.xpos + 10;
                bulletDestY = user.ypos - 10;
            } else if (user.dx < 0 && user.dy < 0) {
                bulletDestX = user.xpos - 10;
                bulletDestY = user.ypos - 10;
            } else {
                bulletDestX = user.xpos;
                bulletDestY = user.ypos;
            }*/

            //System.out.println("bullet " + i + " is going to " + bulletDestX + "," + bulletDestY);

            System.out.println(x + " and " + y);


            angle = Math.atan2(bulletDestY - ypos, bulletDestX - xpos);
            System.out.println(angle);
            xpos += (int) (dx * Math.cos(angle));
            ypos += (int) (dy * Math.sin(angle));

            System.out.println((int) (dx * Math.cos(angle)) + " and " + (int) (dy * Math.sin(angle)));

/*                dx = (bulletDestX - xpos) / 20;
                dy = (bulletDestY - xpos) / 20;*/


        }
        if (isAlive ==true) {
            xpos += (int) (dx * Math.cos(angle));
            ypos += (int) (dy * Math.sin(angle));
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }

}
