
public class Quest {
	private boolean isComplete;
	public int ID;
	public Item reward;
	public String name;
	public Integer count;
	/**
	 * @return the isComplete
	 */
	public boolean checkIsComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public Quest() {       //base constructor  to build an average quest
		name = "Quest #1";
		count = 0;
		isComplete = false;
	}
	
	public int getID() { return ID; }
}
