
import java.awt.Point;
import java.util.Vector;

public class Player extends Entity{
	
	private static Item[] inventory = new Item[8];			//A player has an array of Items that formulate its Inventory, with a max size of 8
	
	private static Vector<Quest> questList = new Vector<>();//A player has a vector of Quests, with no max size, so there is no limit to quests the player can have
	
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
	public static void addQuest(Quest crap) {	//Adds a Quest to the Player's vector of Quests
		questList.add(crap);
	}
	
	public static Quest getQuest(int questID) {	// Getter to search the vector of Quests to determine if the Player has the quest
		for(Quest q : questList) {	//Iterates through the Vector
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
	
	public static void deleteQuest(int questID) {	//Method to delete a Quest by being given its ID
		for(Quest q : questList) {	//Iterates through the Vector
			if(q.getID() == questID) {
				questList.remove(q);
			}
		}
	}
	
	public static Vector<Quest> getQuestList() {	//Getter to access the entire vector of Quests
		return questList;
	}
	public static Item[] getInventory() {			//Getter to access the entire array of Items
		return inventory;
	}



}
