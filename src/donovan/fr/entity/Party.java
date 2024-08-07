package donovan.fr.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import donovan.fr.utils.ComparatorOfPlayers;

public class Party {
	private Deck deck;
	private List<Player> players = new ArrayList<Player>();
	
	ComparatorOfPlayers comparatorOfPlayers = new ComparatorOfPlayers();

	
	public Party() {
	}
	
	public void addPlayer(String namePlayer) {
		this.players.add(new Player(namePlayer));
	}
	
	public void makeRound() {
		this.deck = new Deck();
		
		this.deck.shuffleDeckList();
		for (Player currentPlayer : this.players) {
			this.deck.drawHand(currentPlayer);
			System.out.println(currentPlayer);
		}
		
		List<Player> bestPlayers = this.whoWinTheRound();
		
		StringBuilder resultRoundString = new StringBuilder("\n ");
		for (Player currentPlayer : bestPlayers) {
			resultRoundString.append(currentPlayer.getName() + " avec la main " + currentPlayer.getHand() + "\n ");
		}
		if (bestPlayers.size() > 1) {
			resultRoundString.append("on gagner ce round avec une valeur de ");
		} else {
			resultRoundString.append("a gagner ce round avec une valeur de ");
		}
		resultRoundString.append(bestPlayers.getFirst().getHand().getValue());
		System.out.println(resultRoundString);
//		if (bestPlayers.size()>1) {
//			Scanner scanner = new Scanner(System.in);
//			scanner.next();
//		}
	}
	
	public List<Player> whoWinTheRound() {
		List<Player> bestPlayers = new ArrayList<Player>(Arrays.asList(this.players.getFirst()));
		for (int i = 1 ; i < this.players.size() ; i++) {
			Player currentPlayer = this.players.get(i);
			if (comparatorOfPlayers.compare(currentPlayer,bestPlayers.getFirst()) == 1) {
				bestPlayers = new ArrayList<Player>(Arrays.asList(currentPlayer));
			} else if (comparatorOfPlayers.compare(currentPlayer,bestPlayers.getFirst()) == 0) {
				bestPlayers.add(currentPlayer);
			}
		}
		return bestPlayers;
	}
}
