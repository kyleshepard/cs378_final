
public class Consumable extends Item{
	
protected int strength;
	
	public Consumable() {
		
	}
	
	public Consumable(int ID, String name, int value, String description, int strength, String sprite) {
		super(ID, name, value, description, sprite);
		this.strength = strength;
	}
	
	public int getStrength() { return strength; }

}
