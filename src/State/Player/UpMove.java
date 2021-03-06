package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class UpMove extends StateMove implements State {

	public UpMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_UP)) {
			transition(new IdleMove(player));
		}
		
		if(buffer.isPressed(CONTROL_LEFT)) {
			transition(new LeftUpMove(player));
		}

		if(buffer.isPressed(CONTROL_RIGHT)) {
			transition(new RightUpMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveUp();
		CheckMove.checkMoveUp(player);
	}
}