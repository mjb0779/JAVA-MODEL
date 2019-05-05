package eyegame;

public class Player implements IPlayer{

	public int xPos;
	public int yPos;
	public String shape;
	public String colour;
	public String dir;
	public String pSign;
	public String pBlock;
	public String[] details;
	
	public Player(String playerBlock) {
		this.pBlock = playerBlock;
	}
	
	
	public void getDetails() {
		
		String[] details = pBlock.split(",");
		this.xPos = Integer.parseInt(details[0]);
		this.yPos = Integer.parseInt(details[1]);
		this.colour = details[2];
		this.shape = details[3];
		this.pSign = details[4];
		this.dir = details[5];
		
	}
	
	public void playerMove(int x, int y, String col, String shape, String dir) {
		this.xPos = x;
		this.yPos = y;
		this.colour = col;
		this.shape = shape;
		this.dir = dir;
	}
	
	public String giveDetails(String newBlock) {
		String result = "";
		
		String[] temp = newBlock.split(",");
		int i = 0;
		for(String key: temp) {
			if(key.equals("N") && i == 4) {
				key = this.pSign;
				result += key;
			}else if(key.equals("N") && i == 5) {
				key = this.dir;
				result += key;
			}else {
				result += key;
			}
			
				
			
			i++;
			
		}
		return result;
	}
	
}
