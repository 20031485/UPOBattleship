package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;


public class BattleshipNewGameFrame extends JFrame implements ActionListener, WindowListener{
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final String TITLE = "New Game";
	//constructor
	public BattleshipNewGameFrame() {
		//settings
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(this);
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		
		//label per dire che stiamo facendo
		JLabel newGameLabel = new JLabel("CHOOSE SETTINGS");
		newGameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//bottoni per confermare e per settare il timer
		JButton confirmButton = new JButton("CONFIRM");
		JCheckBox timedCheckBox = new JCheckBox("TIMED");
		
		//radio buttons per scegliere la modalità di gioco
		JLabel gameModeLabel = new JLabel("Select game mode:");
		JRadioButton p1vsp2Button = new JRadioButton("P1vsP2");
		p1vsp2Button.addActionListener(this);
		JRadioButton p1vsCPUButton = new JRadioButton("P1vsCPU");
		p1vsCPUButton.addActionListener(this);
		JPanel gameModePanel = new JPanel();
		gameModePanel.setLayout(new BorderLayout());
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		
		//group of radio buttons for size choice
		JLabel gridSizeLabel = new JLabel("Select grid size:");
		JRadioButton sizeSButton = new JRadioButton("5x5");
		sizeSButton.addActionListener(this);
		JRadioButton sizeMButton = new JRadioButton("10x10");
		sizeMButton.addActionListener(this);
		JRadioButton sizeLButton = new JRadioButton("15x15");
		sizeLButton.addActionListener(this);
		JRadioButton sizeXLButton = new JRadioButton("20x20");
		sizeXLButton.addActionListener(this);
		
		//JPanel for grouping size radio buttons
		JPanel radioButtonSizePanel = new JPanel();
		radioButtonSizePanel.setLayout(new FlowLayout());
		
		//TODO: solo uno selezionato alla volta
		
		radioButtonSizePanel.add(gridSizeLabel);
		radioButtonSizePanel.add(sizeSButton);
		radioButtonSizePanel.add(sizeMButton);
		radioButtonSizePanel.add(sizeLButton);
		radioButtonSizePanel.add(sizeXLButton);
		
		//panel for all radio buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		//buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.add(gameModePanel);
		buttonPanel.add(radioButtonSizePanel);
		buttonPanel.add(timedCheckBox);
		
		
		//add everything to frame
		add(newGameLabel, BorderLayout.BEFORE_FIRST_LINE);
		add(buttonPanel, BorderLayout.CENTER);
		add(confirmButton, BorderLayout.AFTER_LAST_LINE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		BattleshipNewGameFrame gui = new BattleshipNewGameFrame();
		gui.setVisible(true);
	}
}
