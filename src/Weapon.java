
public class Weapon extends Item{
	
	protected int damage;
	
	public Weapon() {
		
	}
	
	public Weapon(String name, String description, int ID, int value, int damage) {
		super(ID, name, value, description);
		this.damage = damage;
	}
	
	public int getDamage() { return damage; }
}
