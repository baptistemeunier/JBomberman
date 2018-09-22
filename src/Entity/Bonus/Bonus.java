package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.EntityRect;
import Entity.Player;

public abstract class Bonus extends EntityRect{

	public Bonus(int x, int y, int width, int height) {
		super(x+width/4, y+height/4, width/2, height/2);
	}
	
	protected void drawBasic(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

	public abstract void updatePlayer(Player player);
}
