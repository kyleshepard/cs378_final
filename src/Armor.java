
public class Armor extends Item{
	
	protected int damageProtection;
	
	public Armor() {
		
	}
	
	public Armor(String name, String description, int ID, int value, int damageProtection) {
		super(name, description, ID, value);
		this.damageProtection = damageProtection;
	}
	
	public int getDamageProtection() { return damageProtection; }
}
