package App;

import java.awt.AWTEvent;
import java.awt.Graphics2D;

public abstract class GameState {

	protected abstract void initialize();
    protected abstract void release();

    protected abstract void pause();
    protected abstract void resume();
    
    protected abstract void handleEvent(AWTEvent event);

    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    
}
