package controller;
import view.CountdownPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import model.BattleshipModel;
import view.CountdownPanel;

public class CountdownController implements ActionListener{
	private CountdownPanel panel;
	private BattleshipModel model;
	public CountdownController(CountdownPanel panel, BattleshipModel model) {
		this.panel = panel;
		this.model = model;
	}
	
	@Override
    public void actionPerformed(ActionEvent ae){
		//if the countdown is not over
		if(!panel.isTimeOver()) {
			//decrease the current time
			panel.setCurrentTime(panel.getCurrentTime() - 1000);
			//display the new current time on the timerLabel
			panel.getTimerLabel().setText(panel.getDateFormat().format(new Date(panel.getCurrentTime())));
		}
		else {
			panel.setBackground(Color.RED);
			model.getPlayer().setTimedOut(true);
		}
	}
}
