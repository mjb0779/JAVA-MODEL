package eyegame;

import java.io.File;
import java.io.IOException;

public interface ISaver {

	public void push(File file, String data) throws IOException ;
}
