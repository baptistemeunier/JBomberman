package App;

import javax.swing.*;

public class Application {

	public static void main(String[] args) {

		JFrame window = new JFrame("Bomberman");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(GamePanel.instance());
		
		window.setVisible(true);
		window.pack();
	}

	
	
}
