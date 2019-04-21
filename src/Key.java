
public class Key extends Item{
	private int lockID;
	
	public Key() {
		
	}
	
	public Key(String name, String description, int ID, int value, int lockID) {
		super(name,description,ID,value);
		this.lockID = lockID;
	}
	
	public int getLockID() { return lockID; }
	
}
