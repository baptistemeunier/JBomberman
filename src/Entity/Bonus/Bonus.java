package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics;

import Entity.EntityRect;
import Entity.Player;

public abstract class Bonus extends EntityRect{

	public Bonus(int x, int y, int width, int height) {
		super(x+width/4, y+height/4, width/2, height/2);
	}

	public abstract void draw(Graphics g);
	
	protected void drawBasic(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

	public abstract void updatePlayer(Player player);
}
