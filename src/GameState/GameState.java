package GameState;

import java.awt.AWTEvent;
import java.awt.Graphics2D;

public abstract class GameState {

	public abstract void initialize();
	public abstract void release();

    public abstract void pause();
    public abstract void resume();
    
    public abstract void handleEvent(AWTEvent event);

    public abstract void update();
    public abstract void draw(Graphics2D g);
    
}
