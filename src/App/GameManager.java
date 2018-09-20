package App;

import java.util.ArrayDeque;

public class GameManager {

	/** List of all states load */
	private ArrayDeque<GameState> states;

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
		states.push(state);
		state.initialize();
		GamePanel.instance().addKeyListener(state);
	}
	
	public void popState() {
		if(!states.isEmpty()) {
			states.peek().release();	
			GamePanel.instance().addKeyListener(states.peek());
			states.pop();
			if(!states.isEmpty()) {
				states.peek().resume();
				GamePanel.instance().addKeyListener(states.peek());
			}
		}
	}
}
