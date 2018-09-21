package Entity;

import java.awt.Color;
import java.awt.Graphics;

import App.GamePanel;
import App.MapGenerator;
import App.PlayingState;
import Utils.Rectangle;

public class Bomb extends Entity {

	private int caseX;
	private int caseY;
	
	private int frameBeforeExplode = GamePanel.FPS * 2;
	private boolean explode = false;
	private int frameBeforeDelete = GamePanel.FPS;
	private boolean delete = false;
	private int blockDistance;
	private int blockDistanceLeft = 0;
	private int blockDistanceRight = 0;
	private int blockDistanceUp = 0;
	private int blockDistanceDown = 0;
	private Player player;

	public Bomb(int x, int y, Player player) {
		this.blockDistance = player.getRange();
		this.player = player;
		caseX = (int) x / PlayingState.BLOCK_SIZE;
		caseY = (int) y / PlayingState.BLOCK_SIZE;
		this.x = caseX * PlayingState.BLOCK_SIZE + PlayingState.BLOCK_SIZE/4;
		this.y = caseY * PlayingState.BLOCK_SIZE + PlayingState.BLOCK_SIZE/4;
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

	public Player getPlayer() {
		return player;
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
				MapGenerator.checkEmptyBlock(this);
			}
		}
	}
	
	public Rectangle[] getExplosionCollisionRects() {
		int sizeLeft = blockDistanceLeft*PlayingState.BLOCK_SIZE;
		int sizeRight = blockDistanceRight*PlayingState.BLOCK_SIZE;
		int sizeUp = blockDistanceUp*PlayingState.BLOCK_SIZE;
		int sizeDown = blockDistanceDown*PlayingState.BLOCK_SIZE;
		Rectangle[] rects = {
				new Rectangle(x-PlayingState.BLOCK_SIZE/4-sizeLeft, y-PlayingState.BLOCK_SIZE/4, PlayingState.BLOCK_SIZE+sizeLeft+sizeRight, PlayingState.BLOCK_SIZE),
				new Rectangle(x-PlayingState.BLOCK_SIZE/4, y-PlayingState.BLOCK_SIZE/4-sizeUp, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE+sizeUp+sizeDown)
		};
		return rects;
	}
	
	public void draw(Graphics g) {
		if(explode) {
			g.setColor(Color.RED);
			Rectangle[] rects = getExplosionCollisionRects();
			g.fillRect(rects[0].x+10, rects[0].y+10, rects[0].width-20, rects[0].height-20);
			g.fillRect(rects[1].x+10, rects[1].y+10, rects[1].width-20, rects[1].height-20);
		}else {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, 30, 30);			
		}
	}
}