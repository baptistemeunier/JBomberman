package State.Bomb;

import java.awt.Graphics2D;

import App.GamePanel;
import Entity.Bomb;
import Animation.BombAnimation;

public class WaitState extends StateBomb {

	private int frameBeforeExplode = GamePanel.FPS * 2;
	private Animation.BombAnimation anim;

	public WaitState(Bomb bomb) {
		super(bomb);
	}

	@Override
	public void initialize() {
		anim = new BombAnimation(frameBeforeExplode);
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		anim = null;
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
		g.drawImage(anim.getFrame(), bomb.getX(), bomb.getY(), 32, 32, null, null);
	}

}