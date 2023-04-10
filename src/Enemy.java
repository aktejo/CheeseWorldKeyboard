

import java.awt.*;

	public class Enemy {

		//VARIABLE DECLARATION SECTION
		//Here's where you state which variables you are going to use.

		public int xpos;                //the x position
		public int ypos;                //the y position
		public int width;
		public int height;
		public boolean isAlive = true;            //a boolean to denote if the hero is alive or dead.
		public int dx;                    //the speed of the hero in the x direction
		public int dy;                    //the speed of the hero in the y direction
		public Rectangle rec;
		public Image pic;
		public int hits;
		//movement booleans
		public boolean right;
		public boolean left;
		public boolean down;
		public boolean up;

		public int speed = 5;

		// METHOD DEFINITION SECTION

		//This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
		// if you put in a String, an int and an int the program will use this constructor instead of the one above.
		public Enemy(int pXpos, int pYpos) {

			xpos = pXpos;
			ypos = pYpos;
			width = 70;
			height = 70;
			dx = 5;
			dy = -5;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);


		} // constructor


		public Enemy(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

			xpos = pXpos;
			ypos = pYpos;
			width = 150;
			height = 150;
			dx = dxParameter;
			dy = dyParameter;
			pic = picParameter;
			isAlive = true;
			hits = 0;
			rec = new Rectangle(xpos, ypos, width, height);

		} // constructor


		//The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
		public void move() {


			xpos = xpos + dx;
			ypos = ypos + dy;


/*
			if (xpos > 1000 - width || xpos < 0) {
				dx = -dx;
			}

			if (ypos < 0 || ypos + height > 700) {
				dy = -dy;
			}
*/

/*
			if(right == true){
				dx = speed;
			} else if (left == true) {
				dx = -speed;
			} else { // (right == false && left == false)
				dx = 0;
			}

			if(down == true){
				dy = speed;
			} else if (up == true) {
				dy = -speed;
			} else {
				dy = 0;
			}

			if(xpos>1000-width){ // right
				xpos = 1000-width;
			}
			if(xpos < 0) { // left
				xpos = 0;
			}
			if(ypos>650-height){ // down
				ypos = 650-height;
			}
			if(ypos < 0) { // up
				ypos = 0;
			}
*/
			rec = new Rectangle(xpos, ypos, width, height);

		}


	} //end of the Mouse object class  definition
