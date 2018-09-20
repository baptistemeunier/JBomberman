package App;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import Entity.Block;
import Entity.Bomb;
import Entity.EntityRect;
import Entity.Player;
import Utils.Rectangle;

public class PlayingState extends GameState {

	public static int BLOCK_SIZE = 50;
	
	public static int NB_BLOCK_X = 19;
	public static int NB_BLOCK_Y = 13;

	ArrayList<EntityRect> elements;
	ArrayList<Block> blocks;
	ArrayList<Player> players;

	private static PlayingState instance;
	public static PlayingState instance() {
		if(instance == null) {
			PlayingState.instance = new PlayingState();
		}
		return PlayingState.instance;
	}

	@Override
	protected void initialize() {
		players = new ArrayList<Player>();
		players.add(new Player("Player 1", 50, 50)); 
		players.add(new Player("Player 2", 50*17, 50*11));
		
		try {
			MapGenerator.generateMap();
		} catch (Exception e) {
			e.printStackTrace();
			//thread.interrupt();			
		}
	}

	@Override
	protected void release() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void handleEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void update() {
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			it.next().update();
		}
	}

	@Override
	protected void draw(Graphics2D g) {

		MapGenerator.draw(g);
		Iterator<Player> playersIt = players.iterator();
		while(playersIt.hasNext()) {
			playersIt.next().draw(g);
		}
		
		// Grid for debug
		g.setColor(Color.BLACK);
		for(int i=0; i < 19; i++) {
			for(int j=0; j < 13; j++) {
				g.drawRect(i*50, j*50, 50,50);
			}
		}

	}

	public void killPlayer(Bomb bomb) {
		Rectangle[] rects = bomb.getExplosionCollisionRects();
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			Player player = it.next();
			Rectangle rect2 = player.getCollisionBox();
			for(int i = 0; i < rects.length; i++) {
				Rectangle rect1 = rects[i];
				if(rect1.x < rect2.x + rect2.width &&
						rect1.x + rect1.width > rect2.x &&
						rect1.y < rect2.y + rect2.height &&
						rect1.height + rect1.y > rect2.y) {
						   System.out.println(player.getName() + " is a looser !");
						   player.kill();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT)  {
			players.get(0).setLeft(true);
		}
		if(keyCode == KeyEvent.VK_RIGHT)  {
			players.get(0).setRight(true);
		}
		if(keyCode == KeyEvent.VK_UP)  {
			players.get(0).setUp(true);
		}
		if(keyCode == KeyEvent.VK_DOWN)  {
			players.get(0).setDown(true);
		}
		if(keyCode == KeyEvent.VK_SPACE)  {
			players.get(0).dropBomb();
		}
		if(keyCode == KeyEvent.VK_Q)  {
			players.get(1).setLeft(true);
		}
		if(keyCode == KeyEvent.VK_D)  {
			players.get(1).setRight(true);
		}
		if(keyCode == KeyEvent.VK_Z)  {
			players.get(1).setUp(true);
		}
		if(keyCode == KeyEvent.VK_S)  {
			players.get(1).setDown(true);
		}
		if(keyCode == KeyEvent.VK_A)  {
			players.get(1).dropBomb();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_LEFT)  {
			players.get(0).setLeft(false);
		}
		if(keyCode == KeyEvent.VK_RIGHT)  {
			players.get(0).setRight(false);
		}
		if(keyCode == KeyEvent.VK_UP)  {
			players.get(0).setUp(false);
		}
		if(keyCode == KeyEvent.VK_DOWN)  {
			players.get(0).setDown(false);
		}
		if(keyCode == KeyEvent.VK_Q)  {
			players.get(1).setLeft(false);
		}
		if(keyCode == KeyEvent.VK_D)  {
			players.get(1).setRight(false);
		}
		if(keyCode == KeyEvent.VK_Z)  {
			players.get(1).setUp(false);
		}
		if(keyCode == KeyEvent.VK_S)  {
			players.get(1).setDown(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
