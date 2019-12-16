package Vue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Hero;
import model.Monster;

class GUITest {

	@Test
	void testScreenMonster() {
		fail("Not yet implemented");
	}

	Monster testM = new Monster();
	GUI intTest = new GUI(null, null);
	Hero testH = new Hero();

	@Test
	void testAjouterClasses() {
		testM.setWaveNumber(2);
		intTest.ajouterClasses(intTest, testM, testH);
		if (intTest.archerChoice.isEnabled() != true && intTest.mageChoice.isEnabled() != true && intTest.berserkChoice.isEnabled() != true) {
			fail("classes");
		}
	}

}
