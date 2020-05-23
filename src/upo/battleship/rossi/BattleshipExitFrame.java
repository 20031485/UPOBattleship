package upo.battleship.rossi;

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

public class BattleshipExitFrame extends JFrame implements ActionListener, WindowListener{
	//fields
	private static final int width = 200;
	private static final int height = 100;
	
	//constructor
	public BattleshipExitFrame() {
		setSize(width, height);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Are you sure?");
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
			System.out.println("ERROR CLOSING WINDOW");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("BattleshipExitFrame opened!");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("BattleshipExitFrame closing!");
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("BattleshipExitFrame closed!");
		this.dispose();
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("BattleshipExitFrame iconified!");
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("BattleshipExitFrame deiconified!");
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("BattleshipExitFrame activated!");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("BattleshipExitFrame deactivated!");
		
	}
	
	//main
	public static void main(String[] args) {
		BattleshipExitFrame gui = new BattleshipExitFrame();
		gui.setVisible(true);
	}
}
