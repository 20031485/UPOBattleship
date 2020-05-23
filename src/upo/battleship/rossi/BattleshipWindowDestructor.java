package upo.battleship.rossi;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BattleshipWindowDestructor extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		BattleshipExitFrame exitFrame = new BattleshipExitFrame();
		exitFrame.setVisible(true);
	}
}
