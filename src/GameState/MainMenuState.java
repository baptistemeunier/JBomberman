package GameState;

import java.awt.AWTEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;

import App.GameManager;
import Entity.Bomb;
import Entity.Player;
import Menu.MenuItem;
import Menu.VerticalMenu;

public class MainMenuState extends GameState {

	public static int BLOCK_SIZE = 50;

	ArrayList<Player> players;
	VerticalMenu menu;
	
	private static MainMenuState instance;
	public static MainMenuState instance() {
		if(instance == null) {
			MainMenuState.instance = new MainMenuState();
		}
		return MainMenuState.instance;
	}

	@Override
	public void initialize() {
		menu = new VerticalMenu();
		menu.addItem(new MenuItem("Play"));
		menu.addItem(new MenuItem("Editor"));
		menu.addItem(new MenuItem("Setting"));
		menu.addItem(new MenuItem("Exit"));
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
