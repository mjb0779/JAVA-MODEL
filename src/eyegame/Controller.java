package eyegame;


public class Controller {

	protected FileHandler fh;
	protected GamePlay gp;
	
	public Controller(FileHandler theFh) {
		this.fh = theFh;
	}
	
	public void go() throws Exception {
		try{
//			this.fh = new FileHandler();
//			this.fh.getFile("H:\\mazeLevel2.txt");
//			String[][] output = this.fh.load();
//			System.out.println("2D array");
//			for(String[] innerArray : output) {
//				for(String data : innerArray) {
//					System.out.print(data);
//				}
//				System.out.println("\r\n");
//			}
//			
//			System.out.println("String");
//		
//			String samp = this.fh.convertArrayToString(output);
//			System.out.println(samp);
//			
//			
//			String[] test = samp.split("-");
//			for(String data: test) {
//				System.out.print(data + " ");
//			}
			
			this.gp = new GamePlay(this.fh);
			
			for(String[] innerArray: this.gp.getMaze("H:\\mazeLevel2.txt")) {
				for(String data : innerArray) {
					System.out.print(data);
				}
				System.out.println("\r\n");
			}
			this.gp.getBlocks();
			this.gp.getPlayer();
			//System.out.println("Number of goals: " + this.gp.goals);
			
			
			
//////////////MOVE Test			
			System.out.println("Old Player Pos:");
			System.out.println(this.gp.player);

			//System.out.println(this.gp.pl.xPos);
			System.out.println("\r\n\r\n++++++++++++MOVING++++++++++++\r\n\r\n");
			this.gp.movePlayer(this.gp.takeBOut("{0,2,Y,D,#,N}"));
//			if (this.gp.tester == true) {
//				
//			}
			System.out.println("New Player Pos:");
			System.out.println(this.gp.player + "\r\n");
			System.out.println("Prints the maze but not in the correct format as the same as above");
			int i = 0;
			for(String[] innerArray: this.gp.map) {
				for(String data : innerArray) {
					i++;
					if(i == 42) {
						data = "\r\n";
						System.out.print(data);
						i = 0;
					}else {
						System.out.print(data);
					}

				}
				
				System.out.println("\r\n");
			}
		
			System.out.println("Number of goals: " + this.gp.goals);
			if(this.gp.win()) {
				System.out.println("YOU WIN!!");
			}

//////////////WIN TEST
			System.out.println("\r\n");
			System.out.println("\r\n\r\n++++++++++++MOVING++++++++++++\r\n\r\n");
			this.gp.movePlayer(this.gp.takeBOut("{2,2,R,D,#,N}"));
			
			System.out.println("New Player Pos:");
			System.out.println(this.gp.player + "\r\n");
			System.out.println("Prints the maze but not in the correct format as the same as above");
			for(String[] innerArray: this.gp.map) {
				for(String data : innerArray) {
					i++;
					if(i == 42) {
						data = "\r\n";
						System.out.print(data);
						i = 0;
					}else {
						System.out.print(data);
					}
				}
				System.out.println("\r\n");
			}
			System.out.println("Number of goals: " + this.gp.goals);
			if(this.gp.win()) {
				System.out.println("YOU WIN!!");
			}
			
			
//////////////SAVER TEST			
			this.gp.saveNewMap("H:\\mazeLevel3.txt");
			
			
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		
	}

}
