package model;

import java.util.Observable;

/**
 * Classe qui cree et enregistre les donnees liees au heros
 * Description des principales fonctionnalites de la classe :
 * 
 * Stock les informations liées au heros tel que la classe choisie, les artefacts obtenus et l'argent d'artefact.
 * @author Nathan Debongnie, Matthieu Luyckx
 */
public class Hero extends Observable {
	
	/**
	 * Variable gardant la valeur a laquelle les degats vont 
	 * etre augment�s a chaque am�lioriations du hero
	 */
	
	private int constUpDamage = 1;
	
	/**
	 * L'integer damage sert a enregistrer les degats infligés par le héros.
	 * Cet attribut sera recalculé à chaque attaque, pour que la valeur finale de cette variable soit infligée
	 * au monstre.
	 * Il variera en fonction des dégats supplémentaires conférés par les artefacts et en fonction des
	 * coups critiques
	 */
	
	private int damage = 1;
	private String attributeHero = "aqua";

	
	
	/**
	 * L'integer checkClass sert à vérifier quelle classe de héros est active. La valeur est décrite comme suit :
	 * checkClass = 0 -> Aucune classe est sélectionnée
	 * checkClass = 1 -> La classe Archer est sélectionnée
	 * 					(double la vitesse d'attaque des familiers)
	 * checkClass = 2 -> La classe Mage est sélectionnée	
	 * 					(confère 5 secondes supplémentaires pour vaincre les boss)
	 * checkClass = 3 -> La classe Berzerker est sélectionnée
	 * 					(confère à chaque attaque 20% de chance de coup critique, ceux-ci effectuant 200% de degats
	 * 					 au monstre)
	 */
	
	protected int checkClass = 0;
	
	/**
	 * Cet attibut permet d'améliorer les dégâts de base du heros indépendement des dégâts supplémentaires
	 * conférés par les artefacts et les couts critiques. Cette valeur ne sera donc incrémentée uniquement dans 
	 * le cas d'un amélioration des dégâts de base.
	 */
	
	private int constDamage = 1;
	
	/**
	 * Cet attribut permet de stocker l'argent spécial pour les artefact récolté durant la résurection (reborn)
	 */
	
	private int artefactMoney = 0;
	
	/**
	 * Cet attribut stocke le prix d'achat d'un nouvel artefact. Cette valeur doit être strictement positive,
	 * et sera de plus en plus grande à chaque achat d'artefact.
	 */
	
	private int artefactCost = 10;
	/**
	 * Cet attribut permet de stocker le type de monstre (Eau, terre ou feu). Cette valeur change de manière aléatoire
	 * via a fonction screenMonster() de la classe GUI
	 */
	
	/*Hero(int petGold,int artefactGold){ //constructeur pour le test
		Hero.gold = petGold;
		Hero.artefactCost = artefactGold;
	}*/
		
	/**
	 * Cette méthode sert a�acheter un nouvel artefact.
	 * 
	 * @author Lucas Pastori
	 * @param Artefact	appelle la classe Artefact pour ajouter le nouvel artefact du
	 *  tableau d'artefacts
	 *  "currentArtefact" et pour retirer celui-ci du tableau "noArtefacts"
	 *  
	 * @param game appelle la classe game pour pouvoir y appliquer les nouveaux artefact via la fonction
	 * "applyArtefacts" qui se trouve dans le classe game.
	 * 
	 */
	
	public void buyArtefact(Artefact artf,game game, int nbrArtfMoney) {
		if(nbrArtfMoney >= getArtefactCost() && artf.getCurrentArtefacts().size() !=5) {
			setArtefactMoney(nbrArtfMoney - getArtefactCost());
			setArtefactCost(getArtefactCost() + artefactCost);
			double nbrNoArtf = (Math.random() *100) % artf.noArtefacts.length;
			while(artf.getCurrentArtefacts().contains(artf.noArtefacts[(int) nbrNoArtf])) {
				nbrNoArtf = (Math.random() *100) % artf.noArtefacts.length;
			}
			artf.getCurrentArtefacts().add(artf.noArtefacts[(int) nbrNoArtf]);
			game.applyArtefacts(game.myArtf, game.myPets, game.myHero, game.myMonster);
		}
		else {
			System.out.println("Vous n'avez pas assez de Artefact Gold pour améliorer.");
		}
	}
	public String getAttributeHero() {
		return attributeHero;
	}
	
	/**
	 * @param x attribut à set (aqua, terra ou pyro)
	 */
	
	public void setAttributeHero(String x) {
		this.attributeHero = x;
	}
	

	
	/**
	 * @return les dégâts actuels du heros
	 */
	
	public int getDamage() {
		return damage;
	}
	
	/**
	 * @return the constUpgradeDamage
	 */
	public int getConstUpDamage() {
		return constUpDamage;
	}
	/**
	 * @param constUpgradeDamage the constUpgradeDamage to set
	 */
	public void setConstUpDamage(int constUpgradeDamage) {
		this.constUpDamage = constUpgradeDamage;
	}
	/**
	 * @return chiffre actuel représentant la classe active
	 * 		0 pour aucune classe choisie
	 * 		1 pour la classe Archer
	 * 		2 pour la classe Mage
	 * 		3 pour la classe Berzerker
	 */

	public int getCheckClass() {
		return checkClass;
	}
	
	/**
	 * @param damage : nouveaux dégats à set
	 */
	
	public void setDamage(int damage) {
		this.damage = damage;
		setChanged();
        notifyObservers();
	}
	
	/**
	 * @param number : nouveau chiffre de classe à rendre actif
	 * 	 	0 pour aucune classe
	 * 		1 pour la classe Archer
	 * 		2 pour la classe Mage
	 * 		3 pour la classe Berzerker
	 */
	
	public void setCheckClass(int number) {
		this.checkClass = number;
	}

	/**
	 * @return les dégats net du heros, hors artefact et coups critiques
	 */

	public int getConstDamage() {
		return constDamage;
	}

	/**
	 * @param constDamage : nouveaux dégâts nets du héros, modifiés uniquement après amélioration de dégats.
	 */
	
	public void setConstDamage(int constDamage) {
		this.constDamage = constDamage;
		setChanged();
        notifyObservers();
	}

	/**
	 * @return la somme d'argent spéciale pour les artefacts
	 */

	public int getArtefactMoney() {
		return artefactMoney;
	}


	/**
	 * @param artefactMoney : met à jour la somme d'argant pour les artefacts
	 */
	
	public void setArtefactMoney(int artefactMoney) {
		this.artefactMoney = artefactMoney;
	}
	
	/**
	 * @return le prix pour les artefacts en vente
	 */

	public int getArtefactCost() {
		return artefactCost;
	}

	/**
	 * @param artefactCost : met à jour le prix pour les artefacts en vente
	 */
	
	public void setArtefactCost(int artefactCost) {
		this.artefactCost = artefactCost;
	}
	
	public static void main(String[] args) {
		
	}
}
