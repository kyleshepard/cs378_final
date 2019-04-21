public class Save implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name = "Default";
    public int level = 0;
    public int health = 100;
    public int xPos = 0;
    public int yPos = 0;
    public String[] inventory = {"Potion","Sword of Truth","10 Pesos"};
    public int questsCompleted = 0;
    public Room curr;

    public Save(String name, int level, int questsCompleted,Room curr){
    	this.name = name;
    	this.level = level;
    	this.questsCompleted = questsCompleted;
    	this.curr = curr;
    }
    
    public Save() {
    	
    }
    
    public Room getCurrentRoom(){
    	return curr;
    }

}