package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import model.BattleshipModel;
import utils.BattleshipState;
import utils.ShipDirection;
import view.BattlePanel;

public class BattleController implements ActionListener{

	private BattleshipModel model;
	private BattlePanel battlePanel;
	
	public BattleController(BattleshipModel model, BattlePanel battlePanel) {
		this.model = model;
		this.battlePanel = battlePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		int gameSize = model.getGameSize();
		int row, col;
		String command = source.getText();
		
		if(command.equals("SAVE GAME")) {
			System.out.println("SAVE");
			PropertyChangeListener[] listeners = model.getPropertyChangeListeners();
			model.saveGame();
			BattleshipModel bm = null;
			try {
				bm = BattleshipModel.loadGame();
				bm.print();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(command.equals("PAUSE")) {
			System.out.println("PAUSE");
			//TODO
		}
		
		if(command.equals("REMATCH")) {
			System.out.println("REMATCH");
			model.setState(BattleshipState.NEWGAME);
			//TODO
		}
		
		else {
			for(int i = 0; i < gameSize; ++i) {
				for(int j = 0; j < gameSize; ++j) {
					if(source == battlePanel.getButtonFromHitsButtonGrid(i, j)) {
						row = i;
						col = j;
						//System.out.println("row = "+ row +", col = "+ col);
						model.hitAndGetHit(row, col);
						//System.out.println(model.getComputer().toString());
					}
				}
			}
			//System.out.println(model.getPlayer().toString());
		}
	}
}
