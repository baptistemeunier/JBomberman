package Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Block;
import GameState.PlayingState;

public class MapFile {

	private String path;
	private int width;
	private int height;
	
	public MapFile(String path) {
		this.path = "maps/" + path;
	}

	ArrayList<Block> load() {
	    BufferedReader reader;
		ArrayList<Block> blocks = new ArrayList<Block>();
		try {
			reader = new BufferedReader(new FileReader(path));
		    String line = reader.readLine();
		    String[] types = line.split("!");
		    Map.NB_BLOCK_X = Integer.parseInt(types[0]);
		    Map.NB_BLOCK_Y = Integer.parseInt(types[1]);
			for (int i = 2; i < types.length-1; i++) {
				int type = Integer.parseInt(types[i]);
				int x = (i-2) % Map.NB_BLOCK_X;
				int y = (i-2-x) / Map.NB_BLOCK_X;
				Block block = new Block(x*PlayingState.BLOCK_SIZE, y*PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, PlayingState.BLOCK_SIZE, type);
				blocks.add(block);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		return blocks;
	}
	
	public void save() {
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path));
		    writer.write(Map.NB_BLOCK_X + "!" + Map.NB_BLOCK_Y + "!");
			for (int y = 0; y < Map.NB_BLOCK_Y;y++) {
				for (int x = 0; x < Map.NB_BLOCK_X;x++) {
					Block b = Map.getBlock(x, y);
				    writer.write(b.getType() + "!");
				}
			}
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}

	/**
	 * Get the map width
	 * @return the map width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get the map height
	 * @return the map height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Set the map width
	 * @param int the map width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Set the map height
	 * @param int the map height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get all static block
	 * @return 
	 */
	public int getStaticBlock() {
		
		return this.height;
	}
}
