package upo.battleship.rossi;

public class BattleshipMain {

	public static void main(String[] args) {
		BattleshipModel battleshipModel = new BattleshipModel();
		BattleshipNewGameFrame gui = new BattleshipNewGameFrame(battleshipModel);
	}

}
