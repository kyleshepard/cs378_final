import java.io.*;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;

public class CS378 {
		//default game resolutions
	public static int[] xRes = {1920, 1280, 1024};
	public static int[] yRes = {1080, 720, 576};
	public static int res = 1;
	private static boolean fullscreen = false;
	
		//variables used for maintaining game speed
	final static double frameRate = 60.0;
	final static double skipTicks = 1000.0/frameRate;
	
		//string used for referencing assets in project directory
	public static String curdir = System.getProperty("user.dir");
	

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CS378 window = new CS378();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				boolean gameRunning = true;
				long nextGameTick = System.currentTimeMillis();
				int sleepTime = 0;
				
				
				while(!gameRunning) {
					//update and launch threads
					//draw screen
					
					nextGameTick += skipTicks;
					sleepTime = (int) (nextGameTick - System.currentTimeMillis());
					if(sleepTime >= 0) {
					
						try { Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							System.out.println("sleep failed");
						}
					}
					else {
						System.out.println("We gotta hurry up!");
					}
				}
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
		frame.setTitle("Battle of Bermuda");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(true);
		}
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(xRes[res], yRes[res]));
		frame.getContentPane().add(layeredPane, BorderLayout.NORTH);
		
			//panel for displaying background image
		JPanel background = new JPanel();
		layeredPane.add(background);
		background.setBounds(0, 0, xRes[res], yRes[res]);
		background.setBackground(new Color(33,33,33));
		//background.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		background.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
			//background using JLabel to hold image
		JLabel backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/testbkg.jpg"),xRes[res],yRes[res]));
		background.add(backgroundLabel);
		
			//interactive panel for user interface (health, inventory, navigation, clickableObjects, etc)
		JPanel UIPanel = new JPanel();
		UIPanel.setOpaque(false);
		layeredPane.setLayer(UIPanel, 1);
		UIPanel.setBounds(0, 0, xRes[res], yRes[res]);
		layeredPane.add(UIPanel);
		//UIPanel.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		UIPanel.setLayout(null);
		
		JLabel healthUI = new JLabel();
		healthUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/heart.png"),(int)(yRes[res]/8.0), (int)(yRes[res]/8.0)));
		healthUI.setBounds((int)(yRes[res] * 0.05), (int)(6.8 * yRes[res] / 8.0), (int)(yRes[res]/8.0), (int)(yRes[res]/8.0));
		UIPanel.add(healthUI);
		
		JLabel compassUI = new JLabel();
		compassUI.setIcon(resizeIcon(new ImageIcon(curdir + "/assets/uielements/compassrose.png"),(int)(yRes[res]/8.0), (int)(yRes[res]/8.0)));
		compassUI.setBounds((xRes[res] / 2) - ((int)(yRes[res]/8.0) / 2), (int)(6.8 * yRes[res] / 8.0), (int)(yRes[res]/8.0), (int)(yRes[res]/8.0));
		UIPanel.add(compassUI);
		
		frame.pack();
	}
	
		//method resizeIcon takes ImageIcon object and preferred size and scales it using smooth scaling
	private ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img); 
	}
}
