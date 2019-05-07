
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



}
