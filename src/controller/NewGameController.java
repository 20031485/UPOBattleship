package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.AbstractButton;
import model.BattleshipModel;
import utils.BattleshipState;
import view.NewGamePanel;

public class NewGameController extends Observable implements ActionListener{
	//attributes
	private BattleshipModel model;
	private NewGamePanel newGamePanel;

	
	//constructor - newGamePanel is necessary because there is no other link possibility
	public NewGameController(BattleshipModel model, NewGamePanel newGamePanel){
		this.model = model;
		this.newGamePanel = newGamePanel;
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) {
		//abstractButton to get both JButtons and JRadioButtons
		AbstractButton source = (AbstractButton)e.getSource();
		String command = source.getText();
		switch(command) {
			case "CONFIRM":
				System.out.println("confirm");
				//TODO: get selections and launch Game
				if(newGamePanel.p1vsp2Button.isSelected()) {
					//feature unavilable
					model.setState(BattleshipState.NEWGAME);
					System.out.println("\tP1vsP2");
				}
				else {
					if(newGamePanel.p1vsCPUButton.isSelected()) {
						System.out.println("\tP1vsCPU");
						if(newGamePanel.easyModeButton.isSelected())
							System.out.println("\t\teasy cheesy");
						if(newGamePanel.hardModeButton.isSelected())
							System.out.println("\t\thard as hell");
						if(newGamePanel.sizeSButton.isSelected())
							System.out.println("\t5x5");
						if(newGamePanel.sizeMButton.isSelected())
							System.out.println("\t10x10");
						if(newGamePanel.sizeLButton.isSelected())
							System.out.println("\t15x15");
						if(newGamePanel.sizeXLButton.isSelected())
							System.out.println("\t20x20");
						if(newGamePanel.timedCheckBox.isSelected())
							System.out.println("\tTimed");
					}
					//perhaps SETSHIPS...
					model.setState(BattleshipState.SETNAMES);
				}
				break;
				
			case "BACK":
				System.out.println("back to start/load");
				model.setState(BattleshipState.WELCOME);
				break;
				
			case "P1vsP2 (unavailable)":
				newGamePanel.difficultyPanel.setVisible(false);;
				break;
				
			case "P1vsCPU":
				newGamePanel.difficultyPanel.setVisible(true);
				break;
				
			case "Timed":
				if(newGamePanel.timedCheckBox.isSelected())
					newGamePanel.timedPanel.setVisible(true);
				else
					newGamePanel.timedPanel.setVisible(false);
				break;
				
			default:
				System.out.println("error in newGameController");
				//TODO throw exception?
				break;
		}
	}
}
