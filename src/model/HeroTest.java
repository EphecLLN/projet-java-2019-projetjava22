
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeroTest {

	@Test
	void test() {
		game test = new game();
		Artefact artf = new Artefact();
		Hero heroTest = new Hero();
		int nbrArtfMoney ;
		
		nbrArtfMoney = 10;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (artf.getCurrentArtefacts().contains("doubleDMG") ||artf.getCurrentArtefacts().contains("every10Hit") ||artf.getCurrentArtefacts().contains("-1Boss") ||artf.getCurrentArtefacts().contains("+5DMG") ||artf.getCurrentArtefacts().contains("doublePet") && nbrArtfMoney != 0) {
			fail("1 achat");
		}
		artf = new Artefact();
		nbrArtfMoney = 30;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (artf.getCurrentArtefacts().contains("doubleDMG") ||artf.getCurrentArtefacts().contains("every10Hit") ||artf.getCurrentArtefacts().contains("-1Boss") ||artf.getCurrentArtefacts().contains("+5DMG") ||artf.getCurrentArtefacts().contains("doublePet") && nbrArtfMoney != 0) {
			fail("2 achat");
		}
		artf = new Artefact();
		nbrArtfMoney = 70;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (artf.getCurrentArtefacts().contains("doubleDMG") ||artf.getCurrentArtefacts().contains("every10Hit") ||artf.getCurrentArtefacts().contains("-1Boss") ||artf.getCurrentArtefacts().contains("+5DMG") ||artf.getCurrentArtefacts().contains("doublePet") && nbrArtfMoney != 0) {
			fail("3 achat");
		}
		artf = new Artefact();
		nbrArtfMoney = 150;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (artf.getCurrentArtefacts().contains("doubleDMG") ||artf.getCurrentArtefacts().contains("every10Hit") ||artf.getCurrentArtefacts().contains("-1Boss") ||artf.getCurrentArtefacts().contains("+5DMG") ||artf.getCurrentArtefacts().contains("doublePet") && nbrArtfMoney != 0) {
			fail("4 achat");
		}
		artf = new Artefact();
		nbrArtfMoney = 310;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (artf.getCurrentArtefacts().contains("doubleDMG") ||artf.getCurrentArtefacts().contains("every10Hit") ||artf.getCurrentArtefacts().contains("-1Boss") ||artf.getCurrentArtefacts().contains("+5DMG") ||artf.getCurrentArtefacts().contains("doublePet") && nbrArtfMoney != 0) {
			fail("5 achat");
		}
		nbrArtfMoney = 5;
		heroTest.buyArtefact(artf, test, nbrArtfMoney);
		if (nbrArtfMoney != 5) {
			fail("pas d'argent");
		}
	}

}
