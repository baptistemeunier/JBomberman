package App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class EventBuffer implements KeyListener, MouseListener {
	
	private HashMap<Integer, KeyEvent> keyEvents;
	private HashMap<Integer, MouseEvent> mouseEvents;
	
	private static EventBuffer instance;

	public static EventBuffer instance() {
		if (instance == null) {
			EventBuffer.instance = new EventBuffer();
		}
		return EventBuffer.instance;
	}

	EventBuffer() {
		 keyEvents = new HashMap<Integer, KeyEvent>();
		 mouseEvents = new HashMap<Integer, MouseEvent>();
	}

	public void add(KeyEvent event) {
		int key = event.getKeyCode();
		keyEvents.remove(key);
		keyEvents.put(key, event);
	}

	private void add(MouseEvent event) {
		int key = event.getButton();
		mouseEvents.remove(key);
		mouseEvents.put(key, event);
	}

	public KeyEvent getKeyState(int key) {
		return keyEvents.remove(key);
	}

	public MouseEvent getMouseState(int button) {
		return mouseEvents.remove(button);
	}

	public MouseEvent isClicked(int button) {
		MouseEvent event = mouseEvents.get(button);
		if(event != null && event.getID() == MouseEvent.MOUSE_CLICKED) {
			mouseEvents.remove(button);
			return event;
		}
		return null;
	}

	public boolean isReleased(int key) {
		KeyEvent event = keyEvents.get(key);
		if(event != null && event.getID() == KeyEvent.KEY_RELEASED) {
			mouseEvents.remove(key);
			return true;
		}
		return false;
	}

	public boolean isPressed(int key) {
		KeyEvent event = keyEvents.get(key);
		if(event != null && event.getID() == KeyEvent.KEY_PRESSED) {
			mouseEvents.remove(key);
			return true;
		}
		return false;
	}

	public void clear() {
		keyEvents.clear();		
		mouseEvents.clear();		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		add(event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		add(event);
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		add(event);		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
