
public class Armor extends Item{
	
	protected int damageThreshold;
	
	public Armor() {
		
	}
	
	public Armor(int ID, String name, int value, String description, int damageThreshold, String sprite) {
		super(ID, name, value, description, sprite);
		this.damageThreshold = damageThreshold;
	}
	
	public int getDamageThreshold() { return damageThreshold; }
}
