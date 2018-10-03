package Utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontLoader {
	
	public static void loadFont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/prstart.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
			System.out.println("[CRITICAL] Enable to load font : prstart.ttf" );
		}
	}

	public static void resetFont(Graphics2D g) {
		g.setFont(new Font("Press Start", Font.PLAIN, 20));		
	}
}
