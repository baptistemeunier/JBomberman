package Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Entity.Block;
import Entity.Bomb;
import Entity.Player;
import Entity.Bonus.*;
import GameState.PlayingState;
import Utils.Rectangle;

public class Map {

	private static ArrayList<Block> blocks;
	private static ArrayList<Bomb> bombs;

	public static int NB_BLOCK_X = 19;
	public static int NB_BLOCK_Y = 13;

	
	public static ArrayList<Block> generateMap() throws Exception {
		bombs = new ArrayList<Bomb>();
		
		ArrayList<Block> blocksEmpty = generateLayout();
		System.out.println(blocksEmpty.size());
		ArrayList<Block> blocksBonus = generateWall(blocksEmpty);
		generateBonus(blocksBonus);
		
		return blocks;
	}
	
	public static ArrayList<Block> generateEmptyMap() {
		blocks = new ArrayList<Block>();
		
		for (int y = 0; y < NB_BLOCK_Y;y++) {
			for (int x = 0; x < NB_BLOCK_X;x++) {
				Block block = new Block(x*PlayingState.BLOCK_SIZE, y*PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, 48);
				blocks.add(block);
			}
		}
		return blocks;
	}
	
	private static ArrayList<Block> generateLayout() {
		MapFile file = new MapFile("basic");
		blocks = file.load();
		ArrayList<Block> blocksEmpty = new ArrayList<Block>();
		
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.getType() == Block.TYPE_EMPTY) {
				blocksEmpty.add(b);				
			}
		}
		return blocksEmpty;
	}

	private static ArrayList<Block> generateWall(ArrayList<Block> blocksEmpty) {
		ArrayList<Block> blocksBonus = new ArrayList<Block>();
		int emptySize = blocksEmpty.size();
		while(blocksEmpty.size() > emptySize*0.4) {
			int index = (int) Math.floor(Math.random() * blocksEmpty.size());
			Block b = blocksEmpty.get(index);
			b.setType(Block.TYPE_WALL);
			blocksBonus.add(b);
			blocksEmpty.remove(index);
		}		
		return blocksBonus;
	}
	
	private static void generateBonus(ArrayList<Block> blockList) {
		// Probability of bonus spawn
		// 3%  => Kill // 17%  => Range // 10%  => Bomb	// 10% => Speed	// 60% => Nothing
		
		Iterator<Block> it = blockList.iterator();
		while(it.hasNext()) {
			double p = Math.random();
			Block b = it.next();
			if(p < 0.03) {
				b.setBonus(new BonusDeath(b.getX(), b.getY(), b.getWidth(), b.getHeight()));
			} else if (p < 0.20) {
				b.setBonus(new BonusMoreRange(b.getX(), b.getY(), b.getWidth(), b.getHeight()));				
			} else if (p < 0.30) {
				b.setBonus(new BonusMoreSpeed(b.getX(), b.getY(), b.getWidth(), b.getHeight()));				
			} else if (p < 0.40) {
				b.setBonus(new BonusMoreBomb(b.getX(), b.getY(), b.getWidth(), b.getHeight()));				
			}
		}
		
	}

	public static Block checkBlockCollision(Rectangle playerCollision) {	
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.isWall() && b.getCollisionBox().checkCollision(playerCollision)) {
				return b;
			}
		}
		return null;
	}
		
	public static void addBomb(int x, int y, Player player) {
		Bomb bomb = new Bomb(x, y, player);
		bombs.add(bomb);
	}
	
	public static Block getBlock(int x, int y) throws IndexOutOfBoundsException {
		return blocks.get(x + y*NB_BLOCK_X);
	}

	public static Block getBlockFromCoordinate(int x, int y) throws IndexOutOfBoundsException {
		x = (int) x / PlayingState.BLOCK_SIZE;
		y = (int) y / PlayingState.BLOCK_SIZE;
		if(x >= NB_BLOCK_X || y >= NB_BLOCK_Y) {
			throw new IndexOutOfBoundsException();
		}
		return getBlock(x, y);
	}

	public static void update() {
		Iterator<Bomb> it = bombs.iterator();
		while(it.hasNext()) {
			Bomb bomb = it.next();
			bomb.update();
			if(bomb.isExplode()) {
				PlayingState.instance().killPlayer(bomb);					
			}
			if(bomb.needToBeRemove()) {
				bomb.getPlayer().addBomb();
				it.remove();
			}
		}		
	}

	public static void checkBlockBonus(Player player) {
		Rectangle playerCollision = player.getCollisionBox();
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block block = it.next();
			Bonus bonus = block.getBonus();
			if(bonus != null && bonus.getCollisionBox().checkCollision(playerCollision)) {
				block.removeBonus();
				bonus.updatePlayer(player);
			}
		}
	}

	public static void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}

		if(bombs != null) {
			Iterator<Bomb> itBomb = bombs.iterator();
			while(itBomb.hasNext()) {
				itBomb.next().draw(g);
			}			
		}
		
	}

}
