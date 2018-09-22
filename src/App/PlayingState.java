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
		MapGenerator.update();
		// Update Bomb
		// Check Bomb collision with player
		// Check Bonus collision with player
		
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
		Rectangle[] rects = bomb.getCollisionBox();
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
		Iterator<Player> playersIt = players.iterator();
		while(playersIt.hasNext()) {
			playersIt.next().handleEvent(event);
		}		
	}
}
