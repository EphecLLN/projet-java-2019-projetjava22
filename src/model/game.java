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
	 * variables utile
	 * ---------------------------------------------*/
	public Archer myArcher = new Archer();
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore implementer (degats par seconde en int. graph.)
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel();
	Timer timerPets = new Timer();
	Timer timerArcher = new Timer();
	
	void attackPets(Monster monstre, Pets monToutou) {
		monstre.setPV(monstre.getPV() - monToutou.getPetDamage() * monToutou.getPetNumber());
		if(monToutou.petNumber != 0) {
			monstre.die(myMonster,this);
		}
		setChanged();
        notifyObservers();
	}

	public void attack(Monster monstre,Hero heroGame, Artefact artf) {
		if (artf.activate10hit == true && this.nbrClic % 10 == 0) {
			monstre.setPV(monstre.getPV() - heroGame.getDamage() * 5);
			monstre.die(monstre,this);
			this.nbrClic ++;
		}
		else {
			monstre.setPV(monstre.getPV() - heroGame.getDamage());
			monstre.die(monstre,this);
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
		monstre.setGoldIncrease(6);
		monstre.setPV(10);
		monstre.setPvIncrease(10);
		monstre.setGoldIncrease(6);
		monstre.setNumber(1);
		monstre.setWaveNumber(1);
		myPet.petNumber = 0;
		setChanged();
        notifyObservers();
	}
	
	public void timerPets() {
		game myGame = new game();
		Pets myPets = new Pets();
		PetsDamages aille = myGame.new PetsDamages();
		this.timerPets.schedule(aille, 0, myPets.petsAttackSpeed);
		this.timerArcher.schedule(aille, 0, myPets.petsAttackSpeed);
	}
	
	void heroChoice() {
		
	}
	
	public void archerChoice(Pets familier, Archer archer) {
		familier.petsAttackSpeed = archer.petsAttackSpeed;


	}
	
	void mageChoice() {
		
	}
	
	void berzerkerChoice() {
		
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
	
	public int getGold() {
		return gold;
	}
	public void setUpgradeValue(int upgradeValue) {
		this.upgradeMonyeValue = upgradeValue;
		setChanged();
		notifyObservers();
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
	
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		game myGame = new game();
		GUI myGUI = new GUI(myGame, null);
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		Timer timerPets = new Timer();
		PetsDamages aille = myGame.new PetsDamages();
		timerPets.schedule(aille, 0, myGame.myPets.getPetsAttackSpeed());
		myGame.heroChoice();
		myGame.myHero.buyArtefact(myGame.myArtf);
		myGame.applyArtefacts(myGame.myArtf, myGame.myPets, myGame.myHero, myGame.myMonster);
		myGame.myConsole.Scan(myGame);
	}
}

