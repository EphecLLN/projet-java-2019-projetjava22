package model;

import java.util.Observable;

public class Pets extends Observable {
	private int petDamages = 1;			//d�gats des pets
	private int petDmgIncrease = 1;		//augmentation des d�gats apr�s une am�lioration
	private int petNumber = 0; 			//nombre de pets
	private int petNumberUP= 1;			//nombre de pets 
	private int petCostUpgrade = 150;	//prix d'am�lioration d'un familier
	private double petBuyIncrease = 0.1;//pourcentage d'augmentation du prix de l'achat d'un familier
	private int petsAttackSpeed = 2000;	//vitesse d'attaque (2000 = 2 secondes)
	private int petCostBuy = 100;		//prix d'achat d'un familier
	
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
	 * 
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

	/**
	 * @param petCostUpgrade the petCostUpgrade to set
	 */
	public void setPetCostUpgrade(int petCostUpgrade) {
		this.petCostUpgrade = petCostUpgrade;
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
