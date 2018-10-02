package App;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;

import javax.swing.JPanel;

import GameState.MainMenuState;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = -6496354673243986202L;

	public static int WIDTH = 1020;
	public static int HEIGHT = 720;

	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;

	private double averageFPS;
	private int frame;
	public static int FPS = 30;
			
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
		addKeyListener(this);
		this.addMouseListener(this);
	}	

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		GameManager.instance().pushState(MainMenuState.instance());
		
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
		GameManager.instance().currentState().update();
	}

	private void gameRender() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		GameManager.instance().currentState().draw(g);
		g.setColor(Color.red);
		g.drawString("Frame : " + frame, 20, HEIGHT-10);
		g.drawString("FPS : " + (int) averageFPS, 20, HEIGHT-20);

	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		GameManager.instance().currentState().handleEvent(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		GameManager.instance().currentState().handleEvent(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		GameManager.instance().currentState().handleEvent(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		GameManager.instance().currentState().handleEvent(event);		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}	
	
}
