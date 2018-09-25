package Menu;

import Utils.Rectangle;

public class MenuItem {
	private String name;
	private Rectangle box;

	public MenuItem(String name) {
		this.name = name;
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
	/**
	 * @param box the box to set
	 */
	public void setBox(Rectangle box) {
		this.box = box;
	}
}
