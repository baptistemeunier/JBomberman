package Animation;

import java.util.ArrayList;
import java.util.Iterator;

public class AnimationManager {
	ArrayList<Animation> animations;
	
	private static AnimationManager instance;
	public static AnimationManager instance() {
		if(instance == null) {
			AnimationManager.instance = new AnimationManager();
		}
		return AnimationManager.instance;
	}

	AnimationManager() {
		animations = new ArrayList<Animation>();
	}
	
	public void update() {
		Iterator<Animation> it = animations.iterator();
		
		while(it.hasNext()) {
			Animation anim = it.next();
			if(anim.isFinish()) {
				it.remove();
			} else {
				anim.tic();				
			}
		}
	}

	public void add(Animation animation) {
		animations.add(animation);
	}

}
