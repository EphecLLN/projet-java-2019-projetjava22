package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetsTest {

	@Test
	void test() {
		int petDamage, petNumber, petIncrease, gold, petCostUpgrade, resultatAttendu, resultatObtenu, petCostBuy;
		Pets pets = new Pets();
		game test = new game();
		
		//Test de la fonction upgradePet
		
		gold = 30;
		petCostUpgrade = 30;	
		petDamage = 0;
		petIncrease = 0;
			resultatAttendu = 0;
			resultatObtenu = pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D0 I0");
				}

		gold = 30;
		petCostUpgrade = 30;				
		petDamage = -1;
		petIncrease = -1;
			resultatAttendu = -2;
			resultatObtenu = pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D-1 I-1");
				}
				
		gold = 30;
		petCostUpgrade = 30;	
		petDamage = 1;
		petIncrease = 1;
			resultatAttendu = 2;
			resultatObtenu = pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade D1 I1");
				}

		gold = 20;
		petCostUpgrade = 30;	
		petDamage = 5;
		petIncrease = 5;
			resultatAttendu = 5;
			resultatObtenu = pets.upgradePet(petDamage, petIncrease, gold, petCostUpgrade);	
				if(resultatAttendu != resultatObtenu) {
					fail("upgrade gold < petCostUpgrade");
				}

		//Test de la méthode attackPet		
				
		petDamage = 1;
		petNumber = 1;
			resultatAttendu = 1;
			resultatObtenu = pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D1 N1");
				}
					
		petDamage = 0;
		petNumber = 0;
			resultatAttendu = 0;
			resultatObtenu = pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D0 N0");
				}
		
		petDamage = -1;
		petNumber = -1;
			resultatAttendu = 1;
			resultatObtenu = pets.attackPet(petDamage, petNumber);
				if(resultatAttendu != resultatObtenu) {
					fail("attack D-1 N-1");
				}
				
		//test de buyPets()
				
		
		gold = 100 ;
		petCostBuy = 100;
		petNumber = 0;
		pets.buyPet(test, gold, petCostBuy, petNumber);
		if (pets.getPetNumber() != 1) {
			fail("1 achat ");
		}
		
		pets = new Pets();
		gold = 300 ;
		petCostBuy = 100;
		petNumber = 0;
		pets.buyPet(test, gold, petCostBuy, petNumber);
		pets.buyPet(test, gold, petCostBuy, petNumber);
		if (pets.getPetNumber() != 1) {
			fail("2 achat " + gold + petNumber);
		}
		
		pets = new Pets();
		gold = 0 ;
		petCostBuy = 100;
		petNumber = 0;
		pets.buyPet(test, gold, petCostBuy, petNumber);
		if (pets.getPetNumber() != 0) {
			fail("pas d'argent ");
		}
		pets = new Pets();
		gold = 0 ;
		petCostBuy = 100;
		petNumber = 1;
		pets.buyPet(test, gold, petCostBuy, petNumber);
		if (pets.getPetNumber() != 1) {
			fail("pas d'argent mais déja 1 ");
		}
	}

}
