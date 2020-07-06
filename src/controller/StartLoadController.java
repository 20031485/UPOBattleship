package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import model.BattleshipModel;
import utils.BattleshipState;
import view.StartLoadPanel;

public class StartLoadController implements ActionListener{
	//attributes
	private BattleshipModel model;
	@SuppressWarnings("unused")
	private StartLoadPanel startLoadPanel;
	
	//constructor
	public StartLoadController(BattleshipModel model, StartLoadPanel startLoadPanel) {
		this.model = model;
		this.startLoadPanel = startLoadPanel;
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String command = source.getText();
		switch(command) {
			case "New game":
				System.out.println("new game");
				model.setState(BattleshipState.NEWGAME);
				break;
				
			case "Load game":
				try {
					model = BattleshipModel.loadGame();
					//System.out.println("Loaded: " + model.toString());
					model.newGame(model.getPlayer(), model.getComputer(), model.getGameSize(), model.isTimed(), model.getMins());
					model.print();
					System.out.println("loading saved file... ready to battle?");
					model.setState(BattleshipState.BATTLE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			default:
				//hopefully never displayed LOL
				System.err.println("ERROR@StartLoadController::actionPerformed()");
		}
	}
}
