package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import com.sun.java.swing.plaf.windows.WindowsBorders;

public class BattleshipNewGameFrame extends JFrame implements ActionListener, WindowListener{
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final String TITLE = "New Game";
	//constructor
	BattleshipNewGameFrame() {
		//settings
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(this);
		setLayout(new BorderLayout());
		
		//label per dire che stiamo facendo
		JLabel newGameLabel = new JLabel("Choose settings:");
		newGameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//bottoni per confermare e per settare il timer
		JButton confirmButton = new JButton("CONFIRM");
		JCheckBox timedCheckBox = new JCheckBox("TIMED");
		
		//group of radio buttons
		//ButtonGroup radioButtonGroup = new ButtonGroup();
		JRadioButton sizeSButton = new JRadioButton("5x5");
		sizeSButton.addActionListener(this);
		JRadioButton sizeMButton = new JRadioButton("10x10");
		sizeMButton.addActionListener(this);
		JRadioButton sizeLButton = new JRadioButton("15x15");
		sizeLButton.addActionListener(this);
		JRadioButton sizeXLButton = new JRadioButton("20x20");
		sizeXLButton.addActionListener(this);
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new FlowLayout());
		radioButtonPanel.add(sizeSButton);
		radioButtonPanel.add(sizeMButton);
		radioButtonPanel.add(sizeLButton);
		radioButtonPanel.add(sizeXLButton);
		
		//panel for all radio buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(radioButtonPanel, BorderLayout.BEFORE_FIRST_LINE);
		buttonPanel.add(timedCheckBox);
		buttonPanel.setLayout(new FlowLayout());
		add(buttonPanel, BorderLayout.CENTER);
		add(timedCheckBox, BorderLayout.SOUTH);
		add(confirmButton, BorderLayout.AFTER_LAST_LINE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[][] args) {
		BattleshipNewGameFrame gui = new BattleshipNewGameFrame();
		gui.setVisible(true);
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
}
