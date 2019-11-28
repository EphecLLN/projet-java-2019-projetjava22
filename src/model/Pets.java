package model;

public class Pets {
	int petDamages = 1;
	int petDmgIncrease = 1;
	static int petNumber = 0;
	int petCostUpgrade = 150;
	static int petBuyIncrease = 1;
	int petsAttackSpeed = 2000;
	int petCostBuy = 100;
	
	/**
	 * Cette méthode sert à améliorer les dégâts des pets
	 * @param petDamage
	 * 		dégâts initiaux(avant l'amélioration) des pets (valeur positive)
	 * @param petIncrease
	 * 		valeur d'amélioration des dégâts
	 * @param gold (valeur strictement positive)
	 * 		solde obtenu par le héros, il faut obligatoirement être supérieur ou égal à l'amélioration
	 * 		pour pouvoir effectuer celle-ci (valeur positive)
	 * @param costUpgrade
	 * 		coût de l'amélioration, il faut avoir un montant supérieur ou égal de golds pour pouvoir
	 * 		effectuer l'amélioration (valeur positive)
	 * @return petDamage
	 * 		retourne les dégâts des pets après avoir été améliorés	
	 */
	
	static int upgradePet(int petDamage, int petIncrease, int gold, int petCostUpgrade) {
		if(gold >= petCostUpgrade) {
			petDamage = petDamage + petIncrease;
			gold -= petCostUpgrade;
		}
		else {
			System.out.println("Vous n'avez pas assez de gold pour améliorer.");
		}
		return petDamage;
	}
	
	/**
	 * Cette méthode sert à calculer les dégâts totaux des pets
	 * @param petDamage
	 * 		dégâts initiaux(avant l'amélioration) des pets
	 * @param petNumber
	 * 		nombre de pets possédés par le héros
	 * @return damages
	 * 		retourne les dégâts totaux des pets	
	 */
	
	int attackPet(int petDamage, int petNumber) {
		int damages = petDamage * petNumber;
		return damages;
	}
	/**
	 * Cette méthode sert à acheter un nouveau pet
	 */
	
	public void buyPet() {
		game myGame = new game();
		if(myGame.gold >= petCostBuy) {
			myGame.gold -= petCostBuy;
			myGame.myPets.petNumber += myGame.myPets.petBuyIncrease;
			petCostBuy += petCostBuy * 10/100;
			System.out.println("Vous avez desormais " + myGame.myPets.petNumber + " familiers.");
			System.out.println("Vous avez desormais " + myGame.myPets.petBuyIncrease + " petBuyIncrease.");
		}
		else {
			System.out.println("Vous n'avez pas assez de gold pour améliorer.");
		}
	}
	
	public int getPetDamage() {
		return petDamages;
	}
	
	public int getPetDmgIncrease() {
		return petDmgIncrease;
	}
	
	public int getPetNumber() {
		return petNumber;
	}
	
	public int getPetCostUpgrade() {
		return petCostUpgrade;
	}
	
	public int getPetBuyIncrease() {
		return petBuyIncrease;
	}
	
	public int getPetsAttackSpeed() {
		return petsAttackSpeed;
	}
	
	public int getPetCostBuy() {
		return petCostBuy;
	}
	
	public void setAddPetNumber(int addPetNumber) {
		this.petNumber += addPetNumber;
	}
	
	public void setPetBuyIncrease(int petincrease) {
		this.petBuyIncrease = petincrease;
	}
	
	public static void main(String[] args) {
		
	}
	
}
