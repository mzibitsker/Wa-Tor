
/**
 * @author Andrew Chait, Camera Finn, Maxim Zibitsker
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class fish {
	public int counter_;
	// TODO: counter not used
	private int life_; // life / number of moves fish did
	private int x_, y_; // coordinates of fish
	private int fbread_; // chronons needed to bread/ number of moves
	private final int left_ = 0, right_ = 1, down_ = 2;
	// possible directions fish can move in
	private int gridWidth_, gridHeight_; // the dimentions of the world

	/**
	 * TESTING
	 * 
	 * @param args
	 */
	// Testing Class & Methods
//	public static void main ( String[] args ) {
//		LinkedList<fish> fish = new LinkedList<fish>();
//		for ( int i = 0 ; i < 10 ; i++ ) {
//			fish.add(new fish(5,5,2,fish));
//			System.out.println("fish " + i + ": " + fish.get(i).getX() + ", "
//			    + fish.get(i).getY());
//		}
//		System.out.println();
//		for ( int i = 0 ; i < fish.size() ; i++ ) {
//			fish.get(i).move(fish);
//			System.out.println("fish " + i + ": " + fish.get(i).getX() + ", "
//			    + fish.get(i).getY());
//		}
//		System.out.println();
//		for ( int i = 0 ; i < fish.size() ; i++ ) {
//			fish.get(i).move(fish);
//			System.out.println("fish " + i + ": " + fish.get(i).getX() + ", "
//			    + fish.get(i).getY());
//		}
//		System.out.println();
//		for ( int i = 0 ; i < fish.size() ; i++ ) {
//			fish.get(i).move(fish);
//			System.out.println("fish " + i + ": " + fish.get(i).getX() + ", "
//			    + fish.get(i).getY());
//		}
//	}

	public fish ( int gridWidth, int gridHeight, int fbread,
	              LinkedList<fish> fish ) {
		life_ = 0;
		fbread_ = fbread;
		gridWidth_ = gridWidth;
		gridHeight_ = gridHeight;
		while ( true ) {
			// ensures that fish are not over lapping when placed in world
			x_ = (int) (Math.random() * gridWidth_);
			y_ = (int) (Math.random() * gridHeight_);
			if ( spaceAvailable(getX(),getY(),fish) ) {
				break;
			}
		}
	}

	/**
	 * Move method for fish Assuming that grid is increasing from left to right
	 * and from top to bottom, and scale on grid goes by 1; if
	 * there is no open space, then the fish doesn't move. If the fish is moving
	 * past a boarder of the world, it will reappear on the other side.
	 * 
	 * @param fish:
	 *          LinkedList of fish population
	 */
	public void move ( LinkedList<fish> fish ) {
		life_++;
		int direction;
		int i = 0;
		while ( i < 4 ) {
			direction = (int) Math.random() * 4;
			if ( direction == left_ ) {
				if ( spaceAvailable(getX() - 1,getY(),fish) ) {
					x_ -= 1;
					break;
				}
			} else if ( direction == right_ ) {
				if ( spaceAvailable(getX() + 1,getY(),fish) ) {
					x_ += 1;
					break;
				}
			} else if ( direction == down_ ) {
				if ( spaceAvailable(getX(),getY() + 1,fish) ) {
					y_ += 1;
					break;
				}
			} else {
				if ( spaceAvailable(getX(),getY() - 1,fish) ) {
					y_ -= 1; // moving up
					break;
				}
			}
			i++;
		}
		if ( getX() > gridWidth_ ) {
			x_ = 0;
		} else if ( getX() < 0 ) {
			x_ = gridWidth_;
		}
		if ( getY() > gridHeight_ ) {
			y_ = 0;
		} else if ( getY() < 0 ) {
			y_ = gridHeight_;
		}
		// check for reproduction.
		reproduce(fish);
	}

	/**
	 * Checks if the space in question is already occupied by a fish from the
	 * linked list storing the fish population
	 * 
	 * @param x:
	 *          x-coordinate in question
	 * @param y:
	 *          y-coordinate in question
	 * @param fish:
	 *          linked list
	 * @return true if the coordinates in question is vacant, false otherwise.
	 */
	public boolean spaceAvailable ( int x, int y, LinkedList<fish> fish ) {
		for ( int i = 0 ; i < fish.size() ; i++ ) {
			if ( x == fish.get(i).getX() && y == fish.get(i).getY() ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Reproduce method. Checks if it is time for the fish to reproduce by
	 * checking the remainder of the dividing the fish's life by fbread_. Checks
	 * the amount of fish in the world, if the world is full of fish, then the
	 * fish doesn't reproduce.
	 * 
	 * @param fish:
	 *          LinkedList of fish population
	 */
	public void reproduce ( LinkedList<fish> fish ) {
		if ( life_ % fbread_ == 0 && fish.size() <= gridWidth_ * gridHeight_ ) {
			fish.add(new fish(gridWidth_,gridHeight_,fbread_,fish));
		}
	}

	/**
	 * Draws fish at its current location
	 * 
	 * @param g
	 */
	public void draw ( Graphics g ) {
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(),10,10);
	}

	/**
	 * Gets x coordinate of fish.
	 * 
	 * @return
	 */
	public int getX () {
		return x_;
	}

	/**
	 * Gets the y coordinate of fish.
	 * 
	 * @return
	 */
	public int getY () {
		return y_;
	}
}
