package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonsterTest {
	game test = new game();
	@Test
	void testDie() {
		Monster tester = new Monster();
		int testPV;
		int testNB;
		int testBNB;
		
		testPV = 0;
		testNB = 1;
		testBNB = 10;
		tester.die(tester, test, testPV, testNB, testBNB);
		if (tester.getPV() != 10 ) {
			fail("test normal");
		}
		tester = new Monster();
		testPV = 0;
		testNB = 9;
		testBNB = 10;
		tester.die(tester, test, testPV, testNB, testBNB);
		if ( tester.getPV() != 30) {
			fail("boss");
		}
		tester = new Monster();
		testPV = 0;
		testNB = 10;
		testBNB = 10;
		tester.die(tester, test, testPV, testNB, testBNB);
		if ( tester.getPV() != 20 && tester.getWaveNumber() != 2 && tester.getNumber() !=1 && tester.getGoldIncrease()!= 12) {
			fail("après boss");
		}
		tester = new Monster();
		testPV = 10;
		testNB = 1;
		testBNB = 10;
		tester.die(tester, test, testPV, testNB, testBNB);
		if ( tester.getPV() != 10) {
			fail("meurt pas mdr");
		}
	}

	@Test
	void testRandomMonster() {
		Monster tester = new Monster();
		
		tester.randomMonster(tester);
		if (tester.getAttribute() != "aqua" && tester.getAttribute()!="pyro" && tester.getAttribute()!="tera"){
			fail("distribution d'attribut");
		}
	}

}
