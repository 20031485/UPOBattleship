package upo.battleship.rossi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

public class BattleshipController{
	//attributes
	
	//model
	private BattleshipModel model;
	
	//sub-controllers
	private BattleshipStartLoadController startLoadController;
	private BattleshipNewGameController newGameController;

	//constructor
	public BattleshipController(BattleshipModel model) {
		this.model = model;
		this.startLoadController = new BattleshipStartLoadController(model);
		//this.newGameController = new BattleshipNewGameController(model);
		//this.setNameController = new BattleshipSetNameController(model);
		//this.setShipsController = new BattleshipSetShipsController(model);
	}
	
	public BattleshipStartLoadController getStartLoadController() {
		return this.startLoadController;
	}
	
	public BattleshipNewGameController getNewGameController() {
		return this.newGameController;
	}
}
