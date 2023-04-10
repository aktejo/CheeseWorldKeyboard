//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class CheeseWorld implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 650;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image cheesePic;
    public Image mousePic;
    public Image tomPic;
    public Image backgroundPic;
    public Image bulletPic;
    public Image cannonpic;
    public Image gempic;
    public Image swordpic;
    public int points = 0;
    public int x;
    public int y;
    public double angle;
    public int bulletDestX;
    public int bulletDestY;

    //Declare the character objects
    public Enemy mouse1;
    public Cheese theCheese;
    public Player user;
    public background background1;
    public bullet[] bullets = new bullet[100];
    public Enemy cannon[];
    public sword sword;
    public gem gem[];

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        CheeseWorld myApp = new CheeseWorld();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public CheeseWorld() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        cheesePic = Toolkit.getDefaultToolkit().getImage("cheese.gif");
        mousePic = Toolkit.getDefaultToolkit().getImage("jerry.gif");
        tomPic = Toolkit.getDefaultToolkit().getImage("tomCat.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage("flag scroll thing.jpeg");
        bulletPic = Toolkit.getDefaultToolkit().getImage("bullet.png");
        cannonpic = Toolkit.getDefaultToolkit().getImage("cannonpic.png");
        swordpic = Toolkit.getDefaultToolkit().getImage("swordslash.png");
        gempic = Toolkit.getDefaultToolkit().getImage("gem.png");


        //create (construct) the objects needed for the game
        mouse1 = new Enemy(700, 600, 4, 4, mousePic);
        theCheese = new Cheese(400, 300, 3, -4, cheesePic);
        user = new Player(250, 250, 0, 0, tomPic);
        background1 = new background(0, -700, 2, backgroundPic);
        sword = new sword(-100, -100, 0, 0, swordpic);

        //Construct and fill bullet array
        bullets = new bullet[20];


        cannon = new Enemy[20];
        for(int i=0; i<cannon.length; i++) {
            cannon[i] = new Enemy((int) (Math.random()*(930)), (int) (Math.random()*-2100), 0, 2, cannonpic);
            bullets[i] = new bullet(100, -100, 0, 0, bulletPic);
        }

        gem = new gem[5];
        for(int i=0; i<gem.length; i++) {
            gem[i] = new gem((int) (Math.random()*(930)), (int) (Math.random()*-2100), 0, 2, gempic);
        }


    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        mouse1.move();
        theCheese.move();
        if (user.isAlive) {
            user.move();
            sword.swordAlive();
        } else if (!user.isAlive) {
            sword.isAlive = false;
        }
        if (sword.isAlive) {
            sword.xpos = user.xpos - 30;
            sword.ypos = user.ypos - 38;
        } else {
            sword.xpos = -100;
            sword.ypos = -100;
        }
        background1.scroll();
        for(int i=0; i<cannon.length; i++) {
            cannon[i].move();
            if (cannon[i].ypos > 700) {
                cannon[i].xpos = (int) (Math.random()*1000);
                cannon[i].ypos = (int) (Math.random()*-2100);
                cannon[i].isAlive = true;
                System.out.println("enemy died, respawned to " + cannon[i].xpos + "," + cannon[i].ypos);
            }
        }
        for (int i=0; i<gem.length; i++) {
            gem[i].move();
            if (gem[i].ypos > 700) {
                gem[i].xpos = (int) (Math.random()*1000);
                gem[i].ypos = (int) (Math.random()*-2100);
            }
        }
        for(int i=0; i< cannon.length; i++) {
/*            x = (int)(Math.random()*500);
            y = (int)(Math.random()*500);
            //if (bullets[i].xpos < (0-bullets[i].width) || bullets[i].xpos > 1000 || bullets[i].ypos < (0-bullets[i].height) || bullets[i].ypos > 700)
            if (bullets[i].xpos < (0) || bullets[i].xpos > 1000 || bullets[i].ypos < (0) || bullets[i].ypos > 700) {
//                bullets[i].xpos = cannon[i].xpos;
//                bullets[i].ypos = cannon[i].ypos;
//                bullets[i].dx = cannon[i].dx;
//                bullets[i].dy = cannon[i].dy;

                bullets[i].xpos = -100;
                bullets[i].ypos = -100;
                bullets[i].dx = 0;
                bullets[i].dy = 0;
                bullets[i].isAlive = false;

            }
            if (x == y && bullets[i].isAlive == false) {
                bullets[i].xpos = cannon[i].xpos;
                bullets[i].ypos = cannon[i].ypos;
                bullets[i].dx = 5;
                bullets[i].dy = 5;
                bullets[i].isAlive = true;
                if (user.dx > 0 && user.dy > 0) {
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
                }

                //System.out.println("bullet " + i + " is going to " + bulletDestX + "," + bulletDestY);

                System.out.println(x + " and " + y);


                angle = Math.atan2(bulletDestY - bullets[i].ypos, bulletDestX - bullets[i].xpos);
                System.out.println(angle);
                bullets[i].xpos += (int) (bullets[i].dx * Math.cos(angle));
                bullets[i].ypos += (int) (bullets[i].dy * Math.sin(angle));

                System.out.println((int) (bullets[i].dx * Math.cos(angle)) + " and " + (int) (bullets[i].dy * Math.sin(angle)));

//                bullets[i].dx = (bulletDestX - bullets[i].xpos) / 20;
//                bullets[i].dy = (bulletDestY - bullets[i].xpos) / 20;


            }
            if (bullets[i].isAlive ==true) {
                bullets[i].xpos += (int) (bullets[i].dx * Math.cos(angle));
                bullets[i].ypos += (int) (bullets[i].dy * Math.sin(angle));
            }*/
            if (user.dx > 0 && user.dy > 0) {
                bullets[i].bulletDestX = user.xpos +(user.width/2) + user.width;
                bullets[i].bulletDestY = user.ypos+(user.height/2) + user.height;
            } else if (user.dx < 0 && user.dy > 0) {
                bullets[i].bulletDestX = user.xpos - user.width;
                bullets[i].bulletDestY = user.ypos+(user.height/2) + user.height;
            } else if (user.dx > 0 && user.dy < 0) {
                bullets[i].bulletDestX = user.xpos+(user.width/2) + user.width;
                bullets[i].bulletDestY = user.ypos - user.height;
            } else if (user.dx < 0 && user.dy < 0) {
                bullets[i].bulletDestX = user.xpos - user.width;
                bullets[i].bulletDestY = user.ypos - user.height;
            } else {
                bullets[i].bulletDestX = user.xpos+(user.width/2);
                bullets[i].bulletDestY = user.ypos+(user.height/2);
            }
            bullets[i].bulletStartingX = cannon[i].xpos+30;
            bullets[i].bulletStartingY = cannon[i].ypos-20;
            bullets[i].move();

        }
    }

    public void checkIntersections() {
        for(int i=0; i< cannon.length; i++) {
            if (user.rec.intersects(bullets[i].rec)) {
                user.isAlive = false;
            }
            if (user.rec.intersects(cannon[i].rec)) {
                user.isAlive = false;
            }
            if (cannon[i].rec.intersects(sword.rec)) {
                cannon[i].isAlive = false;
                cannon[i].ypos = 1000;
            }
        }
        for(int i=0; i< gem.length; i++) {
            if (gem[i].rec.intersects(user.rec)) {
                gem[i].isAlive = false;
                gem[i].ypos = 1000;
                points ++;
            }
        }
    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 25));


        //draw characters to the screen
        g.drawImage(background1.pic, background1.xpos, background1.ypos, background1.width, background1.height, null);
        if (mouse1.isAlive == true) {
            g.drawImage(mouse1.pic, mouse1.xpos, mouse1.ypos, mouse1.width, mouse1.height, null);
        }
        //g.drawImage(theCheese.pic, theCheese.xpos, theCheese.ypos, theCheese.width, theCheese.height, null);
        if(user.isAlive == true) {
            g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);
        }

        for(int i=0; i<gem.length; i++) {
            if(gem[i].isAlive == true) {
                g.drawImage(gempic, gem[i].xpos, gem[i].ypos, gem[i].width, gem[i].height, null);
            }
        }

        for(int i=0; i<cannon.length; i++) {
            if(cannon[i].isAlive == true) {
                g.drawImage(cannonpic, cannon[i].xpos, cannon[i].ypos, cannon[i].width, cannon[i].height, null);
            }
        }

        for(int i=0; i<cannon.length; i++) {
            if(bullets[i].isAlive == true) {
                g.drawImage(bulletPic, bullets[i].xpos, bullets[i].ypos, bullets[i].width, bullets[i].height, null);
            }
        }
        if(sword.isAlive == true) {
            g.drawImage(sword.pic, sword.xpos, sword.ypos, sword.width, sword.height, null);
        }

        if (user.isAlive) {
            g.drawString("Score: " + points, 20, 30);
        }

        if (!user.isAlive) {
            g.setFont(new Font("SansSerif", Font.BOLD, 50));
            g.drawString("GAME OVER", 350, 200);
            g.drawString("Score: " + points, 400, 275);
        }

        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68) { // d
            user.right = true;
        }
        if (keyCode == 39) { // right arrow
            mouse1.right = true;
        }
        if (keyCode == 65) { // a
            user.left = true;
        }
        if (keyCode == 37) { // left arrow
            mouse1.left = true;
        }
        if (keyCode == 83) { // s
            user.down = true;
        }
        if (keyCode == 40) { // down arrow
            mouse1.down = true;
        }
        if (keyCode == 87) { // w
            user.up = true;
        }
        if (keyCode == 38) { // up arrow
            mouse1.up = true;
        }
        if (keyCode == 32) { // spacebar
            sword.space = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;
        }
        if (keyCode == 39) { // right arrow
            mouse1.right = false;
        }
        if (keyCode == 65) { // a
            user.left = false;
        }
        if (keyCode == 37) { // left arrow
            mouse1.left = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
        }
        if (keyCode == 40) { // down arrow
            mouse1.down = false;
        }
        if (keyCode == 87) { // w
            user.up = false;
        }
        if (keyCode == 38) { // up arrow
            mouse1.up = false;
        }
        if (keyCode == 32) { // spacebar
            sword.space = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class
