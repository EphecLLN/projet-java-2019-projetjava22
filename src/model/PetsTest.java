package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetsTest {

	@Test
	void test() {
		int petDamage, petNumber, petIncrease, gold, petCostUpgrade, resultatAttendu, resultatObtenu;
		Pets Pets = new Pets();
		//Test de la fonction upgradePet
		
		gold = 30;
		petCostUpgrade = 30;	
		petDamage = 0;
		petIncrease = 0;
			resultatAttendu = 0;
			resultatObtenu = Pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D0 I0");
				}

		gold = 30;
		petCostUpgrade = 30;				
		petDamage = -1;
		petIncrease = -1;
			resultatAttendu = -2;
			resultatObtenu = Pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D-1 I-1");
				}
				
		gold = 30;
		petCostUpgrade = 30;	
		petDamage = 1;
		petIncrease = 1;
			resultatAttendu = 2;
			resultatObtenu = Pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D1 I1");
				}

		gold = 20;
		petCostUpgrade = 30;	
		petDamage = 5;
		petIncrease = 5;
			resultatAttendu = 5;
			resultatObtenu = Pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade gold < petCostUpgrade");
				}

		//Test de la méthode attackPet		
				
		petDamage = 1;
		petNumber = 1;
			resultatAttendu = 1;
			resultatObtenu = Pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D1 N1");
				}
					
		petDamage = 0;
		petNumber = 0;
			resultatAttendu = 0;
			resultatObtenu = Pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D0 N0");
				}
		
		petDamage = -1;
		petNumber = -1;
			resultatAttendu = 1;
			resultatObtenu = Pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D-1 N-1");
				}
	}

}
