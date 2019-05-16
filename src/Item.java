import java.awt.Point;

public class Item implements Clickable{
	protected String name;
	protected String description;
	String sprite;
	protected int ID;
	protected int value;
	protected boolean active;
	
	public Item() {
		
	}
	
	public Item(int ID, String name, int value, String description, String sprite) {
		this.ID = ID;
		this.name = name;
		this.value = value;
		this.description = description;
		this.sprite = sprite;
	}
	
	public void onClick() {
		
		if(Player.addItemToInventory(this)) {
			System.out.println(name + " added to inventory.");
			active = false;
		}
		else{
			System.out.println("Failed to add " + name + " to inventory. Inventory is full.");
		}
	}
	
	public void setActive( boolean active ) { this.active = active;}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public int getID() { return ID; }
	public int getValue() { return value; }

	@Override
	public Point updateLocation(Point p1, Point p2) {
		return p1;
	}
	
	public boolean isActive() { return active; }
	public String getSprite() { return sprite; }
}
