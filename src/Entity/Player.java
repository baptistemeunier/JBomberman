package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import App.GamePanel;
import App.MapGenerator;

public class Player extends EntityRect {

	private int dx = 0;
	private int dy = 0;
	private int speed = 5;
	private int maxBomb = 5;
	private int range = 1;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private ArrayList<Bomb> bombs;
	private String name;
	private boolean alive = true;

	public Player(String name, int x, int y) {
		super(x, y, 30, 30);
		bombs = new ArrayList<Bomb>();
		this.name = name;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void dropBomb() {
		if(this.alive && bombs.size() != maxBomb) {			
			bombs.add(new Bomb(x, y, range));
		}
	}
	
	public void update() {
		if(this.alive) {
			if(left) {
				dx = -speed;
			}
			if(right) {
				dx = speed;
			}
			if(up) {
				dy = -speed;
			}
			if(down) {
				dy = speed;
			}
			
			int oldx = x;
			int oldy = y;
			x += dx;
			y += dy;
			
			if(x < this.width/2) x = this.width/2;
			if(y < this.height/2) y = this.height/2;
			if(x > GamePanel.WIDTH - this.width/2) x = GamePanel.WIDTH - this.width/2;
			if(y > GamePanel.HEIGHT - this.height/2) y = GamePanel.HEIGHT - this.height/2;
			if(MapGenerator.checkBlockCollision(this.getCollisionBox()))
			{
				x = oldx;
				y = oldy;
			}
			dx = 0;
			dy = 0;
	
			Iterator<Bomb> it = bombs.iterator();
			while(it.hasNext()) {
				Bomb current = it.next();
				current.update();
				if(current.needToBeRemove()) {
					it.remove();
				}
			}
			
			MapGenerator.checkBlockBonus(this);
		}
	}
	
	public void draw(Graphics2D g) {
		if(this.alive) {
			g.setColor(Color.BLUE);
			g.fillRect(this.x, this.y, this.width, this.height);
			g.setColor(Color.RED);
			g.fillRect(this.x + this.width/4, this.y + this.height/4, this.width/2, this.height/2);
		
			Iterator<Bomb> it = bombs.iterator();
			while(it.hasNext()) {
				it.next().draw(g);
			}
		}
	}

	public void addRange() {
		this.range++;
	}

	public void addSpeed() {
		this.speed++;
		
	}

	public void addBomb() {
		this.maxBomb++;
	}

	public String getName() {
		return name;
	}

	public void kill() {
		this.alive = false;
	}

	public boolean isAlive() {
		return this.alive;
	}

}