package App;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Entity.Bomb;
import Entity.Entity;
import Entity.Player;
import Entity.Bonus.Bonus;
import GameState.EndingState;
import GameState.PlayingState;
import Map.Map;
import Utils.Rectangle;

public class Game {

	ArrayList<Player> players;
	HashMap<Integer, Bomb> bombs;
	HashMap<Integer, Bonus> bonus;
	
	private static Game instance;

	public static Game instance() {
		if (instance == null) {
			Game.instance = new Game();
		}
		return Game.instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	private Game() {
		bombs = new HashMap<Integer, Bomb>();
		players = new ArrayList<Player>();
		bonus = new HashMap<Integer, Bonus>();
		
		try {
			Map.generateMap(this);
			createPlayers();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void createPlayers() {
		// Create two player
		players.add(new Player("Player 1", Map.BLOCK_SIZE + Map.BLOCK_SIZE/4, Map.BLOCK_SIZE + Map.BLOCK_SIZE/4));
		players.add(new Player("Player 2", Map.BLOCK_SIZE*(Map.NB_BLOCK_X-2) + Map.BLOCK_SIZE/4, Map.BLOCK_SIZE*(Map.NB_BLOCK_Y-2) + Map.BLOCK_SIZE/4));
	}

	public void update() {
		// 0 : Check end game
		if(players.size() <= 1) {
			String name = null;
			if(!players.isEmpty()) {
				name = players.get(0).getName();
			}

			EndingState.instance().setWinnerName(name);
			EndingState.instance().setLastFrame(PlayingState.instance().drawLastFrame());
			GameManager.instance().setState(EndingState.instance());
		}

		// 1 : Update player
		for(Player player: players) {
			player.update();
		}
		
		// 2 : Update bonus
		for(Bonus b: bonus.values()) {
			b.update();
		}

		// 3 : Update Bombs
		Iterator<Bomb> itBombs = bombs.values().iterator();
		while(itBombs.hasNext()) {
			Bomb bomb = itBombs.next();
			if(bomb.needToBeRemove()) {
				itBombs.remove();
			}
			bomb.update();
			Rectangle[] rects = bomb.getCollisionBox();
			if(bomb.isExplode() && rects != null) {
				Iterator<Player> it = players.iterator();
				while(it.hasNext()) {
					Player player = it.next();
					if(player.getCollisionBox().checkCollision(rects)) {
						it.remove();
					}
				}
			}
		}
		
		// 4 : Update block

	}

	public void draw(Graphics2D g) {
		// 1: Draw background
		Map.draw(g);
		// 2 : Draw Entities
		// 2.1 : Draw bonus
		for(Bonus b: bonus.values()) {
			b.draw(g);
		}

		// 2.2 : Draw bombs
		// 3 : Update bomb
		for(Bomb bomb: bombs.values()) {
			bomb.draw(g);
		}

		// 2.3 : Draw player
		for(Player player: players) {
			player.draw(g);
			checkPlayerHitBonus(player);
		}
	}

	public void addBonus(Bonus b) {
		bonus.put(getHashKey(b.getCaseX(), b.getCaseY()), b);
	}

	public boolean addBomb(Bomb bomb) {
		int index = getHashKey(bomb.getCaseX(), bomb.getCaseY());
		if(bombs.containsKey(index)) {
			return false;
		}
		bombs.put(index, bomb);
		return true;
	}

	public Bomb getBomb(int caseX, int caseY) {
		return bombs.get(getHashKey(caseX, caseY));
	}

	public int getHashKey(int caseX, int caseY) {
		return caseX + caseY * Map.NB_BLOCK_X;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Entity checkCollision(Player player) { //,Rectangle playerCollision) {
		Entity b = Map.checkBlockCollision(player);
		if(b != null) {
			return b;
		}
		b = checkBombCollision(player);
		return b;
	}

	private Bomb checkBombCollision(Player player) {	
		for(Bomb b : bombs.values()) {
			Rectangle[] collision = b.getCollisionBox();
			if(!b.isExplode() && player.getCollisionBox().checkCollision(collision)) {
				//&& b.isWatingPlayerMove() && b.getPlayer() == player) {
				return b;
			}
		}
		return null;
	}

	public void revealBonus(int caseX, int caseY) {
		Bonus b = bonus.get(getHashKey(caseX, caseY));
		if(b != null) {
			b.reveal();
		}
	}

	public void removeBonus(int caseX, int caseY) {
		bonus.remove(getHashKey(caseX, caseY));
	}

	public void checkPlayerHitBonus(Player player) {
		Rectangle playerCollision = player.getCollisionBox();
		Iterator<Bonus> itBonus = bonus.values().iterator();
		while(itBonus.hasNext()) {
			Bonus b = itBonus.next();
			if(b.getCollisionBox().checkCollision(playerCollision)) {
				b.updatePlayer(player);
				itBonus.remove();
			}
		}
	}

	public void removePlayer(Player player) {
		players.remove(player);		
	}

}