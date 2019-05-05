package eyegame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver implements ISaver{

	public File file;
	
	public void push(File theFile, String data) throws IOException {
		try {
			this.file = theFile;
			FileWriter fileWriter = new FileWriter(this.file);
			fileWriter.write(data);
			fileWriter.close();
		}catch(IOException ex) {
			throw new IOException(ex); 
		}
	}
}
