import java.util.HashMap;
import java.util.Map;

public class Save implements java.io.Serializable{

	private static final long serialVersionUID = 1847299946633584979L;
	private Player p;
	private ClickableObject player;
	private static Map<Integer, Room> gameMap = new HashMap<>();				//map ID's to Room objects
    private int currentRoom;

    public Save(Player p, int currentRoom){
    	this.p = p;
    	this.currentRoom = currentRoom;
    }
    
    public Save() {
    	
    }
    
    public Player getPlayer() { return p; }
    public ClickableObject getPlayerObject() { return player; } 
    
    public int getCurrentRoom(){
    	return currentRoom;
    }

    public Map<Integer, Room> getMap(){
    	return gameMap;
    }
    
}