package Entity.Bonus;

import Animation.BonusAnimation;
import Entity.Player;

public class BonusMoreBomb extends Bonus {

	public BonusMoreBomb(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void updatePlayer(Player player) {
		player.addBombSlot();
	}

	@Override
	public void launchAnimation() {
		animation = new BonusAnimation("bomb");
		Animation.AnimationManager.instance().add(animation);		
	}
}
