
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
	
	public Point updateLocation(Point p1, Point p2) {
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
