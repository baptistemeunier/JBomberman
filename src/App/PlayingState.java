package App;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import Entity.Bomb;
import Entity.Player;
import Utils.Rectangle;

public class PlayingState extends GameState {

	public static int BLOCK_SIZE = 50;

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
		players.add(new Player("Player 1", BLOCK_SIZE + BLOCK_SIZE/4, BLOCK_SIZE + BLOCK_SIZE/4));
		players.add(new Player("Player 2", BLOCK_SIZE*(MapGenerator.NB_BLOCK_X-2) + BLOCK_SIZE/4, BLOCK_SIZE*(MapGenerator.NB_BLOCK_Y-2) + BLOCK_SIZE/4));
		
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
		players = null;
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
	protected void update() {
		// Move player
		Iterator<Player> it = players.iterator();
		int nb_left = 0;
		String playerName = null;

		while(it.hasNext()) {
			Player p = it.next();
			p.update();
			if(p.isAlive()) {
				nb_left++;
				playerName = p.getName();
			}
		}	
		// Update Bomb
		// Check Bomb collision with player
		// Check Bonus collision with player
		
		it = players.iterator();
		while(it.hasNext()) {
			Player p = it.next();
			if(p.isAlive()) {
				Iterator<Bomb> itBombs;
				itBombs = p.getBombs().iterator();
				while(itBombs.hasNext()) {
					Bomb b = itBombs.next();
					if(b.isExplode()) {
						killPlayer(b);						
					}
				}
			}
		}		
		if(nb_left <= 1) {
			EndingState.instance().setWinnerName(playerName);
			GameManager.instance().setState(EndingState.instance());
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
			for(int i = 0; i < rects.length; i++) {
				if(rects[i].checkCollision(player.getCollisionBox())) {
					player.kill();
				}
			}
		}
	}

	@Override
	protected void handleEvent(AWTEvent event) {
		if(event.getID() == KeyEvent.KEY_RELEASED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			System.out.println("KEY_RELEASED :" + keyCode);
		} else if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			System.out.println("KEY_PRESSED :" + keyCode);			
		} else if(event.getID() == KeyEvent.KEY_TYPED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			System.out.println("KEY_TYPED :" + keyCode);			
		} else {
			System.out.println("INCONNU");
		}
		
		Iterator<Player> playersIt = players.iterator();
		while(playersIt.hasNext()) {
			playersIt.next().handleEvent(event);
		}		
	}
}
