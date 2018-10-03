package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import Entity.Bonus.Bonus;
import Sprite.SpriteLoader;
import Utils.FontLoader;

/**
 * Class Block
 * This class is use to represents a tile of the map
 * @author baptiste
 */
public class Block extends EntityRect {
	
	public static final int TYPE_EMPTY = 48;
	public static final int TYPE_SOLID = 49;
	public static final int TYPE_WALL = 50;
	public static final int TYPE_SPAWN = 51;
	public static final int TYPE_RESERVED = 52;	
	private int type; // Type of current tile

	private Bonus bonus = null; // Bonus on the tile (if exist)

	/**
	 * Constructor
	 * @param x top Left corner coordinate on x
	 * @param y top Left corner coordinate on y
	 * @param width Width of the tile
	 * @param height Height of the tile 
	 * @param type Type of tile
	 */
	public Block(int x, int y, int width, int height, int type) {
		super(x, y, width, height);
		this.type = type;
	}
	
	/**
	 * Draw the tile
	 * @see Entity.EntityRect#draw(java.awt.Graphics2D)
	 **/
	public void draw(Graphics2D g) {
		SpriteLoader sl = SpriteLoader.instance();
		if(type == TYPE_SOLID) {
			g.drawImage(sl.getStrite("tiles", "block"), this.x, this.y, this.width, this.height, null, null);
		}else if(type == TYPE_WALL) {
			g.drawImage(sl.getStrite("tiles", "wall"), this.x, this.y, this.width, this.height, null, null);
		}else {
			g.drawImage(sl.getStrite("tiles", "grass"), this.x, this.y, this.width, this.height, null, null);
		}
		
		
		if(type == TYPE_SPAWN) {
			g.setColor(Color.red);
			g.setFont(new Font("Arial",Font.PLAIN, 10));
		    g.drawString("Spawn", this.x+5, this.y+this.height/2);			
		    FontLoader.resetFont(g);
		}else if(type == TYPE_RESERVED) {
			g.setColor(Color.red);
		    g.draw(new Line2D.Double(this.x, this.y, this.x+this.width-1, this.y+this.height-1));
		    g.draw(new Line2D.Double(this.x+this.width-1, this.y, this.x, this.y+this.height-1));
		}
		
		if(type == TYPE_EMPTY && bonus != null) {
			bonus.draw(g);
		}
	}

	/**
	 * Get the tile type
	 * @return The type of the tile
	 */
	public int getType() {
		return type;
	}

	/**
	 * Change the tile type
	 * @param int type The new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Get the tile bonus
	 * @return The bonus of the tile
	 */
	public Bonus getBonus() {
		return this.bonus;
	}

	/**
	 * Change the tile bonus
	 * @param Bonus bonus The new bonus
	 */
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	/**
	 * Remove the tile bonus
	 */
	public void removeBonus() {
		if(bonus != null) {
			bonus.remove();
			bonus = null;			
		}
		
	}

	/**
	 * Destroy the tile (i.e set the type to empty type)
	 * Use when the tile is break by a bomb (for example)
	 */
	public void destroy() {
		this.type = TYPE_EMPTY;
		if(bonus != null) {
			bonus.launchAnimation();
		}
	}

	public boolean isWall() {
		return this.type == TYPE_WALL || this.type == TYPE_SOLID;
	}

}
