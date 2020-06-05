package upo.battleship.rossi;

public class BattleshipMain {

	public static void main(String[] args) {
		BattleshipModel battleshipModel = new BattleshipModel();
		BattleshipNewGameView gui = new BattleshipNewGameView(battleshipModel);
	}

}
