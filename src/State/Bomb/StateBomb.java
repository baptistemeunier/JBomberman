package State.Bomb;

import Entity.Bomb;
import State.State;
import Utils.Rectangle;

public abstract class StateBomb implements State {

	protected Bomb bomb;

	public StateBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public void transition(State s) {
		release();
		bomb.setState((StateBomb) s);
		s.initialize();		
	}

	public abstract Rectangle[] getCollisionBox();

}