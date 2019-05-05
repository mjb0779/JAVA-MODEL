package eyegame;

import java.io.IOException;

public interface IFileHandler {

	public void getFile(String newFile);
	public boolean validateMap() throws Exception;
	public String[][] convertStringToArray(String newString);
	public String convertArrayToString(String[][] multArray);
	
	public void save(String[][] rawData, String savePath)throws IOException;
}
