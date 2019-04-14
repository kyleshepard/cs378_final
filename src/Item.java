
public class Item {
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
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public int getID() { return ID; }
	public int getValue() { return value; }
	
}
