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

	public static ArrayList<Block> generateMap() throws Exception {
		blocks = new ArrayList<Block>();

		// Create layout
		ArrayList<Block> bonusBlockList = new ArrayList<Block>();
		String game = "1111111111111111111\n1000000000000000001\n1010101010101010101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1010101010101010101\n1000000000000000001\n1111111111111111111";
		String[] lines = game.split("\n");
		for (int y = 0; y < lines.length;y++) {
			for (int x = 0; x < lines[y].length();x++) {
				int type = (int) lines[y].charAt(x);
				Block block = new Block(x*PlayingState.BLOCK_SIZE, y*PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, type);
				blocks.add(block);
				if(type == Block.TYPE_WALL) {
					bonusBlockList.add(block);
				}
			}
		}
		generateBonus(bonusBlockList);
		
		return blocks;
	}

	private static void generateBonus(ArrayList<Block> blockList) {
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
		// Probability of bonus spawn
		// 3%  => Kill
		// 17%  => Range
		// 10%  => Bomb
		// 10% => Speed
		// 60% => Nothing
		
	}

	public static boolean checkBlockCollision(Rectangle playerCollision) {
		
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block b = it.next();
			if(b.getType() != Block.TYPE_EMPTY) {
				Rectangle rect1 = b.getCollisionBox();
				
				if(rect1.x < playerCollision.x + playerCollision.width &&
					rect1.x + rect1.width > playerCollision.x &&
					rect1.y < playerCollision.y + playerCollision.height &&
					rect1.height + rect1.y > playerCollision.y) {
					   return true;
				}				
			}
		}
		return false;
	}
	
	public static void checkBlockBonus(Player player) {
		Rectangle playerCollision = player.getCollisionBox();
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block block = it.next();
			Bonus bonus = block.getBonus();
			if(bonus != null) {
				Rectangle rect1 = bonus.getCollisionBox();
				if(rect1.x < playerCollision.x + playerCollision.width &&
						rect1.x + rect1.width > playerCollision.x &&
						rect1.y < playerCollision.y + playerCollision.height &&
						rect1.height + rect1.y > playerCollision.y) {
						   block.removeBonus();
						   bonus.updatePlayer(player);
				}
			}
		}
		
	}

	public static void checkEmptyBlock(Bomb bomb) {
		int caseX = bomb.getCaseX();
		int caseY = bomb.getCaseY();
		int deep = bomb.getBlockDistance() + 1;

		int i = 1;
		boolean blocked = false;
		while(i < deep && !blocked) {
			int nbBlockTest = caseX-i + caseY * PlayingState.NB_BLOCK_X;
			if(caseX-i < 0) {
				blocked = true;
				i--;
			} else {
				if(blocks.get(nbBlockTest).getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(blocks.get(nbBlockTest).getType() == Block.TYPE_WALL) {
					blocks.get(nbBlockTest).destroy();
					blocked = true;
				}else {
					i++;					
				}
			}
		}
		bomb.setBlockDistanceLeft((i == deep)?i-1:i);

		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			int nbBlockTest = caseX+i + caseY * PlayingState.NB_BLOCK_X;
			if(caseX+i < 0) {
				blocked = true;
				i--;
			} else {
				if(blocks.get(nbBlockTest).getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(blocks.get(nbBlockTest).getType() == Block.TYPE_WALL) {
					blocked = true;
					blocks.get(nbBlockTest).destroy();
				}else {
					i++;					
				}
			}
		}
		bomb.setBlockDistanceRight((i == deep)?i-1:i);

		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			int nbBlockTest = caseX + (caseY-i) * PlayingState.NB_BLOCK_X;
			if(caseY-i < 0) {
				blocked = true;
				i--;
			} else {
				if(blocks.get(nbBlockTest).getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(blocks.get(nbBlockTest).getType() == Block.TYPE_WALL) {
					blocks.get(nbBlockTest).destroy();
					blocked = true;
				}else {
					i++;					
				}
			}
		}
		bomb.setBlockDistanceUp((i == deep)?i-1:i);

		i = 1;
		blocked = false;
		while(i != deep && !blocked) {
			int nbBlockTest = caseX + (caseY+i) * PlayingState.NB_BLOCK_X;
			if(caseY+i < 0) {
				blocked = true;
				i--;
			} else {
				if(blocks.get(nbBlockTest).getType() == Block.TYPE_SOLID) {
					blocked = true;
					i--;
				}else if(blocks.get(nbBlockTest).getType() == Block.TYPE_WALL) {
					blocks.get(nbBlockTest).destroy();
					blocked = true;
				}else {
					i++;
				}
			}
		}
		bomb.setBlockDistanceDown((i == deep)?i-1:i);

	}

	public static void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
}
