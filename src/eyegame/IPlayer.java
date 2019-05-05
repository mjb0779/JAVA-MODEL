package eyegame;

public interface IPlayer {

	public void getDetails();
	public void playerMove(int x, int y, String col, String shape, String dir);
	public String giveDetails(String newBlock);
}
