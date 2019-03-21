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

public class CS378 {
	public static int[] xRes = {1920, 1280, 1024};
	public static int[] yRes = {1080, 720, 576};
	public static int res = 1;
	private static boolean fullscreen = false;
	private static double frameRate = 60;
	
	public static String curdir = System.getProperty("user.dir");
	
	private static JFrame frame;
	private JTextField Name;
	private JTextField Level;
	private JTextField QuestsCompleted;

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

		JPanel background = new JPanel() {
			/*@Override
			  protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon(curdir + "/assets/teddy.jpg");
				Image bkg = icon.getImage();
				bkg = bkg.getScaledInstance(xRes[res], yRes[res], Image.SCALE_DEFAULT);
			    super.paintComponent(g);
			        g.drawImage(bkg, 0, 0, null);
			}*/
		};
		background.setBounds(0, 0, xRes[res], yRes[res]);
		background.setBackground(new Color(33,33,33));
		background.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		frame.getContentPane().add(background);
		background.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel backgroundLabel = new JLabel(resizeIcon(new ImageIcon(curdir + "/assets/testbkg.jpg"),xRes[res],yRes[res]));
		background.add(backgroundLabel);
		
		JPanel UIPanel = new JPanel() {
		};
		//UIPanel.setVisible(false);
		UIPanel.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		//UIPanel.setBackground(new Color(33, 33, 33));
		frame.getContentPane().add(UIPanel, BorderLayout.CENTER);
		UIPanel.setLayout(null);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Save save1 = new Save(Name.getText(),Integer.parseInt(Level.getText()), Integer.parseInt(QuestsCompleted.getText()));
		        String curdir = System.getProperty("user.dir");

		        try {
		            FileOutputStream fileOut =
		            new FileOutputStream(curdir + "save.dat");
		            ObjectOutputStream out = new ObjectOutputStream(fileOut);
		            out.writeObject(save1);
		            out.close();
		            fileOut.close();
		            System.out.printf("Serialized data is saved in save.dat");
		         } catch (IOException i) {
		            i.printStackTrace();
		         }
				
			}
		
		});
		btnSave.setBounds(0, 0, 75, 23);
		UIPanel.add(btnSave);
		
		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Save save1 = null;
			      String curdir = System.getProperty("user.dir");
			      try {
			         FileInputStream fileIn = new FileInputStream(curdir + "save.dat");
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         save1 = (Save) in.readObject();
			         in.close();
			         fileIn.close();
			      } catch (IOException i) {
			         i.printStackTrace();
			         return;
			      } catch (ClassNotFoundException c) {
			         System.out.println("Employee class not found");
			         c.printStackTrace();
			         return;
			      }
			      
			      System.out.println("Deserialized Save...");
			      System.out.println("Name: " + save1.name);
			      System.out.println("Level: " + save1.level);
			      System.out.println("Health: " + save1.health);
			      System.out.println("Location: [" + save1.xPos + "][" + save1.yPos + "]");
			      System.out.println("Quests Completed: " + save1.questsCompleted);
			      System.out.println("Inventory: ");
			      for ( String s : save1.inventory){
			          System.out.print(s + " ");
			      }
				
			}
		
		});
		btnLoad.setBounds(75, 0, 75, 23);
		UIPanel.add(btnLoad);
		
		Name = new JTextField();
		Name.setBounds(0, 25, 100, 25);
		UIPanel.add(Name);
		Name.setColumns(10);
		
		Level = new JTextField();
		Level.setColumns(10);
		Level.setBounds(0, 50, 100, 25);
		UIPanel.add(Level);
		
		QuestsCompleted = new JTextField();
		QuestsCompleted.setColumns(10);
		QuestsCompleted.setBounds(0, 75, 100, 25);
		UIPanel.add(QuestsCompleted);
		//backgroundLabel.setPreferredSize(new Dimension(xRes[res],yRes[res]));
		
		
		frame.pack();
	}
	
	private ImageIcon resizeIcon(ImageIcon icon, int xSize, int ySize) {
		Image img = icon.getImage();
		img = img.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(img); 
	}
}
