package upo.battleship.rossi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class BattleshipModelTest {

	@Test
	void test() {
		Player p1 = new Player("P1");
		Player p2 = new Player("P2");
		BattleshipModel bm = new BattleshipModel(p1, p2, 15, false);
		assert(bm != null);
		p1.setScore(25);
		p2.setScore(43);
		bm.saveGame();
		assert(BattleshipModel.savedGameExists());
		BattleshipModel bm2 = null;
		try {
			bm2 = BattleshipModel.loadGame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(bm.toString());
		System.out.println(bm2.toString());
		assert(bm.equals(bm2));//returns false but it should return true!
	}

}
