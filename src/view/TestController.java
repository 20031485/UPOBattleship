package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;

import model.BattleshipModel;

	public class TestController implements ActionListener{
			public BattleshipModel model;
			
			
			public TestController(BattleshipModel model) {
				this.model = model;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				String command = source.getText();
				
				switch(command) {
					case "SAVE":
						System.out.println("SAVE");
						model.print();
						model.saveGame();
						break;
					case "LOAD":
						System.out.println("LOAD");
						BattleshipModel newModel = null;
						try {
							newModel = BattleshipModel.loadGame();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						if(newModel != null)
							model = newModel;
						model.print();
						break;
				}
			}
	}