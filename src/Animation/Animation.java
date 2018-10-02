package Animation;

import java.awt.image.BufferedImage;

public interface Animation {

	BufferedImage getFrame();

	void tic();

	boolean isFinish();

}