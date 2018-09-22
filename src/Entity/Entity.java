package Entity;

import java.awt.Graphics2D;


public abstract class Entity {

	protected int x;
	protected int y;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void draw(Graphics2D g);

}