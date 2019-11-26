package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class monsterTest {

	@Test
	void testDie() {
		int PV;
		int Gold;
		monster test1 = new monster(0,10,0,6);
		monster test2 = new monster(0,10,6,6);
		monster test3 = new monster(0,20,0,6);
		monster test4 = new monster(0,20,6,0);
		
		PV = 10;
		Gold = 6;
		test1.die();
		if (test1.PV != PV && test1.gold != Gold) {
			fail("pv basic and gold basic");
		}
		PV = 10;
		Gold = 12;
		test2.die();
		if (test2.PV != PV && test2.gold != Gold) {
			fail("pv basic and gold 2x");
		}
		PV = 20;
		Gold = 6;
		test3.die();
		if (test3.PV != PV && test3.gold != Gold) {
			fail("pv 2x and gold basic");
		}
		PV = 10;
		Gold = 6;
		test4.die();
		if (test4.PV != PV && test4.gold != Gold) {
			fail("pv basic and gold non-up");
		}
		
	}

}
