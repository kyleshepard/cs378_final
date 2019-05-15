
public class Weapon extends Item{
	
	protected int damage;
	
	public Weapon() {
		
	}
	
	public Weapon(String name, String description, int ID, int value, int damage, String sprite) {
		super(ID, name, value, description, sprite);
		this.damage = damage;
	}
	
	public int getDamage() { return damage; }
}
