package GameState;

import java.awt.AWTEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;

import App.GameManager;
import App.GamePanel;
import Entity.Bomb;
import Entity.Player;
import Menu.MenuItem;
import Menu.FreeMenu;
import Sprite.SpriteLoader;

public class MainMenuState extends GameState {

	public static int BLOCK_SIZE = 50;

	ArrayList<Player> players;
	FreeMenu menu;
	
	private static MainMenuState instance;
	public static MainMenuState instance() {
		if(instance == null) {
			MainMenuState.instance = new MainMenuState();
		}
		return MainMenuState.instance;
	}

	@Override
	public void initialize() {
		menu = new FreeMenu();
		menu.addItem(new MenuItem("Play", 250, 225));
		menu.addItem(new MenuItem("Editor", 600, 225));
		menu.addItem(new MenuItem("Setting", 250, 575));
		menu.addItem(new MenuItem("Exit", 600, 575));
	}

	@Override
	public void release() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < GamePanel.WIDTH%50; i++) {
			g.drawImage(SpriteLoader.instance().getStrite("tiles", "block"), i*50, 0, 50, 50, null, null);
		}
		for(int j = 1; j < GamePanel.HEIGHT%50-7; j++) {
			g.drawImage(SpriteLoader.instance().getStrite("tiles", "block"), 0, j*50, 50, 50, null, null);
			g.drawImage(SpriteLoader.instance().getStrite("tiles", "block"), (GamePanel.WIDTH%50-1)*50, j*50, 50, 50, null, null);
		}
		for(int i = 0; i < GamePanel.WIDTH%50; i++) {
			g.drawImage(SpriteLoader.instance().getStrite("tiles", "block"), i*50, (GamePanel.HEIGHT%50-7)*50, 50, 50, null, null);
		}

		for(int i = 1; i < GamePanel.HEIGHT%50-7; i++) {
			for(int j = 1; j < GamePanel.WIDTH%50-1; j++) {
				g.drawImage(SpriteLoader.instance().getStrite("tiles", "grass"), j*50, i*50, 50, 50, null, null);
			}
		}
		menu.draw(g);
	}

	public void killPlayer(Bomb bomb) {

	}

	@Override
	public void handleEvent(AWTEvent event) {	
		String choice = menu.checkItem(event);
		if(choice == "Play") {
			GameManager.instance().pushState(PlayingState.instance());			
		} else if(choice == "Editor") {
			GameManager.instance().pushState(MapEditorState.instance());
		} else if(choice == "Exit") {
			System.exit(0);
		}
	}
}
