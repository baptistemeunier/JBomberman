package Entity.Bonus;

import Animation.BonusAnimation;
import Entity.Player;

public class BonusMoreSpeed extends Bonus {
	
	public BonusMoreSpeed(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void updatePlayer(Player player) {
		player.addSpeed();
	}

	@Override
	public void reveal() {
		animation = new BonusAnimation("speed");
		Animation.AnimationManager.instance().add(animation);		
	}

}
