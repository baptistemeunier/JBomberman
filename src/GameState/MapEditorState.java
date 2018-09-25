package GameState;

import java.awt.AWTEvent;
import java.awt.Graphics2D;

import Entity.Block;
import Map.MapEditor;
import State.MapEditor.*;

public class MapEditorState extends GameState {

	BasicState state;
	
	MapEditor editor = null;
	Block currentblock = null;
	
	private static MapEditorState instance;
	public static MapEditorState instance() {
		if(instance == null) {
			MapEditorState.instance = new MapEditorState();
		}
		return MapEditorState.instance;
	}

	@Override
	public void initialize() {
		state = new InitialisationState();
		state.initialize();
	}

	@Override
	public void release() {
		state.release();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		state.update();
	}

	@Override
	public void draw(Graphics2D g) {
		state.draw(g);
	}

	@Override
	public void handleEvent(AWTEvent event) {
		state.handleEvent(event);
	}

	public void setState(BasicState state) {
		this.state = state;
	}

}
