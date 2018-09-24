package State.Player;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import App.Map;
import Entity.Block;
import Entity.Player;
import State.State;

public class UpMove extends StateMove implements State {

	public UpMove(Player player) {
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
			if(keyCode == CONTROL_UP) {
				transition(new IdleMove(player));
			}
		}
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == CONTROL_RIGHT)  {
				transition(new RightMove(player));
			}
			else if(keyCode == CONTROL_LEFT)  {
				transition(new LeftMove(player));
			}
			else if(keyCode == CONTROL_DOWN)  {
				transition(new DownMove(player));
			}
		}
	}

	@Override
	public void update() {
		player.moveUp();
		if(player.getY() < 0) {
			player.setY(0);
		} else {
			Block b = Map.checkBlockCollision(player.getCollisionBox());
			if(b != null) {
				player.setY(b.getY() + b.getHeight());
			}
		}
	}
}