package Utils;

import App.Game;
import Entity.Block;
import Entity.Entity;
import Entity.Player;
import GameState.PlayingState;
import Map.Map;

public class CheckMove {
	public static void checkMoveLeft(Player player) {
		if(player.getX() < 0) {
			player.setX(0);
		} else {
			Entity entity = checkCollision(player);
			if(entity != null) {
				player.setX(entity.getX() + ((Block )entity).getWidth());
			}
		}
	}

	public static void checkMoveRight(Player player) {
		if(player.getX() + player.getWidth() >= Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE) {
			player.setX(Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE - player.getWidth());
		} else {
			Entity entity = checkCollision(player);
			if(entity != null) {
				player.setX(entity.getX() - player.getWidth());
			}
		}
	}
	
	public static void checkMoveUp(Player player) {
		if(player.getY() < 0) {
			player.setY(0);
		} else {
			Entity entity = checkCollision(player);
			if(entity != null) {
				player.setY(entity.getY() + ((Block )entity).getHeight());
			}
		}
	}
	
	public static void checkMoveDown(Player player) {
		if(player.getY() + player.getHeight() >= Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE) {
			player.setY(Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE - player.getHeight());
		} else {
			Entity entity = checkCollision(player);
			if(entity != null) {
				player.setY(entity.getY() - player.getHeight());
			}
		}
	}
	
	public static Entity checkCollision(Player player) {
		Entity entity = Game.instance().checkCollision(player);
		if(entity != null && entity instanceof Block) {
			return entity;
		}
		return null;
	}
}
