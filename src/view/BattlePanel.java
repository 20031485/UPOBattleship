package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.BattleController;
import model.BattleshipModel;
import utils.BattleshipState;
import utils.Utility;
//import view.CountdownLabel;

public class BattlePanel extends JPanel implements Observer/*, PropertyChangeListener*/{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private BattleController controller;
	
	private static final String TITLE = "BATTLE!";
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	
	private JPanel bothGridsPanel;
	
	private static final TitledBorder shipsGridTitle = BorderFactory.createTitledBorder("YOUR SHIPS");
	private JPanel leftPanel;
	private JPanel shipsGridPanel;
	private JButton[][] shipsButtonGrid;
	
	private static final TitledBorder hitsGridTitle = BorderFactory.createTitledBorder("ENEMY'S SHIPS");
	private JPanel rightPanel;
	private JPanel hitsGridPanel;
	private JButton[][] hitsButtonGrid;
	
	private JPanel allButtonsPanel;
	
	private JButton saveButton;
//	private JButton rematchButton;
	
	private CountdownPanel timerPanel;
	
	private boolean here = false;
	
	
	private static final String PLAYER_STATUS = "PLAYER STATUS: ";
	private static final String COMPUTER_STATUS = "COMPUTER STATUS: ";
	private static final String YOU_WIN = "You win!";
	private static final String YOU_LOSE = "You lose!";
	
	private JLabel playerStatusLabel = new JLabel(PLAYER_STATUS);
	private JLabel computerStatusLabel = new JLabel(COMPUTER_STATUS);
	private JLabel youWinLabel = new JLabel(YOU_WIN);
	private JLabel youLoseLabel = new JLabel(YOU_LOSE);
	

	//private BattleController controller;

	//constructor
	public BattlePanel(BattleshipModel model) {
		this.model = model;
		
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		//link changeListener
		//this.model.addPropertyChangeListener(this);
		this.model.addObserver(this);
		
		//link controller
		this.controller = new BattleController(model, this);
	}
	
	/**
	 * Returns a {@code String} containing the title of the panel.
	 * @return A {@code String} containing the title of the panel.
	 */
	public static String getTitle() {
		return TITLE;
	}
	
	public void setAllComponents() {
		System.out.println("setAllComponents");
			
		//link observables
		this.model.getPlayer().addObserver(this);
		this.model.getComputer().addObserver(this);
		
		//if the game is timed
		if(model.isTimed()) {
			setTimer(model.getMins());	
		}
		
		//create panel with grids
		bothGridsPanel = new JPanel();
		bothGridsPanel.setLayout(new FlowLayout());
		
		//fill panel with grids
		setShipsGrid();
		setHitsGrid();
		
		add(bothGridsPanel, BorderLayout.CENTER);
		
		//create panel with buttons
		setButtonsPanel();
		//countdownPanel.countdownStart();
	}
	
	public void setTimer(long mins) {
		timerPanel = new CountdownPanel(mins, model);
		add(timerPanel, BorderLayout.NORTH);
	}
	
	public void updateAllComponents() {
		updateShipsGrid();
		
		updateHitsGrid();
		
		if(!model.isJustSaved())
			saveButton.setEnabled(false);
		else
			saveButton.setEnabled(true);
	}
	
	public void removeAllComponents() {
		if(timerPanel != null)
			remove(timerPanel);
		if(bothGridsPanel != null)
			remove(bothGridsPanel);
		if(allButtonsPanel != null)
			remove(allButtonsPanel);
	}
	
	public void setShipsGrid() {
		int gameSize = model.getGameSize();
		int dim = 0;
		if(gameSize == 10)
			dim = 30;
		if(gameSize == 15)
			dim = 25;
		if(gameSize == 20)
			dim = 20;

		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		//Player's shipsGrid
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getPlayer().getShipsGrid();
		
		boolean[][] hitsGrid = new boolean[gameSize][gameSize];
		hitsGrid = model.getPlayer().getHitsGrid();
		
		shipsGridPanel = new JPanel();
		shipsGridPanel.setLayout(new GridLayout(gameSize + 1, gameSize + 1));
		
		//the buttonGrid must be one cell smaller than shipsPanel
		//i.e. as big as Player's shipsGrid
		shipsButtonGrid = new JButton[gameSize][gameSize];
		
		for(int i = 0; i < gameSize + 1; ++i) {
			for(int j = 0; j < gameSize + 1; ++j) {
				
				if(i == 0 && j == 0) {
					JTextField t = new JTextField();
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsGridPanel.add(t);
				}
				
				else if(i == 0 && j != 0) {
					JTextField t = new JTextField(Utility.COLUMNS[j]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsGridPanel.add(t);
				}
				
				else if(i != 0 && j == 0) {
					JTextField t = new JTextField(Utility.ROWS[i]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsGridPanel.add(t);
				}
				
				else {
					shipsButtonGrid[i-1][j-1] = new JButton();
					shipsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
					
					//if there are no ships and no hits (true/true)
					if(shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1].setBackground(Color.WHITE);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
						shipsButtonGrid[i-1][j-1].addActionListener(controller);
					}
					//if there are ships but no hits (false/true)
					else if(!shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]){
						shipsButtonGrid[i-1][j-1].setBackground(Color.BLACK);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
					}
					//if there are no ships but hits (true/false)
					else if(shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1].setBackground(Color.BLUE);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
					}
					//if there are both ships and hits (false/false)
					else if(!shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1].setBackground(Color.RED);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
					}
					shipsGridPanel.add(shipsButtonGrid[i-1][j-1]);
				}
			}
		}
		
		shipsGridPanel.setBorder(shipsGridTitle);
		shipsGridPanel.setVisible(true);
		leftPanel.add(shipsGridPanel, BorderLayout.NORTH);
		leftPanel.add(playerStatusLabel, BorderLayout.CENTER);
		bothGridsPanel.add(leftPanel);
	}
	
	public void updateShipsGrid() {
		int gameSize = model.getGameSize();
		
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getPlayer().getInitialShipsGrid();
		
		boolean[][] hitsGrid = new boolean[gameSize][gameSize];
		hitsGrid = model.getPlayer().getHitsGrid();
		
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				//if there are no ships and no hits (true/true)
				if(shipsGrid[i][j] && hitsGrid[i][j]) {
					
				}
				
				//if there are ships but no hits (false/true)
				else if(!shipsGrid[i][j] && hitsGrid[i][j]){
					
				}
				
				//if there are no ships but hits (true/false)
				else if(shipsGrid[i][j] && !hitsGrid[i][j]) {
					shipsButtonGrid[i][j].setBackground(Color.BLUE);
					shipsButtonGrid[i][j].setOpaque(true);
					//statusLabel.setText("Computer missed you!");
				}
				
				//if there are both ships and hits (false/false)
				else if(!shipsGrid[i][j] && !hitsGrid[i][j]) {
					shipsButtonGrid[i][j].setBackground(Color.RED);
					shipsButtonGrid[i][j].setOpaque(true);
					//statusLabel.setText("Computer hits you!");
				}
			}
		}
		
		switch(model.getPlayer().getState()) {
			case HIT:
				playerStatusLabel.setText(PLAYER_STATUS + "It hurts, doesn't it?");
				break;
			
			case HITANDSUNK:
				if(model.getPlayer().isDefeated())
					playerStatusLabel.setText(PLAYER_STATUS + "Ha-ha! You're a looser!");
				else
					playerStatusLabel.setText(PLAYER_STATUS + "Ha-ha! I sunk your ship!");
				break;
				
			case WATER:
				playerStatusLabel.setText(PLAYER_STATUS + "I will hit you next time!");
				break;
		}
	}
	
	public void setHitsGrid() {
		int gameSize = model.getGameSize();
		int dim = 0;
		if(gameSize == 10)
			dim = 30;
		if(gameSize == 15)
			dim = 25;
		if(gameSize == 20)
			dim = 20;
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		//Player's shipsGrid
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getComputer().getShipsGrid();
		
		boolean[][] hitsGrid = new boolean[gameSize][gameSize];
		hitsGrid = model.getComputer().getHitsGrid();
		
		hitsGridPanel = new JPanel();
		hitsGridPanel.setLayout(new GridLayout(gameSize + 1, gameSize + 1));
		
		//the buttonGrid must be one cell smaller than shipsPanel
		//i.e. as big as Player's shipsGrid
		hitsButtonGrid = new JButton[gameSize][gameSize];
		
		for(int i = 0; i < gameSize + 1; ++i) {
			for(int j = 0; j < gameSize + 1; ++j) {
				
				if(i == 0 && j == 0) {
					JTextField t = new JTextField();
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					hitsGridPanel.add(t);
				}
				
				else if(i == 0 && j != 0) {
					JTextField t = new JTextField(Utility.COLUMNS[j]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					hitsGridPanel.add(t);
				}
				
				else if(i != 0 && j == 0) {
					JTextField t = new JTextField(Utility.ROWS[i]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					hitsGridPanel.add(t);
				}
				
				else {
					hitsButtonGrid[i-1][j-1] = new JButton();
					hitsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
					
					//if there are no ships and no hits (true/true)
					if(shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]) {
						hitsButtonGrid[i-1][j-1].setBackground(Color.WHITE);
						//hitsButtonGrid[i-1][j-1].setEnabled(false);
						hitsButtonGrid[i-1][j-1].addActionListener(controller);
					}
					//if there are ships but no hits (false/true)
					else if(!shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]){
						hitsButtonGrid[i-1][j-1].setBackground(Color.WHITE);
						//hitsButtonGrid[i-1][j-1].setOpaque(true);
						//hitsButtonGrid[i-1][j-1].setEnabled(false);
						hitsButtonGrid[i-1][j-1].addActionListener(controller);
					}
					//if there are no ships but hits (true/false)
					else if(shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						hitsButtonGrid[i-1][j-1].setBackground(Color.BLUE);
						hitsButtonGrid[i-1][j-1].setOpaque(true);
						hitsButtonGrid[i-1][j-1].setEnabled(false);
					}
					//if there are both ships and hits (false/false)
					else if(!shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						hitsButtonGrid[i-1][j-1].setBackground(Color.RED);
						hitsButtonGrid[i-1][j-1].setOpaque(true);
						hitsButtonGrid[i-1][j-1].setEnabled(false);
					}
					hitsGridPanel.add(hitsButtonGrid[i-1][j-1]);
				}
			}
		}
		//hitsGridPanel.add(computerStatusLabel);
		hitsGridPanel.setBorder(hitsGridTitle);
		hitsGridPanel.setVisible(true);
		rightPanel.add(hitsGridPanel, BorderLayout.NORTH);
		rightPanel.add(computerStatusLabel, BorderLayout.CENTER);
		bothGridsPanel.add(rightPanel);
	}
	
	public void updateHitsGrid() {
		int gameSize = model.getGameSize();
		
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getComputer().getInitialShipsGrid();
		
		boolean[][] hitsGrid = new boolean[gameSize][gameSize];
		hitsGrid = model.getComputer().getHitsGrid();
		
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {				
				//if there are no ships and no hits (true/true)
				if(shipsGrid[i][j] && hitsGrid[i][j]) {
					
				}
				
				//if there are ships but no hits (false/true)
				else if(!shipsGrid[i][j] && hitsGrid[i][j]){
					
				}
				
				//if there are no ships but hits (true/false)
				else if(shipsGrid[i][j] && !hitsGrid[i][j]) {
					hitsButtonGrid[i][j].setBackground(Color.BLUE);
					hitsButtonGrid[i][j].setEnabled(false);
				}
				
				//if there are both ships and hits (false/false)
				else if(!shipsGrid[i][j] && !hitsGrid[i][j]) {
					hitsButtonGrid[i][j].setBackground(Color.RED);
					hitsButtonGrid[i][j].setEnabled(false);
				}
				hitsButtonGrid[i][j].setOpaque(true);
				
				if(model.getPlayer().isDefeated() || model.getComputer().isDefeated() || model.getPlayer().isTimedOut()) {
					hitsButtonGrid[i][j].setEnabled(false);
				}
			}
		}
		
		switch(model.getComputer().getState()) {
			case HIT:
				computerStatusLabel.setText(COMPUTER_STATUS + "Ouch, you hit me!");
				break;
			
			case HITANDSUNK:
				if(model.getComputer().isDefeated())
					computerStatusLabel.setText(COMPUTER_STATUS + "Oh no! My fleet!");
				else
					computerStatusLabel.setText(COMPUTER_STATUS + "Damn you, you sunk my ship!");
				break;
				
			case WATER:
				computerStatusLabel.setText(COMPUTER_STATUS + "Ha-ha! You missed!");
				break;
		}
		
		
	}
	
	public void setButtonsPanel() {
		allButtonsPanel = new JPanel();
		allButtonsPanel.setLayout(new FlowLayout());
		
		saveButton = new JButton("SAVE GAME");
		saveButton.addActionListener(controller);
		
		//rematchButton = new JButton("REMATCH");
		//rematchButton.addActionListener(controller);
		
		if(model.isJustSaved()) {
			saveButton.setEnabled(false);
		}
		else {
			saveButton.setEnabled(true);
		}
		
		allButtonsPanel.add(saveButton);
		//allButtonsPanel.add(rematchButton);
		
		youWinLabel.setHorizontalAlignment(JLabel.CENTER);
		youWinLabel.setFont(new Font("Monospace", Font.PLAIN, 50));
		allButtonsPanel.add(youWinLabel);
		youWinLabel.setVisible(false);
		
		
		youLoseLabel.setHorizontalAlignment(JLabel.CENTER);
		youLoseLabel.setFont(new Font("Monospace", Font.PLAIN, 50));
		allButtonsPanel.add(youLoseLabel);
		youLoseLabel.setVisible(false);
		
		//rematchButton.setVisible(false);
		
		if(model.isTimed()) {
			/*pauseButton = new JButton("PAUSE");
			pauseButton.addActionListener(controller);
			allButtonsPanel.add(pauseButton);*/
		}
			
		add(allButtonsPanel, BorderLayout.SOUTH);
	}
	
	public JButton getButtonFromHitsButtonGrid(int i, int j) {
		return hitsButtonGrid[i][j];
	}
	
	public void addRematchPanel() {
		
	}
/*	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.BATTLE) {
				setAllComponents();
				this.setVisible(true);
				if(model.isTimed())
					timerPanel.timerStart();
			}
			else
				//removeAllComponents();
				this.setVisible(false);
		}
	}
*/
	@Override
	public void update(Observable o, Object arg) {
		if(model.getState() == BattleshipState.BATTLE) {
			if(!here) {
				setAllComponents();
				this.setVisible(true);
				if(model.isTimed())
					timerPanel.timerStart();
				here = true;
			}
			//real update
			updateAllComponents();
			
			//update saveButton
			if(model.isJustSaved()) {
				saveButton.setEnabled(false);
			}
			else {
				saveButton.setEnabled(true);
			}
			
			//check for win/lose conditions
			if(model.getPlayer().isDefeated() || model.getComputer().isDefeated() || model.getPlayer().isTimedOut()) {

				saveButton.setVisible(false);
				//if(model.isTimed())
					//pauseButton.setVisible(false);
				//if computer wins
				if(model.getPlayer().isDefeated()) {
					youLoseLabel.setVisible(true);
					
					//if time runs out
					if(model.getPlayer().isTimedOut()) {
						if(timerPanel != null)
							timerPanel.timerStop();
					}
				}
				//if player wins
				if(model.getComputer().isDefeated()) {
					if(timerPanel != null)
						timerPanel.timerStop();
					youWinLabel.setVisible(true);
				}
			}
		}
		else {
			//removeAllComponents();
			this.setVisible(false);
			here = false;
		}
		
		
	}
}
