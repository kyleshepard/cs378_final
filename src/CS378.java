
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.*;


public class CS378{
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
	public static JPanel menu = new JPanel();
	private static JPanel background = new JPanel();
	public static JPanel UIPanel = new JPanel();
	public static JPanel gamePanel = new JPanel();	//implement later and separate from UIPanel
	private static JLabel backgroundLabel = new JLabel();
	
	
	public static Menu DialogueBox = new Menu();
	public static Menu FinishQuest = new Menu();
	private static Menu mainMenu = new Menu();
	private static Menu pauseMenu = new Menu();  
	
	private static UIObject healthUI = new UIObject();
	private static UIObject compassUI = new UIObject();
	private static ClickableObject pause = new ClickableObject("");
	private static Map<Integer, Room> gameMap = new HashMap<>();				//map ID's to Room objects
	private static Map<Integer, ClickableObject> gameObjects = new HashMap<>();	//map ID's to all types of ClickableObjects
	
		//used for keeping track of player and movement of player
	public static Player p = new Player(1,"Kyle Jay",100,"player",12);
	public static ClickableObject player = new ClickableObject(p);
	public static Point playerDest = new Point(player.getLocation());
	static Save save1 = new Save();

		//test room stuff
	static Room currentRoom = new Room(57,"Cool City","beach.png", 0, 0, 0, 0,null);
	
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
						for ( Component i : UIPanel.getComponents()) {
							
							//double diffX = playerDest.getX() - player.getX();
							//double diffY = playerDest.getY() - player.getY();
							double diffX = -(i.getX());
							double diffY = -(i.getY());
							
							if (((ClickableObject)i).getObject() instanceof Enemy) {
								diffX += (player.getX() + player.getWidth()/2);
								diffY += (player.getY() + player.getHeight()/2);
							}
							
							if(Math.hypot(diffX, diffY) > i.getWidth() / 2) {
								//((ClickableObject)i).getObject().setHasDestination(true);
							}
						}
					}
				};
				
				Runnable menuDisplay = new Runnable() {	//checks conditions for whether or not to display menus
					@Override
					public void run() {
						
						if(pause.getClicked()) {
							
							pauseMenu.setEnabled(true);
							pause.setClicked(false);
						}
						if (mainMenu.getEnabled()) {
							
							menu.setVisible(true);
							mainMenu.drawMenu();
							int choice = mainMenu.getChoice();
							
							if(choice == 1) {
								System.out.println("start!");
								loadAssets(true);
								//loadRoom(1000);
								UIPanel.setVisible(true);
								background.setVisible(true);
								menu.setVisible(false);
								mainMenu.setEnabled(false);
								loadControls();
								menu.removeAll();
							}
							else if (choice == 2) {
								loadAssets(false);
								System.out.println("load!");
							}
							else if (choice == 3) {
								
								System.out.println("exit!");
								quitGame();
							}

						}
						if(pauseMenu.getEnabled()) {

							menu.setVisible(true);
							pauseMenu.drawMenu();
							int choice = pauseMenu.getChoice();
							
							if (choice == 1) {
								
								menu.removeAll();
								menu.setVisible(false);
								pauseMenu.setEnabled(false);
							}
							else if (choice == 2) {
								
								System.out.println("Save!");
							}
							else if (choice == 3) {
								
								System.out.println("Load!");
							}
							else if (choice == 4) {
								
								quitGame();
							}
							
						}
						if (DialogueBox.getEnabled()) {
							
							menu.setVisible(true);
							DialogueBox.drawMenu();
							int choice = DialogueBox.getChoice();
							
							if(choice == 3) {
								
								//System.out.println("Would you kindly kill some goblins?");
								Player.addQuest(new KillQuest());
								menu.setVisible(false);
								DialogueBox.setEnabled(false);
							}
							else if(choice == 4) {
								
								//System.out.println("Kill those boyos");
								//Player.addQuest(new KillQuest());
								menu.setVisible(false);
								DialogueBox.setEnabled(false);
							}
						}
						if (FinishQuest.getEnabled()) {
							menu.setVisible(true);
							FinishQuest.drawMenu();
							int choice = FinishQuest.getChoice();
							
							if(choice == 3) {
									if(Player.getQuest(123).checkIsComplete() != true) {
										menu.removeAll();
										menu.setVisible(false);
										FinishQuest.setEnabled(false);
										
									}
									else {
											Player.addItemToInventory(KillQuest.reward);
											menu.removeAll();
											menu.setVisible(false);
											FinishQuest.setEnabled(false);
											Player.deleteQuest(123);
									}
							}
							if(choice == 4) {
								FinishQuest.setEnabled(false);
								menu.setVisible(false);
							}
						}
					}
				};
				
					//launch all threads 60 times a second as dictated by variable "framerate"
				scheduledPool.scheduleWithFixedDelay(updatePlayerLocation, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
				scheduledPool.scheduleWithFixedDelay(updateEntityLocations, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
				scheduledPool.scheduleWithFixedDelay(menuDisplay, 0, (int)skipTicks, TimeUnit.MILLISECONDS);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CS378() {
		initialize();
	}

	static void loadAssets(boolean newGame){
		//if new game, load rooms from Rooms.csv. if loading save, load map from save file
		//define FileReader, read in Rooms, put in map with ID being the key
		//put group all ClickableObjects into items map, to always be loaded from csv files, as object states are kept track of by rooms
		
		if(newGame) {
			
			File cd =  new File(curdir + "/assets/data/");
	        File[] files = cd.listFiles();
	        
	        for (File f: files) {
	        	
	            if (f.getName().endsWith(".csv")) {		//we want csv files only
	            	
	            	String errorAt = "";
	            	
					try (FileInputStream is = new FileInputStream(f)) {
			        	
			            InputStreamReader ir = new InputStreamReader(is);
			            BufferedReader rdr = new BufferedReader(ir);
			            String line = rdr.readLine();
			            line = rdr.readLine();
		
			            while (line != null) {
			            	errorAt = line;
			            	
			            	Vector<String> segments = new Vector<>();		//we use a vector to store each piece of info because we don't know how many parameters to expect
			                segments = new Vector<String>(Arrays.asList(line.split(",")));	//the vector is created with each element representing a single parameter
			                
			                int ID = Integer.parseInt(segments.elementAt(0));	//0th and first elements always ID and name
			                String name = segments.get(1);
			                
			                if(f.getName().contentEquals("Rooms.csv")) {	//prepare to add to game map
			                	//System.out.println(segments.elementAt(0));
			                	String sprite = segments.get(2);
			                	int north = Integer.parseInt(segments.get(3));
			                	int east  = Integer.parseInt(segments.get(4));
			                	int south = Integer.parseInt(segments.get(5));
			                	int west  = Integer.parseInt(segments.get(6));
			                	Vector<ClickableObject> initialState = new Vector<>();
			                	Vector<String> itemCoordinates = new Vector<>();
			                	
			                	for (int i = 7; i < segments.size(); i++) {								//WARNING: this completely relies on the object being first declared in the gameObjects map!
			                		itemCoordinates = new Vector<String>(Arrays.asList(segments.get(i).split(" ")));
			                		ClickableObject temp = gameObjects.get(Integer.parseInt(itemCoordinates.get(0)));
			                		temp.setBounds(Double.parseDouble(itemCoordinates.get(1)), Double.parseDouble(itemCoordinates.get(1)), 1.0 / 28.4, 1.0 / 8.0);
			                		initialState.add(temp);
			                	}
			                	
			                	gameMap.put(ID,new Room(ID,name,sprite,north,east,south,west,initialState));
			                	
			                	
			                }else {											//prepare to add to item map
			                	errorAt = line;
			                	//String name = segments.get(1);
			                	int value = 0;
			                	String description = "";
			                	String sprite = "";
			                	int strength = 0;
			                	int health = 0;
			                	
			                	
			                	if(ID >= 1000 & ID <= 4999) {
			                		value = Integer.parseInt(segments.get(2));
			                		description = segments.get(3);
			                		sprite = segments.get(4);
			                		strength = Integer.parseInt(segments.get(5));
			                	} else if (ID >= 6000 & ID <= 9999){
			                		health = Integer.parseInt(segments.get(2));
			                		sprite = segments.get(3);
			                		strength = Integer.parseInt(segments.get(4));
			                	}
			                	
			                	
			                	if (ID >= 1000 || ID <=1999) { 	//armor
			                		gameObjects.put(ID, new ClickableObject(new Armor(ID,name,value,description,sprite,strength)));
			                		
			                	} else if (ID >= 2000 || ID <=2999) {	//weapon
			                		gameObjects.put(ID, new ClickableObject(new Weapon(ID,name,value,description,sprite,strength)));
			                		
			                	} else if (ID >= 3000 || ID <=3999) {	//key
			                		gameObjects.put(ID, new ClickableObject(new Key(ID,name,value,description,sprite)));
			                		
			                	} else if (ID >= 4000 || ID <=4999) {	//consumable
			                		gameObjects.put(ID, new ClickableObject(new Consumable(ID,name,value,description,sprite,strength)));
			                		
			                	} else if (ID >= 5000 || ID <=5999) {	//door (currently unused)
			                		//implement later	
			                		
			                	} else if (ID >= 6000 || ID <=6999) {	//generic Entity
			                		gameObjects.put(ID, new ClickableObject(new Entity(ID,name,health,sprite,strength)));
			                		
			                	} else if (ID >= 7000 || ID <=7999) {	//Enemy
			                		
			                		gameObjects.put(ID, new ClickableObject(new Enemy(ID,name,health,sprite,strength)));
			                	} else if (ID >= 8000 || ID <=8999) {	//Quest Giver
			                		
			                		gameObjects.put(ID, new ClickableObject(new QuestGiver(ID,name,health,sprite,strength)));
			                	} else if (ID >= 9000 || ID <=9999) {	//Merchant
			                		
			                		gameObjects.put(ID, new ClickableObject(new Merchant(ID,name,health,sprite,strength)));
			                	}else {
			                		//nothing cause its not valid (maybe delete?)
			                	}
			                	
			                }
			                
			                line = rdr.readLine();
			            }
					}
					catch (Exception ex) {
		            	System.out.printf("Failed for %s\n", f.getName() + " " + errorAt);
		            }
	            }
	        }
			
		}
		else {
			//load from save
			gameMap = save1.getMap();
			currentRoom = gameMap.get(save1.getCurrentRoom());
			p = save1.getPlayer();
			player = save1.getPlayerObject();
		}
	}
	
	static void loadRoom(int ID) {
		//check if room to load exists before unloading
		if(gameMap.get(ID) != null) {
			
			UIPanel.removeAll();
			buildUI();
			
			gameMap.put(currentRoom.getID(), currentRoom);
			currentRoom = gameMap.get(ID);
			if(currentRoom.firstVisit) {
				currentRoom.firstTimeSetup();
			}
			
			Vector<ClickableObject> objects = currentRoom.getObjects();
			
			backgroundLabel.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/Rooms/" + currentRoom.getBackground()),Res.x,Res.y));
			
			for(int i = 0; i < objects.size(); i++) {
				ClickableObject temp = objects.get(i);
				
				if(temp.getObject() instanceof Entity)
					temp.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/" + temp.getObject().getSprite()),(int)(Res.y/16.0), (int)(Res.y/8.0)));
				else
					temp.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/" + temp.getObject().getSprite()),(int)(Res.y/16.0), (int)(Res.y/8.0)));

				UIPanel.add(temp);
			}
			
		}else {
			System.out.println("problem!");
		}
		//unload assets of current room
		//load new assets (currentRoom = whatever(ID));
	}
	
		//method resizeIcon takes ImageIcon object and preferred size and scales it using smooth scaling
	static ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img); 
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
		
		String[] mm = {"New","Load","Exit Game"};
		mainMenu = new Menu(mm);
		String[] pm = {"Resume","Save","Load","Exit"};
		pauseMenu = new Menu(pm);
		String[] gq = {"Kill that Goblin. ", "Repeatedly.","Accept","Decline"};
		DialogueBox = new Menu(gq);
		String[] fq = {"Have you gotten rid of"," that Goblin yet?", "Complete Quest", "Keep Quest"};
		FinishQuest = new Menu(fq);
		
		layeredPane.setLayer(menu, 2);
		menu.setBounds(0, 0, Res.x, Res.y);
		layeredPane.add(menu);
		menu.setLayout(null);
		menu.setBackground(new Color(20,80,60));
		
			//panel for displaying background image
		layeredPane.add(background);
		background.setBounds(0, 0, Res.x, Res.y);
		background.setBackground(new Color(33,33,33));
		background.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			//background using JLabel to hold image
		//backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/sprites/testbkg.jpg"),Res.x,Res.y));
		backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/sprites/Rooms/" + currentRoom.getBackground()),Res.x,Res.y));
		background.add(backgroundLabel);
		
			//interactive panel for user interface (health, inventory, navigation, clickableObjects, etc)
		UIPanel.setOpaque(false);
		layeredPane.setLayer(UIPanel, 1);
		UIPanel.setBounds(0, 0, Res.x, Res.y);
		layeredPane.add(UIPanel);
		UIPanel.setLayout(null);
		
		buildUI();
		
		
		  //test code
		ClickableObject heart = new ClickableObject(new Item(1000,"heart",5,"issa heart",""));
		heart.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/heart.png"),(int)(Res.y/16.0), (int)(Res.y/16.0)));
		heart.setBounds(0.9, 0.5, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(heart);
		
		ClickableObject sheriff = new ClickableObject(new QuestGiver(8000,"Sheriff",100,"",20));
		sheriff.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/sheriff.png"),(int)(Res.y/16.0), (int)(Res.y/8.0)));
		sheriff.setBounds(0.88, 0.86, 1.0 / 28.4, 1.0 / 8.0);
		UIPanel.add(sheriff);
		
		ClickableObject Goblin = new ClickableObject(new Enemy(7000,"Goblin",100,"",20));
		Goblin.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/goblin.png"),(int)(Res.y/16.0), (int)(Res.y/8.0)));
		Goblin.setBounds(0.95, 0.1, 1.0 / 20.4, 1.0 / 8.0);
		UIPanel.add(Goblin);
		
		
		//loadRoom(1000);
		
			//player
		player.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/player.png"),(int)(Res.y/16.0), (int)(Res.y/8.0)));
		player.setBounds(0.75, 0.4, 1.0 / 28.4, 1.0 / 8.0);
		UIPanel.add(player);
		playerDest = player.getLocation();
		
			//mouse click listener to set destinations for player to go to
		UIPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerDest.setLocation(new Point(e.getX() - player.getWidth()/2, e.getY() - player.getHeight()/2));
				//System.out.println("(" + ((float)e.getX() / (float)Res.x) + "," + ((float)e.getY() / (float)Res.y) + ")");
			}
		});
			//temporary solution to exit game by pressing the '1' key
		//loadControls();
		
		//panels are disabled until activated via menu navigation
		//menu.setVisible(false);
		UIPanel.setVisible(false);
		background.setVisible(false);
		
		mainMenu.setEnabled(true);
		
		frame.pack();
	}
	
	static Action quit= new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			quitGame();
		}
	};
	
	static void loadControls() {
		
		UIPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('W'),"pressedW");
		UIPanel.getActionMap().put("pressedW",quit);
		//System.out.println(UIPanel.getInputMap().allKeys()); //prints null ???
	}
	
	static void buildUI() { 	//define UI elements
			//HP monitor
		healthUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/heart.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		healthUI.setBounds(0.05, 6.8 / 8.0, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(healthUI);
			//Compass for navigation
		compassUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/compassrose.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		compassUI.setBounds((Res.x / 2) - ((int)(Res.y/8.0) / 2), (int)(6.8 * Res.y / 8.0), (int)(Res.y/8.0), (int)(Res.y/8.0));
		UIPanel.add(compassUI);
			//pause button
		pause.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/sprites/uielements/pause.png"),(int)(Res.y/8.0), (int)(Res.y/8.0)));
		pause.setBounds(0.02, 0.05, 1.0 / 14.2, 1.0 / 8.0);
		UIPanel.add(pause);
	}

	private static void quitGame() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}
	
}
