package App;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Utils.Rectangle;

public class EndingState extends GameState {

	private int currentChoice = 0;
	private ArrayList<Rectangle> menuSelection;
	private int choice = -1;
	private String winner;
	
	private static EndingState instance;
	public static EndingState instance() {
		if(instance == null) {
			EndingState.instance = new EndingState();
		}
		return EndingState.instance;
	}

	@Override
	protected void initialize() {
		int test = GamePanel.WIDTH / 6;
		menuSelection = new ArrayList<Rectangle>();
		menuSelection.add(new Rectangle(test-10, 565, 145, 50));
		menuSelection.add(new Rectangle(2*test+test/2-10, 565, 210, 50));
		menuSelection.add(new Rectangle(4*test+ test/2-10, 565, 90, 50));

	}

	@Override
	protected void release() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void update() {
		if(choice != -1) {
			if (choice == 1) {
				//System.exit(0);
			} else if (choice == 2) {
				System.exit(0);
			}else { // choice == 0
				GameManager.instance().setState(PlayingState.instance());
			}
			choice = -1;
		}
	}

	@Override
	protected void draw(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 125));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		g.setColor(Color.YELLOW);

		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();

		Font font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);
		g.drawString("Game Over ", GamePanel.WIDTH / 2 - 120, 100);
		String winText;
		if(winner != null) {
			winText = winner + " win !";
		} else {
			winText = "You are both loser !";			
		}
		g.drawString(winText, GamePanel.WIDTH / 2 - 150, 200);

		int test = GamePanel.WIDTH / 6;
		g.drawString("Restart", test, 600);
		g.drawString("Go to menu", 2*test+test/2, 600);
		g.drawString("Quit", 4*test+ test/2, 600);

		g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));        
		Rectangle rect = menuSelection.get(currentChoice);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);

		g.setFont(oldFont);
        g.setStroke(oldStroke);

	}

	public void setWinnerName(String name) {
		winner = name;
	}

	@Override
	protected void handleEvent(AWTEvent event) {
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
	}

}
