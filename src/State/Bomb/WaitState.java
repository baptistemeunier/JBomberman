package State.Bomb;

import java.awt.Graphics2D;

import App.GamePanel;
import Entity.Bomb;
import GameState.PlayingState;
import Utils.Rectangle;
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
		anim = null;
	}

	@Override
	public void update() {
		System.out.println(bomb);
		if(bomb.isWatingPlayerMove() && getCollisionBox() != null && !bomb.getPlayer().getCollisionBox().checkCollision(getCollisionBox()[0])) {
			bomb.setWatingPlayerMove(false);
		}
		frameBeforeExplode--;
		if(frameBeforeExplode == 0) {
			transition(new ExploseState(bomb));
		}
	}

	@Override
	public void draw(Graphics2D g) {		
		g.drawImage(anim.getFrame(), bomb.getX(), bomb.getY(), 32, 32, null, null);
	}

	@Override
	public Rectangle[] getCollisionBox() {
		if(this.frameBeforeExplode > GamePanel.FPS * 1.7) {
			return null;
		}
		Rectangle[] r = {new Rectangle(bomb.getCaseX()*PlayingState.BLOCK_SIZE+PlayingState.BLOCK_SIZE/4, bomb.getCaseY()*PlayingState.BLOCK_SIZE+PlayingState.BLOCK_SIZE/4, PlayingState.BLOCK_SIZE/2, PlayingState.BLOCK_SIZE/2)};
		return r;
	}

}