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
		//System.out.println(player.toString());
		assert(player.isDefeated() == true);
		player.setShip();
		assert(player.isDefeated() != true);
		//System.out.println(player.toString());
	}
}
