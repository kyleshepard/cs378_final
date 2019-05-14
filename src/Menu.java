import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class is in charge of formatting and displaying the menu.
 * This includes tracking the current state of the menu, updating
 * the menu according to that state, and returning the selection
 * based upon the state.
 * 
 * @author Chad Ross. Modified by Kyle Shepard
 */

public class Menu {
	private boolean enabled;
	private int choice;	//0 means no choice (choice = index + 1)
	private ClickableObject[] choices = {new ClickableObject("")};
	
	Menu(){
		choice = 0;
	}
	
	Menu(String[] options){
		choices = new ClickableObject[options.length];
		for (int i = 0; i < options.length; i++) {
			choices[i] = new ClickableObject(options[i]);
		}
	}
	
	/**
	 * Add all of the GUI components to the main menu.
	 * 
	 * @param panel
	 */
	public void drawMenu() {
		CS378.menu.removeAll();
		// Setup the JLabels that hold the menu selection choices. This
		// is based upon the values found in the selectionText array.
		// The default option is START GAME which is always at index 0.
		 
		Image img = new ImageIcon(CS378.curdir + "/assets/sprites/uielements/FloridaSim2.png").getImage();
		img = img.getScaledInstance(Res.y/2, Res.y/4,  java.awt.Image.SCALE_SMOOTH);
		JLabel title = new JLabel(new ImageIcon(img));
		title.setSize(Res.y/2, Res.y/4);
		title.setBounds(Res.x/2 - title.getWidth()/2, Res.y/ 16, title.getWidth(), title.getHeight());
		
		CS378.menu.add(title);
		
		for(int i = 0; i < choices.length; i++) {
			ClickableObject l = choices[i];
			
			l.setForeground(Color.white);
			l.setFont(new Font("Dialog", Font.PLAIN, (int)Res.y/25));
			l.setBounds(Res.x / 2 - Res.x/8 , Res.y/2 + (i * Res.y / 12), Res.x/4, Res.y/20);
			l.setHorizontalAlignment(SwingConstants.CENTER);
			CS378.menu.add(l);
			choices[i] = l;
		}
		
		// Add all the components to the panel.
		CS378.menu.revalidate();
		CS378.menu.repaint();
		
	}
	
	public int getChoice() { 
		for (int i = 0; i < choices.length; i++) {
			if(choices[i].getClicked()) {
				choices[i].setClicked(false);
				return i + 1;
			}
		}
		return 0;
	}
	
	public boolean getEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) { this.enabled = enabled; }
	
}
