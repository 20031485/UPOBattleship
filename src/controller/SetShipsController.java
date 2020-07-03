package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;

import model.BattleshipModel;
import view.SetShipsPanel;
import utils.BattleshipState;
import utils.ShipDirection;

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
		JButton source = (JButton) e.getSource();
		int gameSize = model.getGameSize();
		int row, col;
		
		String command = source.getText();
		if(command.equals("RANDOM")) {
			System.out.println("Random Set pressed");
			model.getPlayer().randomSetShips();
			System.out.println(model.getPlayer().toString());
		}
		
		if(command.equals("CLEAR")) {
			System.out.println("Clear pressed");
			model.getPlayer().clearShips();
			model.getPlayer().clearShips();
			model.getPlayer().clearShips();
		}
		
		if(command.equals("PLAY")) {
			System.out.println("PLAY pressed");
			model.setState(BattleshipState.BATTLE);
			System.out.println(model.getPlayer().toString());
		}
		
		else {
			for(int i = 0; i < gameSize; ++i) {
				for(int j = 0; j < gameSize; ++j) {
					if(source == setShipsPanel.getButtonFromButtonGrid(i, j)) {
						row = i;
						col = j;
						System.out.println("row = "+ row +", col = "+ col);
						int shipIndex = setShipsPanel.getChooseShip().getSelectedIndex();
						
						ShipDirection direction;
						int directionIndex = setShipsPanel.getChooseDirection().getSelectedIndex();
						
						if(directionIndex == 0)
							direction = ShipDirection.VERTICAL;
						else
							direction = ShipDirection.HORIZONTAL;
						
						model.getPlayer().setShip(shipIndex, row, col, direction);
						System.out.println("model.getPlayer().setShip("+shipIndex+", "+row+", "+col+", "+direction+")");
					}
				}
			}
		}
		System.out.println(model.getPlayer().toString());
	}

}