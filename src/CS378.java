import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CS378 extends KeyAdapter{
		//default game resolutions
	public static int[] xRes = {1920, 1280, 1024};
	public static int[] yRes = {1080, 720, 576};
	public static int res = 2;
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
	
	public static Player p = new Player("Kyle Jay",100,12);
	static ClickableObject player = new ClickableObject(p);
	static Point playerDest = new Point(player.getLocation());
	static int dx;
	static int dy;

	static //test room stuff
	Room currentRoom = new Room("Cool City","DemoRoom2.png", 57, 0, 0, 0, 0);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
				
				try {
					CS378 window = new CS378();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Runnable updateEntityLocations = new Runnable() {
					@Override
					public void run() {
						double diffX = playerDest.getX() - player.getX();
						double diffY = playerDest.getY() - player.getY();
						
						if(Math.hypot(diffX, diffY) > player.getWidth() / 2) {
							p.setHasDestination(true);
						}
						player.setLocation(player.updateLocation(new Point(player.getLocation()),playerDest));
						//System.out.println(p.hasDestination);
						//player.setLocation(p.getX(), p.getY());
					}
				};
				
				Runnable redraw = new Runnable() {
					@Override
					public void run() {
						
						frame.revalidate();
						frame.repaint();
					}
				};
				
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
		frame.setTitle("Untitled");
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
		backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/testbkg.jpg"),Res.x,Res.y));
		backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/sprites/" + currentRoom.getBackground()),Res.x,Res.y));
		background.add(backgroundLabel);
		
			//interactive panel for user interface (health, inventory, navigation, clickableObjects, etc)
		
		UIPanel.setOpaque(false);
		layeredPane.setLayer(UIPanel, 1);
		UIPanel.setBounds(0, 0, Res.x, Res.y);
		layeredPane.add(UIPanel);
		UIPanel.setLayout(null);
		
		
		healthUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/heart.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		healthUI.setBounds(0.05, 6.8 / 8.0, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(healthUI);
		
		
		compassUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/compassrose.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		compassUI.setBounds((Res.x / 2) - ((int)(Res.y/8.0) / 2), (int)(6.8 * Res.y / 8.0), (int)(Res.y/8.0), (int)(Res.y/8.0));
		UIPanel.add(compassUI);
		
		//test code
		ClickableObject heart = new ClickableObject(new Item("heart","issa heart",5,789));
		heart.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/heart.png"),(int)(Res.y/16.0), (int)(Res.y/16.0)));
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
		
		UIPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerDest.setLocation(new Point(e.getX() - player.getWidth()/2, e.getY() - player.getHeight()/2));
				//System.out.println("clicked location: " + playerDestination);
			}
		});
		
		frame.pack();
	}
	
		//method resizeIcon takes ImageIcon object and preferred size and scales it using smooth scaling
	private static ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img); 
	}
	
	
}
