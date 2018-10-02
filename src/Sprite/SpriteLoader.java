package Sprite;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteLoader {
    private HashMap<String, SpriteSheet> spriteSheets = new HashMap<String, SpriteSheet>();

	private static SpriteLoader instance;
	public static SpriteLoader instance() {
		if(instance == null) {
			SpriteLoader.instance = new SpriteLoader();
		}
		return SpriteLoader.instance;
	}

	private void loadSpriteSheet(String name) {
    	BufferedImage img = null;
    	
    	try {
    	    img = ImageIO.read(new File("assets/sprites/" + name + ".png"));
    	    SpriteSheet ss = new SpriteSheet();
    	    ss.setName(name);
    	    ss.setStriteSheet(img);
    	    ss.setMetaData(loadMetaData(name));
    	    spriteSheets.put(name, ss);
    	} catch (IOException e) {
    		System.out.println("Can't open spritesheet " + name + ".png");
    	    System.exit(0);
    	}
    }
    
    private HashMap<String, int[]> loadMetaData(String name) {
	    BufferedReader reader;
	    HashMap<String, int[]> metaDatas = new HashMap<String, int[]>();
		try {
			reader = new BufferedReader(new FileReader("assets/sprites/" + name + ".meta"));
		    String line;
		    while((line = reader.readLine()) != null)
		    {
			    String[] meta = line.split(" ");
		    	int[] params = {
		    			Integer.parseInt(meta[1]),
		    			Integer.parseInt(meta[2]),
		    			Integer.parseInt(meta[3]),
		    			Integer.parseInt(meta[4])
		    	};
		    	metaDatas.put(meta[0], params);
		    }
			reader.close();
    	} catch (IOException e) {
    		System.out.println("Can't open spritesheet metadata of " + name + ".meta");
    	    System.exit(0);
    	}
		return metaDatas;
	}

	private SpriteSheet getSpriteSheet(String name) {
    	if(!spriteSheets.containsKey(name)) {
    		loadSpriteSheet(name);
    	}
		return spriteSheets.get(name);
    }

    public BufferedImage getStrite(String name, String spriteName) {
    	SpriteSheet ss = getSpriteSheet(name);
		return ss.getStrite(spriteName);
    }
}
