package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BattleshipModel;

public class BattleshipGrid extends JFrame{	
	private static final String[] COLUMNS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] ROWS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	
	//dobbiamo mantenere due matrici per ogni griglia:
	/*griglia sinistra:
	 * 	una matrice per memorizzare le mosse del giocatore1
	 * 	una matrice per memorizzare le mosse del giocatore2
	 * 	una matrice per memorizzare le navi del giocatore1
	 * 	una matrice per memorizzare le navi del giocatore2*/
	
	//griglie che verranno aggiornate ad ogni turno
	//la dimensione della griglia è passata dalla classe Game (BattleshipModel)
	private JButton[][] leftButtonGrid;
	private JButton[][] rightButtonGrid;
	
	//dati del giocatore1, passati al costruttore?
	private boolean[][] p1ShipsGrid;
	private boolean[][] p1MovesGrid;
	
	//dati del giocatore2, passati al costruttore?
	private boolean[][] p2ShipsGrid;
	private boolean[][] p2MovesGrid;
	
	//pannello con le due griglie
	private JPanel bothGridsPanel;
	private JPanel leftTable;
	private JPanel rightTable;
	
	//dimensioni della finestra
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	private BattleshipModel battleshipModel;
	
	public BattleshipGrid(BattleshipModel battleshipModel){
		this.battleshipModel = battleshipModel;
		
		setTitle("UPOBattleshipPaLo");
	    setSize(WIDTH, HEIGHT);
	    setLayout(new BorderLayout());
	    //genera la finestra in mezzo allo schermo
	    setLocationRelativeTo(null);
	    
	    //impostazioni bothGridsPanel (pannello in cui mettere le due grid)
	    bothGridsPanel = new JPanel();
	    bothGridsPanel.setLayout(new FlowLayout());
	    bothGridsPanel.setSize(WIDTH, HEIGHT);
	    bothGridsPanel.setBackground(Color.DARK_GRAY);
	    
	    //impostazioni leftTable
	    leftTable = new JPanel();		
		leftTable.setSize(300, 300);
		leftTable.setLayout(new GridLayout(COLUMNS.length, ROWS.length));
		leftButtonGrid = new JButton[COLUMNS.length][ROWS.length];
		
		for (int i = 0; i< COLUMNS.length; i++){
			for (int j= 0; j < ROWS.length; j++){
				if(i != 0 && j != 0){
					leftButtonGrid[i][j] = new JButton();
					leftTable.add(leftButtonGrid[i][j]);
				}
				if (j == 0){
					if (i != 0){
						JTextField t = new JTextField(ROWS[i]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						leftTable.add(t);
					} 
					else{
						JTextField t = new JTextField();
						t.setEditable(false);
						leftTable.add(t);
					}
				}
				else if (i == 0){
					JTextField t = new JTextField(COLUMNS[j]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					leftTable.add(t);
				}
			}
		}
		
		//impostazioni rightTable
	/*	rightTable = new JPanel();
		rightTable.setSize(WIDTH/2, HEIGHT);
		rightTable.setLayout(new GridLayout(COLUMNS.length, ROWS.length));
		rightButtonGrid = new JButton[COLUMNS.length][ROWS.length];
		for(int i = 0; i< COLUMNS.length; i++) {
			for(int j= 0; j < ROWS.length; j++) {
				if(i != 0 && j != 0){
					rightButtonGrid[i][j] = new JButton();
					rightTable.add(rightButtonGrid[i][j]);
				}
				if (j == 0){
					if (i != 0){
						JTextField t = new JTextField(ROWS[i]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						rightTable.add(t);
					} 
					else{
						JTextField t = new JTextField();
						t.setEditable(false);
						rightTable.add(t);
					}
				}
				else if (i == 0){
					JTextField t = new JTextField(COLUMNS[j]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					rightTable.add(t);
				}	
			}
		}*/
		
		//aggiungi le due griglie al pannello
		bothGridsPanel.add(leftTable);
		//bothGridsPanel.add(rightTable);
		
		//posiziona il pannello con le griglie al centro del frame
		add(bothGridsPanel, BorderLayout.CENTER);
	}
			
			
	public static void main(String[] args) {
		BattleshipModel bs = new BattleshipModel();
		BattleshipGrid g = new BattleshipGrid(bs);
		g.setVisible(true);
	}

}
