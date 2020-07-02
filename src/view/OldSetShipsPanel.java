package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import controller.SetShipsController;
import model.BattleshipModel;
import model.Player;
import model.Ship;
import model.Computer;
import utils.BattleshipState;
import utils.ComputerType;
import utils.ShipType;

public class OldSetShipsPanel extends JPanel implements Observer, PropertyChangeListener{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private static final String TITLE = "SET YOUR SHIPS!";
	private static final String[] COLUMNS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] ROWS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	private static final int WIDTH = 1700;//700
	private static final int HEIGHT = 1350;//350
	private SetShipsController setShipsController;
	private JPanel dropDownAndButtonsPanel;
	private JMenuBar mainframe;
	private JPanel shipBoard;
	private JButton[][] buttonGrid;
	
	/**
	 * Array di stringhe per le direzioni
	 */
	private static String[] direction = { "VERTICAL", "HORIZONTAL" };
	
	/**
	 * JComboBox utilizzata per contenere la selezione di navi per la frame di input.
	 */
	private JComboBox chooseShip;
	
	/**
	 * Combo Box used to hold the selection of directions for the input boards.
	 */
	private JComboBox chooseDirection;
	
	/**
	 * bottone che l'utente clicca dopo aver posizionato tutte le sue nave . quando il giocatore clicca
	 * resta i attesa e aspetta che il giocatore 2 posiziona le sue navi.
	 */
	private JButton play;
	private JButton clean;
	//private JButton randomSetOne;
	private JButton randomSetAll;
	

	/**
	 * Constructor for the Class {@code SetShipsPanel}. It creates a JPanel to
	 * be shown in the main game frame
	 * @param model A {@code BattleshipModel} object
	 */
	public SetShipsPanel(BattleshipModel model/*, SetShipsController controller*/) {
		this.model = model;
		this.setShipsController = new SetShipsController(model, this);
		System.out.println("SetShipsPanel: " + model.toString());
		
		//listen to property changes
		this.model.addPropertyChangeListener(this);
		
		//observe model and player
		this.model.addObserver(this);
		this.model.getPlayer().addObserver(this);
		
		//shipBoard = new JPanel();
		//dropDownAndButtonsPanel = new JPanel();
		
		//creates the comboBox + buttons panel
        setDropDownAndButtonsPanel();
		//dropDownAndButtonsPanel = this.getDropDownAndButtonsPanel();
		
		//creates the buttonGrid and shipBoard (containing buttonGrid)
		createButtonGrid();
		
		//add(dropDownAndButtonsPanel, BorderLayout.NORTH);
		//add(shipBoard, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates and adds a panel with two dropdown menus and some buttons to the main panel
	 */
	public void setDropDownAndButtonsPanel() {
		dropDownAndButtonsPanel = new JPanel();
		dropDownAndButtonsPanel.setLayout(new BorderLayout());
		
		JPanel input = new JPanel();
		
		//alloco un array di stringhe della dimensione dell'arraylist di navi del giocatore
		String[] playersShips;// = new String[model.getPlayer().getShipList().size()];
		playersShips = getPlayersShipsStrings(model);
		
		chooseShip = new JComboBox(playersShips);
		chooseShip.setSelectedIndex(0);  // Per impostare la nave zero come nave di default visibile dal giocatore sul combox box
		
		TitledBorder titolo;
		titolo = BorderFactory.createTitledBorder("Ship (length)");
		chooseShip.setBorder(titolo);
		input.add(chooseShip);

		chooseDirection = new JComboBox(direction);
		chooseDirection.setSelectedIndex(0);
		
		titolo = BorderFactory.createTitledBorder("Direction");
		chooseDirection.setBorder(titolo);
		input.add(chooseDirection);
		
		randomSetAll = new JButton("RANDOM");
		randomSetAll.setEnabled(true);
		randomSetAll.addActionListener(setShipsController);
		input.add(randomSetAll);
		
	    clean = new JButton("CLEAR");
		clean.setEnabled(true);
		clean.addActionListener(setShipsController);
		input.add(clean);

		play = new JButton("PLAY");
		play.setEnabled(false);
		play.addActionListener(setShipsController);
		input.add(play);
		
		dropDownAndButtonsPanel.add(input);
		dropDownAndButtonsPanel.setVisible(true);
		
		add(dropDownAndButtonsPanel, BorderLayout.NORTH);
	}
	
	/**
	 * 
	 */
	public void updateDropDownAndButtonsPanel() {
		dropDownAndButtonsPanel = new JPanel();
		dropDownAndButtonsPanel.setLayout(new BorderLayout());
		
		JPanel input = new JPanel();
		
		//alloco un array di stringhe della dimensione dell'arraylist di navi del giocatore
		String[] playersShips;// = new String[model.getPlayer().getShipList().size()];
		playersShips = getPlayersShipsStrings(model);
		
		chooseShip = new JComboBox(playersShips);
		chooseDirection.setSelectedIndex(0);
		
		//if there are no more ships to be set
		if(model.getPlayer().getShipList().size() == 0) {
			play.setEnabled(true);
			play.addActionListener(setShipsController);
		}
	}
	
	public JPanel getDropDownAndButtonsPanel() {
		// TODO Auto-generated method stub
		return dropDownAndButtonsPanel;
	}
	
	/**
	 * Creates and adds a buttonGrid as big as the game size to the main panel
	 */
	public void createButtonGrid(){
		int gameSize = model.getGameSize();
		int dim = 20;
		boolean[][] playersShipsGrid  = new boolean[gameSize][gameSize];
		playersShipsGrid = model.getPlayer().getShipsGrid();
		this.shipBoard = new JPanel();
		this.shipBoard.setLayout(new GridLayout(gameSize + 1, gameSize + 1));
		this.buttonGrid = new JButton[gameSize+1][gameSize+1];//[COLUMNS.length][ROWS.length];
		
		for (int y = 0; y < gameSize + 1; y++) {
			for (int x = 0; x < gameSize +1; x++) {
				
				if (x != 0 && y != 0) {
					if(playersShipsGrid[x-1][y-1]) {//if true, there is no ship underneath
						buttonGrid[x][y] = new JButton();
						buttonGrid[x][y].setPreferredSize(new Dimension(dim, dim));
						shipBoard.add(buttonGrid[x][y]);
						buttonGrid[x][y].addActionListener(setShipsController);
						System.out.println("x:"+ x +", y:"+y);
					}
					else {
						buttonGrid[x][y] = new JButton();
						buttonGrid[x][y].setPreferredSize(new Dimension(dim, dim));
						buttonGrid[x][y].setBackground(Color.BLACK);
						buttonGrid[x][y].setOpaque(true);
						shipBoard.add(buttonGrid[x][y]);
					}
				}
				else if (x == 0) {
					if (y != 0) {
						JTextField t = new JTextField(COLUMNS[y]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						t.setPreferredSize(new Dimension(dim, dim));
						shipBoard.add(t);
					} 
					else {
						JTextField t = new JTextField();
						t.setEditable(false);
						t.setPreferredSize(new Dimension(dim, dim));
						shipBoard.add(t);
					}
				} 

				else if (y == 0) {
					JTextField t = new JTextField(ROWS[x]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					shipBoard.add(t);
				}
			}
		}
		add(shipBoard, BorderLayout.SOUTH);
		shipBoard.setVisible(true);
	}
	
	public void updateButtonGrid() {
		int gameSize = model.getGameSize();
		boolean[][] playersShipsGrid  = new boolean[gameSize][gameSize];
		playersShipsGrid = model.getPlayer().getShipsGrid();
		//this.shipBoard = new JPanel();
		//this.shipBoard.setLayout(new GridLayout(gameSize + 1, gameSize + 1));
		//this.buttonGrid = new JButton[gameSize][gameSize];//[COLUMNS.length][ROWS.length];
		
		for (int y = 0; y < gameSize + 1; y++) {
			for (int x = 0; x < gameSize +1; x++) {
				if (x != 0 && y != 0) {
					if(!playersShipsGrid[x-1][y-1]) {
						buttonGrid[x][y].setBackground(Color.BLACK);
						buttonGrid[x][y].setEnabled(false);
						buttonGrid[x][y].setOpaque(true);
					}
				}
			}
		}
		//setBackground(Color.BLUE);
		shipBoard.setVisible(true);
	}
	
	public JButton[][] getButtonGrid() {
		return buttonGrid;
	}
	
	public JButton getButtonFromButtonGrid(int i, int j) {
		return buttonGrid[i][j];
	}
	
	//methods
	public String getTitle() {
		return TITLE;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//this.remove(shipBoard);
		//setDropDownAndButtonsPanel();
		updateDropDownAndButtonsPanel();
		//createButtonGrid();
		updateButtonGrid();
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.SETSHIPS) {
				//createButtonGrid();
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
	}
	
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
				case CACCIATORPEDINIERE:
					stringArray[i] = "CACCIATORPEDINIERE (" + currentShip.getLength() + ")";
					break;
					
				case SOTTOMARINO:
					stringArray[i] = "SOTTOMARINO (" + currentShip.getLength() + ")";
					break;
				
				case INCROCIATORE:
					stringArray[i] = "INCROCIATORE (" + currentShip.getLength() + ")";
					break;
				
				case CORAZZATE:
					stringArray[i] = "CORAZZATE (" + currentShip.getLength() + ")";
					break;
				
				case PORTAEREI:
					stringArray[i] = "PORTAEREI  (" + currentShip.getLength() + ")";
					break;
				
				default:
					System.err.println("ERROR@SetShipsPanel::getPlayersShipsStrings");
					break;
			}
			++i;
		}
		return stringArray;
	}
}
