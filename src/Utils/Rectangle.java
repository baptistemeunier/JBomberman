package Utils;

import java.awt.Graphics2D;

public class Rectangle {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean checkCollision(Rectangle rect1) {
		if(rect1.x < x + width &&
				rect1.x + rect1.width > x &&
				rect1.y < y + height &&
				rect1.height + rect1.y > y) {
				   return true;
		}
		return false;
	}

	public boolean checkPoint(int x, int y) {
		if(this.x < x &&
				this.x + this.width > x &&
				this.y < y &&
				this.height + this.y > y) {
				   return true;
		}
		return false;
	}

	public void draw(Graphics2D g) {
		g.drawRect(x, y, width, height);
	}
}