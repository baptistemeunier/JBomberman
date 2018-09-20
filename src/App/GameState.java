package App;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;

public abstract class GameState implements KeyListener {

	protected abstract void initialize();
    protected abstract void release();

    protected abstract void pause();
    protected abstract void resume();

    protected abstract void handleEvent();
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    
}
