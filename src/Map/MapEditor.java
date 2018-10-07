package Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import App.GamePanel;
import Entity.Block;

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
	    Map.BLOCK_SIZE_X = GamePanel.WIDTH/Map.NB_BLOCK_X;
	    Map.BLOCK_SIZE_Y = GamePanel.HEIGHT/(Map.NB_BLOCK_Y+1);
		Map.generateEmptyMap();
		blockList = new ArrayList<Block>();
		for(int i = 0; i < blocksTypes.length; i++) {
			blockList.add(new Block(Map.NB_BLOCK_X*Map.BLOCK_SIZE_X+10, Map.NB_BLOCK_Y+Map.BLOCK_SIZE_Y*i+10*i, Map.BLOCK_SIZE_X, Map.BLOCK_SIZE_Y, blocksTypes[i]));
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
