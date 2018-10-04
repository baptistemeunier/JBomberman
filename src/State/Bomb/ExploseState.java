package State.Bomb;

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
	public void update() {
		frameBeforeDelete--;
		if(frameBeforeDelete == 0) {
			bomb.setNeedRemove(true);
			haveToBeRemove = true;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Rectangle[] rects = getCollisionBox();
		for(Rectangle r : rects) {
			g.setColor(Color.RED);
			g.fillRect(r.x+10, r.y+10, r.width-20, r.height-20);			
		}
	}

	public Rectangle[] getCollisionBox() {
		return bombCollision.getCollisionBox();
	}
}