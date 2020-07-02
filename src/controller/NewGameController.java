package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import model.BattleshipModel;
import model.Computer;
import model.Player;
import utils.BattleshipState;
import view.NewGamePanel;

import utils.ComputerType;

public class NewGameController implements ActionListener{
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
				if(newGamePanel.p1vsp2Button.isSelected()) {
					//feature unavailable
					model.setState(BattleshipState.NEWGAME);
					System.out.println("\tP1vsP2");
				}
				else {
					int gameSize = 10;
					ComputerType computerType = ComputerType.STUPID;
					boolean timed = false;
					if(newGamePanel.p1vsCPUButton.isSelected()) {
						System.out.println("\tP1vsCPU");
						
						if(newGamePanel.easyModeButton.isSelected())
							computerType = ComputerType.STUPID;
						
						if(newGamePanel.hardModeButton.isSelected())
							computerType = ComputerType.SMART;
						
						if(newGamePanel.sizeSButton.isSelected())//da togliere
							System.out.println("\t5x5");
						
						if(newGamePanel.sizeMButton.isSelected())
							gameSize = 10;
						
						if(newGamePanel.sizeLButton.isSelected())
							gameSize = 15;
						
						if(newGamePanel.sizeXLButton.isSelected())
							gameSize = 20;
						
						if(newGamePanel.timedCheckBox.isSelected())
							timed = true;
						
						//model = new BattleshipModel(gameSize);
						//model.setGameSize(gameSize);
						model.newGame(new Player(gameSize), new Computer(gameSize, computerType), gameSize, timed);
						model.getPlayer().randomSetShips();
						System.out.println("model.newGame(new Player("+gameSize+"), new Computer("+gameSize+", "+computerType+"), "+gameSize+", "+timed+")");
						System.out.println("GameSize: "+model.getGameSize());
						System.out.println(model.getPlayer().toString());
					}
					model.setState(BattleshipState.SETSHIPS);
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
