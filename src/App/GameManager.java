package App;

import java.util.ArrayDeque;

import GameState.GameState;

public class GameManager {

	/** List of all states load */
	private ArrayDeque<GameState> states;

	private static GameManager instance;
	public static GameManager instance() {
		if(instance == null) {
			GameManager.instance = new GameManager();
		}
		return GameManager.instance;
	}

	public GameManager() {
		states = new ArrayDeque<GameState>();
	}

	public GameState currentState() {
		if(states.isEmpty()) return null;
		return states.peek();
	}

	public void pushState(GameState state) {
		if(!states.isEmpty()) {
			states.peek().pause();
		}
		state.initialize();
		states.push(state);
	}

	public void popState() {
		if(!states.isEmpty()) {
			states.peek().release();	
			states.pop();
			if(!states.isEmpty()) {
				states.peek().resume();
			}
		}
	}

	public void setState(GameState state) {
		popState();
		pushState(state);
	}
}
