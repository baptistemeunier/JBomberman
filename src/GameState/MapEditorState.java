package GameState;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import App.Map;
import Entity.Block;

public class MapEditorState extends GameState {

	ArrayList<Block> blockList;
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
		Map.generateEmptyMap();
		blockList = new ArrayList<Block>();
		blockList.add(new Block(Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE+10, Map.NB_BLOCK_Y, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, 49));
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

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

	}

	@Override
	public void draw(Graphics2D g) {
		Map.draw(g);

		g.setColor(Color.BLACK);
		for(int i=0; i < 19; i++) {
			for(int j=0; j < 13; j++) {
				g.drawRect(i*50, j*50, 50,50);
			}
		}
		Iterator<Block> it = blockList.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
		if(currentblock != null) {
			g.setColor(Color.red);
			g.drawRect(currentblock.getX()-5, currentblock.getY()-5, currentblock.getWidth()+10, currentblock.getHeight()+10);
		}
	}

	@Override
	public void handleEvent(AWTEvent event) {
		if(event.getID() == MouseEvent.MOUSE_CLICKED) {
			int x = ((MouseEvent) event).getX();
			int y = ((MouseEvent) event).getY();
			if(currentblock == null) {
				Iterator<Block> it = blockList.iterator();
				while(it.hasNext()) {
					Block b = it.next();
					if(x > b.getX() && x < (b.getX() + b.getWidth())
							&& y > b.getY() && y < (b.getY() + b.getHeight())) {
						currentblock = b;
					}
				}
			} else {
				try {
					Map.getBlockFromCoordinate(x, y).setType(currentblock.getType());
				} catch(IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
