package Utils;

import GameState.PlayingState;
import Map.Map;
import Entity.Block;
import Entity.Bomb;

public class BombCollision {

	private Bomb bomb;
	private Rectangle[] collisionBox = null;
	
	public BombCollision(Bomb b) {
		this.bomb = b;
	}
	
	public Rectangle[] getCollisionBox() {
		return collisionBox;
	}

	public void createCollisionBox() {

		int caseX = bomb.getCaseX();
		int caseY = bomb.getCaseY();
		int deep = bomb.getRange() + 1;
	
		int i = 1;
		boolean blocked = false;
		while(i < deep && !blocked) {
			if(caseX-i < 0) {
				blocked = true;
				i--;
			} else {
				Block block = Map.getBlock(caseX - i, caseY);
				if(block.getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(block.getType() == Block.TYPE_WALL) {
					block.destroy();
					blocked = true;
				}else {
					i++;
					block.removeBonus();
				}
			}
		}
		int sizeLeft = ((i == deep)?i-1:i)*PlayingState.BLOCK_SIZE;
	
		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			if(caseX+i < 0) {
				blocked = true;
				i--;
			} else {
				Block block = Map.getBlock(caseX + i, caseY);
				if(block.getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(block.getType() == Block.TYPE_WALL) {
					blocked = true;
					block.destroy();
				}else {
					i++;
					block.removeBonus();
				}
			}
		}
		int sizeRight = ((i == deep)?i-1:i)*PlayingState.BLOCK_SIZE;
	
		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			if(caseY-i < 0) {
				blocked = true;
				i--;
			} else {
				Block block = Map.getBlock(caseX, caseY-i);
				if(block.getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(block.getType() == Block.TYPE_WALL) {
					block.destroy();
					blocked = true;
				}else {
					i++;
					block.removeBonus();
				}
			}
		}
		int sizeUp = ((i == deep)?i-1:i)*PlayingState.BLOCK_SIZE;
	
		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			if(caseY+i < 0) {
				blocked = true;
				i--;
			} else {
				Block block = Map.getBlock(caseX, caseY + i);
				if(block.getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(block.getType() == Block.TYPE_WALL) {
					block.destroy();
					blocked = true;
				}else {
					i++;
					block.removeBonus();
				}
			}
		}
		int sizeDown = ((i == deep)?i-1:i)*PlayingState.BLOCK_SIZE;
		
		// Final step create the box
		int x = bomb.getX();
		int y = bomb.getY();
		collisionBox = new Rectangle[2];
		collisionBox[0] = new Rectangle(x-PlayingState.BLOCK_SIZE/4-sizeLeft, y-PlayingState.BLOCK_SIZE/4, PlayingState.BLOCK_SIZE+sizeLeft+sizeRight, PlayingState.BLOCK_SIZE);
		collisionBox[1] = new Rectangle(x-PlayingState.BLOCK_SIZE/4, y-PlayingState.BLOCK_SIZE/4-sizeUp, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE+sizeUp+sizeDown);
	}

}
