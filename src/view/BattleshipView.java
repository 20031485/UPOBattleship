package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

import controller.BattleshipController;
import model.BattleshipModel;

public class BattleshipView extends JFrame implements PropertyChangeListener{
	//attributes
	private static final String TITLE = "UPOBattleship";
	private BattleshipModel model;
	private BattleshipController controller;
	private JLayeredPane layers;
	
	//all panels to be shown
	private StartLoadPanel startLoadPanel;
	private NewGamePanel newGamePanel;
	private SetShipsPanel setShipsPanel;
	private BattlePanel battlePanel;
	private JMenuBar mainframe;
	
	
	
	
	
	
	//constructor
	public BattleshipView(BattleshipModel model) {
		this.model = model;
		this.model.addPropertyChangeListener(this);
		this.controller = new BattleshipController(model);
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new WindowDestructor());
		setLayout(new BorderLayout());
		
		startLoadPanel = new StartLoadPanel(model/*, controller.getStartLoadController()*/);
		add(startLoadPanel);
		startLoadPanel.setVisible(true);
		
		newGamePanel = new NewGamePanel(model/*, controller.getNewGameController()*/);
		add(newGamePanel);
		newGamePanel.setVisible(false);
		
		setShipsPanel = new SetShipsPanel(model);
		add(setShipsPanel);
		setShipsPanel.setVisible(false);
		
		battlePanel = new BattlePanel(model);
		add(battlePanel);
		battlePanel.setVisible(false);
		
		setSize(startLoadPanel.getWidth(), startLoadPanel.getHeight());
		setTitle("WELCOME: "+startLoadPanel.getTitle());
		setVisible(true);
	}
	
	//methods
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			switch(this.model.getState()) {
				case WELCOME:
					setTitle("WELCOME: " + startLoadPanel.getTitle());
					setSize(startLoadPanel.getWidth(), startLoadPanel.getHeight());
					//this.pack();
					break;
				
				case BATTLE:
					setTitle(battlePanel.getTitle());
					setSize(battlePanel.getWidth(), battlePanel.getHeight());
					//this.pack();
					break;
			
				case NEWGAME:
					setTitle(newGamePanel.getTitle());
					setSize(newGamePanel.getWidth(), newGamePanel.getHeight());
					//newGamePanel = new NewGamePanel(model/*, controller.getNewGameController()*/);
					//add(newGamePanel);
					//this.pack();
					break;
				
				case SETSHIPS:
					setTitle(setShipsPanel.getTitle());
					//setShipsPanel = new SetShipsPanel(model/*, controller.getSetShipsController()*/);
					//add(setShipsPanel);
					setSize(setShipsPanel.getWidth(), setShipsPanel.getHeight());
					//setShipsPanel.setVisible(true);
					//this.pack();//activated when created!
					break;
					
				default:
					break;
			}
		}
	}
	
	
	
	
	

/*	
	public JMenuBar createMenuBar() {
        //Costruzione della menuBar
        JMenuBar menuBar = new JMenuBar();
            JMenu menuFile = new JMenu("Setup");
                JMenuItem apri = new JMenuItem("SalvaP");
                JMenuItem esci = new JMenuItem("Esci");
                JMenu radioButtonMenu = new JMenu("Timer");
                    JRadioButtonMenuItem buttonBlue = new JRadioButtonMenuItem("Si");
                    JRadioButtonMenuItem buttonRed = new JRadioButtonMenuItem("No");
                    ButtonGroup group = new ButtonGroup();
                    group.add(buttonBlue);
                    group.add(buttonRed);
                radioButtonMenu.add(buttonBlue);
                radioButtonMenu.add(buttonRed);
            menuFile.add(apri);
            menuFile.add(radioButtonMenu);//popup pull-right
            menuFile.addSeparator();
            menuFile.add(esci);
            JMenu menuHelp = new JMenu("Aiuto");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        esci.addActionListener(new ExitActionListener());
        //Listeners
      return menuBar;
      
      //  menuBar.setVisible(true);
    }
	
	  private class ExitActionListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    }
	*/
}
