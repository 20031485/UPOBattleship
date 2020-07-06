package upo.battleship.rossi;

import controller.BattleshipController;
import model.BattleshipModel;
import view.BattleshipView;

public class BattleshipMVC {
	public static void main(String[] args) {
		BattleshipModel model = new BattleshipModel();
		System.out.println(model.toString());
		BattleshipView view = new BattleshipView(model);
		BattleshipController controller = new BattleshipController(model);
	}
}
