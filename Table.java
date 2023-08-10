import java.util.ArrayList;
import java.util.Scanner;


class Table{
	
	private ArrayList<CardTeam> cardTeams;
	private ArrayList<Integer> Ai;
	private ArrayList<Integer> Bi;
	private int k; //Number of teams.
	private int m; //Total card amount.
	
	private Scanner input = new Scanner(System.in);
	
	public ArrayList<CardTeam> getCardTeams(){
		return cardTeams;
	}
	
	public Table(int k,int m){
		this.k = k;
		this.m = m;
		
		Ai = new ArrayList<Integer>();
		Bi = new ArrayList<Integer>();
		cardTeams = new ArrayList<CardTeam>();
		
		
		boolean ok = true;
		
		/*
			Initializing all the cardTeams(ArrayList cardTeams) based 
			on the user's input.
		*/
		
		do{
			
			/*
				Initializing the Ai for each team(Ai >= 2).
				The sum of Ais must be equal to m.
			*/
			
			ok = true;
			int sumA = 0;
			
			for(int i = 0; i < k; i++){
				int a = 0;
				System.out.printf("Please enter A%d : " , i);
				a = input.nextInt();
				while(a < 2){
					System.out.printf("A%d has to be >= 2.\n" , i);
					System.out.printf("Please enter A%d : " , i);
					a = input.nextInt();
				}
				
				sumA += a;
				Ai.add(a);
				
			}
			
			if(sumA != m){
				ok = false;
				Ai = new ArrayList<Integer>();
				System.out.println("Ai do not add up to M , please enter Ai");
			}
			
		}while(!ok);
		
		System.out.println();
		
		/*
			Initializing the Bi for each team(Bi < Ai).
		*/
		
		for(int i = 0; i < k; i++){
			int maxRem = 0;
			System.out.printf("Please enter B%d : " , i);
			maxRem = input.nextInt();
			
			while(maxRem >= Ai.get(i)){
				System.out.printf("B%d has to less than A%d " , i,i);
				System.out.printf("Please enter B%d : " , i);
				maxRem = input.nextInt();
			}
			
			Bi.add(maxRem);
		}
		
		//Storing all the cardTeams to the ArrayList "cardTeams".
		
		for(int i = 0; i < k; i++){
			CardTeam tempTeam = new CardTeam(Ai.get(i),Bi.get(i));
			cardTeams.add(tempTeam);
		}
	}
}