package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FreeMenu {
	ArrayList<MenuItem> items;
	
	public FreeMenu() {
		items = new ArrayList<MenuItem>();
	}
	
	public void addItem(MenuItem item) {
		items.add(item);
	}

	public String checkItem(MouseEvent event) {
		for (MenuItem item: items) {
			if(item.getBox().checkPoint(event.getX(), event.getY())) {
				return item.getName();
			}
		}
		return null;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Press Start", Font.PLAIN, 40));

		for (MenuItem item: items) {
			g.drawString(item.getName(), item.getX(), item.getY());
		}
	}
}
