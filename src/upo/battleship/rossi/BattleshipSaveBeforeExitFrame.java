package upo.battleship.rossi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattleshipSaveBeforeExitFrame extends JFrame implements ActionListener{
	//fields
	private static final int width = 500;
	private static final int height = 100;
	
	//constructor
	public BattleshipSaveBeforeExitFrame() {
		setSize(width, height);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("Save before exit?");
		add(label, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton yesButton = new JButton("Save and exit");
		yesButton.addActionListener(this);
		buttonPanel.add(yesButton);
		
		JButton noButton = new JButton("Exit without saving");
		noButton.addActionListener(this);
		buttonPanel.add(noButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//catches the command
		String command = e.getActionCommand();
		//interprets the command
		if(command.equals("Save and exit")) {
			//terminates program
			System.out.println("save and exit button");
			System.exit(0);
		}
		else if(command.equals("Exit without saving")) {
			//exits Battleship
			System.out.println("exit without saving");
			System.exit(0);
		}
		else {
			//this will never be displayed - HOPEFULLY
			System.out.println("ERROR CLOSING WINDOW");
		}
	}
	
	public static void main(String[] args) {
		BattleshipSaveBeforeExitFrame gui = new BattleshipSaveBeforeExitFrame();
		gui.setVisible(true);
	}
}
