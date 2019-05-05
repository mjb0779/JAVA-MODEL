package eyegame;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Controller con = new Controller(new FileHandler());
		try{
			con.go();
		}catch(Exception ex) {
			throw new Exception(ex);
		}
	}

}
