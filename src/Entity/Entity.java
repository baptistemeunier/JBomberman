package Entity;

import java.awt.Graphics2D;


/**
 * Class Entity
 * Use to represent a Entity of the game
 * 
 * @author baptiste
 */
public abstract class Entity {

	protected int x;
	protected int y;

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

	/**
	 * Draw function who give the graphic class to draw Entity
	 * @param Graphics2D g the graphic class use to draw the frame 
	 */
	public abstract void draw(Graphics2D g);

}