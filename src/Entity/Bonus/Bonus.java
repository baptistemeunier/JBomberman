package Entity.Bonus;

import java.awt.Color;
import java.awt.Graphics;
import Entity.EntityRect;
import Entity.Player;
import Exception.NoBonusExistsException;

public abstract class Bonus extends EntityRect{

	public static int NO_BONUS = 48;
	public static int BONUS_MORE_RANGE = 49;
	public static int BONUS_MORE_SPEED = 50;
	public static int BONUS_MORE_BOMB = 51;

	protected Bonus(int x, int y, int width, int height) {
		super(x+width/4, y+height/4, width/2, height/2);
	}

	public static Bonus generateBonus(int bonus_id, int x, int y, int width, int height) throws NoBonusExistsException {
		if(bonus_id == NO_BONUS) return null;
		if(bonus_id == BONUS_MORE_RANGE) return new BonusMoreRange(x, y, width, height);
		if(bonus_id == BONUS_MORE_SPEED) return new BonusMoreSpeed(x, y, width, height);
		if(bonus_id == BONUS_MORE_BOMB) return new BonusMoreBomb(x, y, width, height);
		throw new NoBonusExistsException("Bonus with id " + bonus_id + " not exists");
	}

	public abstract void draw(Graphics g);
	
	protected void drawBasic(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

	public abstract void updatePlayer(Player player);
}
