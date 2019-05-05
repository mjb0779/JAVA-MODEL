package eyegame;

import java.io.IOException;
import java.util.Arrays;

public class GamePlay implements IGamePlay{

	public String[][] map;
	public String[] blocks;
	public String[] goalsArray;
	public String player;
	public int goals = 0;
	protected FileHandler fh;
	protected Player pl;
	
	
	//For testing
	public boolean tester;
	public String newMap = "";
	
	public GamePlay(FileHandler theFh) {
		this.fh = theFh;
		
	}
	
	public String[][] getMaze(String newMaze) throws Exception {
		
		try {
			this.fh.getFile(newMaze);
			this.map = this.fh.load();
			this.goalsArray = new String[this.fh.goalNum];
			return this.map;
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	// CODE THAT BREAKS THE OUTPUT
	public void getBlocks() {
		String mapData = this.fh.convertArrayToString(this.map);
		String[] x = new String[this.map[0].length];
		
		this.blocks = mapData.split("-");	
		this.goals = 0;
		int i = 0;
		for(String data: this.blocks) {
			if(data.contains("\r")) {
				String[] temp = data.split("\r\n");
				for(String key: temp) {
					x[i] = key;
					i++;
				}
			}else if(this.checkPlayer(data)) {
				this.player = data;
				x[i] = data;
				i++;
			}else if(data.indexOf("#") != -1) {
				this.goals += 1;
				this.goalsArray[this.goals-1] = this.takeBOut(data);
				x[i] = data;
				i++;
			}else {
				x[i] = data;
				i++;
			}
			
		}
		this.blocks = new String[i];
		i = 0;
		
		for(i = 0; i < this.blocks.length; i++) {
			this.blocks[i] = x[i];
		}
	}
	
	public boolean checkPlayer(String input) {
		
		if(input.indexOf("$") >= 0) {
			return true;
		}
		return false;
	}
	public String takeBOut(String target) {
		String temp = "";
		for(char k: target.toCharArray()) {
			if(k == '{' || k == '}') {
				temp += "";
			}else {
				temp += k;
			}
		}
		return temp;
	}
	public void getPlayer() {
		
		this.player = this.takeBOut(this.player); 
		this.pl = new Player(this.player);
		this.pl.getDetails();
	}
	
	//MOVE
	
	public boolean checkMove(int x, int y, String col, String shape) throws Exception{
		
		if(this.pl.colour.equals(col) || this.pl.shape.equals(shape)) {
			if(this.pl.xPos <= x) {
				if(this.pl.yPos <= y) {
					return true;
				}else {
					throw new Exception("Cannot move backwards.");
				}
			}else {
				throw new Exception("Cannot move backwards.");
			}
		}else {
			throw new Exception("Next move must be of same colour or shape.");
		}
		

	}
	public String changeBlock(String oldBlock) {
		String newBlock = "";
		
		//DUPLICATE FROM PLAYER CLASS
		String[] temp = oldBlock.split(",");
		int i = 0;
		for(String key : temp) {
			if(key.equals("N") && i == 4) {
				key = this.pl.pSign;
				newBlock += key +  ",";
			}else if(key.equals("N") && i == 5) {
				key = this.pl.dir;
				newBlock += key;
			}else if(key.equals("$") && i == 4){
				key = "N";
				newBlock += key +  ",";
			}else if(!key.equals("N") && i == 5){
				key = "N";
				newBlock += key;
			}else if(key.equals("#") && i == 4){
				key = "$";
				newBlock += key +  ",";
			}else {
			
			
				newBlock += key +  ",";
			}
			
			i++;
		}
		newBlock = "{" + newBlock + "}";
		return newBlock;
	}
	
	public void movePlayer(String targetBlock) throws Exception{
		
		this.newMap = "";
		String oldPlayerBlock = this.takeBOut(this.player);
		try {
			String[] temp = targetBlock.split(",");
			String newDir = "";
			if(this.checkMove(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), temp[2], temp[3])) {
				if(this.pl.xPos == Integer.parseInt(temp[0]) && this.pl.yPos <=  Integer.parseInt(temp[1])) {
					newDir = "D";
				}
				if(this.pl.xPos == Integer.parseInt(temp[0]) && this.pl.yPos >=  Integer.parseInt(temp[1])) {
					newDir = "U";
				}
				if(this.pl.xPos >= Integer.parseInt(temp[0]) && this.pl.yPos ==  Integer.parseInt(temp[1])) {
					newDir = "R";
				}
				if(this.pl.xPos <= Integer.parseInt(temp[0]) && this.pl.yPos ==  Integer.parseInt(temp[1])) {
					newDir = "L";
				}
				if(this.checkMoveToGoal(targetBlock)) {
					this.goals -= 1;
				}
				this.pl.playerMove(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), temp[2], temp[3], newDir);
				this.player = this.pl.giveDetails(targetBlock);
				this.tester = true;
				//Map update after move -- NEED TO REDO -- TOO MUCH HARD CODING!
				for(String data: this.blocks) {
					if(this.takeBOut(data).equals(targetBlock)) {
						data = this.changeBlock(targetBlock);
					}else if (this.takeBOut(data).equals(oldPlayerBlock)) {
						data = this.changeBlock(data);
					}
					this.newMap +="{" + this.takeBOut(data) + "}" + "-" ;
				}
				this.map = this.fh.convertStringToArray(this.newMap);
				this.getBlocks();
			}else {
				this.tester = false;
			}
			
		}catch(Exception ex) {
			throw new Exception(ex);
		}
		
	}
	public boolean checkMoveToGoal(String targetBlock) {
		
		boolean contains = Arrays.stream(this.goalsArray).anyMatch(targetBlock::equals);
		if(contains) {
			return true;
		}
		return false;
	}

	public boolean win() {
		if (this.goals == 0) {
			return true;
		}
		return false;
	}
	
	public void saveNewMap(String newPath) throws IOException {
		this.fh.save(this.map, newPath);
	}
}
