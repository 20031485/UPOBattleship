package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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


public class BattleshipNewGamePanel extends JPanel implements PropertyChangeListener{
	
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 250;
	private static final String TITLE = "NEW GAME SETTINGS";

	//model
	private BattleshipModel model;
	
	//own controller
	private BattleshipNewGameController newGameController;
		
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
	protected JCheckBox timedCheckBox;
	
	//ButtonGroups
	private ButtonGroup radioButtonModeGroup;
	private ButtonGroup radioButtonSizeGroup;
	private ButtonGroup difficultyButtonGroup;
	
	//constructor
	public BattleshipNewGamePanel(BattleshipModel model, BattleshipNewGameController controller) {
		this.model = model;
		this.newGameController = new BattleshipNewGameController(model, this);
		this.model.addPropertyChangeListener(this);
		
		//settings
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		
		//all labels
		gameModeLabel = new JLabel("GAME MODE");
		difficultyLabel = new JLabel("DIFFICULTY");
		gridSizeLabel = new JLabel("GRID SIZE:");
		
		//all buttons
		confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(newGameController);
		
		backButton = new JButton ("BACK");
		backButton.addActionListener(newGameController);
		
		p1vsp2Button = new JRadioButton("P1vsP2");
		p1vsp2Button.addActionListener(newGameController);
		
		p1vsCPUButton = new JRadioButton("P1vsCPU");
		p1vsCPUButton.setSelected(true);
		p1vsCPUButton.addActionListener(newGameController);
		
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
		
		//all buttonGroups		
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
		
		//all panels
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
	
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		
		difficultyPanel.add(difficultyLabel, BorderLayout.NORTH);
		difficultyPanel.add(easyModeButton, BorderLayout.CENTER);
		difficultyPanel.add(hardModeButton, BorderLayout.SOUTH);
		
		chooseGameModePanel.add(gameModePanel, BorderLayout.WEST);
		chooseGameModePanel.add(difficultyPanel, BorderLayout.EAST);
				
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

		//NewGamePanel
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(confirmBackButtonPanel, BorderLayout.AFTER_LAST_LINE);
	}
	
	//methods
	public static String getTitle() {
		return TITLE;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.NEWGAME) {
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
		
	}
}
