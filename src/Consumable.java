
public class Consumable extends Item{
	
protected int damage;
	
	public Consumable() {
		
	}
	
	public Consumable(String name, String description, int ID, int value, int damage) {
		super(name, description, ID, value);
		this.damage = damage;
	}
	
	public int getDamage() { return damage; }

}
