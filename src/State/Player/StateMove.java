package State.Player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.Player;
import State.State;

public abstract class StateMove implements State {

	protected int CONTROL_UP;
	protected int CONTROL_DOWN;
	protected int CONTROL_LEFT;
	protected int CONTROL_RIGHT;

	protected Player player;

	public StateMove(Player player) {
		this.player = player;

		if(player.getName() == "Player 1") {
			this.CONTROL_UP = KeyEvent.VK_UP;
			this.CONTROL_DOWN = KeyEvent.VK_DOWN;
			this.CONTROL_LEFT = KeyEvent.VK_LEFT;
			this.CONTROL_RIGHT = KeyEvent.VK_RIGHT;
		}else {
			this.CONTROL_UP = KeyEvent.VK_Z;
			this.CONTROL_DOWN = KeyEvent.VK_S;
			this.CONTROL_LEFT = KeyEvent.VK_Q;
			this.CONTROL_RIGHT = KeyEvent.VK_D;			
		}
	}

	@Override
	public void initialize() {
		this.update();
	}

	public void transition(State s) {
		release();
		player.setStateMove((StateMove) s);
		s.initialize();		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
