package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.SetShipsController;
import model.BattleshipModel;
import model.Ship;
import utils.BattleshipState;
import utils.ShipType;

public class SetShipsPanel extends JPanel implements Observer/*, PropertyChangeListener*/{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private SetShipsController controller;
	private static final String TITLE = "SET YOUR SHIPS!";
	private static final String[] ROWS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] COLUMNS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	private static String[] direction = { "VERTICAL", "HORIZONTAL" };
	
	//base dimensions
	private int WIDTH = 700;
	private int HEIGHT = 700;
	
	private JPanel dropDownAndButtonsPanel; //panel hosting buttons and dropdown menus
	private JPanel shipsPanel; //panel on which the buttonGrid will be shown
	private JButton[][] buttonGrid; //clickable grid of buttons for setting ships
	
	private static final TitledBorder chooseShipTitle = BorderFactory.createTitledBorder("Ship (length)");
	private JComboBox<String> chooseShip;

	private static final TitledBorder chooseDirectionTitle = BorderFactory.createTitledBorder("Direction");
	private JComboBox<String> chooseDirection;
	
	private JButton play;
	private JButton clearShips;
	//private JButton randomSetOne;
	private JButton randomSetAll;
	private JButton back;
	
	
	private boolean here = false;
	

	/**
	 * Constructor for the Class {@code SetShipsPanel}. It creates a JPanel to
	 * be shown in the main game frame
	 * @param model A {@code BattleshipModel} object
	 */
	public SetShipsPanel(BattleshipModel model) {
		this.model = model;
		this.controller = new SetShipsController(model, this);//getNewController
		//System.out.println("SetShipsPanel: " + model.toString());
		this.setLayout(new FlowLayout());
		this.setSize(WIDTH, HEIGHT);
		
		//listen to property changes in model
		//this.model.addPropertyChangeListener(this);
		this.model.addObserver(this);
	}
	
	
	public void setAllComponents() {
		if(model.getGameSize() == 10)
			setSize(WIDTH, HEIGHT);
		if(model.getGameSize() == 15)
			setSize(WIDTH, HEIGHT+50);
		if(model.getGameSize() == 20)
			setSize(WIDTH, HEIGHT+100);
		
		//observe player
		this.model.getPlayer().addObserver(this);
		setDropDownAndButtonsPanel();
		setButtonGrid();
	}
	
	public void updateAllComponents() {
		//System.out.println("updateAllComponents");
		updateDropDownAndButtonsPanel();
		updateButtonGrid();
	}
	/**
	 * Creates and adds a panel with two dropdown menus and some buttons to the main panel
	 */
	public void setDropDownAndButtonsPanel() {
		//if a previous version of dropDown...Panel exists, remove it
		if(dropDownAndButtonsPanel != null)
			this.remove(dropDownAndButtonsPanel);
		
		dropDownAndButtonsPanel = new JPanel();
		dropDownAndButtonsPanel.setLayout(new FlowLayout());
		
		//fill the availableShips String array for the comboBox
		String[] availableShips = getPlayersShipsStrings(model);
		chooseShip = new JComboBox<String>(availableShips);
		chooseShip.setSelectedIndex(0);
		chooseShip.setBorder(chooseShipTitle);
		dropDownAndButtonsPanel.add(chooseShip);
		
		//comboBox for directions
		chooseDirection = new JComboBox<String>(direction);
		chooseDirection.setSelectedIndex(0);
		chooseDirection.setBorder(chooseDirectionTitle);
		dropDownAndButtonsPanel.add(chooseDirection);
		
		//button that sets all ships randomly
		randomSetAll = new JButton("RANDOM");
		randomSetAll.setEnabled(true);
		randomSetAll.addActionListener(controller);
		dropDownAndButtonsPanel.add(randomSetAll);
		
		//button that deletes all ships from the grid
		clearShips = new JButton("CLEAR");
		clearShips.setEnabled(true);
		clearShips.addActionListener(controller);
		dropDownAndButtonsPanel.add(clearShips);
		
		//enabled only if all ships have been set
		play = new JButton("PLAY");
		
		if(model.getPlayer().getShipList().size() == 0)
			play.setEnabled(true);
		else
			play.setEnabled(false);
		play.addActionListener(controller);
		dropDownAndButtonsPanel.add(play);
		
		//button that deletes all ships from the grid
		back = new JButton("BACK");
		back.setEnabled(true);
		back.addActionListener(controller);
		dropDownAndButtonsPanel.add(back);
		
		dropDownAndButtonsPanel.setVisible(true);
		add(dropDownAndButtonsPanel);
	}
	
	public void updateDropDownAndButtonsPanel() {
		//System.out.println("updateDropDownEcc");
		//ships comboBox
		String[] availableShips = getPlayersShipsStrings(model);
		if(chooseShip != null)
		chooseShip.removeAllItems();
		
		for(String s : availableShips) {
			if(chooseShip != null)
			chooseShip.addItem(s);
		}
		if(chooseShip != null) {
			chooseShip.setSelectedIndex(0);
			chooseShip.setBorder(chooseShipTitle);
		}
		//play button
		if(play != null) {
			if(model.getPlayer().getShipList().size() == 0)
				play.setEnabled(true);
			else
				play.setEnabled(false);
		}
	}
	
	/**
	 * Creates and adds a buttonGrid as big as the game size to the main panel
	 */
	public void setButtonGrid(){
		int gameSize = model.getGameSize();
		int dim = 25;
		
		//Player's shipsGrid
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getPlayer().getShipsGrid();
		
		//if a previous version of shipsPanel exists, remove it
		if(shipsPanel != null)
			remove(shipsPanel);
		
		shipsPanel = new JPanel();
		shipsPanel.setLayout(new GridLayout(gameSize + 1, gameSize + 1));
		
		//the buttonGrid must be one cell smaller than shipsPanel
		//i.e. as big as Player's shipsGrid
		buttonGrid = new JButton[gameSize][gameSize];
		
		for(int i = 0; i < gameSize + 1; ++i) {
			for(int j = 0; j < gameSize + 1; ++j) {
				
				if(i == 0 && j == 0) {
					JTextField t = new JTextField();
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsPanel.add(t);
				}
				
				else if(i == 0 && j != 0) {
					JTextField t = new JTextField(COLUMNS[j]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsPanel.add(t);
				}
				
				else if(i != 0 && j == 0) {
					JTextField t = new JTextField(ROWS[i]);
					t.setHorizontalAlignment(JTextField.CENTER);
					t.setPreferredSize(new Dimension(dim, dim));
					t.setEditable(false);
					shipsPanel.add(t);
				}
				
				else {
					//if there is no ship underneath
					if(shipsGrid[i-1][j-1]) {
						buttonGrid[i-1][j-1] = new JButton();
						buttonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						buttonGrid[i-1][j-1].setBackground(Color.WHITE);
						buttonGrid[i-1][j-1].addActionListener(controller);
						shipsPanel.add(buttonGrid[i-1][j-1]);
					}
					//if there is a ship underneath
					else {
						buttonGrid[i-1][j-1] = new JButton();
						buttonGrid[i-1][j-1].setPreferredSize(new Dimension(dim, dim));
						buttonGrid[i-1][j-1].setBackground(Color.BLACK);
						buttonGrid[i-1][j-1].setOpaque(true);
						buttonGrid[i-1][j-1].setEnabled(false);
						shipsPanel.add(buttonGrid[i-1][j-1]);
					}
				}
			}
		}
		shipsPanel.setVisible(true);
		add(shipsPanel);
	}
	
	public void updateButtonGrid() {
		//System.out.println("updateButtonGrid");
		int gameSize = model.getGameSize();
		
		
		//Player's shipsGrid
		boolean[][] shipsGrid = new boolean[gameSize][gameSize];
		shipsGrid = model.getPlayer().getShipsGrid();
	
		for(int i = 0; i < gameSize; ++i) {
			for(int j = 0; j < gameSize; ++j) {
				if(buttonGrid != null) {
					//if there is a ship underneath
					if(!shipsGrid[i][j]) {
						buttonGrid[i][j].setBackground(Color.BLACK);
						buttonGrid[i][j].setOpaque(true);
						buttonGrid[i][j].setEnabled(false);
					}
					else {
						buttonGrid[i][j].setBackground(Color.WHITE);
						buttonGrid[i][j].setOpaque(false);
						buttonGrid[i][j].setEnabled(true);
						if(model.getPlayer().getShipList().size() == 0)
							buttonGrid[i][j].setEnabled(false);
					}
				}
			}
		}
	}
	
	public JButton[][] getButtonGrid() {
		return buttonGrid;
	}
	
	public JButton getButtonFromButtonGrid(int i, int j) {
		return buttonGrid[i][j];
	}
	
	public void removeAllComponents() {
		dropDownAndButtonsPanel = null; //panel hosting buttons and dropdown menus
		shipsPanel = null; //panel on which the buttonGrid will be shown
		buttonGrid = null; //clickable grid of buttons for setting ships
		chooseShip = null;
		chooseDirection = null;
		play = null;
		clearShips = null;
		randomSetAll = null;
		back = null;
	}
	
	//methods
	/**
	 * Returns the String array representation of the list of Player's Ships
	 * @param model The BattleshipModel object
	 * @return A String array
	 */
	public String[] getPlayersShipsStrings(BattleshipModel model) {
		int playerShipsNo = model.getPlayer().getShipList().size();
		String[] stringArray = new String[playerShipsNo];
		
		//if there are no more ships:
		if(playerShipsNo == 0) {
			stringArray = new String[1];
			stringArray[0] = "NO SHIPS LEFT!";
		}
		
		//if there is at least one ship:
		int i = 0;
		while(i < playerShipsNo) {
			Ship currentShip = model.getPlayer().getShipList().get(i);
			ShipType shipType = currentShip.getShipType();
			switch(shipType) {
				case PATROL:
					stringArray[i] = "PATROL (" + currentShip.getLength() + ")";
					break;
					
				case SUBMARINE:
					stringArray[i] = "SUBMARINE (" + currentShip.getLength() + ")";
					break;
				
				case DESTROYER:
					stringArray[i] = "DESTROYER (" + currentShip.getLength() + ")";
					break;
				
				case BATTLESHIP:
					stringArray[i] = "BATTLESHIP (" + currentShip.getLength() + ")";
					break;
				
				case CARRIER:
					stringArray[i] = "CARRIER  (" + currentShip.getLength() + ")";
					break;
				
				default:
					System.err.println("ERROR@SetShipsPanel::getPlayersShipsStrings");
					break;
			}
			++i;
		}
		return stringArray;
	}

	public String getTitle() {
		return TITLE;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public JComboBox<String> getChooseShip() {
		return chooseShip;
	}
	
	public JComboBox<String> getChooseDirection() {
		return chooseDirection;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(model.getState() == BattleshipState.SETSHIPS) {
			if(!here) {
				//set the two panels with their content
				setAllComponents();
				this.setVisible(true);
				here = true;
			}
			else
				updateAllComponents();
		}
		else {
			this.setVisible(false);
			//removeAllComponents();
			this.removeAll();
			here = false;
		}
		
		//real update
		updateAllComponents();
	}
}
