package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleshipView extends JFrame implements PropertyChangeListener{
	//attributes
	private static final String TITLE = "UPOBattleship";
	private BattleshipModel model;
	private BattleshipController controller;
	private JLayeredPane layers;
	
	//all panels to be shown
	private BattleshipStartLoadPanel startLoadPanel;
	private BattleshipNewGamePanel newGamePanel;
	
	//TODO
	//private BattleshipSetNamePanel setNamePanel;
	//private BattleshipSetShipsPanel setShipsPanel;
	
	//constructor
	public BattleshipView(BattleshipModel model) {
		this.model = model;
		this.model.addPropertyChangeListener(this);
		this.controller = new BattleshipController(model);
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new BattleshipWindowDestructor());
		setLayout(new BorderLayout());
		
		startLoadPanel = new BattleshipStartLoadPanel(model, controller.getStartLoadController());
		add(startLoadPanel);
		startLoadPanel.setVisible(true);
		newGamePanel = new BattleshipNewGamePanel(model, controller.getNewGameController());
		add(newGamePanel);
		newGamePanel.setVisible(false);
		setSize(startLoadPanel.getWidth(), startLoadPanel.getHeight());
		setTitle("WELCOME: "+startLoadPanel.getTitle());
		setVisible(true);
	}
	
	//methods
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			switch(this.model.getState()) {
				case WELCOME:
					setTitle("WELCOME: " + startLoadPanel.getTitle());
					setSize(startLoadPanel.getWidth(), startLoadPanel.getHeight());
					break;
				
				case BATTLE:
					break;
				
				case NEWGAME:
					setTitle(newGamePanel.getTitle());
					setSize(newGamePanel.getWidth(), newGamePanel.getHeight());
					break;
				
				case RESUME:
					break;
				
				case SETNAMES:
					break;
				
				default:
					break;
			}
		}
	}
}