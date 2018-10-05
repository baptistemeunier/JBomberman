package State.MapEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import App.EventBuffer;
import App.GameManager;
import GameState.MapEditorState;
import Map.Map;
import Map.MapEditor;
import Map.MapFile;
import State.State;

public class InitialisationState extends BasicState {

	private String name;
	private int width = Map.NB_BLOCK_X;
	private int height = Map.NB_BLOCK_Y;

	@Override
	public void initialize() {
		editor = new MapEditor();
		name = "name";
	}

	@Override
	public void transition(State s) {
		BasicState bs = (BasicState) s;
		release();

		MapFile file = new MapFile(name);
		file.setWidth(width);
		file.setHeight(height);
		editor.setFile(file);
		MapEditorState.instance().setState(bs);
		bs.setEditor(this.editor);
		bs.initialize();
	}

	@Override
	public void update() {
		MouseEvent mEvent = EventBuffer.instance().getMouseState(MouseEvent.BUTTON1);
		if(mEvent != null) {
			if(mEvent.getY() > 570 && mEvent.getY() < 610) {
				if(mEvent.getX() > 300 && mEvent.getX() < 520) {
					transition(new EditorState());
				} else if(mEvent.getX() > 600 && mEvent.getX() < 680) {
					System.out.println("Exit");
					GameManager.instance().popState();
				}
			}
			else if(mEvent.getX() > 450 && mEvent.getX() < 480) {
				if(mEvent.getY() > 150 && mEvent.getY() < 180) {
					width--;					
				} else if(mEvent.getY() > 290 && mEvent.getY() < 350) {
					height--;
				}
			}
			if(mEvent.getX() > 560 && mEvent.getX() < 590) {
				if(mEvent.getY() > 150 && mEvent.getY() < 180) {
					width++;
				} else if(mEvent.getY() > 290 && mEvent.getY() < 350) {
					height++;
				}
			}
		}
		for(KeyEvent event : EventBuffer.instance().getKeyEvents()) {
			if(event != null && event.getID() == KeyEvent.KEY_RELEASED) {
				int key = ((KeyEvent) event).getKeyCode();
				if(key == KeyEvent.VK_BACK_SPACE && name != null && name.length() > 0) {
				        name = name.substring(0, name.length() - 1);
				} else if(key == KeyEvent.VK_ENTER && name != null && name.length() > 0) {
					editor.save();
				} else {
					char c = ((KeyEvent) event).getKeyChar();
					name += c;
				}			
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Font oldFont = g.getFont();
		Font font = new Font("Arial",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.BLUE);

		g.drawString("WIDTH SIZE", 407, 100);
		g.drawRect(207, 110, 607, 100);
		g.drawString(Integer.toString(width), 500, 175);
		g.drawRect(450, 150, 30, 30);
		g.drawString("-", 458, 175);
		g.drawRect(560, 150, 30, 30);
		g.drawString("+", 564, 175);

		g.drawString("HEIGHT SIZE", 400, 250);
		g.drawRect(207, 260, 607, 100);
		g.drawString(Integer.toString(height), 500, 315);
		g.drawRect(450, 290, 30, 30);
		g.drawString("-", 458, 315);
		g.drawRect(560, 290, 30, 30);
		g.drawString("+", 564, 315);
		
		g.drawString("MAP NAME", 420, 400);
		g.drawRect(207, 410, 607, 100);
		g.drawString(name, 500-name.length()*9, 470);

		g.drawString("CREATE MAP", 300, 600);
		g.drawString("EXIT", 600, 600);
		g.setFont(oldFont);		
	}

}
