
public class Quest {
	private boolean isComplete;
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
	public Quest() {
		name = "Quest #1";
		count = 0;
		isComplete = false;
	}
}
