package State;

import java.awt.Graphics2D;

public interface State {

	public void initialize();
	public void release();

	public void transition(State s);

	public void update();
	public void draw(Graphics2D g);

}
