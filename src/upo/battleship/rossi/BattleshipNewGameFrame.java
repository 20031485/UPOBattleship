package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;


public class BattleshipNewGameFrame extends JFrame implements Observer, WindowListener{
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 250;
	private static final String TITLE = "New Game";
	
	private BattleshipModel battleshipModel;
	private BattleshipController battleshipController;
	
	//JPanels
	private JPanel gameModePanel;
	private JPanel radioButtonSizePanel;
	private JPanel buttonPanel;
	private JPanel confirmResetBackButtonPanel;
	protected JPanel difficultyPanel;
	private JPanel chooseGameModePanel;
	
	//JLabels
	private JLabel newGameLabel;
	private JLabel gameModeLabel;
	private JLabel gridSizeLabel;
	private JLabel difficultyLabel;
	
	//JButtons
	protected JButton confirmButton;
	protected JButton resetButton;
	protected JButton backButton;
	
	//JRadioButtons
	protected JRadioButton p1vsp2Button;
	protected JRadioButton p1vsCPUButton;
	protected JRadioButton easyModeButton;
	protected JRadioButton hardModeButton;
	protected JRadioButton sizeSButton;
	protected JRadioButton sizeMButton;
	protected JRadioButton sizeLButton;
	protected JRadioButton sizeXLButton;
	
	//JCheckboxes
	JCheckBox timedCheckBox;
	
	//ButtonGroups
	ButtonGroup radioButtonModeGroup;
	ButtonGroup radioButtonSizeGroup;
	ButtonGroup difficultyButtonGroup;
	
	//constructor
	public BattleshipNewGameFrame(BattleshipModel battleshipModel) {
		this.battleshipModel = battleshipModel;
		this.battleshipController = new BattleshipController(battleshipModel, this);
		//settings
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(this);
		setLayout(new BorderLayout());
		//setBackground(Color.DARK_GRAY);
		
		//tutte le label
		newGameLabel = new JLabel("NEW GAME SETTINGS");
		newGameLabel.setHorizontalAlignment(JLabel.CENTER);
		difficultyLabel = new JLabel("Difficulty: ");
		gameModeLabel = new JLabel("Select game mode:");
		gridSizeLabel = new JLabel("Select grid size:");
		
		//tutti i bottoni
		confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(battleshipController);
		
		resetButton = new JButton("RESET");
		resetButton.addActionListener(battleshipController);
		
		backButton = new JButton ("BACK");
		backButton.addActionListener(battleshipController);
		
		p1vsp2Button = new JRadioButton("P1vsP2");
		p1vsp2Button.addActionListener(battleshipController);
		
		p1vsCPUButton = new JRadioButton("P1vsCPU");
		p1vsCPUButton.setSelected(true);
		p1vsCPUButton.addActionListener(battleshipController);
		
		easyModeButton = new JRadioButton("easy cheesy");
		easyModeButton.setSelected(true);
		
		hardModeButton = new JRadioButton("hard as hell");
		
		sizeSButton = new JRadioButton("5x5");
		
		sizeMButton = new JRadioButton("10x10");
		
		sizeMButton.setSelected(true);
		
		sizeLButton = new JRadioButton("15x15");
		
		sizeXLButton = new JRadioButton("20x20");
		
		timedCheckBox = new JCheckBox("Timed");
		timedCheckBox.setSelected(false);
		
		//tutti i buttonGroup		
		radioButtonModeGroup = new ButtonGroup();
		radioButtonModeGroup.add(p1vsp2Button);
		radioButtonModeGroup.add(p1vsCPUButton);
		
		difficultyButtonGroup = new ButtonGroup();
		difficultyButtonGroup.add(easyModeButton);
		difficultyButtonGroup.add(hardModeButton);
		
		radioButtonSizeGroup = new ButtonGroup();
		radioButtonSizeGroup.add(sizeSButton);
		radioButtonSizeGroup.add(sizeMButton);
		radioButtonSizeGroup.add(sizeLButton);
		radioButtonSizeGroup.add(sizeXLButton);
		
		//tutti i pannelli
		difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BorderLayout());
		
		gameModePanel = new JPanel();
		gameModePanel.setLayout(new BorderLayout());
			
		confirmResetBackButtonPanel = new JPanel();
		confirmResetBackButtonPanel.setLayout(new FlowLayout());
		
		radioButtonSizePanel = new JPanel();
		radioButtonSizePanel.setLayout(new FlowLayout());
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		chooseGameModePanel = new JPanel();
		chooseGameModePanel.setLayout(new FlowLayout());
	
		//add everything to frame
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		
		difficultyPanel.add(difficultyLabel, BorderLayout.NORTH);
		difficultyPanel.add(easyModeButton, BorderLayout.CENTER);
		difficultyPanel.add(hardModeButton, BorderLayout.SOUTH);
		
		chooseGameModePanel.add(gameModePanel);
		chooseGameModePanel.add(difficultyPanel);
		
		radioButtonSizePanel.add(gridSizeLabel);
		radioButtonSizePanel.add(sizeSButton);
		radioButtonSizePanel.add(sizeMButton);
		radioButtonSizePanel.add(sizeLButton);
		radioButtonSizePanel.add(sizeXLButton);
		
		buttonPanel.add(chooseGameModePanel);
		buttonPanel.add(radioButtonSizePanel);
		buttonPanel.add(timedCheckBox);
		
		confirmResetBackButtonPanel.add(confirmButton);
		confirmResetBackButtonPanel.add(resetButton);
		confirmResetBackButtonPanel.add(backButton);
		
		add(newGameLabel, BorderLayout.BEFORE_FIRST_LINE);
		add(buttonPanel, BorderLayout.CENTER);
		add(confirmResetBackButtonPanel, BorderLayout.AFTER_LAST_LINE);
		this.setVisible(true);
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



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	/*public static void main(String[] args) {
		BattleshipNewGameFrame gui = new BattleshipNewGameFrame();
		gui.setVisible(true);
	}*/
}
