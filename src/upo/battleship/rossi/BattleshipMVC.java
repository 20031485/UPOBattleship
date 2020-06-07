package upo.battleship.rossi;

public class BattleshipMVC {
	public static void main(String[] args) {
		BattleshipModel model = new BattleshipModel();
		BattleshipView view = new BattleshipView(model);
		BattleshipController controller = new BattleshipController(model);
	}
}
