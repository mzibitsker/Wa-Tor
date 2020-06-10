import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * @author Andrew Chait, Camera Finn, Maxim Zibitsker
 *
 */
public class Shark {
	int x_;
	int y_;
	int starve_;
	Color color_;
	
	/**
	 * Creates a new shark object
	 * @param xIn_ = the sharks origin x input, should be random
	 * @param yIn_ = the sharks origin y input, should be random
	 * @param starve 
	 */
	public Shark ( int x, int y, int starve) {
	super();
	this.x_ = x;
	this.y_ = y;
	this.starve_ = starve;
	this.color_ = Color.BLUE;
	}
	
	/**
	 * Gets the current X position of a shark
	 * @return the X position of the shark
	 */
	public int getsharkX() {
		return this.x_;
	}
	/**
	 * Gets the current Y position of the shark
	 * @return the Y position of the shark
	 */
	public int getsharkY() {
		return this.y_;
	}
	/**
	 * Changes the x_ and y_ variables for a shark, moving it on the GUI
	 * @param shark - the shark object being moved
	 * @param top - measure to make sure doesn't leave screen, should be 0
	 * @param left - measure to make sure doesn't leave screen, should be 0
	 * @param height - height of the landscape the sharks are drawn on
	 * @param width - width of the landscape the sharks are drawn on
	 * @param sharks - linked list containing all sharks
	 * @param sharkNum - the location of the shark in the linked list being moved
	 */
	public void move ( int top, int left, int height, int width, LinkedList<Shark> sharks, int sharkNum ) {
		if(sharks.size() == 0) {
			return;
		}
		int x = this.getsharkX();
		int y = this.getsharkY();
		int moveY = (int)(0*Math.random());
		int moveX = (int)(0*Math.random());
		moveX=moveX*this.getRandomMinus();
		moveY=moveY*this.getRandomMinus();
		x=x+moveX;
		y=y+moveY;
		if(x<left){
			x = width - 5;
		}
		if(x>width){
			x=left+5;
		}
		if(y<top){
			y=top - 5;
		}
		if(y>height){
			y = height + 5;
		}
		posCheck(x, y, sharks, sharkNum);
		//starve_ = starve_ - 1;
		//if(starve_ == 0) { //checks to see if a shark is dead
			//sharks.remove(sharkNum);
			//System.out.println("Shark " + sharkNum + " has died.");
		//}
			}
	/**
	 * Determines if the respective movement (X/Y) is in the positive
	 * or negative direction
	 * @return either 1 to keep it positive or -1 to change to negative
	 */
	private int getRandomMinus () {
if(Math.random()> .5){
	return -1;
} else {
	return 1;
}
	}
	/**
	 * Checks to make sure a shark's new x position doesn't overlap an existing sharks x position
	 * @param x - the x position being compared
	 * @param sharks - the linked list to compare to
	 * @param sharkNum - the location in the linked list to exclude when comparing
	 * @return - the same x value (if no overlap) or a new one
	 */
	private void posCheck(int x, int y, LinkedList<Shark> sharks, int sharkNum) {
		for(int i = 0; i < sharks.size(); i++) {
			if(i != sharkNum) {
			if(x == (sharks.get(i).getsharkX()) && (y == (sharks.get(i).getsharkY()))) {
				if(x == (sharks.get(i).getsharkX())){
				x = x + 5;
				} else {
					y = y + 5;
				}
				posCheck(x, y, sharks, sharkNum);
			}
			}
		}
		sharks.get(sharkNum).setSharkX(x);
		sharks.get(sharkNum).setSharkY(y);
	}
	private void sReproduce(Shark shark) {
		int newx = shark.getsharkX();
		int newy = shark.getsharkY();
		int starve = shark.getStarve();
		Shark nshark = new Shark(newx, newy, starve);
	}

	public int getStarve() {
		// TODO Auto-generated method stub
		return starve_;
	}
	public void setSharkX(int x) {
		this.x_ = x;
	}
	public void setSharkY(int y) {
		this.y_ = y;
	}

	public void setStarve(int i) {
		this.starve_ = i;
		
	}
	public void drawShark(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(this.getsharkX(),this.getsharkY(),5,5);
	}
}
