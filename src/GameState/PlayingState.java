package GameState;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import App.GameManager;
import Entity.Bomb;
import Entity.Player;
import Map.Map;
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
	public void initialize() {
		players = new ArrayList<Player>();
		players.add(new Player("Player 1", BLOCK_SIZE + BLOCK_SIZE/4, BLOCK_SIZE + BLOCK_SIZE/4));
		players.add(new Player("Player 2", BLOCK_SIZE*(Map.NB_BLOCK_X-2) + BLOCK_SIZE/4, BLOCK_SIZE*(Map.NB_BLOCK_Y-2) + BLOCK_SIZE/4));
		
		try {
			Map.generateMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		players = null;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
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
		Map.update();
		// Update Bomb
		// Check Bomb collision with player
		// Check Bonus collision with player
		
		if(nb_left <= 1) {
			EndingState.instance().setWinnerName(playerName);
			GameManager.instance().setState(EndingState.instance());
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Map.draw(g);
		
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
	public void handleEvent(AWTEvent event) {	
		Iterator<Player> playersIt = players.iterator();
		while(playersIt.hasNext()) {
			playersIt.next().handleEvent(event);
		}		
	}
}
