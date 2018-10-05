package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class LeftUpMove extends StateMove implements State {

	public LeftUpMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_LEFT)) {
			transition(new UpMove(player));
		}

		if(buffer.isReleased(CONTROL_UP)) {
			transition(new LeftMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveLeft();
		CheckMove.checkMoveLeft(player);
		player.moveUp();
		CheckMove.checkMoveUp(player);
	}
}
