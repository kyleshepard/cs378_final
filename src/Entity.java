
public class Entity {
	private String name;
	private int health;
	private double strength;
	//ImageIcon sprite;
	
	public Entity(String _name, int _health, int _strength /*, ImageIcon _sprite */) { //fully featured constructor for basic entity. (might delete later)
		name = _name;
		health = _health;
		strength = _strength;
		//sprite = _sprite;
	}
	
	public Entity() {	// default constructor
		name = "unknown entity";
		health = 100;
		strength = 1.0;
	}
	
	public void setName(String _name) { name = _name; }
	public void setHealth(int _health) { health = _health; }
	public void setStrength(double _strength) { strength = _strength; }
	
	public String getName() { return name; }
	public int getHealth() { return health; }
	public double getStrength() { return strength; }
}
