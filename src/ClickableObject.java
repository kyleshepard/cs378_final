import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableObject extends UIObject {
	
	private static final long serialVersionUID = 5537929707578305439L;
	protected Clickable obj;
	protected int ID;
	
	public ClickableObject(Clickable obj) {
		super();
		this.obj = obj;
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				
				Thread t = new Thread() {
					public void run(){
						CS378.playerDest = getLocation();
						CS378.p.setHasDestination(true);
						System.out.println(CS378.p.hasDestination());
						
						int i = 0;
						while(CS378.p.hasDestination()) {
							System.out.print("");
						}
						System.out.println("escaped!");
						double diffX = CS378.playerDest.getX() - CS378.player.getX();
						double diffY = CS378.playerDest.getY() - CS378.player.getY();
						//System.out.println(diffX + " " + diffY + " <= " + CS378.player.getWidth() + "?");
						if (Math.hypot(diffX, diffY) <= CS378.player.getWidth()) {
							obj.onClick();
						}
						
					}
				};
				t.start();
				
			}
		});
		
	}
	
	
	public Point updateLocation(Point p1, Point p2) {
		return obj.updateLocation(p1, p2);
	}
	public Clickable getObject() { return obj;	}
	
}
