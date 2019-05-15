
public class QuestGiver extends Entity{
	public QuestGiver() {
		
	}
	
	public QuestGiver(int ID, String name, int health, String sprite, int strength) {
		super(ID, name, health, sprite, strength);
	}
	
	public void giveQuest(Quest item){
	Player.addQuest(item);
	}
	
//	public void RemoveQuest(Quest item) {
//		Player.deleteQuest(item);
//	}

	
	@Override
	public void onClick(){
		if(getName().equals("Sheriff")) {
			if(Player.searchQuest(123) == false) {
				CS378.DialogueBox.setEnabled(true);
			}
			else {
				CS378.FinishQuest.setEnabled(true);
			}
		}
	
}
}
