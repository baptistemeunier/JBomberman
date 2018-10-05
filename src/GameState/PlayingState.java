package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import App.Game;
import Entity.Player;
import Map.Map;

public class PlayingState extends GameState {

	public static int BLOCK_SIZE = 50;

	ArrayList<Player> players;

	private static PlayingState instance;
	public static PlayingState instance() {
		if(instance == null) {
			PlayingState.instance = new PlayingState();
		}
		return PlayingState.instance;
	}

	@Override
	public void initialize() {
		
	}

	@Override
	public void release() {
		Game.resetInstance();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
	
	@Override
	public void update() {
		Game.instance().update();
	}

	public BufferedImage drawLastFrame() {
		BufferedImage image = new BufferedImage(BLOCK_SIZE*Map.NB_BLOCK_X, BLOCK_SIZE*Map.NB_BLOCK_Y, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		draw(g);
		return image;
	}

	@Override
	public void draw(Graphics2D g) {
		Game.instance().draw(g);
	}

}
