package Entity;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import App.Map;
import State.Player.IdleMove;
import State.Player.StateMove;

public class Player extends EntityRect {

	private int speed = 5;
	private String name;
	private boolean alive = true;

	private int nb_bombs;
	private int maxBomb = 5;
	private int range = 1;

	StateMove stateMove;


	public Player(String name, int x, int y) {
		super(x, y, 30, 30);

		this.name = name;
		stateMove = new IdleMove(this);
		stateMove.initialize();
	}

	/**
	 * Method for moving the player to the right
	 * WARNING : No collision check or something else
	 * Don't forget to check collision
	 */
	public void moveRight() {
		this.x += speed;
	}

	/**
	 * Method for moving the player to the left
	 * WARNING : No collision check or something else
	 * Don't forget to check collision
	 */
	public void moveLeft() {
		this.x -= speed;
	}

	/**
	 * Method for moving the player to the left
	 * WARNING : No collision check or something else
	 * Don't forget to check collision
	 */
	public void moveUp() {
		this.y -= speed;
	}

	/**
	 * Method for moving the player to the left
	 * WARNING : No collision check or something else
	 * Don't forget to check collision
	 */
	public void moveDown() {
		this.y += speed;
	}

	public int getRange() {
		return range;
	}
	
	public void dropBomb() {
		if(this.alive && nb_bombs != maxBomb) {
			Map.addBomb(x+width/4, y+height/4, this);
			nb_bombs++;
		}
	}
	
	public void update() {
 		if(this.alive) {
 			stateMove.update();
			Map.checkBlockBonus(this);
 		}
	}
	
	public void draw(Graphics2D g) {
		if(this.alive) {
			g.setColor(Color.BLUE);
			g.fillRect(this.x, this.y, this.width, this.height);
			g.setColor(Color.RED);
			g.fillRect(this.x + this.width/4, this.y + this.height/4, this.width/2, this.height/2);
		}
	}

	public void addRange() {
		this.range++;
	}

	public void addSpeed() {
		this.speed++;
		
	}
	public void addBomb() {
		this.nb_bombs++;
	}

	public void addBombSlot() {
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

	public void handleEvent(AWTEvent event) {
		if(event.getID() == KeyEvent.KEY_RELEASED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == KeyEvent.VK_SPACE && name == "Player 1")  {
				this.dropBomb();
			}
			if(keyCode == KeyEvent.VK_A && name == "Player 2")  {
				this.dropBomb();
			}
		}
		this.stateMove.handleEvent(event);
	}

	public void setStateMove(StateMove s) {
		this.stateMove = s;
	}

}