
import java.awt.Point;

import javax.swing.ImageIcon;

public abstract class Entity implements Clickable{
	
	protected String name;
	protected int health;
	protected double strength;
	protected ImageIcon sprite;
	protected int xPos, yPos;
	
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
	
	public Point updateLocation(Point p, int dx, int dy) {
		return p;
	}
	public void setName(String _name) { name = _name; }
	public void setHealth(int _health) { health = _health; }
	public void setStrength(double _strength) { strength = _strength; }
	
	public String getName() { return name; }
	public int getHealth() { return health; }
	public double getStrength() { return strength; }

	public int getX() { return xPos; }
	public int getY() { return yPos; }

}
