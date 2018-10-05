package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class RightDownMove extends StateMove implements State {

	public RightDownMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_RIGHT)) {
			transition(new DownMove(player));
		}

		if(buffer.isReleased(CONTROL_DOWN)) {
			transition(new RightMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveRight();
		CheckMove.checkMoveRight(player);
		player.moveDown();
		CheckMove.checkMoveDown(player);
	}
}
