package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;

public class IdleMove extends StateMove implements State {

	public IdleMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isPressed(CONTROL_LEFT)) {
			transition(new LeftMove(player));
		}
		
		if(buffer.isPressed(CONTROL_RIGHT)) {
			transition(new RightMove(player));
		}
		
		if(buffer.isPressed(CONTROL_UP)) {
			transition(new UpMove(player));
		}
		
		if(buffer.isPressed(CONTROL_DOWN)) {
			transition(new DownMove(player));
		}		
	}

}
