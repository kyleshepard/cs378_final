
public class Armor extends Item{
	
	protected int damageThreshold;
	
	public Armor() {
		
	}
	
	public Armor(int ID, String name, int value, String description, String sprite, int damageThreshold) {
		super(ID, name, value, description, sprite);
		this.damageThreshold = damageThreshold;
	}
	
	public int getDamageThreshold() { return damageThreshold; }
}
