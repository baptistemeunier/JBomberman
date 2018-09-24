package Map;

import Entity.Block;
import GameState.PlayingState;

public class MapFile {

	private String path;
	private int width;
	private int height;
	
	public MapFile(String path) {
		this.path = path;
	}

	private void load() {
		
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
	 * Get all static block
	 * @return 
	 */
	public int getStaticBlock() {
		
		return this.height;
	}
}
