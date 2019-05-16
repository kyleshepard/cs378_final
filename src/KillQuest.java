public class KillQuest extends Quest {	//KillQuest is an extension of Quest
	public static QuestGiver Sheriff;	//Only given by Sheriff
	public int NumberToKill;			//Has a number of Enemies to kill to complete
	public Enemy EnemyToKill;			//Has an Enemy type to kill
	//public int reward;
	
	public KillQuest(){					// Base constructor
		name = "Kill Goblins!";
		ID = 123;
		NumberToKill = 10;
		//EnemyToKill = Enemy.getEnemyType("Goblin");
		reward = new Weapon(2007,"Axe of Goblin Bones",52, "Made from Goblin Bones","", 10);
		
	}
	
}