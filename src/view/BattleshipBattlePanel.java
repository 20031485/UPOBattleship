package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.BattleshipModel;
import utils.BattleshipState;

public class BattleshipBattlePanel extends JPanel implements Observer, PropertyChangeListener{
	private static final long serialVersionUID = 1L;

	//attributes
	private BattleshipModel model;
	private static final String TITLE = "BATTLE!";

	//constructor
	public BattleshipBattlePanel(BattleshipModel model/*, BattleshipSetShipsController controller*/) {
		this.model = model;
	}
	
	//methods
	public static String getTitle() {
		return TITLE;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("setState")) {
			if(model.getState() == BattleshipState.BATTLE) {
				this.setVisible(true);
			}
			else
				this.setVisible(false);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
