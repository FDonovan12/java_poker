package donovan.fr.entity;

public class Value {
	private int id;
	private String name;

	public Value(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
