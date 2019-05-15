
public class QuestGiver extends Entity{
	public QuestGiver() {
		
	}
	
	public QuestGiver(int ID, String name, int health, String sprite, int strength) {
		super(ID, name, health, sprite, strength);
	}
	
	public void giveQuest(Quest item){
	Player.addQuest(item);
	}
	
	public void RemoveQuest(Quest item) {
		Player.deleteQuest(item);
	}

	
	@Override
	public void onClick(){
		CS378.DialogueBox.setEnabled(true);
		
		
		
	}
	
	
}
