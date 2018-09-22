package State.Bomb;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;

import App.GamePanel;
import Entity.Bomb;

public class WaitState extends StateBomb {

	private int frameBeforeExplode = GamePanel.FPS * 2;

	public WaitState(Bomb bomb) {
		super(bomb);
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleEvent(AWTEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		frameBeforeExplode--;
		if(frameBeforeExplode == 0) {
			transition(new ExploseState(bomb));
		}		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval(bomb.getX(), bomb.getY(), 30, 30);			
	}

}