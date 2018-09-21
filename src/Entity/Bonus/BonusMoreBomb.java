package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Player;

public class BonusMoreBomb extends Bonus {

	public BonusMoreBomb(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void updatePlayer(Player player) {
		player.addBomb();
	}

	public void draw(Graphics g) {
		drawBasic(g);
		g.setColor(Color.BLACK);
		g.drawString("B", x+10, y+15);
	}
}
