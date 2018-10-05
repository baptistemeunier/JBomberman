package GameState;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import App.GameManager;
import App.GamePanel;
import Map.Map;
import Utils.FontLoader;
import Utils.Rectangle;

public class EndingState extends GameState {

	private int currentChoice = 0;
	private ArrayList<Rectangle> menuSelection;
	private int choice = -1;
	private String winner;
	private BufferedImage lastFrame;
	
	private static EndingState instance;
	public static EndingState instance() {
		if(instance == null) {
			EndingState.instance = new EndingState();
		}
		return EndingState.instance;
	}

	@Override
	public void initialize() {
		int space = GamePanel.WIDTH / 6;
		menuSelection = new ArrayList<Rectangle>();
		menuSelection.add(new Rectangle(space-10, 565, 145, 50));
		menuSelection.add(new Rectangle(2*space+space/2-10, 565, 210, 50));
		menuSelection.add(new Rectangle(4*space+ space/2-10, 565, 90, 50));

	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

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
		if(choice != -1) {
			if (choice == 1) {
				GameManager.instance().popState();
			} else if (choice == 2) {
				System.exit(0);
			}else {
				GameManager.instance().setState(PlayingState.instance());
			}
			choice = -1;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawImage(lastFrame, 0, 0, PlayingState.BLOCK_SIZE*Map.NB_BLOCK_X, PlayingState.BLOCK_SIZE*Map.NB_BLOCK_Y, null ,null);
		g.setColor(new Color(12,12,12,130));
		g.fillRect(0, 0, PlayingState.BLOCK_SIZE*Map.NB_BLOCK_X, PlayingState.BLOCK_SIZE*Map.NB_BLOCK_Y);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Press Start", Font.PLAIN, 32));
		Stroke oldStroke = g.getStroke();

		g.drawString("Game Over ", 330, 100);
		String winText;
		if(winner != null) {
			winText = winner + " win !";
		} else {
			winText = "You are both loser !";			
		}
		g.drawString(winText, 280, 200);
        FontLoader.resetFont(g);

		int space = GamePanel.WIDTH / 6;
		g.drawString("Restart", space, 600);
		g.drawString("Go to menu", 2*space+space/2, 600);
		g.drawString("Quit", 4*space+ space/2, 600);

		g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));        
		Rectangle rect = menuSelection.get(currentChoice);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);

        g.setStroke(oldStroke);

	}

	public void setWinnerName(String name) {
		winner = name;
	}

	/*
	public void handleEvent(AWTEvent event) {
		if(event.getID() == KeyEvent.KEY_PRESSED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_Q)  {
				currentChoice--;
				if(currentChoice < 0) {
					currentChoice = menuSelection.size()-1;
				}
			}
			else if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)  {
				currentChoice++;
				if(currentChoice == menuSelection.size()) {
					currentChoice = 0;
				}
			}
		}
		if(event.getID() == KeyEvent.KEY_RELEASED) {
			int keyCode = ((KeyEvent) event).getKeyCode();
			if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER)  {
				choice = currentChoice;
			}
		}		
	}*/

	public void setLastFrame(BufferedImage image) {
		lastFrame = image;
	}

}
