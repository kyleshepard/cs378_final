
public class QuestGiver extends Entity{
	public QuestGiver() {
		
	}
	
	public QuestGiver(int ID, String name, int health, String sprite, int strength) {//Base constructor
		super(ID, name, health, sprite, strength);
	}
	
	public void giveQuest(Quest item){	//Lets QuestGivers add a Quest to the Player's vector of Quests
	Player.addQuest(item);
	}
	
//	public void RemoveQuest(Quest item) {
//		Player.deleteQuest(item);
//	}

	
	@Override
	public void onClick(){
		if(getName().equals("Sheriff")) {	//Checks if the ClickableObject is the Sheriff
			if(Player.searchQuest(123) == false) {	//If the Player does not have the KillQuest, open up the dialogue box used to give it to them
				CS378.DialogueBox.setEnabled(true);
			}
			else {
				CS378.FinishQuest.setEnabled(true);	//If the Player does have the KillQuest, open up the FinishQuest Menu used to complete it
			}
		}
	
}
}
