package model;
/**
 * 
 * Classe qui créé et enregistre les données liées au héros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero {
	private int damage = 1;
	int constDamage = 1; 			//variable pr�servant damage d'�tre constament modifi�
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
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public static void main(String[] args) {
		
	}
}
