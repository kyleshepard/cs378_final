import javax.swing.JLabel;

public class UIObject extends JLabel{
	
	private static final long serialVersionUID = 2529161242752983444L;

	public UIObject() {
		super();
	}
	
	//used for setting bounds of UI elements using ratios in relation to the resolution of the game window
	public void setBounds(double xPosMulti, double yPosMult, double widthMult, double heightMult) {
		super.setBounds((int)(Res.x * xPosMulti),(int)(Res.y * yPosMult), (int)(Res.x * widthMult),(int)(Res.y * heightMult));
	}
}
