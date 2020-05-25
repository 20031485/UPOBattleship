package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleshipStartLoadWindow extends JFrame implements ActionListener{
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final String TITLE = "UPOBattleship by Lorenzo Rossi";
	private boolean oldGameExists = false; //TODO implement "checkForSavedFile()" in Game
	//constructors
	BattleshipStartLoadWindow(){
		//settings
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new BattleshipWindowDestructor());
		setLayout(new BorderLayout());
		
		//JLabel
		JLabel label = new JLabel("Welcome to UPOBattleship!");
		add(label, BorderLayout.CENTER);
		
		//JPanel for JButtons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton newGameButton = new JButton("New game");
		newGameButton.addActionListener(this);
		buttonPanel.add(newGameButton);
		
		//add "loadGameButton" only if an old game file exists
		if(oldGameExists) {
			//TODO implement "checkForSavedFile()" in Game
			JButton loadGameButton = new JButton("Load game");
			loadGameButton.addActionListener(this);
			buttonPanel.add(loadGameButton);
		}
		//add panel to frame
		add(buttonPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "New game":
				System.out.println("new game");
				break;
			case "Load game":
					System.out.println("loading saved file");
				break;
			default:
				System.out.println("BattleshipStartLoadWindow error!");
		}
	}
	
	//main
	public static void main(String[] args) {
		BattleshipStartLoadWindow gui = new BattleshipStartLoadWindow();
		gui.setVisible(true);
	}
}
