import java.util.ArrayList;

class State{
	
	private int max; // 1 : MAX
					 //-1 : MIN
	
	private int stateValue; // 1 or -1.
	
	private State parentState;
	private ArrayList<State> childStates;
	
	private ArrayList<CardTeam> cardTeams;
	
	public State(int max,State parentState,ArrayList<CardTeam> cardTeams){
		this.max = max;
		this.parentState = parentState;
		this.cardTeams = cardTeams;
		
		this.childStates = new ArrayList<State>();
	}
	
	
	//Setters-Getters:
	public int getMax(){
		return max;
	}
	
	public void setStateValue(int stateValue){
		this.stateValue = stateValue;
	}
	
	public ArrayList<State> getChildStates(){
		return this.childStates;
	}
	
	public ArrayList<CardTeam> getCardTeams(){
		return cardTeams;
	}
	
	public State getParentState(){
		return parentState;
	}
	
	public int getStateValue(){
		return stateValue;
	}
	
	
	/*
		Helper function for printing the cardTeams of the current State.
	*/
	
	public void printCardTeam(){
		
		for(int i = 0; i < cardTeams.size(); i++){
			System.out.printf("Team %d ",i);
			System.out.println(cardTeams.get(i).getCardAmount()+"\n");
		}
		
	}
	
	
	
	/*
		The following function finds and assigns the "childStates"
		to the current State(ArrayList childStates). It calculates 
		all possible "moves" that can be made , based on each cardTeam's
		current card amount(1 to min(Bi,Ni)).
		
		Each childState corresponds to one of the possible "moves"
		that can be made from the current State.
		
		
	*/
	
	public void findChildStates(){
		for(int i = 0; i < cardTeams.size(); i++){
			if(cardTeams.get(i).hasCards() == true){
				
				// tempMoves declares the maximum number of cards that can be removed.
				
				int tempMoves = cardTeams.get(i).getMaxCardAmount();
				if(cardTeams.get(i).getCardAmount() < tempMoves){
					tempMoves = cardTeams.get(i).getCardAmount();
				}
				
				for(int j = 0; j < tempMoves; j++){
					State tempChild = null;
					ArrayList<CardTeam> tempTeams = new ArrayList<CardTeam>();
					
					for(int l = 0; l < cardTeams.size(); l++){ // Copying all the cardTeams despite the cardTeam which 
															   // has the card-amount that has to be removed.
						if(l != i){
							CardTeam tempCardTeam = new CardTeam(cardTeams.get(l).getCardAmount(),cardTeams.get(l).getMaxCardAmount());
							tempTeams.add(tempCardTeam);
						}else{
							CardTeam tempCardTeam = new CardTeam(cardTeams.get(l).getCardAmount()-(j+1),cardTeams.get(l).getMaxCardAmount());
							tempTeams.add(tempCardTeam);
						}
					}
					
					tempChild = new State((-1)*max,this,tempTeams); // If parent-node is MAX(1) then all of its children-nodes has to be MIN(-1).
					childStates.add(tempChild);
				}	
			}
		}
	}


	/*
		Two States are equal if they have the same total amount of cards
		and their cardTeams are also equal.
	*/
	
	public boolean isEqual(State other){
		if(this.cardTeams.size() != other.getCardTeams().size()){
			return false;
		}
		
		for(int i = 0; i < cardTeams.size(); i++){
			if(this.cardTeams.get(i).isEqual(other.getCardTeams().get(i)) == false){
				return false;
			}
		}
		
		return true;
		
	}
	
	
	
	/*
		If the current State is terminal then its value 
		is calculated based on its parent-node. If the 
		parent-node is MAX(1) then MAX was the one who 
		drew the last card from the table and the static value 
		for the current node is equal to 1.Otherwise the static 
		value of the current node is set to -1(MIN won).
	*/
	
	public int calculateStaticValue(){
		if(parentState == null){
			return this.stateValue;
		}
		
		if(parentState.getMax() == 1){//Parent Max.
			this.stateValue = 1;
			return this.stateValue;
		}
		
		this.stateValue = -1; 		  //Parent Min.
		return this.stateValue;
	}
	
	
	/*
		If there are no cards left then the current node is 
		terminal node(returns True).
	*/
	
	public boolean isTerminal(){
		for(CardTeam team : cardTeams){
			if(team.hasCards() == true){
				return false;
			}
		}
		
		return true;
	}
	
}