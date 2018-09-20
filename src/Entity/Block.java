package Entity;

import java.awt.Color;
import java.awt.Graphics;

import Entity.Bonus.Bonus;

public class Block extends EntityRect {

	public static int TYPE_SOLID = 49;
	public static int TYPE_WALL = 50;
	public static int TYPE_EMPTY = 48;
	private int type;

	private Bonus bonus = null;

	public Block(int x, int y, int width, int height, int type) {
		super(x, y, width, height);
		this.type = type;
	}
	
	public void draw(Graphics g) {
		if(type == TYPE_SOLID) {
			g.setColor(Color.GRAY);			
		}else if(type == TYPE_WALL) {
			g.setColor(new Color(153, 153, 102));
		}else if(type == TYPE_EMPTY) {
			g.setColor(Color.WHITE);
		}
		g.fillRect(this.x, this.y, this.width, this.height);
		if(type == TYPE_EMPTY) {
			if(bonus != null) bonus.draw(g);
		}
	}

	public int getType() {
		return type;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	public void destroy() {
		this.type = TYPE_EMPTY;
	}

	public Bonus getBonus() {
		return this.bonus;
	}

	public void removeBonus() {
		this.bonus = null;
	}
	
}
