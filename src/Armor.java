
public class Armor extends Item{
	
	protected int damageThreshold;
	
	public Armor() {
		
	}
	
	public Armor(int ID, String name, int value, String description, int damageThreshold) {
		super(ID, name, value, description);
		this.damageThreshold = damageThreshold;
	}
	
	public int getDamageThreshold() { return damageThreshold; }
}
