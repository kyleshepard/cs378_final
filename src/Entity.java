
import java.awt.Point;

import javax.swing.ImageIcon;

public abstract class Entity implements Clickable{
	
	protected String name;
	protected int health;
	protected double strength;
	protected String sprite;
	protected boolean hasDestination;
	
	public Entity(String name, int health, int strength) { //fully featured constructor for basic entity. (might delete later)
		this.name = name;
		this.health = health;
		this.strength = strength;
		//sprite = _sprite;
	}
	
	public Entity() {	// default constructor
		name = "unknown entity";
		health = 100;
		strength = 1.0;
	}
	
	public void onClick() {
		
		
	}
	
	@Override
	public Point updateLocation(Point p1, Point p2) {
		double speed = Res.y/250;
		double diffY = (p2.getY() - p1.getY());
		double diffX = (p2.getX() - p1.getX());
		double newX = p1.getX();
		double newY = p1.getY();
		
		if(hasDestination) {
			if(Math.hypot(diffX, diffY) <= Res.y / 32.0)
				hasDestination = false;	
			
			newX += speed * Math.cos(Math.atan2(diffY, diffX));
			newY += speed * Math.sin(Math.atan2(diffY, diffX));
			
		}
		p1.setLocation(newX, newY);
		return p1;
	}
	public void setName(String _name) { name = _name; }
	public void setHealth(int _health) { health = _health; }
	public void setStrength(double _strength) { strength = _strength; }
	public void setHasDestination(boolean hasDestination) { this.hasDestination = hasDestination; }
	
	public String getName() { return name; }
	public int getHealth() { return health; }
	public double getStrength() { return strength; }
	public boolean hasDestination() { return hasDestination; }
	public boolean isActive() { return true; }

}
