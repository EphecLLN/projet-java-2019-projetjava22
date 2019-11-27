package model;
/**
 * 
 * Classe qui créé et enregistre les données liées au héros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero {
	int damage = 1;
	static int artefactMoney = 10;	//artefactMoney : l'argent que le héros possède 
	static int artefactCost = 10; 	//prix d'un artefact DOIT être > 0
	
	/*Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.artefactCost = artefactGold;
	}*/
	
	
	
	/**
	 * Cette méthode sert à acheter un nouvel artefact
	 */
	
	public void buyArtefact(Artefact artf) {
		if(artefactMoney >= artefactCost) {
			artefactMoney -= artefactCost;
			artefactCost += artefactCost;
			double nbrNoArtf = (Math.random() *100) % artf.noArtefacts.length;
			artf.currentArtefacts[(int) nbrNoArtf] += artf.noArtefacts[(int) nbrNoArtf];
			System.out.println("Vous avez acheté un nouvel Artefact.");
			System.out.println(artf.currentArtefacts[(int) nbrNoArtf]);
		}
		else {
			System.out.println("Vous n'avez pas assez de Artefact Gold pour améliorer.");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
