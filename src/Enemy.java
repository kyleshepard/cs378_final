import java.awt.Point;

public class Enemy extends Entity{
	public String EnemyType;
	
	public Enemy(int ID, String name, int health, String sprite, int strength) {//Base constructor
		super(ID, name, health, sprite, strength);
	}

	@Override
	public Point updateLocation(Point p1, Point p2) {	//Method to draw the enemy at a new location
		return p1;
	}
	
	public String getEnemyType(String name) {			//Getter to return the name of the Enemy
		if(EnemyType.contentEquals(name)) {
			return name;
		}
		else { return null; }
	}
	
	@Override
	public void onClick() {
		if(getName().equals("Goblin")) {						//Checks if the Enemy's name is Goblin
			
			if(Player.getQuest(123) instanceof KillQuest) {		//Checks if Player has an instance of KillQuest in their vector of Quests
				if(((KillQuest)Player.getQuest(123)).NumberToKill > 0) {//Checks if that instance of KillQuest has no more enemies to kill
					((KillQuest)Player.getQuest(123)).NumberToKill--;	//If not complete, kill another enemy and output to the console for testing
					System.out.println("Goblin Killed!");
				}
				
				else {													//If there are no more enemies to kill,  marks the quest as complete.
					((KillQuest)Player.getQuest(123)).setComplete(true);
				}
				
			}
		}
	}
}
