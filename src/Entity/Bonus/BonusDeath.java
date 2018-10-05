package Entity.Bonus;

import Animation.BonusAnimation;
import App.Game;
import Entity.Player;

public class BonusDeath extends Bonus {
	
	public BonusDeath(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void updatePlayer(Player player) {
		Game.instance().removePlayer(player);
	}

	public void reveal() {
		animation = new BonusAnimation("death");
		Animation.AnimationManager.instance().add(animation);
	}

}
