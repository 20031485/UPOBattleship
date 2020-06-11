package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//class for when you want to exit and you want to ask the user if he's sure
public class WindowDestructor extends WindowAdapter{
	public void windowClosing(WindowEvent e) {
		ExitFrame exitFrame = new ExitFrame();
		exitFrame.setVisible(true);
	}
}
