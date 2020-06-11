package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.BattleshipModel;
import utils.BattleshipState;

public class SetShipsPanel extends JPanel implements Observer, PropertyChangeListener{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private static final String TITLE = "SET YOUR SHIPS!";
	private static final String[] COLUMNS = { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
	private static final String[] ROWS = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private JPanel gridPanel;
	private JPanel gridTable;
	private JButton[][] buttonGrid;
	

	//constructor
	public SetShipsPanel(BattleshipModel model/*, BattleshipSetShipsController controller*/) {
		this.model = model;
		//this.setSize(WIDTH, HEIGHT);
		
		//carico in dim il gameSize di model
		int dim = model.getGameSize();
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new BorderLayout());
		
		gridTable = new JPanel();
		gridTable.setBackground(Color.GREEN);
		gridTable.setLayout(new GridLayout(dim+1, dim+1));//deve essere dim+1 per contenere le label di righe e colonne
		
		buttonGrid = new JButton[dim][dim];
		
		for(int i = 0; i < dim + 1; ++i) {
			for(int j = 0; j < dim + 1; ++j) {
				System.out.println(i+" "+j+"\n");
				if(i != 0 && j != 0) {
					buttonGrid[i-1][j-1] = new JButton();
					buttonGrid[i-1][j-1].setSize(50, 50);
					gridTable.add(buttonGrid[i-1][j-1]);
				}
				else if(i == 0 && j != 0) {
					gridTable.add(new JLabel(COLUMNS[j]));
				}
				else if(i != 0 && j == 0) {
					gridTable.add(new JLabel(ROWS[i]));
				}
				else if(i == 0 && j == 0) {
					gridTable.add(new JLabel(" "));
				}
			}
		}
		
		add(gridTable, BorderLayout.CENTER);
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
	
	public static void main(String[] args) {
		BattleshipModel m = new BattleshipModel();
		SetShipsPanel bsp = new SetShipsPanel(m);
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle(bsp.getTitle());
		frame.setLayout(new FlowLayout());
		frame.add(bsp);
		frame.setVisible(true);
	}

}
