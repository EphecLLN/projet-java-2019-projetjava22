/**
 * 
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Vue.Console;
import Vue.GUI;
import java.util.Observable;

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game extends Observable {
	/*----------------------------------------------
	 * variables de game
	 * ---------------------------------------------*/

	int nbrClic = 0;
	private int gold = 1000;
	private int upgradeMoneyValue = 10;
	int upgradecroissance = 2;
	private int nbrUpgrade = 0;
	int imageHero = 2;
	int save5 = 0;
	int save2x = 0;
	int savePet = 0;
	
	/*----------------------------------------------
	 * variables utiles à game 
	 * ---------------------------------------------*/
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	
	
	void attackPets(Monster monstre, int pDmg, int pNbr) {
		monstre.setPV(monstre.getPV() - pDmg * pNbr);
		if(pNbr != 0) {
			monstre.die(monstre,this, myMonster.getPV(),myMonster.getNumber(), myMonster.getbossNumber());
		}
		setChanged();
        notifyObservers();
	}

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
        if (artf.activate10hit == true && this.nbrClic % 10 == 0) {
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
	
	public void reborn(Monster monstre,Hero heroGame,Pets myPet) {
		heroGame.setArtefactMoney(heroGame.getArtefactMoney() + (monstre.getWaveNumber() + this.getNbrUpgrade() / 10 + myPets.getPetNumber() /10 -1));
		heroGame.setDamage(1);
		heroGame.setConstDamage(1);
		gold = 0;
		nbrUpgrade = 0;
		setUpgradeMoneyValue(10);
		upgradecroissance = 2;
		myHero.setCheckClass(0);
		monstre.setTempsBoss(20);
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
		save5 = 0;
		save2x = 0;
		applyArtefacts(myArtf, myPet, heroGame, monstre);
		setChanged();
        notifyObservers();
	}
	
	void heroChoice() {
		System.out.println("Vous avez débloqué 3 nouveau héros, choisissez-en un :");
		System.out.println("archer / mage / berzerker");
	}
	
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
			String choixHerosDenied = "bloqué";
			return choixHerosDenied;
		}
	}
	
	public void archerChoice(Hero heroGame) {
		heroGame.setCheckClass(1);
	}
	
	public void mageChoice(Hero heroGame) {
		heroGame.setCheckClass(2);
		myMonster.setTempsBoss(25);
	}
	
	public void berzerkerChoice(Hero heroGame) {
		heroGame.setCheckClass(3);
	}
	
	public int getImageHero() {
		return imageHero;
	}
	
	public void setImageHero(int number) {
		this.imageHero = number;
	}

	
	public void applyArtefacts(Artefact artf,Pets pet,Hero hero, Monster monster) {
		
		for (int i = 0; i<artf.getCurrentArtefacts().size() ; i++) {
			if (artf.getCurrentArtefacts().contains("doublePet") && savePet != 1) {
				pet.setPetNumber(pet.getPetNumber() * 2);
				pet.setPetNumberUP(2);	
				savePet = 1;
			}
			if (artf.getCurrentArtefacts().contains("+5DMG") && save5 != 1) {
				hero.setConstDamage(hero.getConstDamage() + 5);
				save5 = 1;
			}
			if (artf.getCurrentArtefacts().contains("doubleDMG") && save2x != 1) {
				hero.setConstDamage(hero.getConstDamage() *2);
				hero.setConstUpgradeDamage(2);
				save2x = 1;
			}
			if (artf.getCurrentArtefacts().contains("-1Boss")) {
				monster.setbossNumber(9);
			}
			if (artf.getCurrentArtefacts().contains("every10Hit")) {
				artf.activate10hit = true;
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
			if(myMonster.getTempsBoss() == 0) {
				System.out.println("Vous avez perdu.");
				reborn(myMonster, myHero, myPets);
			}
			else if(myMonster.getNumber() == myMonster.getbossNumber()) {
				myMonster.setTempsBoss(myMonster.getTempsBoss() - 1);
				System.out.println(myMonster.getTempsBoss());
			}
		}
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
}


