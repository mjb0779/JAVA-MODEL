package eyegame;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class FileHandlerTest {

	protected FileHandler fh = new FileHandler();
	protected File file = new File("H:\\mazeLevel.txt");
	ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testFHGetFile() {
		
		this.fh.getFile("H:\\mazeLevel.txt");
		Assert.assertEquals(this.file, this.fh.file);
	}
	
	@Test
	public void testFHValidateMapPass() throws Exception {
		
		this.fh.getFile("H:\\mazeLevel.txt");
		Assert.assertTrue(this.fh.validateMap());
	}
	
	@Test
	public void testFHValidateMapFormatFail() throws Exception{
		try {
			this.fh.getFile("H:\\mazeLevel.xt");
			this.fh.validateMap();
		}catch(Exception ex) {
			this.exception.expectMessage("File format is not valid");
		}
	}
	
	@Test
	public void testFHValidateMapDoesNotExist() throws Exception{
		try {
			this.fh.getFile("H:\\mazeLevel7.txt");
			this.fh.validateMap();
		}catch(Exception ex) {
			this.exception.expectMessage("File does not exists");
		}
	}
	
	@Test
	public void testFHDeleteFile() {
		File some = new File("H:\\delTest.txt");
		this.fh.DeleteFile(some);
		Assert.assertTrue(!some.exists());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFHConvertStringToArray() {
		String test = "{0,0,R,D,$,D}-{1,0,B,S,N,N}-{2,0,R,D,N,N}\r\n{0,1,B,F,N,N}-{1,1,G,D,N,N}-{2,1,R,D,N,N}";
		String[][] result = this.fh.convertStringToArray(test);
		String[][] expecteds = {{"{", "0", ",", "0", ",", "R", ",", "D", ",", "$", ",", "D", "}", "-", "{", "1", ",", "0", ",", "B", ",", "S", ",", "N", ",", "N", "}", "-", "{", "2", ",", "0", ",", "R", ",", "D", ",", "N", ",", "N", "}"}, {"{", "0", ",", "1", ",", "B", ",", "F", ",", "N", ",", "N", "}", "-", "{", "1", ",", "1", ",", "G", ",", "D", ",", "N", ",", "N", "}", "-", "{", "2", ",", "1", ",", "R", ",", "D", ",", "N", ",", "N", "}"}};
		
		Assert.assertEquals(expecteds,result);
	}
	
	@Test
	public void testFHConvertArrayToString() {
		String[][] test = {{"{", "0", ",", "0", ",", "R", ",", "D", ",", "$", ",", "D", "}", "-", "{", "1", ",", "0", ",", "B", ",", "S", ",", "N", ",", "N", "}", "-", "{", "2", ",", "0", ",", "R", ",", "D", ",", "N", ",", "N", "}"}, {"{", "0", ",", "1", ",", "B", ",", "F", ",", "N", ",", "N", "}", "-", "{", "1", ",", "1", ",", "G", ",", "D", ",", "N", ",", "N", "}", "-", "{", "2", ",", "1", ",", "R", ",", "D", ",", "N", ",", "N", "}"}};
		String result = this.fh.convertArrayToString(test);
		String expect = "{0,0,R,D,$,D}-{1,0,B,S,N,N}-{2,0,R,D,N,N}\r\n{0,1,B,F,N,N}-{1,1,G,D,N,N}-{2,1,R,D,N,N}\r\n";
		Assert.assertEquals(expect, result);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFHLoadPass() throws Exception {
		this.fh.getFile("H:\\loadTest.txt");
		String[][] expect = {{"{", "0", ",", "0", ",", "R", ",", "D", ",", "$", ",", "D", "}", "-", "{", "1", ",", "0", ",", "B", ",", "S", ",", "#", ",", "N", "}", "-", "{", "2", ",", "0", ",", "R", ",", "D", ",", "N", ",", "N", "}"}, {"{", "0", ",", "1", ",", "B", ",", "F", ",", "N", ",", "N", "}", "-", "{", "1", ",", "1", ",", "G", ",", "D", ",", "N", ",", "N", "}", "-", "{", "2", ",", "1", ",", "R", ",", "D", ",", "N", ",", "N", "}"}};
		
		
		Assert.assertEquals(expect, this.fh.load());
	}
	
	@Test
	public void testFHLoadnoGoal() throws Exception {
		try {
			this.fh.getFile("H:\\loadTest2.txt");
			this.fh.load();
		}catch(Exception ex) {
			this.exception.expectMessage("No goals");
		}
	}
	
	@Test
	public void testFHLoadnoPlayer() throws Exception {
		try {
			this.fh.getFile("H:\\loadTest3.txt");
			this.fh.load();
		}catch(Exception ex) {
			this.exception.expectMessage("No player");
		}
	}
	@Test
	public void testFHLoadTooManyPlayer() throws Exception {
		try {
			this.fh.getFile("H:\\loadTest4.txt");
			this.fh.load();
		}catch(Exception ex) {
			this.exception.expectMessage("Too many player");
		}
	}
	@Test
	public void testFHLoadFileDoesNotExists() throws Exception {
		try {
			this.fh.getFile("H:\\somem.txt");
			this.fh.load();
		}catch(Exception ex) {
			this.exception.expectMessage("File does not exists");
		}
	}
	@Test
	public void testFHLoadWrongFormat() throws Exception {
		try {
			this.fh.getFile("H:\\mazeLevel.xt");
			this.fh.load();
		}catch(Exception ex) {
			this.exception.expectMessage("File format is not valid");
		}
	}
	@Test
	public void testFHSave() throws Exception {
		try {
			String[][] test = {{"{", "0", ",", "0", ",", "R", ",", "D", ",", "$", ",", "D", "}", "-", "{", "1", ",", "0", ",", "B", ",", "S", ",", "#", ",", "N", "}", "-", "{", "2", ",", "0", ",", "R", ",", "D", ",", "N", ",", "N", "}"}, {"{", "0", ",", "1", ",", "B", ",", "F", ",", "N", ",", "N", "}", "-", "{", "1", ",", "1", ",", "G", ",", "D", ",", "N", ",", "N", "}", "-", "{", "2", ",", "1", ",", "R", ",", "D", ",", "N", ",", "N", "}"}};
			
			this.fh.save(test, "H:\\saveTest.txt");
			File file = new File("H:\\saveTest.txt");
			Loader load = new Loader(file);
			String result = load.Fetch();
			String expect = "{0,0,R,D,$,D}-{1,0,B,S,N,N}-{2,0,R,D,N,N}\r\n{0,1,B,F,N,N}-{1,1,G,D,N,N}-{2,1,R,D,N,N}\r\n";
			
			Assert.assertEquals(expect, result);
			
		}catch(Exception ex) {
			this.exception.expectMessage("");
		}
	}
	
}
