package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleshipStartLoadView extends JFrame implements Observer, ActionListener{
	//attributes
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	private static final String TITLE = "UPOBattleship by Lorenzo Rossi";
	
	//constructors
	BattleshipStartLoadView(){
		//settings
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new BattleshipWindowDestructor());
		setLayout(new BorderLayout());
		
		//JLabel
		JLabel label = new JLabel("Welcome to UPOBattleship!");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);
		
		//JPanel for JButtons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton newGameButton = new JButton("New game");
		newGameButton.addActionListener(this);
		buttonPanel.add(newGameButton);
		
		//add "loadGameButton" only if an old game file exists
		if(BattleshipModel.savedGameExists()) {
			JButton loadGameButton = new JButton("Load game");
			loadGameButton.addActionListener(this);
			buttonPanel.add(loadGameButton);
		}
		//add panel to frame
		add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "New game":
				System.out.println("new game");
				//launch BattleshipNewGameView
				break;
			case "Load game":
				System.out.println("loading saved file");
				//launch saved game	
				break;
			default:
				//hopefully never displayed LOL
				System.out.println("BattleshipStartLoadWindow error!");
		}
	}
	
	//main
	public static void main(String[] args) {
		BattleshipStartLoadView gui = new BattleshipStartLoadView();
		gui.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
