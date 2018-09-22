package State.Bomb;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;

import App.GamePanel;
import Entity.Bomb;
import Utils.BombCollision;
import Utils.Rectangle;

public class ExploseState extends StateBomb {

	private int frameBeforeDelete = GamePanel.FPS;
	private boolean haveToBeRemove = false;
	private BombCollision bombCollision;

	public ExploseState(Bomb bomb) {
		super(bomb);
	}

	@Override
	public void initialize() {
		bombCollision = new BombCollision(bomb);
		bombCollision.createCollisionBox();
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
		frameBeforeDelete--;
		if(frameBeforeDelete == 0) {
			haveToBeRemove = true;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		Rectangle[] rects = bombCollision.getCollisionBox();
		g.fillRect(rects[0].x+10, rects[0].y+10, rects[0].width-20, rects[0].height-20);
		g.fillRect(rects[1].x+10, rects[1].y+10, rects[1].width-20, rects[1].height-20);
	}

	public boolean needRemove() {
		return haveToBeRemove;
	}

	public Rectangle[] getCollisionBox() {
		return bombCollision.getCollisionBox();
	}
}