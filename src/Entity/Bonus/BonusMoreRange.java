package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Player;

public class BonusMoreRange extends Bonus {

	public BonusMoreRange(int x, int y, int width, int height) {
		super(x ,y, width, height);
	}

	public void updatePlayer(Player player) {
		player.addRange();
	}

	public void draw(Graphics g) {
		drawBasic(g);
		g.setColor(Color.BLACK);
		g.drawString("R", x+10, y+15);		
	}

}
