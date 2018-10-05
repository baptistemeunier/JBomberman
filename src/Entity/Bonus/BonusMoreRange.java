package Entity.Bonus;

import Animation.BonusAnimation;
import Entity.Player;

public class BonusMoreRange extends Bonus {

	public BonusMoreRange(int x, int y, int width, int height) {
		super(x ,y, width, height);
	}

	public void updatePlayer(Player player) {
		player.addRange();
	}

	public void reveal() {
		animation = new BonusAnimation("range");
		Animation.AnimationManager.instance().add(animation);
	}

}
