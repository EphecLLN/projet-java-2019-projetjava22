package model;

import java.util.Observable;

public class Pets extends Observable {
	int petDamages = 1;
	int petDmgIncrease = 1;
	int petNumber = 0;
	int petCostUpgrade = 150;
	double petBuyIncrease = 0.1;
	int petUpgradeIncrease = 100;
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
	 */
	
	public void upgradePet(game game,Pets myPets) {
		if(game.getGold() >= myPets.petCostUpgrade) {
			myPets.petDamages =myPets.petDamages + myPets.petDmgIncrease;
			game.setGold(game.getGold() - myPets.petCostUpgrade);
			myPets.setPetCostUpgrade(getPetCostUpgrade() + myPets.petUpgradeIncrease);
			myPets.setPetUpgradeIncrease(getPetUpgradeIncrease()*2);
		}
		else {
			System.out.println("Vous n'avez pas assez de gold pour améliorer.");
		}
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
	
	public void buyPet(game game) {
		if(game.getGold() >= petCostBuy) {
			game.setGold( game.getGold() - petCostBuy);
			game.myPets.petNumber++;
			petCostBuy += petCostBuy * petBuyIncrease;
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
	
	public void setPetCostUpgrade(int valeur) {
		this.petCostUpgrade = valeur;
	}
	
	public int getPetUpgradeIncrease() {
		return petUpgradeIncrease;
	}
	public void setPetUpgradeIncrease(int prix) {
		this.petUpgradeIncrease = prix;
	}
	
	public double getPetBuyIncrease() {
		return petBuyIncrease;
	}
	
	public int getPetCostBuy() {
		return petCostBuy;
	}
	
	public void setAddPetNumber(int addPetNumber) {
		this.petNumber += addPetNumber;
		setChanged();
        notifyObservers();
	}
	
	public void setPetBuyIncrease(int petincrease) {
		this.petBuyIncrease = petincrease;
		setChanged();
        notifyObservers();
	}
	
	public static void main(String[] args) {
		
	}
	
}
