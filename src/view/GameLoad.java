package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import model.BattleshipModel;



public class GameLoad extends JPanel {

	private JPanel gameBoard;
	private JPanel gameBoard2;
	
	
	
	 private JMenuBar menuBar = new JMenuBar();
	  private JMenu test1 = new JMenu("Fichier");
	  private JMenu test1_2 = new JMenu("Sous ficher");
	  private JMenu test2 = new JMenu("Edition");

	  private JMenuItem item1 = new JMenuItem("Ouvrir");
	  private JMenuItem item2 = new JMenuItem("Fermer");
	  private JMenuItem item3 = new JMenuItem("Lancer");
	  private JMenuItem item4 = new JMenuItem("Arrêter");

	  private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
	  private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");

	  private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
	  private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");
	
	
	/**
	 * Matrice di buttoni usati per fare la griglia del gioco
	 */
	private BattleshipModel model;
	private JPanel pane;
	private JButton[][] grid;
	
	
	private static final String[] COLUMNS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] ROWS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	
	public GameLoad( BattleshipModel model) {
		this.model = model;
		
		//this.controller =controller;
	    //this.model.addPropertyChangeListener(this);
		gameBoard = new JPanel();
		this.setGameBoard();
		gameBoard = this.getGameBoard();
		gameBoard2 = new JPanel();
        
		this.setGameBoard2();
		gameBoard2 = this.getGameBoard2();
	    
		add(gameBoard, BorderLayout.EAST);
		add(gameBoard2, BorderLayout.WEST);
	
		System.out.println("ciao sono il modello di gameLoad");
	}
	
	
	/*
	 * NaveBoard = new JPanel();
		frame1 = new JPanel();
        this.setNaveInputFrame1();
		frame1 = this.getNaveInputFrame1();
		this.setPosizionaNaveGrigliaInput();
		NaveBoard = this.getPosizionaNaveGrigliaInput();
		add(frame1, BorderLayout.NORTH);
		add(frame1, BorderLayout.SOUTH);
	 */
	
	
	
 public final void setGameBoard() {
          
		 
		 gameBoard.setLayout(new GridLayout(COLUMNS.length, ROWS.length));  //model.getgamesize
			grid = new JButton[COLUMNS.length][ROWS.length];
			for (int j = 0; j < COLUMNS.length; j++) {

				for (int i = 0; i < ROWS.length; i++) {

					if (i != 0 && j != 0) {
						grid[i][j] = new JButton();
						gameBoard.add(grid[i][j]);
						//grid[i][j].addActionListener(new BoardButton());
					}
					if (i == 0) {
						if (j != 0) {
							JTextField t = new JTextField(ROWS[j]);
							t.setEditable(false);
							t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
							gameBoard.add(t);
						} else {
							JTextField t = new JTextField();
							t.setEditable(false);
							gameBoard.add(t);
						}
					} else if (j == 0) {
						JTextField t = new JTextField(COLUMNS[i]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard.add(t);
					}
				}
			}
			// gameBoard.setSize(250, 250);
			gameBoard.setVisible(true);
		 
		
	 }
	 
	
	 public final JPanel getGameBoard() {
		 return gameBoard;
	 }
	 
	 
	 
	 
public final void setGameBoard2() {
          
		 
		 gameBoard2.setLayout(new GridLayout(COLUMNS.length, ROWS.length));  //model.getgamesize
			grid = new JButton[COLUMNS.length]
			                   [ROWS.length];
			for (int j = 0; j < COLUMNS.length; j++) {

				for (int i = 0; i < ROWS.length; i++) {

					if (i != 0 && j != 0) {
						grid[i][j] = new JButton();
						gameBoard2.add(grid[i][j]);
						//grid[i][j].addActionListener(new BoardButton());
					}
					if (i == 0) {
						if (j != 0) {
							JTextField t = new JTextField(ROWS[j]);
							t.setEditable(false);
							t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
							gameBoard2.add(t);
						} else {
							JTextField t = new JTextField();
							t.setEditable(false);
							gameBoard2.add(t);
						}
					} else if (j == 0) {
						JTextField t = new JTextField(COLUMNS[i]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						gameBoard2.add(t);
					}
				}
			}
			// gameBoard.setSize(250, 250);
			gameBoard2.setVisible(true);
		 
		
	 }
	 
	
	 public final JPanel getGameBoard2() {
		 return gameBoard2;
	 }
	 
	 
		public static void main(String[] args) {
			BattleshipModel m = new BattleshipModel();
			GameLoad bsp = new GameLoad(m);
			JFrame a = new JFrame();
			a.setContentPane(bsp);
			a.getContentPane();
			a.pack();
			a.setVisible(true);
		}
	
}
