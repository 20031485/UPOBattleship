package upo.battleship.rossi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BattleshipController implements ActionListener, WindowListener{
	//attributes
	private BattleshipModel battleshipModel;
	private BattleshipNewGameView battleshipNewGameFrame;
	//constructor
	public BattleshipController(BattleshipModel battleshipModel, BattleshipNewGameView battleshipNewGameFrame) {
		this.battleshipModel = battleshipModel;
		this.battleshipNewGameFrame = battleshipNewGameFrame;
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch(actionCommand) {
		case "CONFIRM":
			System.out.println("confirm");
			//TODO: get selections and launch Game
			if(battleshipNewGameFrame.p1vsp2Button.isSelected()) {
				System.out.println("\tP1vsP2");
				//BattleshipModel.savedGameExists();
			}
			if(battleshipNewGameFrame.p1vsCPUButton.isSelected()) {
				System.out.println("\tP1vsCPU");
				if(battleshipNewGameFrame.easyModeButton.isSelected())
					System.out.println("\t\teasy cheesy");
				if(battleshipNewGameFrame.hardModeButton.isSelected())
					System.out.println("\t\thard as hell");
			}
			if(battleshipNewGameFrame.sizeSButton.isSelected())
				System.out.println("\t5x5");
			if(battleshipNewGameFrame.sizeMButton.isSelected())
				System.out.println("\t10x10");
			if(battleshipNewGameFrame.sizeLButton.isSelected())
				System.out.println("\t15x15");
			if(battleshipNewGameFrame.sizeXLButton.isSelected())
				System.out.println("\t20x20");
			if(battleshipNewGameFrame.timedCheckBox.isSelected())
				System.out.println("\tTimed");
			break;
		case "BACK":
			System.out.println("back");
			//the next comment is just for testing purposes
			//battleshipNewGameFrame.setSize(300, 300);
			//TODO re-launch startLoadGameFrame
			break;
		case "P1vsP2":
			battleshipNewGameFrame.difficultyPanel.setVisible(false);;
			break;
		case "P1vsCPU":
			battleshipNewGameFrame.difficultyPanel.setVisible(true);
			break;
		default:
			System.out.println("error");
			//TODO throw exception?
			break;
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
