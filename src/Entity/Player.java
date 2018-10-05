package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import App.EventBuffer;
import App.Game;
import State.Player.IdleMove;
import State.Player.StateMove;

public class Player extends EntityRect {

	private int speed = 5;
	private String name;

	@SuppressWarnings("unused")
	private int nb_bombs;
	@SuppressWarnings("unused")
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
		if(Game.instance().addBomb(new Bomb(x+width/4, y+height/4, this))) {
			nb_bombs++;		
		}
	}
	
	public void update() {
		if(EventBuffer.instance().isReleased(KeyEvent.VK_SPACE) && name == "Player 1") {
			this.dropBomb();
		}
 		if(EventBuffer.instance().isReleased(KeyEvent.VK_A) && name == "Player 2") {
 			this.dropBomb();
		}
		stateMove.update();
		//Map.checkBlockBonus(this);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.RED);
		g.fillRect(this.x + this.width/4, this.y + this.height/4, this.width/2, this.height/2);
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

	public void setStateMove(StateMove s) {
		this.stateMove = s;
	}

}