package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.BattleshipModel;
import model.Computer;
import model.Player;
import utils.ComputerType;

class BattleshipModelTest {

	@Test
	void test() {
		int gameSize = 10;
		Player p1 = new Player(gameSize);
		Computer p2 = new Computer(gameSize, ComputerType.STUPID);
		p1.randomSetShips();
		p2.randomSetShips();
		
		BattleshipModel bm = new BattleshipModel(p1, p2, gameSize, false, 0);
		assert(bm != null);
		
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
		System.out.println(bm.toString());
		assertEquals(bm, bm2);
		
		//newGame test
		BattleshipModel bm3 = new BattleshipModel();
		bm3.newGame(p1, p2, gameSize, false, 0);
		assertEquals(bm, bm3);
		System.out.println("\n\nbm:\n"+bm.toString());
		System.out.println("\n\nbm2:\n"+bm2.toString());
		System.out.println("\n\nbm3:\n"+bm3.toString());
	}

}
