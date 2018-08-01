package shi.alg.ch23.core;

import java.util.HashMap;
import java.util.Map;

public class GameControl {

	protected GameState state;
	protected Map<Integer, Player> players = new HashMap<Integer, Player>();

	public void SetPlayer(Player player, int key) {
		player.setState(state);
		players.put(key, player);
	}

	public Player getPlayer(int key) {
		return players.get(key);
	}

	public void InitGameState(GameState state) {
		this.state = state;
	}

	public void run() {
		while (!state.isGameOver()) {
			int playerId = state.getCurrentPlayer();
			Player currentPlayer = getPlayer(playerId);
			assert (currentPlayer != null);

			int np = currentPlayer.getNextPosition();
			state.setGameCell(np, playerId);
			state.printGame();
			state.SwitchPlayer();
		}
		int winner = state.getWinner();
		if (Const.PLAYER_NULL == winner) {
			System.out.println("GAME OVER! draw");
		} else {
			Player winnerPlayer = getPlayer(winner);
			System.out.println("Winner is " + winnerPlayer.getPlayerName());
		}
	}

	public static int getPeerPlayer(int player_id) {
		return (player_id == Const.PLAYER_A) ? Const.PLAYER_B : Const.PLAYER_A;
	};

}
