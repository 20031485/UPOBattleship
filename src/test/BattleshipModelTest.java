package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.BattleshipModel;
import model.Player;

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
		
		//savedGameExist test
		assert(BattleshipModel.savedGameExists());
		
		//saveGame & loadGame test
		BattleshipModel bm2 = null;
		try {
			bm2 = BattleshipModel.loadGame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(bm, bm2);
		
		//newGame test
		BattleshipModel bm3 = new BattleshipModel();
		bm3.newGame(p1, p2, 15, false);
		assertEquals(bm, bm3);
	}

}
