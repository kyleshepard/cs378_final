
public class QuestGiver extends Entity{
	public QuestGiver() {
		
	}
	
	public QuestGiver(String name, int health, int strength) {
		super(name, health, strength);
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
