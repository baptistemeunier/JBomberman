import java.awt.Color;
import java.awt.Graphics;

public class BonusMoreSpeed extends Bonus {
	
	protected BonusMoreSpeed(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void updatePlayer(Player player) {
		player.addSpeed();
	}

	public void draw(Graphics g) {
		drawBasic(g);
		g.setColor(Color.BLACK);
		g.drawString("S", x+10, y+15);
	}

}
