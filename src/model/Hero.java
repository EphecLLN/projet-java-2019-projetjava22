package model;

import java.util.Observable;

/**
 * 
 * Classe qui créé et enregistre les données liées au héros
 * @author Nathan Debongnie 2TL2
 *
 */
public class Hero extends Observable {
	private int damage = 1;
	protected int checkClass = 0;
	private int constDamage = 1; 			//variable pr�servant damage d'�tre constament modifi�
	private int artefactMoney = 0;	//artefactMoney : l'argent que le héros possède 
	private int artefactCost = 10; 	//prix d'un artefact DOIT être > 0
	private int attribute;
	/*Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.artefactCost = artefactGold;
	}*/
	
	
	
	/**
	 * Cette méthode sert à acheter un nouvel artefact
	 */
	
	public void buyArtefact(Artefact artf,game game) {
		if(getArtefactMoney() >= getArtefactCost()) {
			setArtefactMoney(getArtefactMoney() - getArtefactCost());
			setArtefactCost(getArtefactCost() + artefactCost);
			double nbrNoArtf = (Math.random() *100) % artf.noArtefacts.length;
			artf.getCurrentArtefacts()[(int) nbrNoArtf] += artf.noArtefacts[(int) nbrNoArtf];
			game.applyArtefacts(game.myArtf, game.myPets, game.myHero, game.myMonster);
		}
		else {
			System.out.println("Vous n'avez pas assez de Artefact Gold pour améliorer.");
		}
	}
	public void setAttribute(int x) {
		this.attribute = x;
	}
	
	public int getDamage() {
		return damage;
	}

	public int getCheckClass() {
		return checkClass;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
		setChanged();
        notifyObservers();
	}
	
	public void setCheckClass(int number) {
		this.checkClass = number;
	}


	public int getConstDamage() {
		return constDamage;
	}

	public void setConstDamage(int constDamage) {
		this.constDamage = constDamage;
		setChanged();
        notifyObservers();
	}

	public static void main(String[] args) {
		
	}

	public int getArtefactMoney() {
		return artefactMoney;
	}

	public void setArtefactMoney(int artefactMoney) {
		this.artefactMoney = artefactMoney;
	}

	public int getArtefactCost() {
		return artefactCost;
	}

	public void setArtefactCost(int artefactCost) {
		this.artefactCost = artefactCost;
	}
}
