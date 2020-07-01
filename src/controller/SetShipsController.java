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
	public SetShipsController(BattleshipModel model /*, SetShipsPanel setShipsPanel*/) {
		this.model = model;
	//	this.setShipsPanel = setShipsPanel;
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton source = (AbstractButton)e.getSource();
		//if(source instanceof JButton) {
			int x = source.getX();
			int y = source.getY();
		System.out.println("x = "+x+", y = "+y);
	}

}