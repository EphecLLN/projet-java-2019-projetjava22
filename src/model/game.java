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
	int constUpgradeDamage = 1;
	private int nbrUpgrade = 0;
	int imageHero = 2;
	
	/*----------------------------------------------
	 * variables utile à game 
	 * ---------------------------------------------*/
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	clic actionClic = new clic();
	upgrade UP = new upgrade();
	archerChoice Archer = new archerChoice();
	mageChoice Mage = new mageChoice();
	berzerkerChoice Berzerker = new berzerkerChoice();
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore implementer (degats par seconde en int. graph.)
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel();
	JButton buttonUP = new JButton();
	JFrame window = new JFrame();
	JPanel choiceClass = new JPanel();
	JButton buttonArcher = new JButton();
	JButton buttonMage = new JButton();
	JButton buttonBerzerker = new JButton();
	
	void attackPets(Monster monstre, Pets myPet) {
		monstre.setPV(monstre.getPV() - myPet.getPetDamage() * myPet.getPetNumber());
		if(myPet.petNumber != 0) {
			monstre.die(monstre,this);
		}
		setChanged();
        notifyObservers();
	}

	public void attack(Monster monstre,Hero heroGame, Artefact artf) {
		if(heroGame.getCheckClass() == 3) {
			double randomBerzerker = (Math.random() *100) % 5;
			if((int) randomBerzerker == 1) {
				System.out.println("CRITIQUE !");
				monstre.setPV(monstre.getPV() - (heroGame.getDamage() * 2));
				monstre.die(monstre,this);
				this.nbrClic ++;
				imageHero++;
			}
			else {
				monstre.setPV(monstre.getPV() - heroGame.getDamage());
				monstre.die(monstre,this);
				this.nbrClic ++;
				imageHero++;
			}
		}
        if (artf.activate10hit == true && this.nbrClic % 10 == 0) {
			monstre.setPV(monstre.getPV() - heroGame.getDamage() * 5);
			monstre.die(monstre,this);
			this.nbrClic ++;
			imageHero++;
		}
		else if(heroGame.getCheckClass() != 3){
			monstre.setPV(monstre.getPV() - heroGame.getDamage());
			monstre.die(monstre,this);
			this.nbrClic ++;
			imageHero++;
		}
		
		setChanged();
        notifyObservers();
	}
	
	public void upgrade(Hero heroGame) {
		if (gold >= getUpgradeMoneyValue()) {
			heroGame.setConstDamage(heroGame.getConstDamage() + constUpgradeDamage);
			heroGame.setDamage(heroGame.getConstDamage());
			gold = gold - getUpgradeMoneyValue();
			setUpgradeMoneyValue(getUpgradeMoneyValue() + upgradecroissance) ;
			upgradecroissance  += 2 ;
			setNbrUpgrade(getNbrUpgrade() + 1);
		}
		setChanged();
        notifyObservers();
	}
	
	public void reborn(Monster monstre,Hero heroGame,Pets myPet) {
		heroGame.setArtefactMoney(heroGame.getArtefactMoney() + (monstre.getWaveNumber() ) + (this.getNbrUpgrade() / 10 + this.myPets.getPetNumber() /10 - 1));
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
		myPet.petNumber = 0;
		myPet.petCostBuy= 100;
		myPet.petCostUpgrade = 150;
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
	
		
		
	public void genererUI() { //commande g�n�rant l'inteface ainsi que les bouttons
		 
		JFrame window = new JFrame();
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 400, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);
		
		ImageIcon slimeBleu = new ImageIcon(game.class.getResource("/images/slime bleu.png")); //creation d'une image en tant que ic�ne.
		
		JButton bouttonMonstre = new JButton();
		bouttonMonstre.setBackground(Color.white);
		bouttonMonstre.setFocusPainted(false);
		bouttonMonstre.setBorder(null);
		bouttonMonstre.setIcon(slimeBleu); // attribution de l'ic�ne au boutton (faire ressembler a un monstre).
		bouttonMonstre.addActionListener(actionClic); // activation de clic() en int.graph.
		monstrePanel.add(bouttonMonstre);
		
		JPanel ensembleBoutton = new JPanel();
		ensembleBoutton.setLayout(new GridLayout(6,1));
		ensembleBoutton.setBounds(600,100,150,400);
		ensembleBoutton.setBackground(Color.white);
		window.add(ensembleBoutton);
		
		ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		JButton buttonUP = new JButton();
		buttonUP.setBackground(Color.white);
		buttonUP.setFocusPainted(false);
		buttonUP.setBorder(null);
		buttonUP.setIcon(UPIcon);
		buttonUP.addActionListener(UP);
		ensembleBoutton.add(buttonUP);	
		
		JPanel compteur = new JPanel();
		compteur.setBounds(100,100,400,200);
		compteur.setBackground(Color.GRAY);
		compteur.setLayout(new GridLayout(4,1));
		window.add(compteur);
		
		PVLabel.setForeground(Color.black );
		Font PVEcriture = new Font("Comic Sans MS", Font.PLAIN, 24 );
		PVLabel.setFont(PVEcriture);
		PVLabel.setText("monstre PV : " + myMonster.getPV());
		PVLabel.setText("PV : " + myMonster.getPV());
		compteur.add(PVLabel);
		
		argentLabel.setForeground(Color.black );
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + gold );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("degats actuels :" + myHero.getDamage());
		compteur.add(degatLabel);
		
		coutUPLabel.setForeground(Color.black);
		coutUPLabel.setFont(stats);
		coutUPLabel.setText("co�t : " + getUpgradeValue());
		ensembleBoutton.add(coutUPLabel);
		
		window.setVisible(true);
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
	    	attackPets(myMonster, myPets);
			PVLabel.setText("monstre PV : " + myMonster.getPV());
			argentLabel.setText("argent : " + gold );
	    }
	}
	
	public class ArcherPetsDamages extends TimerTask {
	    public void run() {
	    	if(myHero.checkClass == 1) {
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
	
	public class clic implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
				attack(myMonster,myHero,myArtf);
				PVLabel.setText("monstre PV : " + myMonster.getPV());
				argentLabel.setText("argent : " + gold );
			}
	 }
	
	public class upgrade implements ActionListener{
      public void actionPerformed(ActionEvent event) {
             upgrade(myHero);
             argentLabel.setText("argent : " + gold );
             degatLabel.setText("d�g�ts actuels :" + myHero.getDamage());
             coutUPLabel.setText("co�t : " + getUpgradeValue());
         }
	}

	public class archerChoice implements ActionListener{
	      public void actionPerformed(ActionEvent event) {
	             mageChoice(myHero);
	      }
	}

	public class mageChoice implements ActionListener{
	      public void actionPerformed(ActionEvent event) {
	             mageChoice(myHero);
	      }
	}
	
	public class berzerkerChoice implements ActionListener{
	      public void actionPerformed(ActionEvent event) {
	             berzerkerChoice(myHero);
	      }
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		game myGame = new game();
		myGame.genererUI();
		
		System.out.println("essaaaaaaaaaaaaaaaaaaaaai");
		
		myGame.myHero.buyArtefact(myGame.myArtf, myGame);
		myGame.myConsole.Scan(myGame);
	}
}


