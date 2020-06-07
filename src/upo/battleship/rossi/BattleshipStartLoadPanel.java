package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

//THIS IS A VIEW

public class BattleshipStartLoadPanel extends JPanel implements PropertyChangeListener{
	//attributes
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private static final String TITLE = "NEW/LOAD GAME";
	private BattleshipModel model;
	private BattleshipStartLoadController controller;
	private JLabel label;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	
	private JButton newGameButton;
	protected JButton loadGameButton;
	
	//constructors
	BattleshipStartLoadPanel(BattleshipModel model, BattleshipStartLoadController controller){
		this.model = model;
		this.controller = controller;
		this.model.addPropertyChangeListener(this); //view listens to model
		
		//settings
		this.setLayout(new BorderLayout());
		this.setSize(WIDTH, HEIGHT);
		
		//mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//JLabel
		label = new JLabel("Welcome to UPOBattleship!");
		label.setHorizontalAlignment(JLabel.CENTER);
		
		mainPanel.add(label, BorderLayout.NORTH);
		
		//JPanel for JButtons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(controller);
		buttonPanel.add(newGameButton);
		
		loadGameButton = new JButton("Load game");
		loadGameButton.addActionListener(controller);
		buttonPanel.add(loadGameButton);
		if(!BattleshipModel.savedGameExists())
			loadGameButton.setVisible(false);
		
		
		//add panel to frame
		
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		add(mainPanel);
		setVisible(true);
	}
		
	public String getTitle() {
		return TITLE;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.WELCOME) {
				if(BattleshipModel.savedGameExists())
					loadGameButton.setVisible(true);
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
	}
}
