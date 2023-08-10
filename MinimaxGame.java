import java.util.Scanner;

class MinimaxGame{
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter 'k'(number of teams) and 'm'(total amount of cards): ");
		int k = input.nextInt();
		int m = input.nextInt();
		
		Table myTable = new Table(k,m);
		
		State root = new State(1,null,myTable.getCardTeams());
	
		Minimax myMinimax = new Minimax(root);
		myMinimax.runGame();
		
	}
	
}