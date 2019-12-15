package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class gameTest {
	game tests = new game();
	
	@Test
	void testAttackPets() {
		int nbrP;
		int dmgP;
		
		Monster tester = new Monster();
		nbrP = 1;
		dmgP = 1;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 9) {
			fail("1*1");
		}
		tester = new Monster();
		nbrP = 1;
		dmgP = 2;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 8) {
			fail("1*2");
		}
		tester = new Monster();
		nbrP = 2;
		dmgP = 1;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 8) {
			fail("2*1");
		}
		tester = new Monster();
		nbrP = 2;
		dmgP = 5;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 10) {
			fail("kill");
		}
		tester = new Monster();
		nbrP = 1;
		dmgP = 0;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 10) {
			fail("1*0");
		}
		tester = new Monster();
		nbrP = 0;
		dmgP = 1;
		tests.attackPets(tester, dmgP, nbrP);
		if (tester.getPV() != 10) {
			fail("0*1");
		}
	}

	@Test
	void testAttack() {
		Monster tester = new Monster();
		Artefact Ftest = new Artefact();
		int dmg ;
		String hAttribute ;
		String mAttribute ;
		
		dmg = 1;
		hAttribute = "aqua";
		mAttribute = "aqua";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("equal aqua && 1 dmg");
		}
		tester = new Monster();
		dmg = 1;
		hAttribute = "pyro";
		mAttribute = "pyro";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("equal pyro && 1 dmg");
		}
		tester = new Monster();
		dmg = 1;
		hAttribute = "tera";
		mAttribute = "tera";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("equal tera && 1 dmg");
		}
		tester = new Monster();
		dmg = 1;
		hAttribute = "aqua";
		mAttribute = "pyro";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("advantage aqua && 1 dmg");
		}
		tester = new Monster();
		dmg = 1;
		hAttribute = "pyro";
		mAttribute = "tera";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("advantage pyro && 1 dmg");
		}
		tester = new Monster();
		dmg = 1;
		hAttribute = "tera";
		mAttribute = "aqua";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("advantage tera && 1 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "aqua";
		mAttribute = "aqua";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("equal aqua && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "pyro";
		mAttribute = "pyro";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("equal pyro && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "tera";
		mAttribute = "tera";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 8) {
			fail("equal tera && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "aqua";
		mAttribute = "pyro";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 6) {
			fail("advantage aqua && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "pyro";
		mAttribute = "tera";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 6) {
			fail("advantage pyro &&21 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "tera";
		mAttribute = "aqua";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 6) {
			fail("advantage tera && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "aqua";
		mAttribute = "tera";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("disavantage aqua && 2 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "pyro";
		mAttribute = "aqua";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("disavantage pyro &&21 dmg");
		}
		tester = new Monster();
		dmg = 2;
		hAttribute = "tera";
		mAttribute = "pyro";
		tests.attack(tester, Ftest, dmg, hAttribute, mAttribute);
		if (tester.getPV() != 9) {
			fail("disavantage tera && 2 dmg");
		}
	}

	@Test
	void testUpgrade() {
		Hero testeur = new Hero();
		int upDmg;
		
		upDmg = 1;
		tests.upgrade(testeur, upDmg);
		if (testeur.getDamage() != 2) {
			fail("+1dmg");
		}
		testeur = new Hero();
		upDmg = 2;
		tests.upgrade(testeur, upDmg);
		if (testeur.getDamage() != 3) {
			fail("+1dmg");
		}
		testeur = new Hero();
		upDmg = 0;
		tests.upgrade(testeur, upDmg);
		if (testeur.getDamage() != 1) {
			fail("+1dmg");
		}
	}

	@Test
	void testReborn() {
		Hero testeur = new Hero();
		Pets testP = new Pets();
		Monster testM = new Monster();
		Artefact testA = new Artefact();
		tests.reborn(testM, testeur, testP,testA);
		if (testeur.getDamage() != 1) {
			fail("damage");
		}
		if (testeur.getConstDamage() != 1) {
			fail("constDmg");
		}
		if (tests.getGold() != 0) {
			fail("gold");
		}
		if (tests.getNbrUpgrade() != 0) {
			fail("nbrUP");
		}
		if (testeur.getCheckClass() != 0) {
			fail("hClass");
		}
		if (testM.getPV() != 10) {
			fail("mPV");
		}
		if (testM.getGoldIncrease() != 6) {
			fail("goldAugmentation");
		}
		if (testM.getWaveNumber()!= 1) {
			fail("mPV");
		}
		if (testM.getPvIncrease() != 10) {
			fail("PVAugmentation");
		}
		if (testM.getNumber() != 1) {
			fail("PVAugmentation");
		}
		if (testP.getPetNumber() != 0) {
			fail("nbrPet");
		}
		if (testP.getPetCostBuy() != 100) {
			fail("nbrPet");
		}
	}

	@Test
	void testArcherChoice() {
	Hero testeur = new Hero();
	
	tests.archerChoice(testeur);
	if (testeur.getCheckClass() != 1) {
		fail("archer test");
		}
	}

	@Test
	void testMageChoice() {
		Hero testeur = new Hero();
		
		tests.mageChoice(testeur);
		if (testeur.getCheckClass() != 2) {
			fail("mage test");
			}
		}


	@Test
	void testBerzerkerChoice() {
		Hero testeur = new Hero();
		
		tests.berzerkerChoice(testeur);
		if (testeur.getCheckClass() != 3) {
			fail("berzerker test");
			}
		}


	@Test
	void testApplyArtefacts() {
		Hero testeur= new Hero();
		Artefact ATest = new Artefact();
		Pets PTest = new Pets();
		Monster MTest = new Monster();
		int nbrArtf = 10;

		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		if(ATest.getCurrentArtefacts().contains("doubleDMG") && testeur.getDamage() != 2 ) {
			fail("double Dmg");
		}
		if(ATest.getCurrentArtefacts().contains("+5DMG") && testeur.getDamage() != 6 ) {
			fail("+5");
		}
		if(ATest.getCurrentArtefacts().contains("doublePet") && PTest.getPetNumber() != 0 ) {
			fail("double pet");
		}
		if(ATest.getCurrentArtefacts().contains("-1Boss") && MTest.getbossNumber() != 9 ) {
			fail("double Dmg");
		}
		if(ATest.getCurrentArtefacts().contains("every10Hit") && ATest.isActivate10Hit() != true ) {
			fail("every10Hit");
		}
		tests = new game();
		testeur= new Hero();
		ATest = new Artefact();
		PTest = new Pets();
		MTest = new Monster();
		nbrArtf = 310;
		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		testeur.buyArtefact(ATest, tests, nbrArtf);
		tests.applyArtefacts(ATest, PTest, testeur, MTest);
		if(testeur.getDamage() != 12 && testeur.getDamage() != 7) {
			fail("artefact complet " + testeur.getDamage() + " "+ ATest.getCurrentArtefacts());
		}
	}

}
