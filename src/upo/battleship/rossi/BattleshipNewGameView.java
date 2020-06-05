package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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


public class BattleshipNewGameView extends JPanel implements Observer, WindowListener{
	
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 250;
	private static final String TITLE = "New Game Settings";
	
	//controller
	private BattleshipController battleshipController;
	
	//JPanels
	private JPanel gameModePanel;
	private JPanel radioButtonSizePanel;
	private JPanel buttonPanel;
	private JPanel confirmBackButtonPanel;
	protected JPanel difficultyPanel;
	private JPanel chooseGameModePanel;
	
	//JLabels
	private JLabel gameModeLabel;
	private JLabel gridSizeLabel;
	private JLabel difficultyLabel;
	
	//JButtons
	protected JButton confirmButton;
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
	public BattleshipNewGameView(BattleshipModel battleshipModel) {
		this.battleshipController = new BattleshipController(battleshipModel, this);
		//settings
		setSize(WIDTH, HEIGHT);
		//setTitle(TITLE);
		//setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//addWindowListener(new BattleshipWindowDestructor());
		//addWindowListener(this);
		setLayout(new BorderLayout());
		//setLocationRelativeTo(null);
		
		//tutte le label
		gameModeLabel = new JLabel("GAME MODE");
		difficultyLabel = new JLabel("DIFFICULTY");
		gridSizeLabel = new JLabel("GRID SIZE:");
		
		//tutti i bottoni
		confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(battleshipController);
		
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
			
		confirmBackButtonPanel = new JPanel();
		confirmBackButtonPanel.setLayout(new FlowLayout());
		
		radioButtonSizePanel = new JPanel();
		radioButtonSizePanel.setLayout(new FlowLayout());
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		chooseGameModePanel = new JPanel();
		chooseGameModePanel.setLayout(new BorderLayout());
	
		//add everything to frame
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		//gameModePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		difficultyPanel.add(difficultyLabel, BorderLayout.NORTH);
		difficultyPanel.add(easyModeButton, BorderLayout.CENTER);
		difficultyPanel.add(hardModeButton, BorderLayout.SOUTH);
		
		chooseGameModePanel.add(gameModePanel, BorderLayout.WEST);
		chooseGameModePanel.add(difficultyPanel, BorderLayout.EAST);
		//chooseGameModePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		radioButtonSizePanel.add(gridSizeLabel);
		radioButtonSizePanel.add(sizeSButton);
		radioButtonSizePanel.add(sizeMButton);
		radioButtonSizePanel.add(sizeLButton);
		radioButtonSizePanel.add(sizeXLButton);
		
		buttonPanel.add(chooseGameModePanel);
		buttonPanel.add(radioButtonSizePanel);
		buttonPanel.add(timedCheckBox);
		
		confirmBackButtonPanel.add(confirmButton);
		confirmBackButtonPanel.add(backButton);
		
		add(buttonPanel, BorderLayout.CENTER);
		add(confirmBackButtonPanel, BorderLayout.AFTER_LAST_LINE);
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
}
