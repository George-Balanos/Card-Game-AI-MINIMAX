import java.util.ArrayList;
import java.util.Scanner;


class Minimax{

	private MinimaxQueue myQueue;
	private Scanner input;
	private State root;
	
	public Minimax(State root){
		this.root = root;
		this.myQueue = new MinimaxQueue();
		this.myQueue.enqueue(this.root);
		
		this.input = new Scanner(System.in);
		
		
		/*
			Creating the stateTree.First we dequeue a State
			from the MinimaxQueue and calculate all its childStates.
			For each childState we check if it is a terminal State. If
			the previous condition is true then we don't enqueue the 
			childState. Otherwise we enqueue the current childState.
			
			This operation continues until the MinimaxQueue is empty.
		*/
		
		do{
			
			State currentState = this.myQueue.dequeue();
			
			
			if(currentState.isTerminal() == false){
				currentState.findChildStates();
				
				for(int i = 0; i < currentState.getChildStates().size(); i++){
					if(currentState.getChildStates().get(i).isTerminal() == true){
						continue;
					}
					
					this.myQueue.enqueue(currentState.getChildStates().get(i));
				}
				
			}
			
		}while(this.myQueue.isEmpty() == false);
		
	}
	
	public int runMinimax(State currentState,int turn){
		
		/*
			Base case of the recursion : The currentState is terminal and its
			value is calculated by the "calculateStaticValue()" function.
		*/
		
		if(currentState.isTerminal() == true){
			currentState.calculateStaticValue();
			return currentState.getStateValue();
		}
		
		/*
			The value of currentState is calculated based on its childStates values
			which are calculated recursively. If (turn == 1) then MAX is the current 
			"player" and chooses his maximum evaluated childState. If (turn == 0) then 
			MIN is the current "player" and chooses his minimum evaluated childState.
			Finally the current "player" returns his value to his parentState.
		*/
		
		if(turn == 1){
			int maxVal = Integer.MIN_VALUE;

			for(State child: currentState.getChildStates()){

				int val = runMinimax(child,-1);
				if(maxVal < val){
					
					maxVal = val;
					currentState.setStateValue(maxVal);
				}else{

					currentState.setStateValue(maxVal);
				}
				
			}
			
			return maxVal;
			
		}else{
			int minVal = Integer.MAX_VALUE;

			for(State child: currentState.getChildStates()){

				int val = runMinimax(child,1);
				if(minVal > val){
					
					minVal = val;
					currentState.setStateValue(minVal);
				}else{

					currentState.setStateValue(minVal);
				}
				
			}
			
			return minVal;
			
		}
		
		
	}
	
	public void runGame(){
		
		//Create the stateTree.
		runMinimax(this.root,1);
		
		
		State currentState = this.root;
		int turn = 1;
		
		System.out.println();
		System.out.println("Initializing game: ");
		currentState.printCardTeam();
		System.out.println();
		
		
		/*
			MAX is always the first player(turn == 1).
			
			MAX(PC):Chooses his best(maximum) move based on 
			his childStates values. If none of his childStates
			have value equal to 1 then he picks the first of 
			his childStates.
			
			
			MIN(PLAYER):His movement depends on the player's 
			choices.
			
			
		*/
		
		while(currentState.isTerminal() == false){ // Execute the while-loop until there are no cards left to remove(terminal Node).
			if(turn == 1){ //PC's turn.
				
				int selectedState = 0;
				int childVal = -1;
				for(int i = 0; i < currentState.getChildStates().size(); i++){
					if(currentState.getChildStates().get(i).getStateValue() > childVal){
						selectedState = i;
						childVal = currentState.getChildStates().get(i).getStateValue();
					}
				}
				
				
				/*
					The following for-loop is only used to print the move that the PC 
					made. Comment it out if necessary.
				*/
				
				int diffTeam = 0;
				int valDiff = 0;
				for(int i = 0; i < currentState.getCardTeams().size(); i++){
					if(currentState.getCardTeams().get(i).isEqual(currentState.getChildStates().get(selectedState).getCardTeams().get(i))){
						continue;
					}
					
					diffTeam = i;
					valDiff = currentState.getCardTeams().get(i).getCardAmount() - currentState.getChildStates().get(selectedState).getCardTeams().get(i).getCardAmount();
					break;
				}
				
				
				//The game continues from the chosen childState.
				
				currentState = currentState.getChildStates().get(selectedState);
				System.out.println("PC's turn: ");
				
				System.out.printf("PC removed %d card(s) from Team %d\n",valDiff,diffTeam);
				
				
				currentState.printCardTeam();
				
				turn = -1;
				
				//Player's turn.
				
				System.out.println();
				
				
			}else{ //Player's turn.
				
				
				System.out.println("Player's turn: ");
				System.out.println("Make your move (team,amount): ");
				int selectedTeam = input.nextInt();
				int amount = input.nextInt();
				
				
				/*
					maxA indicates the maximum number of cards that the player
					can remove from the team he selected.
				*/
				
				int maxA = 0;
				if(currentState.getCardTeams().get(selectedTeam).getCardAmount() >= currentState.getCardTeams().get(selectedTeam).getMaxCardAmount()){
					maxA = currentState.getCardTeams().get(selectedTeam).getMaxCardAmount();
				}else if(currentState.getCardTeams().get(selectedTeam).getCardAmount() < currentState.getCardTeams().get(selectedTeam).getMaxCardAmount()){
					maxA = currentState.getCardTeams().get(selectedTeam).getCardAmount();
				}
				
				
				/*
					Run the following While-loop until the player
					gives a valid move.
				*/
				
				while((currentState.getCardTeams().get(selectedTeam).hasCards() == false) || (amount > maxA)){
					System.out.println("NOT VALID MOVE!");
					System.out.println("Make your move (team,amount): ");
					selectedTeam = input.nextInt();
					amount = input.nextInt();
				}
				
				
				/*	Creating a copy of the selected team and "removing" the selected amount of cards.
					
					The following for-loop matches the childState that includes the "modified" team
					(that is the cardTeam whose cards was reduced by the given amount(user input)).
					Then the game continues from the chosen childState.
					
				*/
				
				CardTeam tempTeam = new CardTeam(currentState.getCardTeams().get(selectedTeam).getCardAmount()-amount,currentState.getCardTeams().get(selectedTeam).getMaxCardAmount());
				
				State selectedState = null;
				for(int i = 0; i < currentState.getChildStates().size(); i++){
					if(currentState.getChildStates().get(i).getCardTeams().get(selectedTeam).isEqual(tempTeam)){
						selectedState = currentState.getChildStates().get(i);
						break;
					}
				}
				
				currentState = selectedState;
				turn = 1;
				
				//PC's turn.
				
				System.out.println();
			}
		}
		
		if(turn == 1){ // Player drew the last card.
			System.out.println("Player WINS!");
		}else{ // PC drew the last card.
			System.out.println("PC WINS!");
		}
		
	}
}