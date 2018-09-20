import java.awt.Color;
import java.awt.Graphics;

public class BonusMoreBomb extends Bonus {

	protected BonusMoreBomb(int x, int y, int width, int height) {
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
