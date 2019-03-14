import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class CS378 {
	public static int[] xRes = {1920, 1280, 1024};
	public static int[] yRes = {1080, 720, 576};
	public static int res = 1;
	private static boolean fullscreen = false;
	private static double frameRate = 60;
	
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(true);
		}

		JPanel gameWindow = new JPanel() {
			/*@Override
			  protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon(curdir + "/assets/teddy.jpg");
				Image bkg = icon.getImage();
				bkg = bkg.getScaledInstance(xRes[res], yRes[res], Image.SCALE_DEFAULT);
			    super.paintComponent(g);
			        g.drawImage(bkg, 0, 0, null);
			}*/
		};
		gameWindow.setBounds(0, 0, 297, 125);
		gameWindow.setBackground(new Color(33,33,33));
		gameWindow.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		frame.getContentPane().add(gameWindow);
		
		JLabel backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/teddy.jpg"),xRes[res],yRes[res]));
		gameWindow.add(backgroundLabel);
		//backgroundLabel.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		
		
		frame.pack();
	}
	
	private ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_FAST);
		return new ImageIcon(img); 
	}
	
}
