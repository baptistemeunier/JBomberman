package State.MapEditor;

import Map.MapEditor;
import State.State;

public abstract class BasicState implements State {

	MapEditor editor;
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	public MapEditor getEditor() {
		return editor;
	}

	public void setEditor(MapEditor editor) {
		this.editor = editor;
	}
}
