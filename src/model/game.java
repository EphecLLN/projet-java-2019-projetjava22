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
	private int gold = 0;
	private int upgradeMonyeValue = 10;
	int upgradecroissance = 2;
	int constUpgradeDamage = 1;
	int nbrUpgrade = 0;
	
	/*----------------------------------------------
	 * variables utile à game 
	 * ---------------------------------------------*/
	public Archer myArcher = new Archer();
	Mage myMage = new Mage();
	Berzerker myBerzerker = new Berzerker();
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore implementer (degats par seconde en int. graph.)
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel();
	
	void attackPets(Monster monstre, Pets monToutou) {
		monstre.setPV(monstre.getPV() - monToutou.getPetDamage() * monToutou.getPetNumber());
		if(monToutou.petNumber != 0) {
			monstre.die(myMonster,this);
		}
		setChanged();
        notifyObservers();
	}

	public void attack(Monster monstre,Hero heroGame, Artefact artf) {
		if(myBerzerker.getCheckClassBerzerker() == 1) {
			double randomBerzerker = (Math.random() *100) % 5;
			if((int) randomBerzerker == 1) {
				System.out.println("CRITIQUE !");
				monstre.setPV(monstre.getPV() - (heroGame.getDamage() * 2));
				monstre.die(myMonster,this);
				this.nbrClic ++;
			}
			else {
				monstre.setPV(monstre.getPV() - heroGame.getDamage());
				monstre.die(myMonster,this);
				this.nbrClic ++;
			}
		}
        if (artf.activate10hit == true && this.nbrClic % 10 == 0) {
			monstre.setPV(monstre.getPV() - heroGame.getDamage() * 5);
			monstre.die(monstre,this);
			this.nbrClic ++;
		}
		else {
			monstre.setPV(monstre.getPV() - heroGame.getDamage());
			monstre.die(myMonster,this);
			this.nbrClic ++;
		}
		
		setChanged();
        notifyObservers();
	}
	public void upgrade(Hero heroGame) {
		if (gold >= getUpgradeValue()) {
			heroGame.setConstDamage(heroGame.getConstDamage() + constUpgradeDamage);
			heroGame.setDamage(heroGame.getConstDamage());
			gold = gold - getUpgradeValue();
			setUpgradeValue(getUpgradeValue() + upgradecroissance) ;
			upgradecroissance  += 2 ;
			nbrUpgrade ++;
		}
		setChanged();
        notifyObservers();
	}
	
	public void reborn(Monster monstre,Hero heroGame,Pets myPet) {
		heroGame.setDamage(1);
		heroGame.setConstDamage(1);
		gold = 0;
		myArcher.setCheckClassArcher(0);;
		myBerzerker.setCheckClassBerzerker(0);
		myMage.setCheckClassMage(0);
		monstre.setTempsBoss(20);
		monstre.setGoldIncrease(6);
		monstre.setPV(10);
		monstre.setPvIncrease(10);
		monstre.setGoldIncrease(6);
		monstre.setNumber(1);
		monstre.setWaveNumber(1);
		myPet.petNumber = 0;
		myPet.petCostBuy= 100;
		myPet.petCostUpgrade = 150;
		setUpgradeValue(10);
		applyArtefacts(myArtf, myPet, heroGame, monstre);
		setChanged();
        notifyObservers();
	}
	
	void heroChoice() {
		System.out.println("Vous avez débloqué 3 nouveau héros, choisissez-en un :");
		System.out.println("archer / mage / berzerker");
	}
	
	public void archerChoice(Pets familier, Archer archer) {
		this.myArcher.setCheckClassArcher(1);
		System.out.println("Vous avez choisi la classe archer. Vos familiers doublent leur vitesse d'attaque.");
	}
	
	public void mageChoice() {
		this.myMage.setCheckClassMage(1);
		myMonster.setTempsBoss(25);
		System.out.println("Vous avez choisi la classe mage. Vous gagnez 5 secondes suppémentaire pour vaincre chaque boss.");
	}
	
	public void berzerkerChoice() {
		this.myBerzerker.setCheckClassBerzerker(1);
		System.out.println("Vous avez choisi la classe berzerker. Vous avez désormais 20% de chance d'effectuez un coup critique.");
	}

	public void applyArtefacts(Artefact artf,Pets pet,Hero hero, Monster monster) {
		for (int i = 0; i<artf.getCurrentArtefacts().length ; i++) {
			if (artf.getCurrentArtefacts()[i].contentEquals("doublePet")) {
				pet.petNumber = (pet.petNumber * 2);
				pet.petBuyIncrease = 2;	
			}
			if (artf.getCurrentArtefacts()[i].contentEquals("doubleDMG") ) {
				hero.setConstDamage(hero.getConstDamage() + 1);
				this.constUpgradeDamage ++;
			}
			if (artf.getCurrentArtefacts()[i].contentEquals("+5DMG")) {
				hero.setConstDamage(hero.getConstDamage() + 5);
			}
			if (artf.getCurrentArtefacts()[i].contentEquals("-1Boss")) {
				monster.setbossNumber(9);
			}
			if (artf.getCurrentArtefacts()[i].contentEquals("every10Hit")) {
				artf.activate10hit = true;
			}
			hero.setDamage(hero.getConstDamage());
		}
	}
	
	public int getUpgradeValue() {
		return upgradeMonyeValue;
	}

	public void setUpgradeValue(int upgradeValue) {
		this.upgradeMonyeValue = upgradeValue;
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
	
	public class choixArcher implements ActionListener{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + gold );
	              degatLabel.setText("degats actuels :" + myHero.getDamage());
	              coutUPLabel.setText("cout : " + upgradeMonyeValue);
	       }
	}
	
	public class PetsDamages extends TimerTask {
	    public void run() {
	    	attackPets(myMonster, myPets);
			PVLabel.setText("monstre PV : " + myMonster.getPV());
			argentLabel.setText("argent : " + gold );
	    }
	}
	
	public class ArcherPetsDamages extends TimerTask {
	    public void run() {
	    	if(myArcher.checkClassArcher == 1) {
	    		attackPets(myMonster, myPets);
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
		game myGame = new game();
		GUI myGUI = new GUI(myGame, null);
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		
		// Timer pour les degats des pets
		Timer timerPets = new Timer();
		PetsDamages aille = myGame.new PetsDamages();
		timerPets.schedule(aille, 0, 2000);
		
		// Timer pour les dégats supplémentaires de la classe Archer
		Timer timerArcher = new Timer();
		ArcherPetsDamages ouille = myGame.new ArcherPetsDamages();
		timerArcher.schedule(ouille, 1000, 2000);
		
		// Timer pour le décompte de 20/25 secondes au moment des boss
		Timer timerBoss = new Timer();
		ChronoMonstre chrono = myGame.new ChronoMonstre();
		timerBoss.schedule(chrono, 0, 1000);
		
		myGame.myHero.buyArtefact(myGame.myArtf, myGame);
		myGame.myConsole.Scan(myGame);
	}
}

