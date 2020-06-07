package upo.battleship.rossi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class BattleshipNewGameController extends Observable implements ActionListener{
	//attributes
	private BattleshipModel model;
	private BattleshipNewGamePanel newGamePanel;

	
	//constructor - newGamePanel is necessary because there is no other link possibility
	BattleshipNewGameController(BattleshipModel model, BattleshipNewGamePanel newGamePanel){
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
				System.out.println("\tP1vsP2");
			}
			if(newGamePanel.p1vsCPUButton.isSelected()) {
				System.out.println("\tP1vsCPU");
				if(newGamePanel.easyModeButton.isSelected())
					System.out.println("\t\teasy cheesy");
				if(newGamePanel.hardModeButton.isSelected())
					System.out.println("\t\thard as hell");
			}
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
			model.setState(BattleshipState.SETNAMES);
			break;
		case "BACK":
			System.out.println("back to start/load");
			model.setState(BattleshipState.WELCOME);
			break;
		case "P1vsP2":
			newGamePanel.difficultyPanel.setVisible(false);;
			break;
		case "P1vsCPU":
			newGamePanel.difficultyPanel.setVisible(true);
			break;
		default:
			System.out.println("error");
			//TODO throw exception?
			break;
		}
	}
}
