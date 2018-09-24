package Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Entity.Block;

public class MapFile {

	private String path;
	private int width;
	private int height;
	
	public MapFile(String path) {
		this.path = "maps/" + path;
	}

	private void load() {
		
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
	 * Get all static block
	 * @return 
	 */
	public int getStaticBlock() {
		
		return this.height;
	}
}
