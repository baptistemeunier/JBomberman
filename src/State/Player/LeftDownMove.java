package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class LeftDownMove extends StateMove implements State {

	public LeftDownMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_LEFT)) {
			transition(new DownMove(player));
		}

		if(buffer.isReleased(CONTROL_DOWN)) {
			transition(new LeftMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveLeft();
		CheckMove.checkMoveLeft(player);
		player.moveDown();
		CheckMove.checkMoveDown(player);
	}
}
