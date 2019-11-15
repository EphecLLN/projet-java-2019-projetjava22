package model;
/**
 * 
 * Classe qui cr�� et enregistre les donn�es li�es au h�ros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero {

	static int gold = 0; 			//artefactMoney : l'argent que le h�ros poss�de 
	static int petCount = 0;		//nombre de pet que le h�ros poss�de
	static int petCost = 100; 		//prix d'un pet DOIT �tre > 0
	
	static int artefactMoney = 0;	// l'argent que le h�ros poss�de 
	static int artefactCost = 10; 	//prix d'un artefact DOIT �tre > 0
	
	Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.petCost = petCost;
		
		Hero.artefactMoney = artefactGold;
		Hero.artefactCost = artefactCost;
	}
	
	/**
	 * Cette m�thode sert � acheter un nouveau pet
	 */
	
	public void buyPet() {
		if(gold >= petCost) {
			petCount++;
			gold -= petCost;
			petCost += petCost * 10/100;
			System.out.print("Vous avez achet� un nouveau pet.");			}
		else {
			System.out.println("Vous n'avez pas assez de gold pour am�liorer.");
		}
	}
	
	/**
	 * Cette m�thode sert � acheter un nouvel artefact
	 */
	
	public void buyArtefact() {
		if(artefactMoney >= artefactCost) {
			artefactMoney -= artefactCost;
			artefactCost += artefactCost;
			System.out.println("Vous avez achet� un nouvel Artefact.");
		}
		else {
			System.out.println("Vous n'avez pas assez de Artefact Gold pour am�liorer.");
		}
	}
	
	public static void main(String[] args) {
		
	}
}