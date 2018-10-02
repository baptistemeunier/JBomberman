package State.Bomb;

import java.awt.AWTEvent;

import Entity.Bomb;
import State.State;
import Utils.Rectangle;

public abstract class StateBomb implements State {

	protected Bomb bomb;

	public StateBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	@Override
	public void handleEvent(AWTEvent event) {
		// TODO Auto-generated method stub
	}

	public void transition(State s) {
		release();
		bomb.setState((StateBomb) s);
		s.initialize();		
	}

	public abstract Rectangle[] getCollisionBox();

}