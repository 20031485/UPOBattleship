package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.BattleshipModel;
import model.Computer;
import model.Player;
import utils.ComputerType;

public class TestView extends JFrame implements PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton saveButton;
	private JButton loadButton;
	protected BattleshipModel model = null;
	
	public TestView(BattleshipModel model) {
		this.model = model;
		this.setSize(200, 200);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.model.addPropertyChangeListener(this);
		
		TestController actionListener = new TestController(model);

		saveButton = new JButton("SAVE");
		saveButton.addActionListener(actionListener);
		this.add(saveButton);
		
		loadButton = new JButton("LOAD");
		loadButton.addActionListener(actionListener);
		this.add(loadButton);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		int gameSize = 15;
		BattleshipModel m = new BattleshipModel(new Player(gameSize), new Computer(gameSize, ComputerType.SMART), gameSize, false, 0);
		m.getPlayer().randomSetShips();
		m.getComputer().randomSetShips();
		TestView tv = new TestView(m);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
