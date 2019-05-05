package eyegame;

import java.io.File;



public class MapValidator implements IMapValidator{

	public File content;
	
	private char player = '$';
	private char goal = '#';
	public int goalNum = 0;
	public int playerNum = 0;
	
	public MapValidator(File newContent) {
		this.content = newContent;
		this.goalNum = 0;
		this.playerNum = 0;
	}
	
	public boolean checkPlayer(String newContent) throws Exception {

		
		for(char ch: newContent.toCharArray()) {
			if(ch == this.player) {
				this.playerNum += 1;
			}
		}
		if(playerNum == 0) {
			throw new Exception("No player");
		}
		if(playerNum < 1) {
			throw new Exception("Too many players");
		}else {
			return true;
		}
		
	}
	
	public boolean checkGoal(String newContent) throws Exception{
		
		for (char ch: newContent.toCharArray()) {
			if(ch  == this.goal) {
				this.goalNum += 1;
			}
		}
		if (this.goalNum <= 0) {
			throw new Exception("No goals detected");
		}else {
			return true;
		}
		
	}
	
	public boolean isFileValid() throws Exception {
		
		if(this.doesFileExists(this.content)) {
			String temp = this.getFileExtension(this.content);
			if(temp.equals(".txt")){
				return true;
			}else {
				throw new Exception("File format is not valid");
			}
		}else {
			throw new Exception("File does not exists");
		}
	}
	
	public String getFileExtension(File file) {
		String extension = "";
		
		try {
			if(file != null && file.exists()) {
				String name = file.getName();
				extension = name.substring(name.lastIndexOf("."));
			}
		}catch (Exception e) {
			extension = "";
		}
		
		return extension;
	}
	
	public boolean doesFileExists(File file) {
		if(file.exists()) {
			return true;
		}
		return false;
	}
}
