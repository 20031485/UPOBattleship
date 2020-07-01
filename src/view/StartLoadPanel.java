package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.StartLoadController;
import model.BattleshipModel;
import utils.BattleshipState;

//THIS IS A VIEW

public class StartLoadPanel extends JPanel implements PropertyChangeListener{
	private static final long serialVersionUID = 1L;
	//attributes
	private static final int WIDTH = 300;
	private static final int HEIGHT = 100;
	private static final String TITLE = "NEW/LOAD GAME";
	private BattleshipModel model;
	private StartLoadController controller;
	private JLabel label;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	
	private JButton newGameButton;
	protected JButton loadGameButton;
	
	//constructors
	StartLoadPanel(BattleshipModel model/*, StartLoadController controller*/){
		this.model = model;
		this.controller =  new StartLoadController(model, this);//controller;
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
