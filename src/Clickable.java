import java.awt.Point;

public interface Clickable {
	public String getName();
	public void onClick();
	public Point updateLocation(Point p, int dx, int dy);
}