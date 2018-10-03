package Menu;

import Utils.Rectangle;

public class MenuItem {
	private String name;
	private Rectangle box;
	private int x;
	private int y;

	public MenuItem(String name, int x, int y) {
		this.name = name;
		this.setX(x);
		this.setY(y);
		this.box = new Rectangle(x-25, y-40, name.length()*45, 50);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the box
	 */
	public Rectangle getBox() {
		return box;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
