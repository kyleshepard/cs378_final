
import java.awt.Point;

public class Player extends Entity{
	
	private static Item[] inventory = new Item[8];
	
	public Player() {
		
	}
	
	public Player(String name, int health, int strength /* ImageIcon _sprite */) {
		super(name, health, strength /*, ImageIcon _sprite*/);
		//initialize inventory
	}
	
	//iterates through inventory and attempts to add item
	//returns true if item is successfully added
	public static boolean addItemToInventory(Item item) {
		for(int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = item;
				return true;
			}
		}
		return false;
	}
	
	public static Item[] getInventory() {
		return inventory;
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

}
