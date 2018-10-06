package Utils;

import App.Game;
import Entity.Block;
import Entity.Bomb;
import Entity.Entity;
import Entity.Player;
import GameState.PlayingState;
import Map.Map;

public class CheckMove {
	public static void checkMoveLeft(Player player) {
		if(player.getX() < 0) {
			player.setX(0);
		} else {
			Entity entity = Game.instance().checkCollision(player);
			if(entity instanceof Block) {
				player.setX(entity.getX() + ((Block )entity).getWidth());
			}
			if(entity instanceof Bomb) {
				player.moveRight();
			}
		}
	}

	public static void checkMoveRight(Player player) {
		if(player.getX() + player.getWidth() >= Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE) {
			player.setX(Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE - player.getWidth());
		} else {
			Entity entity = Game.instance().checkCollision(player);
			if(entity instanceof Block) {
				player.setX(entity.getX() - player.getWidth());
			}
			if(entity instanceof Bomb) {
				player.moveLeft();
			}
		}
	}
	
	public static void checkMoveUp(Player player) {
		if(player.getY() < 0) {
			player.setY(0);
		} else {
			Entity entity = Game.instance().checkCollision(player);
			if(entity instanceof Block) {
				player.setY(entity.getY() + ((Block )entity).getHeight());
			}
			if(entity instanceof Bomb) {
				player.moveDown();
			}
		}
	}
	
	public static void checkMoveDown(Player player) {
		if(player.getY() + player.getHeight() >= Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE) {
			player.setY(Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE - player.getHeight());
		} else {
			Entity entity = Game.instance().checkCollision(player);
			if(entity instanceof Block) {
				player.setY(entity.getY() - player.getHeight());
			}
			if(entity instanceof Bomb) {
				player.moveLeft();
			}
		}
	}
	
}
