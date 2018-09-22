package App;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Entity.Block;
import Entity.Bomb;
import Entity.Player;
import Entity.Bonus.*;
import Utils.Rectangle;

public class MapGenerator {

	private static ArrayList<Block> blocks;
	private static ArrayList<Bomb> bombs;

	public static int NB_BLOCK_X = 19;
	public static int NB_BLOCK_Y = 13;

	
	public static ArrayList<Block> generateMap() throws Exception {
		blocks = new ArrayList<Block>();
		bombs = new ArrayList<Bomb>();
		
		ArrayList<Block> blocksEmpty = generateLayout();
		ArrayList<Block> blocksBonus = generateWall(blocksEmpty);
		generateBonus(blocksBonus);
		
		return blocks;
	}
	
	private static ArrayList<Block> generateLayout() {
		ArrayList<Block> blocksEmpty = new ArrayList<Block>();
		
		String game = "1111111111111111111\n1000000000000000001\n1010101010101010101\n1000000000000000001\n1010101010101010101\n1000000000000000001\n1010101010101010101\n1000000000000000001\n1010101010101010101\n1000000000000000001\n1010101010101010101\n1000000000000000001\n1111111111111111111";
		String[] lines = game.split("\n");
		for (int y = 0; y < lines.length;y++) {
			for (int x = 0; x < lines[y].length();x++) {
				int type = (int) lines[y].charAt(x);
				Block block = new Block(x*PlayingState.BLOCK_SIZE, y*PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, type);
				blocks.add(block);
				if(type == Block.TYPE_EMPTY) {
					blocksEmpty.add(block);
				}
			}
		}
		return blocksEmpty;
	}

	private static ArrayList<Block> generateWall(ArrayList<Block> blocksEmpty) {
		ArrayList<Block> blocksBonus = new ArrayList<Block>();
		removeBlockSpawn(blocksEmpty);	// Remove block at spawn to avoid player spawn problem
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

	private static ArrayList<Block> removeBlockSpawn(ArrayList<Block> blocksEmpty) {
		blocksEmpty.remove(0);
		blocksEmpty.remove(0);
		blocksEmpty.remove(NB_BLOCK_X-4);
		blocksEmpty.remove(blocksEmpty.size()-NB_BLOCK_X+1);
		blocksEmpty.remove(blocksEmpty.size()-1);
		blocksEmpty.remove(blocksEmpty.size()-1);
		return blocksEmpty;
	}

	public static Block checkBlockCollision(Rectangle playerCollision) {	
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.getType() != Block.TYPE_EMPTY & b.getCollisionBox().checkCollision(playerCollision)) {
				return b;
			}
		}/*
		Iterator<Bomb> itBomb = bombs.iterator();
		while(itBomb.hasNext()) {
			Bomb bomb = itBomb.next();
			Block block = getBlock(bomb.getCaseX(), bomb.getCaseY());
			if(block.getCollisionBox().checkCollision(playerCollision)) {
				return block;
			}
		}*/
		return null;
	}
		
	public static void addBomb(int x, int y, Player player) {
		Bomb bomb = new Bomb(x, y, player);
		bombs.add(bomb);
	}
	
	public static Block getBlock(int x, int y) {
		return blocks.get(x + y*NB_BLOCK_X);
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

		Iterator<Bomb> itBomb = bombs.iterator();
		while(itBomb.hasNext()) {
			itBomb.next().draw(g);
		}
		
	}
}
