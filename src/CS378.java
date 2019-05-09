
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class CS378 extends KeyAdapter{
		//default game resolutions
	public static int[] xRes = {1920, 1366, 1280, 1024};
	public static int[] yRes = {1080, 768, 720, 576};
	public static int res = 3;
	Res r = new Res(xRes[res], yRes[res]);
	private static boolean fullscreen = false;
	
		//variables used for maintaining game speed
	final static double frameRate = 60.0;
	final static double skipTicks = 1000.0/frameRate;
	
		//string used for referencing assets in project directory
	public static String curdir = System.getProperty("user.dir");
	
	private static JFrame frame;
	private static JLayeredPane layeredPane = new JLayeredPane();
	private static JPanel background = new JPanel();
	private static JPanel UIPanel = new JPanel();
	private static JLabel backgroundLabel = new JLabel();
	private static UIObject healthUI = new UIObject();
	private static UIObject compassUI = new UIObject();
	private static Map<Integer, Room> gameMap = new HashMap<>();
	
		//used for keeping track of player and movement of player
	public static Player p = new Player("Kyle Jay",100,12);
	public static ClickableObject player = new ClickableObject(p);
	public static Point playerDest = new Point(player.getLocation());

		//test room stuff
	static Room currentRoom = new Room("Cool_City","DemoRoom2.png", 57, 0, 0, 0, 0);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(3);
				
				try {
					CS378 window = new CS378();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Runnable updatePlayerLocation = new Runnable() {
					@Override
					public void run() {
							//calculate distance between player and destination
						double diffX = playerDest.getX() - player.getX();
						double diffY = playerDest.getY() - player.getY();
						
							//only bother moving if the player isn't already there
						if(Math.hypot(diffX, diffY) > player.getWidth() / 2) {
							p.setHasDestination(true);
						}
							//moves player towards destination if member variable hasDestination is true
						player.setLocation(player.updateLocation(new Point(player.getLocation()),playerDest));
						
					}
				};
				
				Runnable updateEntityLocations = new Runnable() {
					@Override
					public void run() {
							//calculate distance between player and destination
						for ( ClickableObject i : currentRoom.getObjects()) {
							
							//double diffX = playerDest.getX() - player.getX();
							//double diffY = playerDest.getY() - player.getY();
							double diffX = -(i.getX());
							double diffY = -(i.getY());
							
							if (i.getObject() instanceof Enemy) {
								diffX += (player.getX() + player.getWidth()/2);
								diffY += (player.getY() + player.getHeight()/2);
							}
							
							if(Math.hypot(diffX, diffY) > i.getWidth() / 2) {
								p.setHasDestination(true);
							}
						}
						
						
							//only bother moving if the player isn't already there
						
							//moves player towards destination if member variable hasDestination is true
						player.setLocation(player.updateLocation(new Point(player.getLocation()),playerDest));
						
					}
				};
				
				Runnable redraw = new Runnable() {	//quite possibly useless, but were gonna leave it anyway
					@Override
					public void run() {
						frame.revalidate();
						frame.repaint();
					}
				};
				
					//launch all threads 60 times a second as dictated by variable "framerate"
				scheduledPool.scheduleWithFixedDelay(updatePlayerLocation, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
				scheduledPool.scheduleWithFixedDelay(updateEntityLocations, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
				scheduledPool.scheduleWithFixedDelay(redraw, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CS378() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			//create window
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Florida Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(true);
		}
		
		
		layeredPane.setPreferredSize(new Dimension(Res.x, Res.y));
		frame.getContentPane().add(layeredPane, BorderLayout.NORTH);
		
			//panel for displaying background image
		
		layeredPane.add(background);
		background.setBounds(0, 0, Res.x, Res.y);
		background.setBackground(new Color(33,33,33));
		background.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			//background using JLabel to hold image
		backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/sprites/testbkg.jpg"),Res.x,Res.y));
		backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/sprites/" + currentRoom.getBackground()),Res.x,Res.y));
		background.add(backgroundLabel);
		
			//interactive panel for user interface (health, inventory, navigation, clickableObjects, etc)
		
		UIPanel.setOpaque(false);
		layeredPane.setLayer(UIPanel, 1);
		UIPanel.setBounds(0, 0, Res.x, Res.y);
		layeredPane.add(UIPanel);
		UIPanel.setLayout(null);
		
			//define UI elements
				//HP monitor
		healthUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/heart.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		healthUI.setBounds(0.05, 6.8 / 8.0, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(healthUI);
				//Compass for navigation
		compassUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/sprites/compassrose.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		compassUI.setBounds((Res.x / 2) - ((int)(Res.y/8.0) / 2), (int)(6.8 * Res.y / 8.0), (int)(Res.y/8.0), (int)(Res.y/8.0));
		UIPanel.add(compassUI);
		
		//test code
		ClickableObject heart = new ClickableObject(new Item("heart","issa heart",5,789));
		heart.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/heart.png"),(int)(Res.y/16.0), (int)(Res.y/16.0)));
		heart.setBounds(0.5, 0.5, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(heart);
		
		ClickableObject sheriff = new ClickableObject(new QuestGiver("Sheriff",100,20));
		sheriff.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/sheriff.png"),(int)(Res.y/16.0), (int)(Res.y/8.0)));
		sheriff.setBounds(0.6, 0.6, 1.0 / 28.4, 1.0 / 8.0);
		UIPanel.add(sheriff);
		
		
		player.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/player.png"),(int)(Res.y/16.0), (int)(Res.y/8.0)));
		player.setBounds(0.4, 0.4, 1.0 / 28.4, 1.0 / 8.0);
		UIPanel.add(player);
		playerDest = player.getLocation();
		
			//mouse click listener to set destinations for player to go to
		UIPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerDest.setLocation(new Point(e.getX() - player.getWidth()/2, e.getY() - player.getHeight()/2));			}
		});
			//temporary solution to exit game by pressing the '1' key
		Action quit= new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		};
		UIPanel.getInputMap().put(KeyStroke.getKeyStroke("1"),"pressed1");
		UIPanel.getActionMap().put("pressed1",quit);
		
		frame.pack();
	}
	
		//method resizeIcon takes ImageIcon object and preferred size and scales it using smooth scaling
	static ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img); 
	}
	
	static void loadRoom(int ID) {
		//unload assets of current room
		//load new assets (currentRoom = whatever(ID));
	}
	
	private static void quitGame() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}
	
}
