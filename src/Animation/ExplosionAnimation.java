package Animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Block;
import Map.Map;
import Sprite.SpriteLoader;
import Utils.BombCollision;

public class ExplosionAnimation implements Animation {
	
	private BufferedImage current;
	public int state = 1;
	private int changeFrameInterval;
	private int frameNb = 0;
	private BombCollision bombCollision;

	public ExplosionAnimation(int frameBeforeDelete, BombCollision bc) {
		AnimationManager.instance().add(this);
		this.changeFrameInterval = frameBeforeDelete/5;
		bombCollision = bc;
	}
	
	/* (non-Javadoc)
	 * @see Animation.Animation#getFrame()
	 */
	@Override
	public BufferedImage getFrame() {
		return current;
	}
	
	/* (non-Javadoc)
	 * @see Animation.Animation#tic()
	 */
	@Override
	public void tic() {
		frameNb++;
		if(frameNb > changeFrameInterval) {
			frameNb = 0;
			state++;
		}
	}

	@Override
	public boolean isFinish() {
		return state == 5 && frameNb == changeFrameInterval;
	}

	public void drawFrame(Graphics2D g) {
		BufferedImage sprite = SpriteLoader.instance().getStrite("explosion", "explosion-" + state);
		for(Block block : bombCollision.getBlocks()) {
			g.drawImage(sprite, block.getX(), block.getY(), Map.BLOCK_SIZE_X, Map.BLOCK_SIZE_Y, null, null);
		}		
	}

}
