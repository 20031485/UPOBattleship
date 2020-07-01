package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

import model.BattleshipModel;
import view.SetShipsPanel;

public class BattleshipController{
	//attributes
	
	//model
	private BattleshipModel model;
	
	//sub-controllers
	private StartLoadController startLoadController;
	private NewGameController newGameController;
	private SetShipsController setShipsController;
	private SetShipsPanel a;
	//private BattleController battleController;

	//constructor
	public BattleshipController(BattleshipModel model) {
		this.model = model;
		this.startLoadController = new StartLoadController(model);
		//this.newGameController = new NewGameController(model);
		this.setShipsController = new SetShipsController(model/*, a*/);
		//this.battleController = new BattleController();
	}
	
	public StartLoadController getStartLoadController() {
		return this.startLoadController;
	}
	
	public NewGameController getNewGameController() {
		return this.newGameController;
	}
	
	public SetShipsController getSetShipsController() {
		return this.setShipsController;
	}
}