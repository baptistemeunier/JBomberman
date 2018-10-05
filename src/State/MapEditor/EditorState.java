package State.MapEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import App.EventBuffer;
import App.GamePanel;
import Entity.Block;
import GameState.PlayingState;
import Map.Map;
import State.State;

public class EditorState extends BasicState {

	Block currentblock = null;

	@Override
	public void initialize() {
		editor.initialize();
	}

	@Override
	public void transition(State s) {

	}

	public void update() {
		MouseEvent mEvent = EventBuffer.instance().getMouseState(MouseEvent.BUTTON1);
		if(mEvent != null && mEvent.getID() == MouseEvent.MOUSE_CLICKED) {
			int x = mEvent.getX();
			int y = mEvent.getY();
			if(x > GamePanel.WIDTH/2 && x < (GamePanel.WIDTH/2 + 170)
					&&  y > Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE+20 && y < (Map.NB_BLOCK_Y*PlayingState.BLOCK_SIZE+20 + 40)) {
				System.out.println("Created !!");
				editor.save();
				System.exit(0);
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
	}

	@Override
	public void draw(Graphics2D g) {
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
	}

}
