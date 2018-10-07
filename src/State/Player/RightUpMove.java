package State.Player;

import App.EventBuffer;
import Entity.Player;
import State.State;
import Utils.CheckMove;

public class RightUpMove extends StateMove implements State {

	public RightUpMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	public void handleEvent() {
		EventBuffer buffer = EventBuffer.instance();
		if(buffer.isReleased(CONTROL_RIGHT)) {
			transition(new UpMove(player));
		}

		if(buffer.isReleased(CONTROL_UP)) {
			transition(new RightMove(player));
		}
	}

	@Override
	public void update() {
		handleEvent();
		player.moveRight();
		CheckMove.checkMoveRight(player);
		player.moveUp();
		CheckMove.checkMoveUp(player);
	}
}
