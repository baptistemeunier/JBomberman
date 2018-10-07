package Utils;

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
		
		Block block = Map.getBlock(b.getCaseX(), b.getCaseY());
		if(!blocks.contains(block)) {
			blocks.add(block);						
		}

		int r = checkSide(b, -1, 0);
		int sizeLeft = ((r == deep)?r-1:r)*Map.BLOCK_SIZE_X;
	
		r = checkSide(b, +1, 0);
		int sizeRight = ((r == deep)?r-1:r)*Map.BLOCK_SIZE_X;
	
		r = checkSide(b, 0, -1);
		int sizeUp = ((r == deep)?r-1:r)*Map.BLOCK_SIZE_Y;
	
		r = checkSide(b, 0, +1);
		int sizeDown = ((r == deep)?r-1:r)*Map.BLOCK_SIZE_Y;
		
		// Final step create the box
		int x = b.getX();
		int y = b.getY();

		collisionBoxArrayList.add(new Rectangle(x-Map.BLOCK_SIZE_X/4-sizeLeft, y-Map.BLOCK_SIZE_Y/4, Map.BLOCK_SIZE_X+sizeLeft+sizeRight, Map.BLOCK_SIZE_Y));
		collisionBoxArrayList.add(new Rectangle(x-Map.BLOCK_SIZE_X/4, y-Map.BLOCK_SIZE_Y/4-sizeUp, Map.BLOCK_SIZE_X, Map.BLOCK_SIZE_Y+sizeUp+sizeDown));
	}

	private int checkSide(Bomb b, int dx, int dy) {
		System.out.println("Case main : " + b.getCaseX() + "," + b.getCaseY());
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
				Bomb bomb = Game.instance().getBomb(caseX + dx*i, caseY + dy);
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

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

}
