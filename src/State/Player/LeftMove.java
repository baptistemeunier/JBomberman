package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class LeftMove extends StateMove implements State {

	public LeftMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_LEFT)) {
			transition(new IdleMove(player));
		}
		
		if(buffer.isPressed(CONTROL_UP)) {
			transition(new LeftUpMove(player));
		}
		
		if(buffer.isPressed(CONTROL_DOWN)) {
			transition(new LeftDownMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveLeft();
		CheckMove.checkMoveLeft(player);
	}
}
