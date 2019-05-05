package eyegame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

class LoaderSaverGamePlayTest {

	protected File file = new File("H:\\mazeLevel.txt");
	protected File fileToSave = new File("H:\\mazeLevel6.txt");
	protected FileHandler fh = new FileHandler();
	protected GamePlay gp = new GamePlay(this.fh);
	ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testLoaderFetchPass() throws Exception {
		Loader loader = new Loader(this.file);
		String content =  "{0,0,R,D,$,D}-{1,0,B,S,N,N}-{2,0,R,D,N,N}-{0,1,B,F,N,N}-{1,1,G,D,N,N}-{2,1,R,D,N,N}-{0,2,Y,S,N,N}-{1,2,P,L,N,N}-{2,2,R,D,N,N}-{0,3,R,S,N,N}-{1,3,B,S,N,#}-{2,3,B,D,N,N,}-";

		String test = loader.Fetch();
		Assert.assertEquals(content, test);
	}
	
	@Test
	public void testSaverPushPass() throws Exception {
		String content =  "{0,0,R,D,$,D}-{1,0,B,S,N,N}-{2,0,R,D,N,N}-{0,1,B,F,N,N}-{1,1,G,D,N,N}-{2,1,R,D,N,N}-{0,2,Y,S,N,N}-{1,2,P,L,N,N}-{2,2,R,D,N,N}-{0,3,R,S,N,N}-{1,3,B,S,N,#}-{2,3,B,D,N,N,}-";
		Saver save = new Saver();
		save.push(this.fileToSave, content);
		
		Loader loader = new Loader(this.fileToSave);
		String test = loader.Fetch();
		Assert.assertEquals(test, content);
	}
	
	@Test 
	public void testGamePlayWinTrue() {
		this.gp.goals = 0;
		Assert.assertTrue(this.gp.win());
	}
	

	@Test 
	public void testGamePlayWinFalse() {
		this.gp.goals = 1;
		Assert.assertTrue(!this.gp.win());
	}
	
	@Test
	public void testCheckMoveToGoalTrue() {
		String testBlock = "{0,0,R,D,$,D}";
		this.gp.goalsArray = new String[] {"{0,0,R,D,$,D}"};
		Assert.assertTrue(this.gp.checkMoveToGoal(testBlock));
	}
	
	@Test
	public void testCheckMoveToGoalFalse() {
		String testBlock = "{0,1,R,D,$,D}";
		this.gp.goalsArray = new String[] {"{0,0,R,D,$,D}"};
		Assert.assertTrue(!this.gp.checkMoveToGoal(testBlock));
	}
	
	
	@Test
	public void testCheckMovePass() throws Exception {
		String testPlayer = "{0,0,R,D,$,D}";
		this.gp.player = testPlayer;
		this.gp.getPlayer();
		
		Assert.assertTrue(this.gp.checkMove(2, 0, "R", "D"));
		
	}
	
	@Test
	public void testCheckMoveXBackwards() {
		try {
			String testPlayer = "{2,0,R,D,$,D}";
			this.gp.player = testPlayer;
			this.gp.getPlayer();
			this.gp.checkMove(0, 0, "R", "D");
		}catch(Exception ex){
			this.exception.expectMessage("Cannot move backwards.");
		}
	}
	
	@Test
	public void testCheckMoveYBackwards() {
		try {
			String testPlayer = "{2,2,R,D,$,D}";
			this.gp.player = testPlayer;
			this.gp.getPlayer();
			this.gp.checkMove(2, 0, "R", "D");
		}catch(Exception ex){
			this.exception.expectMessage("Cannot move backwards.");
		}
	}
	@Test
	public void testCheckMoveNotSameColorShape() {
		try {
			String testPlayer = "{2,0,R,D,$,D}";
			this.gp.player = testPlayer;
			this.gp.getPlayer();
			this.gp.checkMove(2,3, "B", "S");
		}catch(Exception ex){
			this.exception.expectMessage("Next move must be of same colour or shape.");
		}
	}
	
	@Test
	public void testGetPlayer() throws Exception {
		String[][] holder = this.gp.getMaze("H:\\mazeLevel2.txt");
		this.gp.getBlocks();
		this.gp.getPlayer();
		Assert.assertTrue(this.gp.pl.xPos == 0);
		
	}
	
	@Test 
	public void testChangeBlockPrevPlayerBlock() {
		String test = "0,0,R,D,$,D";
		String expect = "{0,0,R,D,N,N}";
		Assert.assertEquals(expect, this.gp.changeBlock(test));
	}
	
	@Test
	public void testChangeBlockGoalBlock() {
		String test = "0,0,R,D,#,D";
		String expect = "{0,0,R,D,$,N}";
		Assert.assertEquals(expect, this.gp.changeBlock(test));
	}
	
	@Test
	public void testMovePlayerPass() throws Exception {
		this.gp.getMaze("H:\\mazeLevel2.txt");
		this.gp.getBlocks();
		this.gp.getPlayer();
		this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
		
		Assert.assertEquals("{0,2,Y,D,$,D}", this.gp.player);
	}
	
	@Test
	public void testMovePlayerPassMinGoal() throws Exception {
		this.gp.getMaze("H:\\mazeLevel2.txt");
		this.gp.getBlocks();
		this.gp.getPlayer();
		this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
		
		Assert.assertEquals(1, this.gp.goals);
	}
	
	@Test
	public void testMovePlayerBackwards() throws Exception {
		try {
			this.gp.getMaze("H:\\mazeLevel2.txt");
			this.gp.getBlocks();
			this.gp.getPlayer();
			this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
			this.gp.movePlayer(this.gp.takeBOut("{0,0,R,D,N,N}"));
		}catch(Exception ex) {
			this.exception.expectMessage("Cannot move bacwards");
		}		
	}
	
	@Test
	public void testMovePlayerwrongShapeColour() throws Exception {
		try {
			this.gp.getMaze("H:\\mazeLevel2.txt");
			this.gp.getBlocks();
			this.gp.getPlayer();
			this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
			this.gp.movePlayer(this.gp.takeBOut("{1,2,P,L,N,N}"));
		}catch(Exception ex) {
			this.exception.expectMessage("Next move must be of same colour or shape.");
		}		
	}
	@Test
	public void testMovePlayerWin() throws Exception {
		
		this.gp.getMaze("H:\\mazeLevel2.txt");
		this.gp.getBlocks();
		this.gp.getPlayer();
		this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
		this.gp.movePlayer(this.gp.takeBOut("{2,2,R,D,#,N}"));
		
		Assert.assertTrue(this.gp.win());
				
	}
}
