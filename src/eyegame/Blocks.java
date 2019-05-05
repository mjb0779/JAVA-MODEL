package eyegame;

public class Blocks {

	protected int xPos;
	protected int yPos;
	protected String shape;
	protected String colour;
	public String sp;
	
	public Blocks(int newXPos, int newYPos, String newShape, String newColour, String newSp) {
		this.xPos = newXPos;
		this.yPos = newYPos;
		this.shape = newShape;
		this.colour = newColour;
		this.sp = newSp;
	}
}
