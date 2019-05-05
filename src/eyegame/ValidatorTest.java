package eyegame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class ValidatorTest {

	protected FileHandler fh;
	ExpectedException exception = ExpectedException.none();
	
	
	@Test
	public void testValidatorExistsTrue() {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file); 
		boolean result = mv.doesFileExists(file);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testValidatorExistsFalse() {
		File file = new File("H:\\mazeLevel4.txt");
		MapValidator mv = new MapValidator(file); 
		boolean result = mv.doesFileExists(file);
		Assert.assertFalse(result);
	}
	
	@Test
	public void testValidatorExtensionPass() {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		String ex = mv.getFileExtension(file);
		Assert.assertEquals(".txt", ex);
	}
	
	@Test
	public void testValidatorExtensionFail() {
		
		File file = new File("H:\\mazeLevel.xt");
		MapValidator mv = new MapValidator(file);
		String ex = mv.getFileExtension(file);
		
		this.exception.expectMessage("");
	}
	
	@Test
	public void testValidatorIsFileValidPass() throws Exception {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		Assert.assertTrue(mv.isFileValid());
	
	}
	
	@Test
	public void testValidatorIsFileValidFormatFail() throws Exception{
		try {
			File file = new File("H:\\mazeLevel.xt");
			MapValidator mv = new MapValidator(file);
		}catch(Exception ex) {
			Assert.assertEquals(ex.getMessage(), "File format is not valid");
		}
	}
	
	@Test
	public void testValidatorIsFileValidFileDoesNotExists() throws Exception{
		try {
			File file = new File("H:\\mazeLevel5.txt");
			MapValidator mv = new MapValidator(file);
		}catch(Exception ex) {
			Assert.assertEquals(ex.getMessage(), "File does not exists");
		}
	}
	
	@Test
	public void testValidatorCheckGoalPass() throws Exception {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		Loader load = new Loader(file);
		String content = load.Fetch();
		Assert.assertTrue(mv.checkGoal(content));
	}
	
	@Test
	public void testValidatorCheckGoalNoGoals() throws Exception {
		try {
			File file = new File("H:\\mazeLevel3.txt");
			MapValidator mv = new MapValidator(file);
			Loader load = new Loader(file);
			String content = load.Fetch();
			mv.checkGoal(content);
		}catch(Exception ex) {
			Assert.assertEquals(ex.getMessage(), "No goals detected");
		}
	}
	
	@Test
	public void testValidatorCheckGoalCorrectGoals() throws Exception {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		Loader load = new Loader(file);
		String content = load.Fetch();
		if(mv.checkGoal(content)) {
			Assert.assertEquals(1, mv.goalNum);
		}
	}
	
	@Test
	public void testValidatorCheckPlayerPass() throws Exception {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		Loader load = new Loader(file);
		String content = load.Fetch();
		Assert.assertTrue(mv.checkPlayer(content));
	}
	
	@Test
	public void testValidatorCheckPlayerNoPlayer() {
		try {
			File file = new File("H:\\mazeLevel3.txt");
			MapValidator mv = new MapValidator(file);
			Loader load = new Loader(file);
			String content = load.Fetch();
			mv.checkPlayer(content);
		}catch(Exception ex){
			Assert.assertEquals(ex.getMessage(), "No player");
		}
	}
	
	@Test
	public void testValidatorCheckPlayerTooManyPlayer() {
		try {
			File file = new File("H:\\mazeLevel5.txt");
			MapValidator mv = new MapValidator(file);
			Loader load = new Loader(file);
			String content = load.Fetch();
			mv.checkPlayer(content);
		}catch(Exception ex){
			Assert.assertEquals(ex.getMessage(), "Too many players");
		}
	}
	
	@Test
	public void testValidatorCheckPlayerPlayerNum() throws Exception {
		File file = new File("H:\\mazeLevel.txt");
		MapValidator mv = new MapValidator(file);
		Loader load = new Loader(file);
		String content = load.Fetch();
		if(mv.checkPlayer(content)) {
			Assert.assertEquals(1, mv.playerNum);
		}
	}

}
