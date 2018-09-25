package Menu;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import App.GamePanel;
import Utils.Rectangle;

public class VerticalMenu {
	ArrayList<MenuItem> items;
	
	public VerticalMenu() {
		items = new ArrayList<MenuItem>();
	}
	
	public void addItem(MenuItem item) {
		items.add(item);
		int x = GamePanel.WIDTH/2;
		int y = GamePanel.HEIGHT/(items.size()+1);
		int i = 1;
		
		for (MenuItem it: items) {
			it.setBox(new Rectangle(x-50, y*i-30, item.getName().length()*30, 40));
			i++;
		}
	}

	public String checkItem(AWTEvent event) {
		if(event.getID() == MouseEvent.MOUSE_CLICKED) {
			MouseEvent mEvent = (MouseEvent) event;
			for (MenuItem item: items) {
				if(item.getBox().checkPoint(mEvent.getX(), mEvent.getY())) {
					return item.getName();
				}
			}
		}
		return null;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		int x = GamePanel.WIDTH/2;
		int y = GamePanel.HEIGHT/(items.size()+1);
		int i = 1;
		
		Font oldFont = g.getFont();
		Font font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);

		for (MenuItem item: items) {
			g.setFont(font);
			g.drawString(item.getName(), x-50, y*i);
			item.getBox().draw(g);
			i++;
		}
		g.setFont(oldFont);		
	}
}
