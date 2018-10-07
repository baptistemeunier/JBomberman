package State.Bomb;

import java.awt.Graphics2D;

import Animation.ExplosionAnimation;
import App.GamePanel;
import Entity.Bomb;
import Utils.BombCollision;
import Utils.Rectangle;

public class ExploseState extends StateBomb {

	private BombCollision bombCollision;
	private ExplosionAnimation animation;
	
	public ExploseState(Bomb bomb) {
		super(bomb);
	}

	@Override
	public void initialize() {
		bombCollision = new BombCollision(bomb);
		bombCollision.createCollisionBox();
		animation = new ExplosionAnimation((int) (GamePanel.FPS * 0.7), bombCollision);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		animation.tic();
		if(animation.isFinish()) {
			bomb.markForRemove();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		animation.drawFrame(g);
	}

	public Rectangle[] getCollisionBox() {
		return bombCollision.getCollisionBox();
	}
}