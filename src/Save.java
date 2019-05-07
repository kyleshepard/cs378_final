public class Save implements java.io.Serializable{

	private static final long serialVersionUID = 1847299946633584979L;
	private Player p;
    private Room currentRoom;

    public Save(Player p,Room currentRoom){
    	this.p = p;
    	this.currentRoom = currentRoom;
    }
    
    public Save() {
    	
    }
    
    public Room getCurrentRoom(){
    	return currentRoom;
    }

}