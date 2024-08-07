package donovan.fr.entity;

public class Card {
	private Value value;
	private Color color;

	public Card(Value value, Color color) {
		this.value = value;
		this.color = color;
	}
	
	public Value getValue() {
		return this.value;
	}
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", this.value, this.color);
	}
}
