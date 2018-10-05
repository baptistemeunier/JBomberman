package Entity;

import java.awt.Graphics2D;

import Utils.Rectangle;

public class EntityRect extends Entity {
	
	protected int width;
	protected int height;

	public EntityRect(int x, int y, int width, int height) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
		
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x, y, width, height);
	}

	public void draw(Graphics2D g) {
		g.drawRect(this.x, this.y, this.width, this.height);
	}
}
