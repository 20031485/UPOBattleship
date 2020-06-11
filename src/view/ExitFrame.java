package view;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class ExitFrame extends JFrame implements ActionListener{
	//fields
	private static final int width = 200;
	private static final int height = 100;
	
	//constructor
	public ExitFrame() {
		setSize(width, height);
		setLocationRelativeTo(null);
		//getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel("Are you sure?");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton yesButton = new JButton("YES");
		yesButton.addActionListener(this);
		buttonPanel.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.addActionListener(this);
		buttonPanel.add(noButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//catches the command
		String command = e.getActionCommand();
		//interprets the command
		if(command.equals("YES")) {
			//terminates program
			System.exit(0);
		}
		else if(command.equals("NO")) {
			//closes just the current exit window
			dispose();
		}
		else {
			//this will never be displayed
			System.out.println("ERROR CLOSING WINDOW");
		}
	}
	
	//main
	public static void main(String[] args) {
		ExitFrame gui = new ExitFrame();
		gui.setVisible(true);
	}
}
