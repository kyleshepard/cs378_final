import java.awt.Point;

public class Item implements Clickable{
	protected String name;
	protected String description;
	protected int ID;
	protected int value;
	
	public Item() {
		
	}
	
	public Item(String name, String description, int ID, int value) {
		this.name = name;
		this.description = description;
		this.ID = ID;
		this.value = value;
	}
	
	public void onClick() {
		
		if(Player.addItemToInventory(this)) {
			System.out.println(name + " added to inventory.");
		}
		else{
			System.out.println("Failed to add " + name + " to inventory. Inventory is full.");
		}
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public int getID() { return ID; }
	public int getValue() { return value; }

	@Override
	public Point updateLocation(Point p1, Point p2) {
		return p1;
	}
	
}
