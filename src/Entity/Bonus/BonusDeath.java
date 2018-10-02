package Entity.Bonus;

import Animation.BonusAnimation;
import Entity.Player;

public class BonusDeath extends Bonus {
	
	public BonusDeath(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void updatePlayer(Player player) {
		player.kill();
	}

	public void launchAnimation() {
		animation = new BonusAnimation("death");
		Animation.AnimationManager.instance().add(animation);
	}

}
