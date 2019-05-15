
public class Weapon extends Item{
	
	protected int damage;
	
	public Weapon() {
		
	}
	
	public Weapon(int ID, String name, int value, String description, String sprite, int damage) {
		super(ID, name, value, description, sprite);
		this.damage = damage;
	}
	
	public int getDamage() { return damage; }
}
