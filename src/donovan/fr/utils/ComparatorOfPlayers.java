package donovan.fr.utils;

import java.util.Comparator;
import java.util.List;

import donovan.fr.entity.Player;

public class ComparatorOfPlayers implements Comparator<Player> {

	@Override
	public int compare(Player player1, Player player2) {
		List<Integer> valueHandPlayer1 = player1.getHand().getValue();
		List<Integer> valueHandPlayer2 = player2.getHand().getValue();
		for (int i = 0; i < Math.min(valueHandPlayer1.size(), valueHandPlayer2.size()); i++) {
			Integer currentValuePlayer1 = valueHandPlayer1.get(i);
			Integer currentValuePlayer2 = valueHandPlayer2.get(i);
			if (currentValuePlayer1 > currentValuePlayer2) {
				return 1;
			} else if (currentValuePlayer1 < currentValuePlayer2){
				return -1;
			}
		}
		return 0;
	}

}
