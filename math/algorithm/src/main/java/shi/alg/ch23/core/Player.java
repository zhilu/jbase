package shi.alg.ch23.core;

public abstract class Player {

	protected int playerId;
	protected String playerName=null;
	protected GameState state;
	
	public abstract int getNextPosition();

	
	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public GameState getState() {
		return state;
	}


	public void setState(GameState state) {
		this.state = state;
	}
	
	
}
