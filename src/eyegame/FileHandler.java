package eyegame;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler implements IFileHandler {

	public File file;
	public String filePath;
	public String[][] output;
	public String content;
	public String conOut;
	public int goalNum = 0;
	protected String separators = "\r\n|\r|\n";
	protected MapValidator myMv;
	
	
	public void getFile(String newFile) {
		this.filePath = newFile;
		this.file = new File(newFile);
		this.myMv = new MapValidator(this.file);
	}
	
	public boolean validateMap() throws Exception {
		
		try {
			if(this.myMv.isFileValid()) {
				return true;
			}
		}catch (Exception ex){
			throw new Exception(ex);
		}
		return false;
	}
	
	public String[][] load() throws Exception {
		
		
		try {
			if(this.validateMap()) {
				Loader load = new Loader(this.file);
				this.content = load.Fetch();
				if(this.myMv.checkPlayer(this.content)) {
					if(this.myMv.checkGoal(this.content)) {
						this.output =  this.convertStringToArray(this.content);
						this.goalNum = this.myMv.goalNum;
					}
				}	
			}
			return this.output;		
		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}
	public void DeleteFile(File file) {
		file.delete();
	}
	
	public String[][] convertStringToArray(String newString){

		String[] temp2;
		String[] temp = newString.split(separators);
		
		//FREAKIN HARD CODED!!
		String[] temp3 = temp[0].split("");
		int y = temp3.length;
		String[][] mapArr = new String[temp.length] [y];

		int i = 0;
		for (String line: temp) {
			int count = 0;
			temp2 = line.split("|");
			for(String key: temp2) {
				mapArr[i][count] = key;
				count+=1;
			}
			i += 1;
			temp2 = null;
		}
		return mapArr;
		
	}
	public String convertArrayToString(String[][] multArray) {
		String result = "";

		for(int i = 0; i < multArray.length; i++) {
			for(int count = 0; count < multArray[0].length; count++) {
				result += multArray[i][count];
				
			}
			if(i < multArray[0].length - 1) {
				result += "\r\n";
			}
		}
		//this.conOut = result.replace("},{", "}-{");
		this.conOut = result;
		return this.conOut;
		
	}
	
	
	
	public void save(String[][] rawData, String savePath) throws IOException {
		try {
			File saveFile = new File(savePath);
			if(this.myMv.doesFileExists(saveFile)) {
				PrintWriter writer = new PrintWriter(saveFile);
				writer.print("");
				writer.close();
			}
			Saver saver = new Saver();
			String data = this.convertArrayToString(rawData);
			saver.push(saveFile,data);
		}catch (IOException ex) {
			ex.getMessage();
		}
		
	}
	
}
