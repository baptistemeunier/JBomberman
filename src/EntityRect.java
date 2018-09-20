
import java.awt.Graphics;

public class EntityRect extends Entity {
	
	int width;
	int height;

	public EntityRect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public EntityRect(int width, int height) {
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

	public void draw(Graphics g) {
		g.drawRect(this.x, this.y, this.width, this.height);
	}
}
