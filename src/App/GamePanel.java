package App;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JPanel;

import GameState.MainMenuState;
import Utils.FontLoader;

public class GamePanel extends JPanel implements Runnable {

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
		if (instance == null) {
			GamePanel.instance = new GamePanel();
		}
		return GamePanel.instance;
	}

	private GamePanel() {
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(EventBuffer.instance());
		addMouseListener(EventBuffer.instance());
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		GameManager.instance().pushState(MainMenuState.instance());
		FontLoader.loadFont();

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 30;

		long targetTime = 1000 / FPS;

		while (running) {
			startTime = System.nanoTime();

			gameUpdate();
			gameRender();
			gameDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			if (waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {

				}
			}
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			frame++;
			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	private void gameUpdate() {
		GameManager.instance().currentState().update();
		EventBuffer.instance().clear();
	}

	private void gameRender() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		FontLoader.resetFont(g);

		GameManager.instance().currentState().draw(g);
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString("Frame : " + frame, 20, HEIGHT - 10);
		g.drawString("FPS : " + (int) averageFPS, 20, HEIGHT - 20);
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

}
