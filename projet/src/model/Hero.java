package model;
/**
 * 
 * Classe qui créé et enregistre les données liées au héros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero {

	static int gold = 0; 			//artefactMoney : l'argent que le héros possède 
	static int petCount = 0;		//nombre de pet que le héros possède
	static int petCost = 100; 		//prix d'un pet DOIT être > 0
	
	static int artefactMoney = 0;	// l'argent que le héros possède 
	static int artefactCost = 10; 	//prix d'un artefact DOIT être > 0
	
	Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.artefactCost = artefactCost;
	}
	
	/**
	 * Cette méthode sert à acheter un nouveau pet
	 */
	
	public void buyPet() {
		if(gold >= petCost) {
			petCount++;
			gold -= petCost;
			petCost += petCost * 10/100;
			System.out.print("Vous avez acheté un nouveau pet.");			}
		else {
			System.out.println("Vous n'avez pas assez de gold pour améliorer.");
		}
	}
	
	/**
	 * Cette méthode sert à acheter un nouvel artefact
	 */
	
	public void buyArtefact() {
		if(artefactMoney >= artefactCost) {
			artefactMoney -= artefactCost;
			artefactCost += artefactCost;
			System.out.println("Vous avez acheté un nouvel Artefact.");
		}
		else {
			System.out.println("Vous n'avez pas assez de Artefact Gold pour améliorer.");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
