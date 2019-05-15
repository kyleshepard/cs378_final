
import java.awt.Point;
import java.util.Vector;

public class Player extends Entity{
	
	private static Item[] inventory = new Item[8];
	
	private static Vector<Quest> questList = new Vector<>();
	
	public Player() {
		
	}
	
	public Player(int ID, String name, int health, String sprite, int strength) {
		super(ID, name, health, sprite, strength);
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
	public static void addQuest(Quest crap) {
		questList.add(crap);
	}
	
	public static Quest getQuest(int questID) {
		for(Quest q : questList) {
			if (q.getID() == questID)
				return q;
		}
		return null;
	}
	public static boolean searchQuest(int questID) {
		for(Quest q : questList) {
			if(q.getID() == questID) {
				return true;
			}
		}
		return false;
	}
	
	public static void deleteQuest(int questID) {
		for(Quest q : questList) {
			if(q.getID() == questID) {
				questList.remove(q);
			}
		}
	}
	
	public static Vector<Quest> getQuestList() {
		return questList;
	}
	public static Item[] getInventory() {
		return inventory;
	}



}
