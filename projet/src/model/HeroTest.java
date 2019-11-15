package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeroTest {

	@Test
	void test() {
		
		int expectedGold;
		int expectedPetCount;
		int expectedArtMoney;
		

		Hero testPet1 = new Hero(50,0);
		Hero testPet2 = new Hero(100,0);
		Hero testPet3 = new Hero(200,0);
		
		Hero testArt1 = new Hero(0,5);
		Hero testArt2 = new Hero(0,10);
		Hero testArt3 = new Hero(0,20);
		
		
		expectedGold = 50;
		expectedPetCount = 0;
		testPet1.buyPet();
		if(Hero.gold != expectedGold || Hero.petCount != expectedPetCount) {
			fail("gold not decreased or pet bought");
		}
		
		expectedGold = 0;
		expectedPetCount = 1;
		testPet2.buyPet();
		if(Hero.gold != expectedGold || Hero.petCount != expectedPetCount) {
			fail("gold not decreased or pet not bought");
		}
		
		expectedGold = 100;
		expectedPetCount = 1;
		testPet3.buyPet();
		if(Hero.gold != expectedGold || Hero.petCount != expectedPetCount) {
			fail("gold not correctly decreased or pet not bought");
		}
		
		
		expectedArtMoney = 5;
		testArt1.buyArtefact();
		if(Hero.artefactMoney != expectedArtMoney) {
			fail("Artefact money decreased without reason");
		}
		
		expectedArtMoney = 0;
		testArt2.buyArtefact();
		if(Hero.artefactMoney != expectedArtMoney) {
			fail("Artefact money decreased without reason");
		}
		
		
		expectedArtMoney = 10;
		testArt1.buyArtefact();
		if(Hero.artefactMoney != expectedArtMoney) {
			fail("Artefact money decreased without reason");
		}
		
	}

}
