import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * The mgPanel is where the Wa-Tor program will be displayed to the user in a
 * window
 * 
 * @author Max
 */

public class mgPanel extends JPanel {
	private mgPanel pan_;
	private Timer timer_;

	public static void main ( String[] args ) {
		mgPanel pan_ = new mgPanel();
		JFrame window = new JFrame("Wa-Tor");
		window.setContentPane(pan_);
		window.setJMenuBar(pan_.getMenuBar());
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
	} // end of main

	// ___________________INSTANCE VARIABLES____________________________________
	// JPanel used to display the animals
	private animalPanel animalPanel_;
	// JPanel used to display the labels
	private labelPanel labelPanel_;
	// JPanel used to display the buttons
	private buttonPanel buttonPanel_;
	// String Labels
	private String nFish_, nShark_;
	// JLabels
	private JLabel fishL_, sharkL_, grid_, fBreed_, sBreed_, starve_;
	// JButtons
	private JButton startB_, stopB_, restartB_;
	// Size
	private int width_, height_, nFBreed_, nSBreed_, nStarve_;
	// Boolean variables for the state machine of the program
	private boolean start_, stop_, restart_;
	// Boolean variables to test the state of the users input for width and height
	private boolean w_, h_;
	// Font varialbe to be used for buttons and labels in the label and button panels
	private static final Font f1_ = new Font("Garramond",Font.BOLD,8);
	public LinkedList<Shark> sharks = new LinkedList<Shark>();

	/**
	 * This panel is used to cumulatively display all of the components into a
	 * window
	 */
	public mgPanel () {
		// initialize the start/stop/restart buttons to false
		start_ = false;
		stop_ = false;
		restart_ = false;
		// initialize the w & h tests to false;
		w_ = false;
		h_ = false;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(500,500));
		this.setLayout(new BorderLayout());
		// create the three panels that will be displayed to the user
		labelPanel_ = new labelPanel();
		buttonPanel_ = new buttonPanel();
		animalPanel_ = new animalPanel();
		// add the panels to mgPanel
		this.add(labelPanel_,BorderLayout.NORTH);
		this.add(buttonPanel_,BorderLayout.SOUTH);
		this.add(animalPanel_,BorderLayout.CENTER);
	} // end of constructor 
	
	/**
	 * This panel is used to draw labels for the GUI component
	 * 
	 * @author Max
	 */
	public class labelPanel extends JPanel {

		public labelPanel () {
			// Layout
			this.setLayout(new GridLayout(1,7));
			// Background
			this.setBackground(Color.WHITE);
			// Proceed to create JLabels for labelPanel
			// grid size
			grid_ = new JLabel("Grid: " + "Width " + " Height ");
			grid_.setBackground(Color.BLUE);
			grid_.setForeground(Color.WHITE);
			grid_.setHorizontalAlignment(0);
			grid_.setOpaque(true);
			grid_.setFont(f1_);
			// add fish label to left of panel
			this.add(grid_);
			// Number of Fish
			// nBreed_ = fish.size();
			fishL_ = new JLabel("Fish: " + nFish_);
			fishL_.setBackground(Color.BLUE);
			fishL_.setForeground(Color.WHITE);
			fishL_.setHorizontalAlignment(0);
			fishL_.setOpaque(true);
			fishL_.setFont(f1_);
			// add fish label to left of panel
			this.add(fishL_);
			// Number of Sharks
			nSBreed_ = sharks.size();
			sharkL_ = new JLabel("Sharks: " + nShark_);
			sharkL_.setBackground(Color.BLUE);
			sharkL_.setForeground(Color.WHITE);
			sharkL_.setHorizontalAlignment(0);
			sharkL_.setOpaque(true);
			sharkL_.setFont(f1_);
			// add shark label to right of panel
			this.add(sharkL_);

		}// end constructor
	}// end labelPanel

	public void update () {
		timer_.start();

	}

	/**
	 * This is the animalPanel which draws the sharks & fish in the GUI. 
	 * It also draws the grid that the fish & sharks move along &
	 */
	public class animalPanel extends JPanel {

		public animalPanel () {
			this.setBackground(Color.WHITE);
			// set these parameters with width_ and height_ input from user
			this.setPreferredSize(new Dimension(200,200));
			// initialize the sharks to 10
			init(10);
			
		}

		// paint two different types depending on button

		// if empty have what i want

		// paint method to draw a grid
		public void paintComponent ( Graphics g ) {
			super.paintComponent(g);
			// call the draw method to display sharks in the GUI
			// If START & STOP == TRUE draw sharks & fish
			// IF RESTART == TRUE DONT DRAW ANYTHING

			if ( start_  == true ) {
				for ( int i = 0 ; i < sharks.size() ; i++ ) {
					sharks.get(i).drawShark(g);
				}
			}

			// ALWAYS DRAW THE GRID
			g.setColor(Color.BLACK);
			// use width and height to change cells based off JMenuBar
			// nested for loops that draw a grid for the animals to swim in
			// iterate through the width and update the displacement
			for ( int x = 0 ; x <= this.getWidth() ; x = x + 4 ) {
				// iterate through the height and update the displacement
				for ( int y = 0 ; y <= this.getHeight() ; y = y + 4 ) {
					// Horizontal lines
					g.drawLine(0,y,this.getWidth(),y);
					// Vertical lines
					g.drawLine(x,0,x,this.getHeight());
				} // end for
			} // end nested for

			/*
			 * // test drawing a line with random dimensions
			 * g.drawLine(0,0,this.getWidth(),this.getHeight());
			 */

		} // end of paintComponent
	} // end of animalPanel



	/**
	 * @param painter
	 *          - where the shark will be drawn
	 * @param x
	 *          - x coordinate for the drawing
	 * @param y
	 *          - y coordinate for the drawing
	 */
	public void init ( int numShark ) {
		for ( int j = 0 ; j < numShark ; j++ ) {
			int sx = (int) (1 * Math.random() * 100);
			int sy = (int) (1 * Math.random() * 100);
			// test increase third parameter to see more movement
			Shark shark = new Shark(sx,sy,15);
			sharks.add(shark);
		}
	}

	/**
	 * This is the buttonPanel, which is used to display three buttons in the GUI.
	 * They allow the user to start/stop/restart the GUI
	 * @author Max
	 *
	 */
	public class buttonPanel extends JPanel implements ActionListener {

		public buttonPanel () {
			// Layout
			this.setLayout(new GridLayout(1,3));
			// Background
			this.setBackground(Color.YELLOW);
			// create the three buttons
			startB_ = new JButton("Start");
			stopB_ = new JButton("Stop");
			restartB_ = new JButton("Restart");
			// create the start button
			startB_ = new JButton("Start");
			startB_.setBackground(Color.DARK_GRAY);
			startB_.setForeground(Color.BLACK);
			startB_.setOpaque(true);
			startB_.setVisible(true);
			startB_.addActionListener(this);
			this.add(startB_);
			// create the stop button
			stopB_ = new JButton("Stop");
			stopB_.setBackground(Color.DARK_GRAY);
			stopB_.setForeground(Color.BLACK);
			stopB_.setOpaque(true);
			stopB_.setVisible(true);
			// stopB_.addActionListener(this);
			this.add(stopB_);
			// create the restart button
			restartB_ = new JButton("Restart");
			restartB_.setBackground(Color.DARK_GRAY);
			restartB_.setForeground(Color.BLACK);
			restartB_.setOpaque(true);
			restartB_.setVisible(true);
			// restartB.addActionListener(this);
			this.add(restartB_);
		}// end buttonPanel

		/*
		 * (non-Javadoc)
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed ( ActionEvent e ) {
			// TODO Auto-generated method stub
			JButton src = (JButton) e.getSource();
			// String source = e.getActionCommand();
			if ( src == startB_ ) {
			
				// call the init method for shark
				init(10);
				// create timer
				timer_ = new Timer(100,new updateMove());
				// start the timer to move sharks periodically
				timer_.start();
				// set start == true
				start_ = true;
				stop_ = false;
			} // end if
			if ( src == restartB_ ) {
				restart_ = true;
			} // end if
			if ( src == stopB_ ) {
				// call method here to freeze the move method of the fish/sharks
				timer_.stop();
				stop_ = true;
				start_ = false;
			} // end if
		}// end actionPerformed
	}// end of button panel

	public class updateMove implements ActionListener {
		
		public updateMove () {
		// leave empty only actionPerformed needed
		}
	
		/*
		 * (non-Javadoc)
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed ( ActionEvent e ) {
			// TODO Auto-generated method stub
		// either make a method move the sharks periodically or do it here locally
			for ( int i = 0 ; i < sharks.size() ; i++ ) {
				sharks.get(i).move(0,0,100,100,sharks,i);
				sharks.get(i).setStarve(sharks.get(i).getStarve() - 1);
				//animalPanel_.repaint();
				if ( sharks.get(i).getStarve() == 0 ) {
					System.out.println("Shark dies!");
					sharks.get(i).color_ = Color.WHITE;
					sharks.remove(i);
				//animalPanel_.repaint();
				} // end if
				  // System.out.println(sharks.get(i) + " " + sharks.get(i).getsharkX()
				  // +
				  // " " + sharks.get(i).getsharkY());
				
			} // end for
		animalPanel_.repaint();}
	}

	/**
	 * This returns the menubar so that the user can make various selections
	 * 
	 * @return
	 */
	public JMenuBar getMenuBar () {
		JMenuBar menubar = new JMenuBar();
		MenuHandler listener = new MenuHandler();

		JMenu sizeMenu = new JMenu("Size");
		JMenuItem addWidth = new JMenuItem("Add Width...");
		addWidth.addActionListener(listener);
		sizeMenu.add(addWidth);
		JMenuItem addHeight = new JMenuItem("Add Height...");
		addHeight.addActionListener(listener);
		sizeMenu.add(addHeight);
		sizeMenu.addSeparator();
		menubar.add(sizeMenu);

		JMenu feedMenu = new JMenu("Breed");
		JMenuItem addFishB = new JMenuItem("Add Fish Breed...");
		addFishB.addActionListener(listener);
		feedMenu.add(addFishB);

		JMenuItem addSharkB = new JMenuItem("Add Shark Breed...");
		addSharkB.addActionListener(listener);
		feedMenu.add(addSharkB);
		feedMenu.addSeparator();

		menubar.add(feedMenu);
		JMenu starveMenu = new JMenu("Starve");
		JMenuItem addStarve = new JMenuItem("Add Starve...");
		addStarve.addActionListener(listener);
		starveMenu.add(addStarve);
		starveMenu.addSeparator();

		menubar.add(starveMenu);

		return menubar;
	} // end JMenuBar

	/**
	 * @author Max Class that is used to handle the menu options for the user to
	 *         select
	 */
	public class MenuHandler implements ActionListener {
		public void actionPerformed ( ActionEvent evt ) {
			String command = evt.getActionCommand();
			// Tests the the commands in the MenuHandler to check what the user selects
			if ( command.equals("Add Width...") ) {

				String wText =
				    JOptionPane.showInputDialog(mgPanel.this,"Enter the Width.");
				width_ = Integer.parseInt(wText);
				w_ = true;

			} // end if

			if ( command.equals("Add Height...") ) {

				String hText =
				    JOptionPane.showInputDialog(mgPanel.this,"Enter the Height.");

				height_ = Integer.parseInt(hText);
				h_ = true;

			} // end if
		
			
			
			if ( command.equals("Add Fish Breed...") ) {

				String fbText =
				    JOptionPane.showInputDialog(mgPanel.this,"Enter the Fish Breed.");
				nFBreed_ = Integer.parseInt(fbText);

			} // end if 

			if ( command.equals("Add Shark Breed...") ) {

				String sBText =
				    JOptionPane.showInputDialog(mgPanel.this,"Enter the Shark Breed.");

				nSBreed_ = Integer.parseInt(sBText);
			} // end if 

			if ( command.equals("Add Starve...") ) {

				String sText =
				    JOptionPane.showInputDialog(mgPanel.this,"Enter the Shark Breed.");

				nStarve_ = Integer.parseInt(sText);
			}// end if 
		}

		// update method to set the parameters of the JMenuBar

	}
}// end of main
