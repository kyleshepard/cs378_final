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
		reward = new Weapon("Axe of Goblin Bones", "Made from Goblin Bones", 1234, 100, 10,"");
		//reward = 2000;
		
		
	}
	
}