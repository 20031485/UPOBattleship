package upo.battleship.rossi;

import java.awt.event.WindowAdapter;

public class BattleshipWindowDestructor extends WindowAdapter{
	public void windowClosing() {
		BattleshipExitFrame exitFrame = new BattleshipExitFrame();
		exitFrame.setVisible(true);
	}
}
