package State.Player;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import GameState.PlayingState;
import Map.Map;
import Entity.Block;
import Entity.Entity;
import Entity.Player;
import State.State;

public class RightMove extends StateMove implements State {

	public RightMove(Player player) {
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
			if(keyCode == CONTROL_RIGHT)  {
				transition(new IdleMove(player));
			}
		}
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == CONTROL_LEFT)  {
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
		player.moveRight();
		if(player.getX() + player.getWidth() >= Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE) {
			player.setX(Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE - player.getWidth());
		} else {
			Entity e = Map.checkCollision(player);
			if(e != null) {
				if(e instanceof Block) {
					player.setX(e.getX() - player.getWidth());
				} else {
					player.moveLeft();					
				}
			}
		}
	}

}
