package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

public class BattlePanel extends JPanel implements Observer, PropertyChangeListener{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private BattleController controller;
	
	private static final String TITLE = "BATTLE!";
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 560;
	
	private JPanel bothGridsPanel;
	
	private static final TitledBorder shipsGridTitle = BorderFactory.createTitledBorder("YOUR SHIPS");
	private JPanel shipsGridPanel;
	private JButton[][] shipsButtonGrid;
	
	private static final TitledBorder hitsGridTitle = BorderFactory.createTitledBorder("ENEMY'S SHIPS");
	private JPanel hitsGridPanel;
	private JButton[][] hitsButtonGrid;
	
	private JPanel allButtonsPanel;
	
	private JButton saveButton;
	private JButton pauseButton;
	
	private JPanel timerPanel;
	private static final TitledBorder timerTitle = BorderFactory.createTitledBorder("TIMER");
	private JLabel timerLabel;

	

	//private BattleController controller;

	//constructor
	public BattlePanel(BattleshipModel model) {
		this.model = model;
		
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		//link changeListener
		this.model.addPropertyChangeListener(this);
		
		//link controller
		this.controller = new BattleController(model, this);
	}
	
	//methods
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
			timerPanel = new JPanel();
			timerPanel.setLayout(new BorderLayout());
			
			//TODO set timerPanel content
			JLabel label = new JLabel("Un bellissimo timer");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font("Monospace", Font.PLAIN, 20));
			label.setBorder(timerTitle);
			timerPanel.add(label);
			add(timerPanel, BorderLayout.NORTH);
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
		
	}
	
	public void updateAllComponents() {
		updateShipsGrid();
		
		updateHitsGrid();
		
		if(model.isJustSaved())
			saveButton.setEnabled(false);
		else
			saveButton.setEnabled(true);
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
					//if there are no ships and no hits (true/true)
					if(shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1] = new JButton();
						shipsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						shipsButtonGrid[i-1][j-1].setBackground(Color.WHITE);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
						shipsButtonGrid[i-1][j-1].addActionListener(controller);
						shipsGridPanel.add(shipsButtonGrid[i-1][j-1]);
					}
					//if there are ships but no hits (false/true)
					else if(!shipsGrid[i-1][j-1] && hitsGrid[i-1][j-1]){
						shipsButtonGrid[i-1][j-1] = new JButton();
						shipsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						shipsButtonGrid[i-1][j-1].setBackground(Color.BLACK);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
						shipsGridPanel.add(shipsButtonGrid[i-1][j-1]);
					}
					//if there are no ships but hits (true/false)
					else if(shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1] = new JButton();
						shipsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						shipsButtonGrid[i-1][j-1].setBackground(Color.BLUE);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
						shipsGridPanel.add(shipsButtonGrid[i-1][j-1]);
					}
					//if there are both ships and hits (false/false)
					else if(!shipsGrid[i-1][j-1] && !hitsGrid[i-1][j-1]) {
						shipsButtonGrid[i-1][j-1] = new JButton();
						shipsButtonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						shipsButtonGrid[i-1][j-1].setBackground(Color.RED);
						shipsButtonGrid[i-1][j-1].setOpaque(true);
						shipsButtonGrid[i-1][j-1].setEnabled(false);
						shipsGridPanel.add(shipsButtonGrid[i-1][j-1]);
					}
				}
			}
		}
		shipsGridPanel.setBorder(shipsGridTitle);
		shipsGridPanel.setVisible(true);
		bothGridsPanel.add(shipsGridPanel);
	}
	
	public void updateShipsGrid() {
		int gameSize = model.getGameSize();
		
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getPlayer().getShipsGrid();
		
		boolean[][] hitsGrid = new boolean[gameSize][gameSize];
		hitsGrid = model.getPlayer().getHitsGrid();
		
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				//if there are no ships and no hits (true/true)
				if(shipsGrid[i][j] && hitsGrid[i][j]) {
					//doesn't touch the button
				}
				
				//if there are ships but no hits (false/true)
				else if(!shipsGrid[i][j] && hitsGrid[i][j]){
					//doesn't touch the button
				}
				
				//if there are no ships but hits (true/false)
				else if(shipsGrid[i][j] && !hitsGrid[i][j]) {
					shipsButtonGrid[i][j].setBackground(Color.BLUE);
					shipsButtonGrid[i][j].setOpaque(true);
				}
				
				//if there are both ships and hits (false/false)
				else if(!shipsGrid[i][j] && !hitsGrid[i][j]) {
					shipsButtonGrid[i][j].setBackground(Color.RED);
					shipsButtonGrid[i][j].setOpaque(true);
				}
			}
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
					hitsButtonGrid[i-1][j-1].setBackground(Color.WHITE);
					hitsButtonGrid[i-1][j-1].addActionListener(controller);
					hitsGridPanel.add(hitsButtonGrid[i-1][j-1]);
				}
			}
		}
		hitsGridPanel.setBorder(hitsGridTitle);
		hitsGridPanel.setVisible(true);
		bothGridsPanel.add(hitsGridPanel);
	}
	
	public void updateHitsGrid() {
		int gameSize = model.getGameSize();
		
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getComputer().getShipsGrid();
		
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
					System.out.println("ship:" + shipsGrid[i][j] + "\thit: "+hitsGrid[i][j]);
					System.out.println("\nBLUE\n");
					hitsButtonGrid[i][j].setBackground(Color.BLUE);
					hitsButtonGrid[i][j].setOpaque(true);
					hitsButtonGrid[i][j].setEnabled(false);
				}
				
				//if there are both ships and hits (false/false)
				else if(!shipsGrid[i][j] && !hitsGrid[i][j]) {
					System.out.println("ship:" + shipsGrid[i][j]+"\thit: " + hitsGrid[i][j]);
					System.out.println("\nRED\n");
					hitsButtonGrid[i][j].setBackground(Color.RED);
					hitsButtonGrid[i][j].setOpaque(true);
					hitsButtonGrid[i][j].setEnabled(false);
				}
			}
		}
	}
	
	public void setButtonsPanel() {
		allButtonsPanel = new JPanel();
		allButtonsPanel.setLayout(new FlowLayout());
		
		saveButton = new JButton("SAVE GAME");
		saveButton.addActionListener(controller);
		if(model.isJustSaved()) {
			saveButton.setEnabled(false);
		}
		allButtonsPanel.add(saveButton);
		
		if(model.isTimed()) {
			pauseButton = new JButton("PAUSE");
			pauseButton.addActionListener(controller);
			allButtonsPanel.add(pauseButton);
		}
			
		add(allButtonsPanel, BorderLayout.SOUTH);
	}
	
	public JButton getButtonFromHitsButtonGrid(int i, int j) {
		return hitsButtonGrid[i][j];
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.BATTLE) {
				setAllComponents();
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		updateAllComponents();
	}
}
