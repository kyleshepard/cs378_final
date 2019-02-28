import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class CS378 {
	
	public static String curdir = System.getProperty("user.dir");
	
	private JFrame frmTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CS378 window = new CS378();
					window.frmTitle.setVisible(true);
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
		frmTitle = new JFrame();
		frmTitle.setTitle("Title");
		frmTitle.getContentPane().setBackground(Color.DARK_GRAY);
		frmTitle.setBounds(100, 100, 1600, 900);
		frmTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitle.getContentPane().setLayout(null);
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		textPanel.setBounds(10, 11, 1100, 839);
		frmTitle.getContentPane().add(textPanel);
		
		JPanel previewPanel = new JPanel();
		previewPanel.setBackground(Color.BLACK);
		previewPanel.setBounds(1120, 318, 454, 237);
		frmTitle.getContentPane().add(previewPanel);
		previewPanel.setLayout(null);
		
		JLabel previewImage = new JLabel("");
		previewImage.setBounds(10, 11, 434, 215);
		previewImage.setIcon(new ImageIcon(curdir + "/assets/roomtest.png"));
		previewPanel.add(previewImage);
	}
}
