package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

import model.BattleshipModel;

public class BattleshipController{
	//attributes
	
	//model
	private BattleshipModel model;
	
	//sub-controllers
	private StartLoadController startLoadController;
	private NewGameController newGameController;

	//constructor
	public BattleshipController(BattleshipModel model) {
		this.model = model;
		this.startLoadController = new StartLoadController(model);
		//this.newGameController = new BattleshipNewGameController(model);
		//this.setNameController = new BattleshipSetNameController(model);
		//this.setShipsController = new BattleshipSetShipsController(model);
	}
	
	public StartLoadController getStartLoadController() {
		return this.startLoadController;
	}
	
	public NewGameController getNewGameController() {
		return this.newGameController;
	}
}
