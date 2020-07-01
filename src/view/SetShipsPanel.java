package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import utils.BattleshipState;

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
	private JPanel NaveBoard;
	private JButton[][] buttonGrid;
	
	private static String[] navi = { "PORTAEREI", "CORAZZATE", "SOTTOMARINO",
			"CACCIATORPERDINIERE", "INCROCIATORE" };
	
	/**
	 * Array di stringhe per le direzioni
	 */
	private static String[] direzione = { "Verticale", "Horizzontale" };
	
	/**
	 * JComboBox utilizzata per contenere la selezione di navi per la frame di input.
	 */
	private JComboBox SceltaNave;
	

	/**
	 * Combo Box used to hold the selection of directions for the input boards.
	 */
	private JComboBox SceltaDirezione;
	
	/**
	 * bottone che l'utente clicca dopo aver posizionato tutte le sue nave . quando il giocatore clicca
	 * resta i attesa e aspetta che il giocatore 2 posiziona le sue navi.
	 */
	private JButton play;
	
	private JButton clean;
	

	//constructor
	public SetShipsPanel(BattleshipModel model  , SetShipsController controller) {
		this.model = model;
		this.controller =controller;
		this.model.addPropertyChangeListener(this);
		NaveBoard = new JPanel();
		frame1 = new JPanel();
		mainframe = new JMenuBar();
        this.setNaveInputFrame1();
		frame1 = this.getNaveInputFrame1();
		this.setPosizionaNaveGrigliaInput();
		NaveBoard = this.getPosizionaNaveGrigliaInput();
	//	this.createMenuBar();
	//	mainframe = this.getCreateMenuBar();
		add(frame1, BorderLayout.NORTH);
		add(NaveBoard, BorderLayout.SOUTH);
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
		JTextField textoIndicatico = new JTextField();
		textoIndicatico.setText("Benvenuto");
		textoIndicatico.setEditable(false);
		input.add(textoIndicatico);
		
		SceltaNave = new JComboBox(navi);
		SceltaNave.setSelectedIndex(0);  // Per impostare la nave zero come nave di default visibile dal giocatore sul combox box
		
		

		TitledBorder titolo;
		titolo = BorderFactory.createTitledBorder("Navi");
		//titolo.setTitleJustification(TitledBorder.CENTER);
		SceltaNave.setBorder(titolo);
		input.add(SceltaNave);

		
		SceltaDirezione = new JComboBox(direzione);
		SceltaDirezione.setSelectedIndex(0);
		
		titolo = BorderFactory.createTitledBorder("Direzione");
		SceltaDirezione.setBorder(titolo);
		input.add(SceltaDirezione);
		
		play = new JButton("play");
		play.setEnabled(true);
		input.add(play);
		
	    clean = new JButton("Cancella");
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
	 
	 NaveBoard.setLayout(new GridLayout(COLUMNS.length, ROWS.length));
		grid = new JButton[COLUMNS.length][ROWS.length];
		for (int y = 0; y < COLUMNS.length; y++) {

			for (int x = 0; x < ROWS.length; x++) {

				if (x != 0 && y != 0) {
					grid[x][y] = new JButton();
					NaveBoard.add(grid[x][y]);
					//grid[x][y].addActionListener(new PlacementButton());
				}
				if (x == 0) {
					if (y != 0) {
						JTextField t = new JTextField(ROWS[y]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						NaveBoard.add(t);
					} else {
						JTextField t = new JTextField();
						t.setEditable(false);
						NaveBoard.add(t);
					}
				} else if (y == 0) {
					JTextField t = new JTextField(COLUMNS[x]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					NaveBoard.add(t);
				}
			}
		}
		// shipBoard.setSize(250, 250);
		NaveBoard.setVisible(true);
	
	}
	
	
	
	
	
	
	
 
 public final JPanel getPosizionaNaveGrigliaInput()
{
	return NaveBoard;
}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.SETSHIPS) {
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
	
	
	
	public static void main(String[] args) {
		BattleshipModel m = new BattleshipModel();
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
