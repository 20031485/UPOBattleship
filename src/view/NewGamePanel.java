package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import controller.NewGameController;
import model.BattleshipModel;
import utils.BattleshipState;


public class NewGamePanel extends JPanel implements PropertyChangeListener{
	private static final long serialVersionUID = 1L;
	
	//attributes
	private static final int WIDTH = 1000;//400
	private static final int HEIGHT = 500;//225
	private static final String TITLE = "NEW GAME SETTINGS";

	//model
	private BattleshipModel model;
	
	//own controller
	private NewGameController newGameController;
		
	//JPanels
	private JPanel gameModePanel;
	private JPanel radioButtonSizePanel;
	private JPanel buttonPanel;
	private JPanel confirmBackButtonPanel;
	public JPanel difficultyPanel;
	public JPanel timedPanel;
	private JPanel chooseGameModePanel;
	
	//JLabels
	private JLabel gameModeLabel;
	private JLabel gridSizeLabel;
	private JLabel difficultyLabel;
	
	//JButtons
	protected JButton confirmButton;
	protected JButton backButton;
	
	//JRadioButtons
	public JRadioButton p1vsp2Button;
	public JRadioButton p1vsCPUButton;
	public JRadioButton easyModeButton;
	public JRadioButton hardModeButton;
	public JRadioButton sizeSButton;
	public JRadioButton sizeMButton;
	public JRadioButton sizeLButton;
	public JRadioButton sizeXLButton;
	public JRadioButton timed5minsButton;
	public JRadioButton timed10minsButton;
	public JRadioButton timed15minsButton;
	
	//JCheckboxes
	public JCheckBox timedCheckBox;
	
	//ButtonGroups
	private ButtonGroup radioButtonModeGroup;
	private ButtonGroup radioButtonSizeGroup;
	private ButtonGroup difficultyButtonGroup;
	private ButtonGroup timedButtonGroup;
	
	//constructor
	public NewGamePanel(BattleshipModel model/*, NewGameController controller*/) {
		this.model = model;
		//instance its own controller
		this.newGameController = new NewGameController(model, this);
		this.model.addPropertyChangeListener(this);
		System.out.println("NewGamePanel: " + model.toString());
		
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
		
		p1vsp2Button = new JRadioButton("P1vsP2 (unavailable)");
		p1vsp2Button.addActionListener(newGameController);
		
		p1vsCPUButton = new JRadioButton("P1vsCPU");
		p1vsCPUButton.setSelected(true);
		p1vsCPUButton.addActionListener(newGameController);
		
		easyModeButton = new JRadioButton("easy cheesy");
		easyModeButton.setSelected(true);
		
		hardModeButton = new JRadioButton("hard as hell");
		
		sizeMButton = new JRadioButton("10x10");
		sizeMButton.setSelected(true);
		sizeLButton = new JRadioButton("15x15");
		sizeXLButton = new JRadioButton("20x20");
		
		timedCheckBox = new JCheckBox("Timed");
		timedCheckBox.setSelected(false);
		
		timed5minsButton = new JRadioButton("5mins");
		timed5minsButton.setSelected(true);
		timed10minsButton = new JRadioButton("10mins");
		timed15minsButton = new JRadioButton("15mins");
		
		
		
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
		
		timedButtonGroup = new ButtonGroup();
		timedButtonGroup.add(timed5minsButton);
		timedButtonGroup.add(timed10minsButton);
		timedButtonGroup.add(timed15minsButton);
		
		
		//all panels
		difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BorderLayout());
		
		gameModePanel = new JPanel();
		gameModePanel.setLayout(new BorderLayout());
		gameModePanel.setBackground(Color.BLUE);
			
		confirmBackButtonPanel = new JPanel();
		confirmBackButtonPanel.setLayout(new FlowLayout());
		confirmBackButtonPanel.setBackground(Color.GREEN);
		
		radioButtonSizePanel = new JPanel();
		radioButtonSizePanel.setLayout(new FlowLayout());
		radioButtonSizePanel.setBackground(Color.YELLOW);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		chooseGameModePanel = new JPanel();
		chooseGameModePanel.setLayout(new BorderLayout());
		
		timedPanel = new JPanel();
		timedPanel.setLayout(new BorderLayout());
		timedPanel.setBackground(Color.BLUE);
	
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		
		difficultyPanel.add(difficultyLabel, BorderLayout.NORTH);
		difficultyPanel.add(easyModeButton, BorderLayout.CENTER);
		difficultyPanel.add(hardModeButton, BorderLayout.SOUTH);
		
		chooseGameModePanel.add(gameModePanel, BorderLayout.WEST);
		chooseGameModePanel.add(difficultyPanel, BorderLayout.EAST);
				
		radioButtonSizePanel.add(gridSizeLabel);
		radioButtonSizePanel.add(sizeMButton);
		radioButtonSizePanel.add(sizeLButton);
		radioButtonSizePanel.add(sizeXLButton);
		
		buttonPanel.add(chooseGameModePanel);
		buttonPanel.add(radioButtonSizePanel);
		
		timedPanel.add(timedCheckBox, BorderLayout.NORTH);
		timedPanel.add(timed5minsButton, BorderLayout.CENTER);
		timedPanel.add(timed10minsButton, BorderLayout.CENTER);
		timedPanel.add(timed15minsButton, BorderLayout.CENTER);
		timedPanel.setBackground(Color.BLACK);
		
		//buttonPanel.add(timedCheckBox);
		buttonPanel.add(timedPanel);
		buttonPanel.add(confirmButton);
		buttonPanel.add(backButton);
		//buttonPanel.setBackground(Color.BLUE);
		
		//confirmBackButtonPanel.add(confirmButton);
		//confirmBackButtonPanel.add(backButton);

		//confirmBackButtonPanel.setBackground(Color.RED);
		//NewGamePanel
		this.add(buttonPanel, BorderLayout.CENTER);
		//this.add(confirmBackButtonPanel, BorderLayout.PAGE_END);
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
