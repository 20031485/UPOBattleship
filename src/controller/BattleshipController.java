package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;

import model.BattleshipModel;
import view.BattlePanel;
import view.NewGamePanel;
import view.SetShipsPanel;
import view.StartLoadPanel;

public class BattleshipController{
	//attributes
	
	//model
	private BattleshipModel model;

	//constructor
	public BattleshipController(BattleshipModel model) {
		this.model = model;
	}
	
	//methods
	public StartLoadController giveStartLoadController(BattleshipModel model, StartLoadPanel panel) {
		return new StartLoadController(model, panel);
	}
	
	public NewGameController giveNewGameController(BattleshipModel model, NewGamePanel panel) {
		return new NewGameController(model, panel);
	}
	
	public SetShipsController giveSetShipsController(BattleshipModel model, SetShipsPanel panel) {
		return new SetShipsController(model, panel);
	}
	
	public BattleController giveBattleController(BattleshipModel model, BattlePanel panel) {
		return new BattleController(model, panel);
	}
}