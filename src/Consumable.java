
public class Consumable extends Item{
	
protected int strength;
	
	public Consumable() {
		
	}
	
	public Consumable(String name, String description, int ID, int value, int strength) {
		super(name, description, ID, value);
		this.strength = strength;
	}
	
	public int getStrength() { return strength; }

}
