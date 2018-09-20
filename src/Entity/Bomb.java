package Entity;

import java.awt.Color;
import java.awt.Graphics;

import App.GamePanel;
import Utils.Rectangle;

public class Bomb extends Entity {

	private int caseX;
	private int caseY;
	
	private int frameBeforeExplode = GamePanel.FPS * 2;
	private boolean explode = false;
	private int frameBeforeDelete = GamePanel.FPS * 2;
	private boolean delete = false;
	private int blockDistance;
	private int blockDistanceLeft = 0;
	private int blockDistanceRight = 0;
	private int blockDistanceUp = 0;
	private int blockDistanceDown = 0;
	
	Bomb(int x, int y, int range) {
		this.blockDistance = range;
		caseX = (int) x/GamePanel.BLOCK_SIZE;
		caseY = (int) y/GamePanel.BLOCK_SIZE;
		this.x = caseX*GamePanel.BLOCK_SIZE + GamePanel.BLOCK_SIZE/4;
		this.y = caseY*GamePanel.BLOCK_SIZE + GamePanel.BLOCK_SIZE/4;

	}

	public int getCaseX() {
		return caseX;
	}

	public int getCaseY() {
		return caseY;
	}

	public int getBlockDistance() {
		return blockDistance;
	}

	
	public int getBlockDistanceLeft() {
		return blockDistanceLeft;
	}

	public void setBlockDistanceLeft(int blockDistanceLeft) {
		this.blockDistanceLeft = blockDistanceLeft;
	}

	public int getBlockDistanceRight() {
		return blockDistanceRight;
	}

	public void setBlockDistanceRight(int blockDistanceRight) {
		this.blockDistanceRight = blockDistanceRight;
	}

	public int getBlockDistanceUp() {
		return blockDistanceUp;
	}

	public void setBlockDistanceUp(int blockDistanceUp) {
		this.blockDistanceUp = blockDistanceUp;
	}

	public int getBlockDistanceDown() {
		return blockDistanceDown;
	}

	public void setBlockDistanceDown(int blockDistanceDown) {
		this.blockDistanceDown = blockDistanceDown;
	}

	public boolean isExplode() {
		return this.explode;
	}

	public boolean needToBeRemove() {
		return this.delete;
	}

	public void update() {
		if(explode) {
			frameBeforeDelete--;
			if(frameBeforeDelete == 0) {
				delete = true;
			}	

		}else {
			frameBeforeExplode--;
			if(frameBeforeExplode == 0) {
				explode = true;
				GamePanel.instance().checkEmptyBlock(this);
				GamePanel.instance().killPlayer(this);
			}
		}
	}
	
	public Rectangle[] getExplosionCollisionRects() {
		int sizeLeft = blockDistanceLeft*GamePanel.BLOCK_SIZE;
		int sizeRight = blockDistanceRight*GamePanel.BLOCK_SIZE;
		int sizeUp = blockDistanceUp*GamePanel.BLOCK_SIZE;
		int sizeDown = blockDistanceDown*GamePanel.BLOCK_SIZE;
		Rectangle[] rects = {
				new Rectangle(x-GamePanel.BLOCK_SIZE/4-sizeLeft, y-GamePanel.BLOCK_SIZE/4, GamePanel.BLOCK_SIZE+sizeLeft+sizeRight, GamePanel.BLOCK_SIZE),
				new Rectangle(x-GamePanel.BLOCK_SIZE/4, y-GamePanel.BLOCK_SIZE/4-sizeUp, GamePanel.BLOCK_SIZE, GamePanel.BLOCK_SIZE+sizeUp+sizeDown)
		};
		return rects;
	}
	
	public void draw(Graphics g) {
		if(explode) {
			Rectangle[] rects = getExplosionCollisionRects();
			g.fillRect(rects[0].x+10, rects[0].y+10, rects[0].width-20, rects[0].height-20);
			g.fillRect(rects[1].x+10, rects[1].y+10, rects[1].width-20, rects[1].height-20);
		}else {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, 20, 20);			
		}
	}

}