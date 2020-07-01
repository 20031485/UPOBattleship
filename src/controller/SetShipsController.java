package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;

import model.BattleshipModel;
import view.SetShipsPanel;

public class SetShipsController implements ActionListener{
	//attributes
	private BattleshipModel model;
	private SetShipsPanel setShipsPanel;
	
	//constructor
	public SetShipsController(BattleshipModel model, SetShipsPanel setShipsPanel) {
		this.model = model;
		this.setShipsPanel = setShipsPanel;
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton source = (AbstractButton)e.getSource();
		int gameSize = model.getGameSize();
		int row, column;
		
		String command = source.getText();
		if(command.equals("RANDOM")) {
			System.out.println("Random Set pressed");
			model.getPlayer().randomSetShips();
			System.out.println(model.getPlayer().toString());
		}
		if(command.equals("CLEAR")) {
			System.out.println("Clear pressed");
			model.getPlayer().clearShips();
		}
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				if(source == setShipsPanel.getButtonFromButtonGrid(i, j)) {
					row = i;
					column = j;
					System.out.println("x = "+i+", y = "+j);
				}
			}
		}
		System.out.println("x = "+source.getX()+", y = "+source.getY());
	}

}