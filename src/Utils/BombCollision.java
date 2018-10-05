package Utils;

import GameState.PlayingState;
import Map.Map;

import java.util.ArrayList;

import App.Game;
import Entity.Block;
import Entity.Bomb;

public class BombCollision {

	private Bomb bombMain;
	private Rectangle[] collisionBox = null;
	private ArrayList<Rectangle> collisionBoxArrayList;
	private ArrayList<Block> blocks;

	public BombCollision(Bomb b) {
		this.bombMain = b;
	}
	
	public Rectangle[] getCollisionBox() {
		return collisionBox;
	}

	public void createCollisionBox() {
		blocks = new ArrayList<Block>();
		
		collisionBoxArrayList = new ArrayList<Rectangle>();
		createBox(bombMain);
		for(Block block: blocks) {
			if(block.getType() == Block.TYPE_WALL) {
				block.destroy();
			}else {
				Game.instance().removeBonus(block.getCaseX(), block.getCaseY());				
			}
		}

		collisionBox = collisionBoxArrayList.toArray(new Rectangle[collisionBoxArrayList.size()]);

	}

	private void createBox(Bomb b) {
		int deep = b.getRange() + 1;
		
		int r = checkSide(b, -1, 0);
		int sizeLeft = ((r == deep)?r-1:r)*PlayingState.BLOCK_SIZE;
	
		r = checkSide(b, +1, 0);
		int sizeRight = ((r == deep)?r-1:r)*PlayingState.BLOCK_SIZE;
	
		r = checkSide(b, 0, -1);
		int sizeUp = ((r == deep)?r-1:r)*PlayingState.BLOCK_SIZE;
	
		r = checkSide(b, 0, +1);
		int sizeDown = ((r == deep)?r-1:r)*PlayingState.BLOCK_SIZE;
		
		// Final step create the box
		int x = b.getX();
		int y = b.getY();

		collisionBoxArrayList.add(new Rectangle(x-PlayingState.BLOCK_SIZE/4-sizeLeft, y-PlayingState.BLOCK_SIZE/4, PlayingState.BLOCK_SIZE+sizeLeft+sizeRight, PlayingState.BLOCK_SIZE));
		collisionBoxArrayList.add(new Rectangle(x-PlayingState.BLOCK_SIZE/4, y-PlayingState.BLOCK_SIZE/4-sizeUp, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE+sizeUp+sizeDown));
	}

	private int checkSide(Bomb b, int dx, int dy) {
		int caseX = b.getCaseX();
		int caseY = b.getCaseY();
		int deep = b.getRange() + 1;

		int i = 1;
		boolean blocked = false;
		while(i < deep && !blocked) {
			if(caseX-i < 0) {
				blocked = true;
				i--;
			} else {
				Block block = Map.getBlock(caseX + dx*i, caseY + dy);
				Bomb bomb = Game.instance().getBomb(caseX, caseY);
				if(bomb != null) {
					if(!bomb.needToBeRemove() && bomb != this.bombMain) {
						bomb.markForRemove();
						createBox(bomb);						
					}
				}
				if(block.getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(block.getType() == Block.TYPE_WALL) {
					if(!blocks.contains(block)) {
						blocks.add(block);						
					}
					blocked = true;
				}else {
					if(!blocks.contains(block)) {
						blocks.add(block);						
					}
					i++;
				}
			}
		}
		return i;
	}
}
