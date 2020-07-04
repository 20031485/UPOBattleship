package view;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class CountdownPanel extends /*JFrame*/JPanel{

	private static final long serialVersionUID = 1L;
	private ActionListener actionListener;
	private final JLabel clock;
	private final long time;
	private final SimpleDateFormat dateFormat; 
	private int currentTime;
	private Timer timer;
	
	public CountdownPanel(long minutes){
		//setLocation(400,300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		time = minutes*60000;
		    
		dateFormat = new SimpleDateFormat("mm : ss");
		    
		clock = new JLabel(dateFormat.format(new Date(time)),JLabel.CENTER);
		    
		currentTime = 0;
		    
		actionListener = new ActionListener(){
			long x = time - 1000;
			public void actionPerformed(ActionEvent ae){
				if(x == 0)
					timer.stop();
				clock.setText(dateFormat.format(new Date(x)));
				x -= 1000;
			}
		};
		    
		timer = new Timer(1000, actionListener);
		    
		JPanel jp = new JPanel();
		
		jp.add(clock);
		
		//getContentPane().add(jp);
		//timer.start();
		//pack();
	}
	  
	public void countdownStart() {
		timer.start();
	}
	
	public void countdownStop() {
		timer.stop();
	}
	  
	public static void main(String args[]){
		CountdownPanel p = new CountdownPanel(1);
		p.setVisible(true);
		p.countdownStart();
	}
}