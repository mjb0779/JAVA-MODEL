package eyegame;

import java.io.IOException;

public interface IGamePlay {

	public String[][] getMaze(String newMaze) throws Exception;
	public boolean checkPlayer(String input);
	public boolean checkMove(int x, int y, String col, String shape) throws Exception;
	public boolean checkMoveToGoal(String targetBlock);
	public void getBlocks();
	public void getPlayer();
	public void movePlayer(String targetBlock) throws Exception;
	public String changeBlock(String oldBlock);
	public boolean win();
	public void saveNewMap(String newPath) throws IOException ;
}
