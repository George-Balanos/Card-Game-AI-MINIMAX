class CardTeam{
	
	private int cardAmount; //Current card amount(Ni).
	private int maxCardAmount; //Max card amount we can remove(Bi).
	
	public CardTeam(int cardAmount,int maxCardAmount){
		this.cardAmount = cardAmount;
		this.maxCardAmount = maxCardAmount;
	}
	
	//Setters-Getters:
	public int getMaxCardAmount(){
		return this.maxCardAmount;
	}
	
	public int getCardAmount(){
		return this.cardAmount;
	}
	
	/*
		Decreases the "cardAmount" by the given parameter("amount"). 
	*/
	
	public void removeCards(int amount){
		this.cardAmount = this.cardAmount - amount;
	}
	
	
	/*
		Two cardTeams are equal if they have the same "cardAmount"(current amount of cards) 
		and the same max card-amount removal(maxCardAmount).
	*/
	
	public boolean isEqual(CardTeam other){
		if(this.cardAmount == other.getCardAmount() && this.maxCardAmount == other.getMaxCardAmount()){
			return true;
		}
		
		return false;
	}
	
	
	/*
		If the cardTeam doesn't have any cards the function returns False,
		otherwise it returns True.
	*/
	
	public boolean hasCards(){
		if(cardAmount > 0){
			return true;
		}
		
		return false;
	}
	
}