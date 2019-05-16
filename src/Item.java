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
	
	public Item(int ID, String name, int value, String description, String sprite) {	//Base constructor
		this.ID = ID;
		this.name = name;
		this.value = value;
		this.description = description;
		this.sprite = sprite;
	}
	
	public void onClick() {
		
		if(Player.addItemToInventory(this)) {	//If you click on an item, it attempts to add it to the inventory
			System.out.println(name + " added to inventory.");
			active = false;
		}
		else{
			System.out.println("Failed to add " + name + " to inventory. Inventory is full.");
		}
	}
	
	public void setActive( boolean active ) { this.active = active;}	//Setter to set an item as active or not
	
	public String getName() { return name; }	//Getter to return the Name of an item
	public String getDescription() { return description; }	//Getter to return the Description of an item
	public int getID() { return ID; }	//Getter to return the ID of an Item
	public int getValue() { return value; }	//Getter to return the Value of an Item

	@Override
	public Point updateLocation(Point p1, Point p2) {	//Lets you draw the object at a new point
		return p1;
	}
	
	public String getSprite() { return sprite; }
	public boolean isActive() { return active; }	//Getter to return whether or not the item is active
	
}
