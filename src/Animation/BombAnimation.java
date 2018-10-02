package Animation;

import java.awt.image.BufferedImage;

import Sprite.*;

public class BombAnimation {
	
	public static final int NORMAL = 0;
	public static final int CLOSE_TO_EXPLOSE = 1;
	private BufferedImage anim1, anim2, anim3, anim4, current;
	public int state = BombAnimation.NORMAL;

	public BombAnimation() {
		anim1 = SpriteLoader.instance().getStrite("bombs", "anim-normal-1");
		anim2 = SpriteLoader.instance().getStrite("bombs", "anim-normal-2");
		anim3 = SpriteLoader.instance().getStrite("bombs", "anim-close-1");
		anim4 = SpriteLoader.instance().getStrite("bombs", "anim-close-2");
		current = anim1;
	}
	
	public BufferedImage getFrame() {
		return current;
	}
	
	private void nextFrame() {
		if(state == CLOSE_TO_EXPLOSE) {
			if(current == anim3) {
				current = anim4;
			} else {
				current = anim3;			
			}
		} else {
			if(current == anim1) {
				current = anim2;
			} else {
				current = anim1;			
			}			
		}
	}
	
	public void tic() {
		nextFrame();
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
