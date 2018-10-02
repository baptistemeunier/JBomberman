package Sprite;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteSheet {
	private String name;
	private BufferedImage striteSheet;
	private HashMap<String, int[]> metaData;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BufferedImage getStriteSheet() {
		return striteSheet;
	}

	public void setStriteSheet(BufferedImage striteSheet) {
		this.striteSheet = striteSheet;
	}
	
	public BufferedImage getStrite(String name) {
		if(!metaData.containsKey(name)) {
			System.out.println("Can't find sprite " + name + " in sptrite sheet " + this.name);
			return striteSheet.getSubimage(0, 0, 1, 1);
		}
		int[] meta = metaData.get(name);
		return striteSheet.getSubimage(meta[0], meta[1], meta[2], meta[3]);
	}
	public void setMetaData(HashMap<String, int[]> metaData) {
		this.metaData = metaData;
	}
	
}
