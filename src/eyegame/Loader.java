package eyegame;

import java.io.File;

//import java.io.FileReader;
import java.util.Scanner;



public class Loader implements ILoader{


	protected File file;
	public String content = "";
	
	public Loader(File newFilePath){
		this.file = newFilePath;
	}
	
	public String Fetch() throws Exception {
		
		Scanner sc = new Scanner(this.file);
		sc.useDelimiter("\\Z");
		this.content += sc.next();
		
		sc.close();
		return this.content;
		
	}
	
	
	
}
