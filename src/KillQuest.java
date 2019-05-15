public class KillQuest extends Quest {
	public static QuestGiver Sheriff;
	public int NumberToKill;
	public Enemy EnemyToKill;	
	//public int reward;
	
	public KillQuest(){
		name = "Kill Goblins!";
		ID = 123;
		NumberToKill = 10;
		//EnemyToKill = Enemy.getEnemyType("Goblin");
		reward = new Weapon(2007,"Axe of Goblin Bones",52, "Made from Goblin Bones","", 10);
		
	}
	
}