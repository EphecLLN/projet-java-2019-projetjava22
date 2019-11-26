package model;
/**
 * 
 * Classe qui créé et enregistre les données liées au héros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero {

	static int gold = 0; 			//artefactMoney : l'argent que le héros possède 
	int damage = 1;
	static int artefactMoney = 0;	// l'argent que le héros possède 
	static int artefactCost = 10; 	//prix d'un artefact DOIT être > 0
	
	/*Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.artefactCost = artefactGold;
	}*/
	
	
	
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
