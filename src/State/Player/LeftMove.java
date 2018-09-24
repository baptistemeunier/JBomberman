package State.Player;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import App.Map;
import Entity.Block;
import Entity.Player;
import State.State;

public class LeftMove extends StateMove implements State {

	public LeftMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleEvent(AWTEvent event) {
		if(event.getID() == KeyEvent.KEY_RELEASED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == CONTROL_LEFT)  {
				transition(new IdleMove(player));
			}
		}
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == CONTROL_RIGHT)  {
				transition(new RightMove(player));
			}
			else if(keyCode == CONTROL_UP)  {
				transition(new UpMove(player));
			}
			else if(keyCode == CONTROL_DOWN)  {
				transition(new DownMove(player));
			}
		}
	}

	@Override
	public void update() {
		player.moveLeft();
		if(player.getX() < 0) {
			player.setX(0);
		} else {
			Block b = Map.checkBlockCollision(player.getCollisionBox());
			if(b != null) {
				player.setX(b.getX() + b.getWidth());
			}
		}
	}
}
