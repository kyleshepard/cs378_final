
public class Room implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int ID;
	protected int north, south, east, west;	//Room ID of rooms North, South, East, and West
	//ImageIcon Background;
	//Vector<Pair(ClickableObject,Pair(Integer,Integer))> items = new Vector<>();
	
	public Room() {
		
	}
	
	public Room(String name, int ID, int north, int south, int east, int west) {
		this.name = name;
		this.ID = ID;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}
	
	int getAdjacentRoom(char d) {
		return 468;	//no other rooms exist right now, so assume zero. FIX LATER
	}
	
	String getName() { return name; }
	
	int getID() { return ID; }
}
