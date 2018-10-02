package Animation;

import java.awt.image.BufferedImage;

import Sprite.SpriteLoader;

public class BonusAnimation implements Animation {

	private BufferedImage anim1, anim2, current;
	private boolean finish = false;
	private int frame = 0;

	public BonusAnimation(String name) {
 		anim1 = SpriteLoader.instance().getStrite("bonus", name + "-1");
		anim2 = SpriteLoader.instance().getStrite("bonus", name + "-2");
		current = anim1;
	}

	@Override
	public BufferedImage getFrame() {
		return current;
	}

	@Override
	public void tic() {
		frame++;
		if(frame == 10) {
			if(current == anim1) {
				current = anim2;
			} else {
				current = anim1;			
			}
			frame = 0;
		}
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	@Override
	public boolean isFinish() {
		return finish;
	}

}
