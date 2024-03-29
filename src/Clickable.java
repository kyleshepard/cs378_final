import java.awt.Point;

public interface Clickable {
	public String getName();
	public void onClick();
	public Point updateLocation(Point p1, Point p2);
	public boolean isActive();
	public void setActive(boolean active);
	String getSprite();
}
