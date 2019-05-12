import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableObject extends UIObject {
	
	private static final long serialVersionUID = 5537929707578305439L;
	protected Clickable obj;
	protected int ID;
	protected boolean clicked;
	
	public ClickableObject(Clickable obj) {
		super();
		this.obj = obj;
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {	//evoked when ClickableObject is clicked on
				
				Thread t = new Thread() {	//launch new thread to handle loop otherwise game screen freezes until player stops moving
					public void run(){
						CS378.playerDest = getLocation();	//set player destination to object's coordinates
						CS378.p.setHasDestination(true);	//force game to recognize the player has a destination (or else loop below will be skipped)
						
						while(CS378.p.hasDestination()) {	//designed to do nothing until the player has stopped moving by reaching the object or colliding with something else
							System.out.print("");			//for some godforsaken reason, java demands this be here otherwise the code below never gets executed. 
						}

						double diffX = CS378.playerDest.getX() - CS378.player.getX();	//calculate distance between player and object
						double diffY = CS378.playerDest.getY() - CS378.player.getY();

						if (Math.hypot(diffX, diffY) <= CS378.player.getWidth()) {		//only allow interaction if player and object are close enough
							obj.onClick();
						}
						
					}
				};
				t.start();
				
			}
		});
		
	}
	
	public ClickableObject(String text, int ID) {	//used for main menu and pause menu labels
		super(text);
		clicked = false;
		this.ID = ID;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clicked = true;
			}
		});	
	}
	
	public ClickableObject() {
		super();
	}
	
	int getID() { return ID; }
	
	public Point updateLocation(Point p1, Point p2) {
		return obj.updateLocation(p1, p2);
	}
	public Clickable getObject() { return obj;}
	public boolean getClicked() { return clicked; }

	public void setClicked(boolean clicked) { this.clicked = clicked; }
}
