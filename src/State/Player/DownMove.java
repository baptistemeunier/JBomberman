package State.Player;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import App.MapGenerator;
import App.PlayingState;
import Entity.Block;
import Entity.Player;
import State.State;

public class DownMove extends StateMove implements State {

	public DownMove(Player player) {
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
			if(keyCode == CONTROL_DOWN)  {
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
			else if(keyCode == CONTROL_UP)  {
				transition(new UpMove(player));
			}
		}
	}

	@Override
	public void update() {
		player.moveDown();
		if(player.getY() + player.getHeight() >= MapGenerator.NB_BLOCK_X*PlayingState.BLOCK_SIZE) {
			player.setY(MapGenerator.NB_BLOCK_Y*PlayingState.BLOCK_SIZE + player.getHeight());
		} else {
			Block b = MapGenerator.checkBlockCollision(player.getCollisionBox());
			if(b != null) {
				player.setY(b.getY()- player.getHeight());				
			}
		}
	}

}
