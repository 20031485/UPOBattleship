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
import javax.swing.border.TitledBorder;

import controller.SetShipsController;
import model.BattleshipModel;
import model.Player;
import model.Ship;
import model.Computer;
import utils.BattleshipState;
import utils.ComputerType;
import utils.ShipType;

public class SetShipsPanel extends JPanel implements Observer, PropertyChangeListener{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private static final String TITLE = "SET YOUR SHIPS!";
	private static final String[] COLUMNS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] ROWS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	private SetShipsController controller;
	private JPanel frame1;
	private JMenuBar mainframe;
	private JMenuItem pvp;
	private JButton[][] grid;
	private JPanel gridPanel;
	private JPanel shipBoard;
	private JButton[][] buttonGrid;
	
	private static String[] navi = { "PORTAEREI", "CORAZZATE", "SOTTOMARINO",
			"CACCIATORPERDINIERE", "INCROCIATORE" };
	
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
	private JButton randomSetShips;
	

	//constructor
	public SetShipsPanel(BattleshipModel model  , SetShipsController controller) {
		this.model = model;
		this.controller =controller;
		this.model.addPropertyChangeListener(this);
		
		shipBoard = new JPanel();
		frame1 = new JPanel();
		mainframe = new JMenuBar();
        this.setNaveInputFrame1();
		frame1 = this.getNaveInputFrame1();
		this.setPosizionaNaveGrigliaInput();
		shipBoard = this.getPosizionaNaveGrigliaInput();
	//	this.createMenuBar();
	//	mainframe = this.getCreateMenuBar();
		add(frame1, BorderLayout.NORTH);
		add(shipBoard, BorderLayout.SOUTH);
	//	add(mainframe, BorderLayout.WEST);
		this.setMainFrame();
		System.out.println("ciao sono il modello di setship");
		
	 //	gridTable.setBackground(Color.GREEN);
	
		
	
		
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
	
	
	public final void setNaveInputFrame1() 
	{
		frame1.setLayout(new GridLayout());
		
		JPanel input = new JPanel();
		/*JTextField textoIndicatico = new JTextField();
		textoIndicatico.setText("Benvenuto");
		textoIndicatico.setEditable(false);
		input.add(textoIndicatico);
		*/
		//alloco un array di stringhe della dimensione dell'arraylist di navi del giocatore
		String[] playersShips = new String[model.getPlayer().getShipList().size()];
		playersShips = getPlayersShipsStrings(model);
		
		chooseShip = new JComboBox(playersShips);
		chooseShip.setSelectedIndex(0);  // Per impostare la nave zero come nave di default visibile dal giocatore sul combox box
		
		

		TitledBorder titolo;
		titolo = BorderFactory.createTitledBorder("Ship (length)");
		//titolo.setTitleJustification(TitledBorder.CENTER);
		chooseShip.setBorder(titolo);
		input.add(chooseShip);

		
		chooseDirection = new JComboBox(direction);
		chooseDirection.setSelectedIndex(0);
		
		titolo = BorderFactory.createTitledBorder("Direction");
		chooseDirection.setBorder(titolo);
		input.add(chooseDirection);
		
		randomSetShips = new JButton("Random set");
		randomSetShips.setEnabled(true);
		input.add(randomSetShips);
		
		play = new JButton("Play");
		play.setEnabled(true);
		input.add(play);
		
	    clean = new JButton("Clear");
		play.setEnabled(true);
		input.add(clean);

		frame1.add(input);
		frame1.setVisible(true);
		
		
	}
	
	public JPanel getNaveInputFrame1() {
		// TODO Auto-generated method stub
		return frame1;
	}
	
	
	public final void setPosizionaNaveGrigliaInput() 
	{
	 
	 shipBoard.setLayout(new GridLayout(model.getGameSize()+1, model.getGameSize()+1));
		grid = new JButton[COLUMNS.length][ROWS.length];
		for (int y = 0; y < model.getGameSize()+1; y++) {

			for (int x = 0; x < model.getGameSize()+1; x++) {

				if (x != 0 && y != 0) {
					grid[x][y] = new JButton();
					grid[x][y].setPreferredSize(new Dimension(15, 15));
					shipBoard.add(grid[x][y]);
					//grid[x][y].addActionListener(new PlacementButton());
				}
				if (x == 0) {
					if (y != 0) {
						JTextField t = new JTextField(COLUMNS[y]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						shipBoard.add(t);
					} else {
						JTextField t = new JTextField();
						t.setEditable(false);
						shipBoard.add(t);
					}
				} else if (y == 0) {
					JTextField t = new JTextField(ROWS[x]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					shipBoard.add(t);
				}
			}
		}
		shipBoard.setVisible(true);
	
	}
	
	
	
	
	
	
	
 
 public final JPanel getPosizionaNaveGrigliaInput()
{
	return shipBoard;
}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.SETSHIPS) {
				setPosizionaNaveGrigliaInput();
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	
	public final void setMainFrame() {
	
		
		
		
	}
	
	
	public final JMenuBar getCreateMenuBar() {
		return mainframe;
	}

	
	public void createMenuBar() {
        //Costruzione della menuBar
        JMenuBar menuBar = new JMenuBar();
            JMenu menuFile = new JMenu("Setup");
                JMenuItem apri = new JMenuItem("Apri");
                JMenuItem esci = new JMenuItem("Esci");
                JMenu radioButtonMenu = new JMenu("Setta sfondo");
                    JRadioButtonMenuItem buttonBlue = new JRadioButtonMenuItem("Blue");
                    JRadioButtonMenuItem buttonRed = new JRadioButtonMenuItem("Rosso");
                    JRadioButtonMenuItem buttonGreen = new JRadioButtonMenuItem("Verde");
                    ButtonGroup group = new ButtonGroup();
                    group.add(buttonBlue);
                    group.add(buttonGreen);
                    group.add(buttonRed);
                radioButtonMenu.add(buttonBlue);
                radioButtonMenu.add(buttonGreen);
                radioButtonMenu.add(buttonRed);
            menuFile.add(apri);
            menuFile.add(radioButtonMenu);//popup pull-right
            menuFile.addSeparator();
            menuFile.add(esci);
            JMenu menuHelp = new JMenu("Aiuto");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        //Listeners
      
        esci.addActionListener(new ExitActionListener());
        menuBar.setVisible(true);
    }

	
	  private class ExitActionListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
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
	
	
	public static void main(String[] args) {
		BattleshipModel m = new BattleshipModel();
		m.newGame(new Player(10), new Computer(10, ComputerType.STUPID), 10, false);
		SetShipsController controller = new SetShipsController(m);
		SetShipsPanel bsp = new SetShipsPanel(m,  controller);
		JFrame a = new JFrame();
		//frame.setSize(600, 600);
		//frame.setTitle(bsp.getTitle());
	//	frame.setLayout(new FlowLayout());
		a.setContentPane(bsp);
		a.getContentPane();
		a.pack();
		a.setVisible(true);
	}
	
	

}
