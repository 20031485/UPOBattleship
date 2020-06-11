package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import model.BattleshipModel;
import utils.BattleshipState;

public class StartLoadController implements ActionListener{
	//attributes
	private BattleshipModel model;
	
	//constructor
	public StartLoadController(BattleshipModel model) {
		this.model = model;
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
				System.out.println("loading saved file... ready to battle?");
				model.setState(BattleshipState.BATTLE);
				break;
			default:
				//hopefully never displayed LOL
				System.out.println("BattleshipStartLoadWindow error!");
		}
	}
}
