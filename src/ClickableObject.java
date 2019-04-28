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
				System.out.println("Clicked on: " + getObject().getName());
				obj.onClick();
			}
		});
	}
	
	
	public Point updateLocation(Point p, int dx, int dy) {
		return obj.updateLocation(p, dx, dy);
	}
	public Clickable getObject() { return obj;	}
}
