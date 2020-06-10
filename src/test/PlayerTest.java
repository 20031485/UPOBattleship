package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.BattleshipPlayer;

class PlayerTest {

	@Test
	void test() {
		BattleshipPlayer battleshipPlayer = new BattleshipPlayer("Gianni");
		
		assert(battleshipPlayer != null);
		
		battleshipPlayer.setScore(345);
		
		assert(battleshipPlayer.getName().equals("Gianni"));
		assert(battleshipPlayer.getScore() == 345);
		
		battleshipPlayer.resetGrids(10);
		
		assert(battleshipPlayer.isDefeated() == true);
		
		battleshipPlayer.setShip();
		
		assert(battleshipPlayer.isDefeated() != true);
		
		BattleshipPlayer player2 = new BattleshipPlayer();
		player2.setName("Gianni");
		player2.setScore(345);
		
		assertEquals(battleshipPlayer, player2);
		
		BattleshipPlayer player3 = new BattleshipPlayer();
		
		assertNotEquals(battleshipPlayer, player3);
	}
}
