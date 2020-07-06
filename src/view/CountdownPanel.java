package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.CountdownController;
import model.BattleshipModel;

//import guitimer.Countdown;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownPanel extends JPanel {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	//public long countdown;
	private long time;
	private long currentTime;
	private final JLabel timerLabel;
	private static final TitledBorder timerTitle = BorderFactory.createTitledBorder("TIMER");
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm : ss");
	private BattleshipModel model;
	
	private CountdownController controller;
	public CountdownPanel(long mins, BattleshipModel model){
	    time = mins *60000;
	    currentTime = time;
	    this.model = model;
	    	    
	    timerLabel = new JLabel(dateFormat.format(new Date(time)),JLabel.CENTER);
	    timerLabel.setFont(new Font("Monospace", Font.PLAIN, 30));
	   	timerLabel.setBorder(timerTitle);
	    controller = new CountdownController(this, model);
	    setTimer(controller);  
	    add(timerLabel);

	  }
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public void setTimer(ActionListener actionListener) {
		this.timer = new Timer(1000, actionListener);
	}
	
	public void timerStart() {
		this.timer.start();
	}
	
	public void timerStop() {
		this.timer.stop();
	}
	
	public boolean isTimeOver() {
		return (currentTime == 0);
	}
	
	public JLabel getTimerLabel() {
		return this.timerLabel;
	}
	
	public SimpleDateFormat getDateFormat() {
		return this.dateFormat;
	}
	/*
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BattleshipModel model = new BattleshipModel();
		CountdownPanel cp = new CountdownPanel(1, model);
		cp.setVisible(true);
		f.add(cp);
		f.pack();
		f.setVisible(true);
		cp.timerStart();
	}*/
}
