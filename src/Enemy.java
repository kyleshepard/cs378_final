import java.awt.Point;

public class Enemy extends Entity{
	public String EnemyType;
	
	public Enemy(int ID, String name, int health, String sprite, int strength) {
		super(ID, name, health, sprite, strength);
	}

	@Override
	public Point updateLocation(Point p1, Point p2) {
		return p1;
	}
	
	public String getEnemyType(String name) {
		if(EnemyType.contentEquals(name)) {
			return name;
		}
		else { return null; }
	}
	
	@Override
	public void onClick() {
		if(getName().equals("Goblin")) {
			
			if(Player.getQuest(123) instanceof KillQuest) {
				if(((KillQuest)Player.getQuest(123)).NumberToKill > 0) {
					((KillQuest)Player.getQuest(123)).NumberToKill--;
					System.out.println("Goblin Killed!");
				}
				
				else {
					((KillQuest)Player.getQuest(123)).setComplete(true);
				}
				
			}
		}
	}
}
