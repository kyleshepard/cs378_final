import java.util.Vector;

public class Room implements java.io.Serializable{

	private static final long serialVersionUID = 2781292159526708005L;
	protected String name;
	protected int ID;
	protected int north, south, east, west;	//Room ID of rooms North, South, East, and West
	protected String background;
	protected boolean firstVisit;
	protected Vector<ClickableObject> objects = new Vector<>();
	
	public Room() {
		
	}
	
	public Room(String name, String background, int ID, int north, int south, int east, int west) {
		this.name = name;
		this.background = background;
		this.ID = ID;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}
	
	int getAdjacentRoom(char d) {
		if (d == 'n' || d == 'N') {
			return north;
		} else if (d == 'e' || d == 'E') {
			return east;
		} else if (d == 's' || d == 'S') {
			return south;
		} else if (d == 'w' || d == 'W') {
			return west;
		}
		return 0;
	}
	
	String getName() { return name; }
	String getBackground() { return background; }
	
	int getID() { return ID; }
	Vector<ClickableObject> getObjects() { return objects; }
}
