package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics2D;

import Animation.BonusAnimation;
import Entity.EntityRect;
import Entity.Player;

public abstract class Bonus extends EntityRect{

	BonusAnimation animation;

	public Bonus(int x, int y, int width, int height) {
		super(x+width/6, y+height/6, width/6*4, height/6*4);
	}

	protected void drawBasic(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

	public abstract void updatePlayer(Player player);

	public void remove() {
		if(animation != null) {
			animation.setFinish(true);			
		}
	}

	public abstract void launchAnimation();
	
	public void draw(Graphics2D g) {
		if(animation != null) {
			g.drawImage(animation.getFrame(), x, y, width, height, null, null);			
		}
	}
}
