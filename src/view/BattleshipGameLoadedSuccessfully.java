package view;

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


//NOT ESSENTIAL


public class BattleshipGameLoadedSuccessfully extends JFrame implements ActionListener{
	//fields
	private static final int width = 200;
	private static final int height = 100;
	
	//constructor
	public BattleshipGameLoadedSuccessfully() {
		setSize(width, height);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("Game loaded successfully!");
		add(label, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(this);
		buttonPanel.add(okButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//catches the command
		String command = e.getActionCommand();
		//interprets the command
		if(command.equals("OK")) {
			//terminates program
			this.dispose();
			//TODO launches loaded game
		}
		else {
			//hopefully this will never be displayed
			System.out.println("ERROR CLOSING WINDOW");
		}
	}
	
	public static void main(String[] args) {
		BattleshipGameLoadedSuccessfully gui = new BattleshipGameLoadedSuccessfully();
		gui.setVisible(true);
	}

}
