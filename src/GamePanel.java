import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static int WIDTH = 1020;
	public static int HEIGHT = 720;
	public static int BLOCK_SIZE = 50;
	public static int FPS = 30;
	public static int NB_BLOCK_X = 19;
	public static int NB_BLOCK_Y = 13;

	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private double averageFPS;
	private int frame;
	
	ArrayList<EntityRect> elements;
	ArrayList<Block> blocks;
	
	ArrayList<Player> players;
		
	private static GamePanel instance;
	public static GamePanel instance() {
		if(instance == null) {
			GamePanel.instance = new GamePanel();
		}
		return GamePanel.instance;
	}

	private GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		

		players = new ArrayList<Player>();
		players.add(new Player("Player 1 win", 50, 50)); 
		players.add(new Player("Player 2 win", 50*17, 50*11));
		
		try {
			createBackground();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			thread.interrupt();
			
		}
		
	}	

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		this.addKeyListener(this);
	}

	public void run() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 30;
		
		long targetTime = 1000 / FPS;

		while(running) {
			
			startTime = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			if(waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				}catch(InterruptedException e) {
					
				}				
			}
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			frame++;
			if(frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	private void gameUpdate() {
		Iterator<Player> it = players.iterator();
		while(it.hasNext()) {
			it.next().update();
		}
	}

	private void gameRender() {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawString("TEST String", 100, 100);
		g.setColor(Color.red);
		g.drawString("Frame : " + frame, 20, HEIGHT-10);
		g.drawString("FPS : " + (int) averageFPS, 20, HEIGHT-20);

		g.setColor(Color.BLACK);
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}

		// Grid for debug
		Iterator<EntityRect> it2 = elements.iterator();
		g.setColor(Color.BLACK);
		while(it2.hasNext()) {
			it2.next().draw(g);
		}

		Iterator<Player> it3 = players.iterator();
		while(it3.hasNext()) {
			it3.next().draw(g);
		}
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
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
	
	private void createBackground() throws Exception {
		elements = new ArrayList<EntityRect>();
		blocks = new ArrayList<Block>();
		// Create grid for debug
		for(int i=0; i < 19; i++) {
			for(int j=0; j < 13; j++) {
				elements.add(new EntityRect(i*50, j*50, 50,50));
			}
		}

		// Create layout
		String game = "1111111111111111111\n1000000000000000001\n1010101010101010101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1012121212121212101\n1002222222222222001\n1010101010101010101\n1000000000000000001\n1111111111111111111";
		String bonusMaps = "0000000000000000000\n0000000000000000030\n0000000000000000030\n0111111111111111110\n0000000000000000000\n0000000000000000000\n0000000000000000000\n0000000000000000000\n0000000000000000000\n0222222222222222220\n0000000000000000000\n0000000000000000000\n0000000000000000000";
		String[] lines = game.split("\n");
		String[] linesBonus = bonusMaps.split("\n");
		for (int y = 0; y < lines.length;y++) {
			for (int x = 0; x < lines[y].length();x++) {
				int type = (int) lines[y].charAt(x);
				int bonus = (int) linesBonus[y].charAt(x);
				blocks.add(new Block(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, type, bonus));
			}
		}
	}

	public boolean checkBlockCollision(int x, int y, int width, int height) {
		Iterator<Block> it = blocks.iterator();
		while(it.hasNext()) {
			Block current = it.next();
			if(current.getType() != Block.TYPE_EMPTY &&
				x < current.getX() + BLOCK_SIZE &&
				x + width > current.getX() &&
				y < current.getY() + BLOCK_SIZE &&
				height + y > current.getY()) {
			   return true;
			}
		}
		return false;
	}

	// [0] => Deep on left, [1] deep on right, 2 top, 3 down
	public void checkEmptyBlock(Bomb bomb) {
		int caseX = bomb.getCaseX();
		int caseY = bomb.getCaseY();
		int deep = bomb.getBlockDistance() + 1;

		int nbBlock = caseX + caseY * NB_BLOCK_X;

		int i = 1;
		boolean blocked = false;
		while(i < deep && !blocked) {
			int nbBlockTest = caseX-i + caseY * NB_BLOCK_X;
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
			int nbBlockTest = caseX+i + caseY * NB_BLOCK_X;
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
			int nbBlockTest = caseX + (caseY-i) * NB_BLOCK_X;
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
			int nbBlockTest = caseX + (caseY+i) * NB_BLOCK_X;
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
						   System.exit(0);
				}
			}
		}

	}

	public void checkBlockBonus(Player player) {
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
	
}
