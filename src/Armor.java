
public class Armor extends Item{
	
	protected int damageThreshold;
	
	public Armor() {
		
	}
	
	public Armor(String name, String description, int ID, int value, int damageThreshold) {
		super(name, description, ID, value);
		this.damageThreshold = damageThreshold;
	}
	
	public int getDamageThreshold() { return damageThreshold; }
}
