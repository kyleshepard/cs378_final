
public class Weapon extends Item{
	
	protected int damage;	//The only special thing a Weapon has is it's damage potential
	
	public Weapon() {
		
	}
	
	public Weapon(int ID, String name, int value, String description, String sprite, int damage) {//Base constructor
		super(ID, name, value, description, sprite);
		this.damage = damage;
	}
	
	public int getDamage() { return damage; }	//Getter to return how much damage a Weapon does
}
