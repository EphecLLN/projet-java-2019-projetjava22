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
	private int upgradeMoneyValue = 10;
	int upgradecroissance = 2;
	int constUpgradeDamage = 1;
	private int nbrUpgrade = 0;
	
	/*----------------------------------------------
	 * variables utile à game 
	 * ---------------------------------------------*/
	public Archer myArcher = new Archer();
	public Mage myMage = new Mage();
	public Berzerker myBerzerker = new Berzerker();
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console(this, null);
	clic actionClic = new clic();
	upgrade UP = new upgrade();
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
	
	void attackPets(Monster monstre, Pets monToutou) {
		monstre.setPV(monstre.getPV() - monToutou.getPetDamage() * monToutou.getPetNumber());
		if(monToutou.petNumber != 0) {
			monstre.die(monstre,this);
		}
		setChanged();
        notifyObservers();
	}

	public void attack(Monster monstre,Hero heroGame, Artefact artf) {
		if(heroGame.getCheckClassBerzerker() == 1) {
			double randomBerzerker = (Math.random() *100) % 5;
			if((int) randomBerzerker == 1) {
				System.out.println("CRITIQUE !");
				monstre.setPV(monstre.getPV() - (heroGame.getDamage() * 2));
				monstre.die(monstre,this);
				this.nbrClic ++;
			}
			else {
				monstre.setPV(monstre.getPV() - heroGame.getDamage());
				monstre.die(monstre,this);
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
		applyArtefacts(myArtf, myPet, heroGame, monstre);
		setChanged();
        notifyObservers();
	}
	
	void heroChoice() {
		System.out.println("Vous avez débloqué 3 nouveau héros, choisissez-en un :");
		System.out.println("archer / mage / berzerker");
	}
	
	public String getHeroChoice() {
		if(myMonster.getWaveNumber() >= 2 && myHero.getCheckClassArcher() == 0 && myHero.getCheckClassMage() == 0 && myHero.getCheckClassBerzerker() == 0) {
			String choixHerosOk = "archer / mage / berzerker";
			return choixHerosOk;
		}
		else {
			String choixHerosDenied = "bloqué";
			return choixHerosDenied;
		}
	}
	
	public void archerChoice(Hero heroGame) {
		heroGame.setCheckClassArcher(1);
		System.out.println("Vous avez choisi la classe archer. Vos familiers doublent leur vitesse d'attaque.");
	}
	
	public void mageChoice(Hero heroGame) {
		heroGame.setCheckClassMage(1);
		myMonster.setTempsBoss(25);
		System.out.println("Vous avez choisi la classe mage. Vous gagnez 5 secondes suppémentaire pour vaincre chaque boss.");
	}
	
	public void berzerkerChoice(Hero heroGame) {
		heroGame.setCheckClassBerzerker(1);
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
	
	public void ajouterClasses() {
		ImageIcon ArcherIcon = new ImageIcon(game.class.getResource("/images/héro-4.png.png"));	
		ImageIcon MageIcon = new ImageIcon(game.class.getResource("/images/héro-3.png.png"));	
		ImageIcon BerzerkerIcon = new ImageIcon(game.class.getResource("/images/héro-2.png.png"));	
		
		buttonArcher.setBackground(Color.white);
		buttonArcher.setFocusPainted(false);
		buttonArcher.setBorder(null);
		buttonArcher.setIcon(ArcherIcon);
		buttonArcher.addActionListener(UP); //ENCORE A CHANGER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		buttonMage.setBackground(Color.white);
		buttonMage.setFocusPainted(false);
		buttonMage.setBorder(null);
		buttonMage.setIcon(MageIcon);
		buttonMage.addActionListener(UP); //ENCORE A CHANGER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		buttonBerzerker.setBackground(Color.white);
		buttonBerzerker.setFocusPainted(false);
		buttonBerzerker.setBorder(null);
		buttonBerzerker.setIcon(BerzerkerIcon);
		buttonBerzerker.addActionListener(UP); //ENCORE A CHANGER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		choiceClass.add(buttonArcher);
		choiceClass.add(buttonMage);
		choiceClass.add(buttonBerzerker);
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
		
		ImageIcon slimeBleu = new ImageIcon(game.class.getResource("/images/slime_bleu.png")); //creation d'une image en tant que ic�ne.
		
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
		return upgradeMonyeValue;
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

	public class choixArcher implements ActionListener{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + gold );
	              degatLabel.setText("degats actuels :" + myHero.getDamage());
	              coutUPLabel.setText("cout : " + upgradeMoneyValue);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		game myGame = new game();
		myGame.genererUI();
		
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


