package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class DownMove extends StateMove implements State {

	public DownMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_DOWN)) {
			transition(new IdleMove(player));
		}
		
		if(buffer.isPressed(CONTROL_LEFT)) {
			transition(new LeftDownMove(player));
		}

		if(buffer.isPressed(CONTROL_RIGHT)) {
			transition(new RightDownMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveDown();
		CheckMove.checkMoveDown(player);
	}

}
