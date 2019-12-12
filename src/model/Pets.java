package model;

import java.util.Observable;

public class Pets extends Observable {
<<<<<<< HEAD
	int petDamages = 1;
	int petDmgIncrease = 1;
	int petNumber = 0;
	int petCostUpgrade = 150;
	double petBuyIncrease = 0.1;
	int petUpgradeIncrease = 100;
	int petsAttackSpeed = 2000;
	int petCostBuy = 100;
=======
	private int petDamages = 1;
	private int petDmgIncrease = 1;
	private int petNumber = 0;
	private int petNumberUP= 1;
	private int petCostUpgrade = 150;
	private double petBuyIncrease = 0.1;
	private int petsAttackSpeed = 2000;
	private int petCostBuy = 100;
>>>>>>> master
	
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
		if(game.getGold() >= getPetCostBuy()) {
			game.setGold( game.getGold() - getPetCostBuy());
			game.myPets.setPetNumber(game.myPets.getPetNumber() + getPetNumberUP());
			setPetCostBuy(getPetCostBuy() + getPetCostBuy() * petBuyIncrease);
		}
		else {
			System.out.println("Vous n'avez pas assez de gold pour améliorer.");
		}
	}
	

	/**
	 * @return the petNumber
	 */
	public int getPetNumber() {
		return petNumber;
	}

	/**
	 * @param petNumber the petNumber to set
	 */
	public void setPetNumber(int petNumber) {
		this.petNumber = petNumber;
	}

	/**
	 * @return the petCostBuy
	 */
	public int getPetCostBuy() {
		return petCostBuy;
	}

	/**
	 * @param d the petCostBuy to set
	 */
	public void setPetCostBuy(double d) {
		this.petCostBuy = (int) d;
	}

	/**
	 * @return the petCostUpgrade
	 */
	public int getPetCostUpgrade() {
		return petCostUpgrade;
	}
<<<<<<< HEAD
	
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
=======

	/**
	 * @param petCostUpgrade the petCostUpgrade to set
	 */
	public void setPetCostUpgrade(int petCostUpgrade) {
		this.petCostUpgrade = petCostUpgrade;
>>>>>>> master
	}

	/**
	 * @return the petDamages
	 */
	public int getPetDamages() {
		return petDamages;
	}

	/**
	 * @param petDamages the petDamages to set
	 */
	public void setPetDamages(int petDamages) {
		this.petDamages = petDamages;
	}

	/**
	 * @return the petNumberUP
	 */
	public int getPetNumberUP() {
		return petNumberUP;
	}

	/**
	 * @param petNumberUP the petNumberUP to set
	 */
	public void setPetNumberUP(int petNumberUP) {
		this.petNumberUP = petNumberUP;
	}

	
	public void setAddPetNumber(int addPetNumber) {
		this.setPetNumber(this.getPetNumber() + addPetNumber);
		setChanged();
        notifyObservers();
	}
	
	public static void main(String[] args) {
		
	}
	
}
