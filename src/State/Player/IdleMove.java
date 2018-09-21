package State.Player;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import Entity.Player;
import State.State;

public class IdleMove extends StateMove implements State {

	public IdleMove(Player player) {
		super(player);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleEvent(AWTEvent event) {
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == CONTROL_RIGHT)  {
				transition(new RightMove(player));
			}
			else if(keyCode == CONTROL_LEFT)  {
				transition(new LeftMove(player));
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
		// TODO Auto-generated method stub
		
	}

}
