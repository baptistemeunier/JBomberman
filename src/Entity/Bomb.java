package Entity;

import java.awt.Graphics2D;

import App.PlayingState;
import State.Bomb.*;
import Utils.Rectangle;

public class Bomb extends Entity {

	private int caseX;
	private int caseY;
	
	private int range;
	private Player player;
	private StateBomb state;

	public Bomb(int x, int y, Player player) {
		this.range = player.getRange();
		this.player = player;
		caseX = (int) x / PlayingState.BLOCK_SIZE;
		caseY = (int) y / PlayingState.BLOCK_SIZE;
		this.x = caseX * PlayingState.BLOCK_SIZE + PlayingState.BLOCK_SIZE/4;
		this.y = caseY * PlayingState.BLOCK_SIZE + PlayingState.BLOCK_SIZE/4;

		state = new WaitState(this);
		state.initialize();
	}

	public int getCaseX() {
		return caseX;
	}

	public int getCaseY() {
		return caseY;
	}

	public void setState(StateBomb s) {
		this.state = s;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isExplode() {
		return this.state instanceof ExploseState;
	}

	public int getRange() {
		return range;
	}

	public void update() {
		state.update();
	}
	
	public void draw(Graphics2D g) {
		state.draw(g);
	}

	public boolean needToBeRemove() {
		return this.isExplode() && ((ExploseState) state).needRemove();
	}

	public Rectangle[] getCollisionBox() {
		if(this.isExplode()) {
			return ((ExploseState) this.state).getCollisionBox();
		}
		return null;
	}
	
}