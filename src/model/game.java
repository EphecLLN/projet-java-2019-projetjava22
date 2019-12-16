/**
 * 
 */
package model;

import java.util.Observable;
import java.util.TimerTask;

import Vue.Console;

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game extends Observable {
	/*----------------------------------------------
	 * variables de game
	 * ---------------------------------------------*/
	
	private int nbrClic = 0;
	private int gold = 0;
	private int upgradeMoneyValue = 10; // permet d'augmenter le cout de l'amelioration
	private int upgradecroissance = 2;	// augmente la variable du dessus 
	private int nbrUpgrade = 0;
	private int imageHero = 2;			// permet une animation dans la GUI
	
	/*----------------------------------------------
	 * variables utiles à game 
	 * ---------------------------------------------*/
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	
	/** 
	 * methode infligeant des degats au monstre selon le nonmbre de familiers que l'on possede
	 * et le nombre de degats que ceux-ci possedent.
	 * @param monstre : monstre affecter par les degats
	 * @param petDmg  : degats actuel des familiers
	 * @param petNbr  :	nombre de familiers actuels
	 */
	void attackPets(Monster monstre, int petDmg, int petNbr) {
		monstre.setPV(monstre.getPV() - petDmg * petNbr);
		if(petNbr != 0) {
			monstre.die(monstre,this, myMonster.getPV(),myMonster.getNumber(), myMonster.getbossNumber());
		}
		setChanged();
        notifyObservers();
	}
	
	/**
	 * methode infligeant les degats au monstre a chaque clic
	 * Verifie le systeme d'element ainsi que la classe berserker et le "every10Hit".
	 * @param monstre : monstre subissant les degats
	 * @param artf : permet de verifier si every10hit est active
	 * @param dmg : degats du hero applique au monstre
	 * @param dmgAtribute : attribut du hero
	 * @param monsterAttribute : attribut du monstre
	 */
	public void attack(Monster monstre, Artefact artf, int dmg, String dmgAtribute, String monsterAttribute) {
		if(myHero.getCheckClass() == 3) {
			double randomBerzerker = (Math.random() *100) % 5;
			if((int) randomBerzerker == 1) {
				System.out.println("CRITIQUE !");
				monstre.setPV(monstre.getPV() - (dmg * 2));
				monstre.die(monstre,this, monstre.getPV(), monstre.getNumber(), monstre.getbossNumber());
				this.nbrClic ++;
				imageHero++;
			}
		}
        if (artf.isActivate10Hit() == true && this.nbrClic % 10 == 0) {
			monstre.setPV(monstre.getPV() - dmg * 5);
			this.nbrClic ++;
			imageHero++;
		}
        if (dmgAtribute == "pyro" && monsterAttribute == "tera") {
        	monstre.setPV(monstre.getPV() - dmg * 2);
        }
        if (dmgAtribute == "tera" && monsterAttribute == "aqua") {
        	monstre.setPV(monstre.getPV() - dmg * 2);
        }
        if (dmgAtribute == "aqua" && monsterAttribute == "pyro") {
        	monstre.setPV(monstre.getPV() - dmg * 2);
        }
        if ((dmgAtribute == "pyro" && monsterAttribute == "aqua")) {
        	monstre.setPV(monstre.getPV() - dmg / 2);
        }
        if (dmgAtribute == "tera" && monsterAttribute == "pyro") {
        	monstre.setPV(monstre.getPV() - dmg / 2);
        }
        if (dmgAtribute == "aqua" && monsterAttribute == "tera") {
        	monstre.setPV(monstre.getPV() - dmg / 2);
        }
        if (dmgAtribute ==  monsterAttribute ) {
        	monstre.setPV(monstre.getPV() - dmg);
        }
        monstre.die(monstre,this, monstre.getPV(), monstre.getNumber(), monstre.getbossNumber());
		this.nbrClic ++;
		imageHero++;
		setChanged();
	    notifyObservers();
	}
	/**
	 * methode qui augmente les degats du hero 
	 * @param heroGame : hero dont les degats sont augmentes
	 * @param constUP : valeur d'incrementation des degats
	 */
	public void upgrade(Hero heroGame, int constUP) {
		if (gold >= getUpgradeMoneyValue()) {
			heroGame.setConstDamage(heroGame.getConstDamage() + constUP);
			heroGame.setDamage(heroGame.getConstDamage());
			setGold(getGold() - getUpgradeMoneyValue());
			setUpgradeMoneyValue(getUpgradeMoneyValue() + upgradecroissance) ;
			upgradecroissance  += 2 ;
			setNbrUpgrade(getNbrUpgrade() + 1);
		}
		setChanged();
        notifyObservers();
	}
	/**
	 * Methode qui reinitialise le jeu au premier monstre.
	 * Celle-ci donne aussi de l'argent permettant d'acheter des artefacts (dependant de l'avancement en jeu).
	 * @param monstre : permet de reinitialiser certains parametres du monstre.
	 * @param heroGame : permet de reinitialiser certains parametres du heros.
	 * @param myPet : permet de reinitialiser certains parametres du pet.
	 * @param artf : reactive les artefacts.
	 */
	public void reborn(Monster monstre,Hero heroGame,Pets myPet,Artefact artf) {
		heroGame.setArtefactMoney(heroGame.getArtefactMoney() + (monstre.getWaveNumber() + this.getNbrUpgrade() / 10 + myPets.getPetNumber() /10 -1));
		heroGame.setDamage(1);
		heroGame.setConstDamage(1);
		gold = 0;
		nbrUpgrade = 0;
		setUpgradeMoneyValue(10);
		upgradecroissance = 2;
		myHero.setCheckClass(0);
		monstre.setTimeBoss(20);
		monstre.setGoldIncrease(6);
		monstre.setPV(10);
		monstre.setPvIncrease(10);
		monstre.setGoldIncrease(6);
		monstre.setNumber(1);
		monstre.setWaveNumber(1);
		myPet.setPetNumber(0);
		myPet.setPetCostBuy(100);
		myPet.setPetCostUpgrade(150);
		myPet.setPetDamages(1);
		artf.setActivate5More(false);
		artf.setActivate2xDmg(false);
		artf.setActivate2xPet(false);
		applyArtefacts(myArtf, myPet, heroGame, monstre);
		setChanged();
        notifyObservers();
	}
	/**
	 * affiche le choix de hero en console
	 */
	void heroChoice() {
		System.out.println("Vous avez débloqué 3 nouveau héros, choisissez-en un :");
		System.out.println("archer / mage / berzerker");
	}
	/**
	 * debloque le choix de hero
	 * @return le choix du joueur.
	 */
	public String getHeroChoice() {
		if(myMonster.getWaveNumber() >= 2 && myHero.getCheckClass() == 0) {
			String choixHerosOk = "archer / mage / berzerker";
			return choixHerosOk;
		}
		else if(myHero.getCheckClass() == 1) {
			String choixHeroArcher = "Archer (Double la vitesse d'attaque des familiers)";
			return choixHeroArcher;
		}
		else if(myHero.getCheckClass() == 2) {
			String choixHeroMage = "Mage (Confère 5 secondes supplémentaires pour vaincre les boss)";
			return choixHeroMage;

		}
		else if(myHero.getCheckClass() == 3) {
			String choixHeroBerzerker = "Berzerker (Vous avez 20% de chance de coup critique)";
			return choixHeroBerzerker;

		}
		else {
			String choixHerosDenied = " / ";
			return choixHerosDenied;
		}
	}
	/**
	 * transforme le hero en archer
	 * @param heroGame hero a modifier
	 */
	public void archerChoice(Hero heroGame) {
		heroGame.setCheckClass(1);
	}
	/**
	 * transforme le hero en mage
	 * @param heroGame hero a modifier
	 */
	public void mageChoice(Hero heroGame) {
		heroGame.setCheckClass(2);
		myMonster.setTimeBoss(25);
	}
	/**
	 * transforme le hero en berserker
	 * @param heroGame hero a modifier
	 */
	public void berzerkerChoice(Hero heroGame) {
		heroGame.setCheckClass(3);
	}
	
	public int getImageHero() {
		return imageHero;
	}
	
	public void setImageHero(int number) {
		this.imageHero = number;
	}

	/**
	 * methode qui permet d'activer les artefacts a l'achat ou lors d'un reborn
	 * @param artf : stocke les artefacts actuels
	 * @param pet : familier sur lequel agir
	 * @param hero : hero sur lequel agir
	 * @param monster : monstre sur lequel agir
	 */
	public void applyArtefacts(Artefact artf,Pets pet,Hero hero, Monster monster) {
		
		for (int i = 0; i<artf.getCurrentArtefacts().size() ; i++) {
			if (artf.getCurrentArtefacts().contains("doublePet") && artf.isActivate2xPet() != true) {
				pet.setPetNumber(pet.getPetNumber() * 2);
				pet.setPetNumberUP(2);	
				artf.setActivate2xPet(true);
			}
			if (artf.getCurrentArtefacts().contains("+5DMG") && artf.isActivate5More() != true) {
				hero.setConstDamage(hero.getConstDamage() + 5);
				artf.setActivate5More(true);
			}
			if (artf.getCurrentArtefacts().contains("doubleDMG") && artf.isActivate2xDmg() != true) {
				hero.setConstDamage(hero.getConstDamage() *2);
				hero.setConstUpDamage(2);
				artf.setActivate2xDmg(true);
			}
			if (artf.getCurrentArtefacts().contains("-1Boss")) {
				monster.setbossNumber(9);
			}
			if (artf.getCurrentArtefacts().contains("every10Hit")) {
				artf.setActivate10Hit(true);
			}
			hero.setDamage(hero.getConstDamage());
			
		}
	}
	

	
	public int getUpgradeValue() {
		return upgradeMoneyValue;
	}
	public int getUpgradeMoneyValue() {
		return upgradeMoneyValue;
	}

	public void setUpgradeMoneyValue(int upgradeValue) {
		this.upgradeMoneyValue = upgradeValue;
		setChanged();
		notifyObservers();
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
		setChanged();
		notifyObservers();
	}
	
	public int getNbrUpgrade() {
		return nbrUpgrade;
	}

	public void setNbrUpgrade(int nbrUpgrade) {
		this.nbrUpgrade = nbrUpgrade;
	}
	
	public class PetsDamages extends TimerTask {
	    public void run() {
	    	attackPets(myMonster, myPets.getPetDamages(), myPets.getPetNumber());
	    }
	}
	
	public class ArcherPetsDamages extends TimerTask {
	    public void run() {
	    	if(myHero.checkClass == 1) {
	    		attackPets(myMonster, myPets.getPetDamages(), myPets.getPetNumber());
	    	}
	    }
	}
	
	public class ChronoMonstre extends TimerTask {
		public void run() {
			if(myMonster.getTimeBoss() == 0) {
				System.out.println("Vous avez perdu. Reborn en cours...");
				reborn(myMonster, myHero, myPets, myArtf);
			}
			else if(myMonster.getNumber() == myMonster.getbossNumber()) {
				myMonster.setTimeBoss(myMonster.getTimeBoss() - 1);
			}
			setChanged();
	        notifyObservers();
		}
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}


