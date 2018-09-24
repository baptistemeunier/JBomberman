package GameState;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import App.GamePanel;
import Entity.Block;
import Map.Map;
import Map.MapEditor;

public class MapEditorState extends GameState {

	MapEditor editor = null;
	Block currentblock = null;
	private boolean created;
	private String name;
	
	private static MapEditorState instance;
	public static MapEditorState instance() {
		if(instance == null) {
			MapEditorState.instance = new MapEditorState();
		}
		return MapEditorState.instance;
	}

	@Override
	public void initialize() {
		editor = new MapEditor();
		editor.initialize();
	}

	@Override
	public void release() {
		editor = null;
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
		if(created != true) {
			editor.draw(g);

			if(currentblock != null) {
				g.setColor(Color.red);
				g.drawRect(currentblock.getX()-5, currentblock.getY()-5, currentblock.getWidth()+10, currentblock.getHeight()+10);
			}
			g.setColor(Color.BLUE);
			Font oldFont = g.getFont();
			Font font = new Font("Arial",Font.BOLD,30);
			g.setFont(font);

			g.drawString("Save Map", GamePanel.WIDTH/2, Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE + 50);		
			g.setFont(oldFont);			
		}else {
			Font oldFont = g.getFont();
			Font font = new Font("Arial",Font.BOLD,30);
			g.setFont(font);
			g.setColor(Color.BLUE);

			g.drawString("Map name : " + name, 200, GamePanel.HEIGHT/2);		
			g.setFont(oldFont);			

		}
	}

	@Override
	public void handleEvent(AWTEvent event) {
		if(created != true) {
			if(event.getID() == MouseEvent.MOUSE_CLICKED) {
				int x = ((MouseEvent) event).getX();
				int y = ((MouseEvent) event).getY();
				if(x > GamePanel.WIDTH/2 && x < (GamePanel.WIDTH/2 + 170)
						&&  y > Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE+20 && y < (Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE+20 + 40)) {
					created = true;
					name = "";
				}
				if(currentblock == null) {
					Iterator<Block> it = editor.getBlockList().iterator();
					while(it.hasNext()) {
						Block b = it.next();
						if(x > b.getX() && x < (b.getX() + b.getWidth())
								&& y > b.getY() && y < (b.getY() + b.getHeight())) {
							currentblock = b;
						}
					}
				} else {
					if(x >= Map.NB_BLOCK_X*PlayingState.BLOCK_SIZE) {
						Iterator<Block> it = editor.getBlockList().iterator();
						while(it.hasNext()) {
							Block b = it.next();
							if(x > b.getX() && x < (b.getX() + b.getWidth())
									&& y > b.getY() && y < (b.getY() + b.getHeight())) {
								currentblock = b;
							}
						}					
					}else {
						try {
							Map.getBlockFromCoordinate(x, y).setType(currentblock.getType());
						} catch(IndexOutOfBoundsException e) {
							e.printStackTrace();
						}						
					}
				}
			}	
		}else {
			if(event.getID() == KeyEvent.KEY_RELEASED) {
				int key = ((KeyEvent) event).getKeyCode();
				if(key == KeyEvent.VK_BACK_SPACE && name != null && name.length() > 0) {
				        name = name.substring(0, name.length() - 1);
				} else if(key == KeyEvent.VK_ENTER && name != null && name.length() > 0) {
					editor.save(name);
				} else {
					char c = ((KeyEvent) event).getKeyChar();
					name += c;
				}
			}
		}
	}

}
