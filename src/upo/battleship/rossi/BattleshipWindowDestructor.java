package upo.battleship.rossi;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//class for when you want to exit and you want to ask the user if he's sure
public class BattleshipWindowDestructor extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		BattleshipExitFrame exitFrame = new BattleshipExitFrame();
		exitFrame.setVisible(true);
	}
}
