import java.awt.Color;
import java.awt.Font;
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
	private ClickableObject[] choices = {new ClickableObject("",0)};
	
	Menu(){
		choice = 0;
	}
	
	Menu(String[] options){
		choices = new ClickableObject[options.length];
		for (int i = 0; i < options.length; i++) {
			choices[i] = new ClickableObject(options[i],i);
		}
	}
	
	/**
	 * Add all of the GUI components to the main menu.
	 * 
	 * @param panel
	 */
	public void drawMenu() {
		
		// Setup the JLabels that hold the menu selection choices. This
		// is based upon the values found in the selectionText array.
		// The default option is START GAME which is always at index 0.
		for(int i = 0; i < choices.length; i++) {
			ClickableObject l = choices[i];
			
			l.setForeground(Color.white);
			l.setFont(new Font("Dialog", Font.PLAIN, (int)Res.y/25));
			l.setBorder(BorderFactory.createLineBorder(Color.gray,1));
			l.setBounds(Res.x / 32, Res.y/4 + (i * Res.y / 12), Res.x/4 - Res.x/16, Res.y/20);
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
