package donovan.fr;

import donovan.fr.entity.Party;

public class Main {
	public static void main(String[] args) {
		Party party = new Party();
		party.addPlayer("Donovan");
		party.addPlayer("David");
		party.addPlayer("Dorian");
		party.addPlayer("Benjamin");
		party.makeRound();
		party.makeRound();
		party.makeRound();
		int count = 0;
		while (true) {
			System.out.println(count++);
			party.makeRound();
		}
	}
	
}
