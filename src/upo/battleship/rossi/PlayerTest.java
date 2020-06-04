package upo.battleship.rossi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void test() {
		Player player = new Player("Gianni");
		
		assert(player != null);
		
		player.setScore(345);
		
		assert(player.getName().equals("Gianni"));
		assert(player.getScore() == 345);
		
		player.resetGrids(10);
		
		assert(player.isDefeated() == true);
		
		player.setShip();
		
		assert(player.isDefeated() != true);
		
		Player player2 = new Player();
		player2.setName("Gianni");
		player2.setScore(345);
		
		assertEquals(player, player2);
		
		Player player3 = new Player();
		
		assertNotEquals(player, player3);
	}
}
