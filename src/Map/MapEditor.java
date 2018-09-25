package Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import Entity.Block;
import GameState.PlayingState;

public class MapEditor {

	private int[] blocksTypes = {
			Block.TYPE_SOLID,
			Block.TYPE_RESERVED,
			Block.TYPE_SPAWN,
			Block.TYPE_EMPTY,
	};
	
	private ArrayList<Block> blockList;
	private MapFile file;

	public MapEditor() {
		
	}
	
	public void initialize() {
		Map.generateEmptyMap();
		blockList = new ArrayList<Block>();
		for(int i = 0; i < blocksTypes.length; i++) {
			blockList.add(new Block(Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE+10, Map.NB_BLOCK_Y+PlayingState.BLOCK_SIZE*i+10*i, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, blocksTypes[i]));
		}		
	}

	public void save() {
		file.save();
	}

	public ArrayList<Block> getBlockList() {
		return blockList;
	}

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
	}

	public void setFile(MapFile file) {
		this.file = file;
	}
}
