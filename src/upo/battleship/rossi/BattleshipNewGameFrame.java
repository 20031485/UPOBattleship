package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
	private static final int HEIGHT = 250;
	private static final String TITLE = "New Game";
	
	//JPanels
	private JPanel gameModePanel;
	private JPanel radioButtonSizePanel;
	private JPanel buttonPanel;
	private JPanel confResBackButtonPanel;
	
	//JLabels
	private JLabel newGameLabel;
	private JLabel gameModeLabel;
	private JLabel gridSizeLabel;
	
	//JButtons
	private JButton confirmButton;
	private JButton resetButton;
	private JButton backButton;
	
	//JRadioButtons
	private JRadioButton p1vsp2Button;
	private JRadioButton p1vsCPUButton;
	private JRadioButton sizeSButton;
	private JRadioButton sizeMButton;
	private JRadioButton sizeLButton;
	private JRadioButton sizeXLButton;
	
	//JCheckboxes
	JCheckBox timedCheckBox;
	
	//ButtonGroups
	ButtonGroup radioButtonModeGroup;
	ButtonGroup radioButtonSizeGroup;
	
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
		newGameLabel = new JLabel("NEW GAME SETTINGS");
		newGameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//bottoni per confermare e per settare il timer
		confirmButton = new JButton("CONFIRM");
		confirmButton.addActionListener(this);
		resetButton = new JButton("RESET");
		resetButton.addActionListener(this);
		backButton = new JButton ("BACK");
		backButton.addActionListener(this);
		timedCheckBox = new JCheckBox("Timed");
		
		//radio buttons per scegliere la modalità di gioco
		gameModeLabel = new JLabel("Select game mode:");
		radioButtonModeGroup = new ButtonGroup();
		p1vsp2Button = new JRadioButton("P1vsP2");
		p1vsp2Button.addActionListener(this);
		p1vsCPUButton = new JRadioButton("P1vsCPU");
		p1vsCPUButton.setSelected(true);
		p1vsCPUButton.addActionListener(this);
		radioButtonModeGroup.add(p1vsp2Button);
		radioButtonModeGroup.add(p1vsCPUButton);
		
		//add buttons to panel
		gameModePanel = new JPanel();
		gameModePanel.setLayout(new BorderLayout());
		gameModePanel.add(gameModeLabel, BorderLayout.NORTH);
		gameModePanel.add(p1vsp2Button, BorderLayout.CENTER);
		gameModePanel.add(p1vsCPUButton, BorderLayout.SOUTH);
		
		//group of radio buttons for size choice
		gridSizeLabel = new JLabel("Select grid size:");
		sizeSButton = new JRadioButton("5x5");
		sizeSButton.addActionListener(this);
		sizeMButton = new JRadioButton("10x10");
		sizeMButton.addActionListener(this);
		sizeMButton.setSelected(true);
		sizeLButton = new JRadioButton("15x15");
		sizeLButton.addActionListener(this);
		sizeXLButton = new JRadioButton("20x20");
		sizeXLButton.addActionListener(this);
		
		//add radio buttons to group
		radioButtonSizeGroup = new ButtonGroup();
		radioButtonSizeGroup.add(sizeSButton);
		radioButtonSizeGroup.add(sizeMButton);
		radioButtonSizeGroup.add(sizeLButton);
		radioButtonSizeGroup.add(sizeXLButton);
		
		//add actionListener to buttonGroup
		
		
		//JPanel for grouping size radio buttons
		radioButtonSizePanel = new JPanel();
		radioButtonSizePanel.setLayout(new FlowLayout());
		radioButtonSizePanel.add(gridSizeLabel);
		radioButtonSizePanel.add(sizeSButton);
		radioButtonSizePanel.add(sizeMButton);
		radioButtonSizePanel.add(sizeLButton);
		radioButtonSizePanel.add(sizeXLButton);
		
		//panel for all radio buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(gameModePanel);
		buttonPanel.add(radioButtonSizePanel);
		buttonPanel.add(timedCheckBox);
		
		//panel for confirm, reset and back buttons
		confResBackButtonPanel = new JPanel();
		confResBackButtonPanel.setLayout(new FlowLayout());
		confResBackButtonPanel.add(confirmButton);
		confResBackButtonPanel.add(resetButton);
		confResBackButtonPanel.add(backButton);
		
		//add everything to frame
		add(newGameLabel, BorderLayout.BEFORE_FIRST_LINE);
		add(buttonPanel, BorderLayout.CENTER);
		add(confResBackButtonPanel, BorderLayout.AFTER_LAST_LINE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch(actionCommand) {
		case "CONFIRM":
			System.out.println("confirm");
			//TODO: get selections and launch Game
			if(p1vsp2Button.isSelected())
				System.out.println("\tP1vsP2");
			if(p1vsCPUButton.isSelected())
				System.out.println("\tP1vsCPU");
			if(timedCheckBox.isSelected())
				System.out.println("\tTimed");
			break;
		case "RESET":
			System.out.println("reset");
			//TODO reset all selections
			break;
		case "BACK":
			System.out.println("back");
			//TODO re-launch startLoadGameFrame
			break;
		default:
			System.out.println("error");
			//TODO throw exception?
			break;
		}
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
