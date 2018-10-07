package Entity;

import java.awt.Graphics2D;

import Map.Map;

/**
 * Class Entity
 * Use to represent a Entity of the game
 * 
 * @author baptiste
 */
public abstract class Entity {

	protected int x;
	protected int y;
	protected int caseX;
	protected int caseY;

	Entity(int x, int y) {
		caseX = (int) x / Map.BLOCK_SIZE_X;
		caseY = (int) y / Map.BLOCK_SIZE_Y;
	}

	/**
	 * Get the x coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Get the y coordinate
	 * @return the y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Set the x coordinate
	 * @param x the new x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the y coordinate
	 * @param y the new y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	public int getCaseX() {
		return caseX;
	}
	
	public void setCaseX(int caseX) {
		this.caseX = caseX;
	}
	
	public int getCaseY() {
		return caseY;
	}
	
	public void setCaseY(int caseY) {
		this.caseY = caseY;
	}

	/**
	 * Draw function who give the graphic class to draw Entity
	 * @param Graphics2D g the graphic class use to draw the frame 
	 */
	public abstract void draw(Graphics2D g);

}