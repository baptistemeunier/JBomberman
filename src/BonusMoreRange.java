import java.awt.Color;
import java.awt.Graphics;

public class BonusMoreRange extends Bonus {

	protected BonusMoreRange(int x, int y, int width, int height) {
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
