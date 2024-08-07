package donovan.fr.entity;

public class Color {
	private int id;
	private String symbol;
	
	
	
	public Color(int id, String symbol) {
		this.id = id;
		this.symbol = symbol;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.symbol;
	}
}
