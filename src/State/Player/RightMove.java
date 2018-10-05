package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class RightMove extends StateMove implements State {

	public RightMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub		
	}
	
	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_RIGHT)) {
			transition(new IdleMove(player));
		}
		
		if(buffer.isPressed(CONTROL_UP)) {
			transition(new RightUpMove(player));
		}
		
		if(buffer.isPressed(CONTROL_DOWN)) {
			transition(new RightDownMove(player));
		}
	}
	
	@Override
	public void update() {
		handleEvent();
		player.moveRight();
		CheckMove.checkMoveRight(player);
	}

}
