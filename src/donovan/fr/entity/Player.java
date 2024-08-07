package donovan.fr.entity;


public class Player {
	private Hand hand;
	private String name;
	private float amount = 120f;
	

	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}
			
	@Override
	public String toString() {
		return String.format(" Le joueur %s a %s %s comme main et %.1f en solde", this.name, this.hand, this.hand.getValue(),this.amount);
	}
}
