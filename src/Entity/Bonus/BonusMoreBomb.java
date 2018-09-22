package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics2D;

import Entity.Player;

public class BonusMoreBomb extends Bonus {

	public BonusMoreBomb(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void updatePlayer(Player player) {
		player.addBombSlot();
	}

	public void draw(Graphics2D g) {
		drawBasic(g);
		g.setColor(Color.BLACK);
		g.drawString("B", x+10, y+15);
	}
}
