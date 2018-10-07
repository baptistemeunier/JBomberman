package Map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import App.Game;
import Entity.Block;
import Entity.Player;
import Entity.Bonus.*;

public class Map {

	private static ArrayList<Block> blocks;
	private static Game game;
	public static int NB_BLOCK_X;
	public static int NB_BLOCK_Y;
	public static int BLOCK_SIZE_X;
	public static int BLOCK_SIZE_Y;

	
	/**
	 * Create a map for Game instance
	 * @return The block list
	 * @throws Exception
	 */
	public static void generateMap(Game g) throws Exception {
		blocks = generateLayout(blocks);
		game = g;
		ArrayList<Block> blocksWall = generateWall();
		generateBonus(blocksWall);
	}
	
	/**
	 * Create a empty map with the good size
	 * @return The block list
	 */
	public static void generateEmptyMap() {
		blocks = new ArrayList<Block>();
		
		for (int y = 0; y < NB_BLOCK_Y;y++) {
			for (int x = 0; x < NB_BLOCK_X;x++) {
				Block block = new Block(x*BLOCK_SIZE_X, y*BLOCK_SIZE_Y, BLOCK_SIZE_X, BLOCK_SIZE_Y, Block.TYPE_EMPTY);
				blocks.add(block);
			}
		}
	}
	
	/**
	 * Load and populate the game layout
	 * @return The Block list
	 */
	private static ArrayList<Block> generateLayout(ArrayList<Block> blocks) {
		MapFile file = new MapFile("empty");
		blocks = file.load();
		return blocks;
	}

	/**
	 * Put random wall in the playground
	 * @return The Block list
	 */
	private static ArrayList<Block> generateWall() {
		ArrayList<Block> blocksEmpty = new ArrayList<Block>();
		ArrayList<Block> blocksWall = new ArrayList<Block>();
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.getType() == Block.TYPE_EMPTY) {
				blocksEmpty.add(b);				
			}
		}

		int emptySize = blocksEmpty.size();
		while(blocksEmpty.size() > emptySize*0.4) {
			int index = (int) Math.floor(Math.random() * blocksEmpty.size());
			Block b = blocksEmpty.get(index);
			b.setType(Block.TYPE_WALL);
			blocksEmpty.remove(index);
			blocksWall.add(b);
		}
		return blocksWall;
	}
	
	/**
	 * Generate random bonus hidden in the wall
	 * @return The Block list
	 */
	public static void generateBonus(ArrayList<Block> blocksWall) {
			// Probability of bonus spawn
		// 3%  => Kill // 17%  => Range // 10%  => Bomb	// 10% => Speed	// 60% => Nothing
		
		Iterator<Block> it = blocksWall.iterator();
		while(it.hasNext()) {
			double p = Math.random();
			Block b = it.next();
			if(p < 0.02) {
				game.addBonus(new BonusDeath(b.getX(), b.getY(), b.getWidth(), b.getHeight()));
			} else if (p < 0.10) {
				game.addBonus(new BonusMoreRange(b.getX(), b.getY(), b.getWidth(), b.getHeight()));
			} else if (p < 0.20) {
				game.addBonus(new BonusMoreSpeed(b.getX(), b.getY(), b.getWidth(), b.getHeight()));
			} else if (p < 0.30) {
				game.addBonus(new BonusMoreBomb(b.getX(), b.getY(), b.getWidth(), b.getHeight()));
			}
		}
	}

	public static ArrayList<Block> getBlocks() {
		return blocks;
	}

	public static Block getBlock(int x, int y) throws IndexOutOfBoundsException {
		return blocks.get(x + y*NB_BLOCK_X);
	}

	public static Block getBlockFromCoordinate(int x, int y) throws IndexOutOfBoundsException {
		x = (int) x / Map.BLOCK_SIZE_X;
		y = (int) y / Map.BLOCK_SIZE_Y;
		if(x >= NB_BLOCK_X || y >= NB_BLOCK_Y) {
			throw new IndexOutOfBoundsException();
		}
		return getBlock(x, y);
	}

	public static void draw(Graphics2D g) {
		for(Block block : blocks) {
			block.draw(g);
		}
	}

	public static Block checkBlockCollision(Player player) {	
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.isWall() && b.getCollisionBox().checkCollision(player.getCollisionBox())) {
				return b;
			}
		}
		return null;
	}
}
