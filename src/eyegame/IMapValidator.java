package eyegame;

import java.io.File;

public interface IMapValidator {

	public boolean checkPlayer(String newContent) throws Exception;
	public boolean checkGoal(String newContent) throws Exception;
	public boolean isFileValid() throws Exception;
	public String getFileExtension(File file);
	public boolean doesFileExists(File file);
}
