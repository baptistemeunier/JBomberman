package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import State.Bomb.*;
import Utils.Rectangle;
import Map.Map;
/**
 * Class Bomb
 * This class is use to represents a bomb
 * @author baptiste
 */
public class Bomb extends Entity {

	private int range; // Range of explosion
	private Player player; // Player who drop this bomb
	private StateBomb state; // State who handle the bomb's life

	private boolean watingPlayerMove = true;
	private boolean needToBeRemove = false;
	
	/**
	 * Constructor
	 * @param x : x coordinate of where the bomb is drop
	 * @param y : y coordinate of where the bomb is drop
	 * @param player PLayer who drop the bomb
	 */
	public Bomb(int x, int y, Player player) {
		super(x, y);
		this.range = player.getRange();
		this.player = player;
		this.x = caseX * Map.BLOCK_SIZE_X + Map.BLOCK_SIZE_X/4;
		this.y = caseY * Map.BLOCK_SIZE_Y + Map.BLOCK_SIZE_Y/4;
		state = new WaitState(this);
		state.initialize();
	}

	/**
	 * Get the x coordinate of the case
	 * @return the x coordinate of the case
	 */
	public int getCaseX() {
		return caseX;
	}

	/**
	 * Get the y coordinate of the case
	 * @return the y coordinate of the case
	 */
	public int getCaseY() {
		return caseY;
	}

	/**
	 * Get the player who drop the bomb
	 * @return player who drop the bomb
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Set the current state
	 * @param StateBomb s the new state
	 */
	public void setState(StateBomb s) {
		this.state = s;
	}

	/**
	 * Boolean function to know if the bomb is explode
	 * @return true is explode false otherwise
	 */
	public boolean isExplode() {
		return this.state instanceof ExploseState;
	}

	/**
	 * Get the range of the bomb
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * Boolean function who say if the bomb need to be remove of the playground
	 * @return true if bomb need to be remove false otherwise
	 */
	public boolean needToBeRemove() {
		return this.needToBeRemove;
	}


	public void markForRemove() {
		player.addBomb();
		this.needToBeRemove = true;
	}

	/**
	 * Get the collision box of the bomb
	 * @return The collision box
	 */
	public Rectangle[] getCollisionBox() {
		return state.getCollisionBox();
	}
	
	public void setWatingPlayerMove(boolean waiting) {
		watingPlayerMove = waiting;
	}

	public boolean isWatingPlayerMove() {
		return watingPlayerMove;
	}

	/**
	 * Function who update the bomb
	 */
	public void update() {
		state.update();
	}
	
	/**
	 * @see Entity.Entity#draw(java.awt.Graphics2D)
	 **/
	public void draw(Graphics2D g) {
		state.draw(g);
		
		g.setColor(Color.BLUE);
		if( this.getCollisionBox() != null) {
			g.drawRect(this.getCollisionBox()[0].x, this.getCollisionBox()[0].y, this.getCollisionBox()[0].width, this.getCollisionBox()[0].height);			
		}
	}

	public void forceExplosion() {
		if(!isExplode()) {
			state.transition(new ExploseState(this));			
		}

	}

}