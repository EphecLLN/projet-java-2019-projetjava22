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

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game {
	int nbrClic = 0;
	public static int gold = 0;
	private int upgradeValue = 10;
	int upgradecroissance = 2;
	public Archer myArcher = new Archer();
	public Mage myMage = new Mage();
	public Berzerker myBerzerker = new Berzerker();
	public Artefact myArtf = new Artefact();
	public Monster myMonster = new Monster();
	public Hero myHero = new Hero();
	public Pets myPets = new Pets();
	Console myConsole = new Console();
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore implementer (degats par seconde en int. graph.
	clic actionClic = new clic();		//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	upgrade UP = new upgrade();			//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel();
	
	void attackPets(Monster monstre, Pets monToutou) {
		game myGame = new game();
		monstre.setPV(monstre.getPV() - myGame.myPets.getPetDamage() * myGame.myPets.getPetNumber());
		if(Pets.petNumber != 0) {
			monstre.die(myMonster,this);
		}
	}

	public void attack(Monster monstre,Hero heroGame, Artefact artf) {
		if(heroGame.getCheckClassBerzerker() == 1) {
			double randomBerzerker = (Math.random() *100) % 5;
			if((int) randomBerzerker == 1) {
				System.out.println("CRITIQUE !");
				monstre.setPV(monstre.getPV() - (heroGame.getDamage() * 2));
				monstre.die(myMonster,this);
				this.nbrClic ++;
				this.applyArtefacts();
			}
			else {
				monstre.setPV(monstre.getPV() - heroGame.getDamage());
				monstre.die(myMonster,this);
				this.nbrClic ++;
				this.applyArtefacts();
			}
		}
		else {
			monstre.setPV(monstre.getPV() - heroGame.getDamage());
			monstre.die(myMonster,this);
			this.nbrClic ++;
			this.applyArtefacts();
		}
	}
	public void upgrade(Hero heroGame) {
		if (gold >= getUpgradeValue()) {
			heroGame.constDamage ++;
			heroGame.setDamage(heroGame.constDamage);
			gold = gold - getUpgradeValue();
			setUpgradeValue(getUpgradeValue() + upgradecroissance) ;
			upgradecroissance  += 2 ;
			this.applyArtefacts();
		}
	}
	
	public void reborn(Monster monstre,Hero heroGame) {
		heroGame.setDamage(1);
		game.gold = 0;
		heroGame.setCheckClassArcher(0);;
		heroGame.setCheckClassBerzerker(0);
		heroGame.setCheckClassMage(0);
		monstre.setTempsBoss(20);
		monstre.setGoldIncrease(6);
		monstre.setPV(10);
		monstre.setPvIncrease(10);
		monstre.setGoldIncrease(6);
		monstre.setNumber(1);
		monstre.setWaveNumber(1);
		this.applyArtefacts();
	}
	
	void heroChoice() {
		System.out.println("Vous avez débloqué 3 nouveau héros, choisissez-en un :");
		System.out.println("archer / mage / berzerker");
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

	public void applyArtefacts() {
		for (int i = 0; i<this.myArtf.currentArtefacts.length ; i++) {
			if (this.myArtf.currentArtefacts[i].contentEquals("doublePet")) {
				Pets.petNumber = (Pets.petNumber * 2);
				Pets.petBuyIncrease = 2;
				this.myHero.setDamage(this.myHero.constDamage);
			}
			if (this.myArtf.currentArtefacts[i].contentEquals("doubleDMG") ) {
				this.myHero.setDamage(this.myHero.constDamage * 2);
			}
			if (this.myArtf.currentArtefacts[i].contentEquals("+5DMG")) {
				this.myHero.setDamage(this.myHero.constDamage + 5);
			}
			if (this.myArtf.currentArtefacts[i].contentEquals("-1Boss")) {
				this.myMonster.setbossNumber(9);
				this.myHero.setDamage(this.myHero.constDamage);
			}
			if (this.myArtf.currentArtefacts[i].contentEquals("every10Hit")) {
				if(this.nbrClic % 10 == 0) {
					this.myHero.setDamage(this.myHero.constDamage + this.myHero.constDamage * 5);
				}
				 if(this.nbrClic % 10 == 1) {
					 this.myHero.setDamage(this.myHero.constDamage - this.myHero.constDamage / 5);
				}
			}
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
		return upgradeValue;
	}
	
	public int getGold() {
		return gold;
	}
	public void setUpgradeValue(int upgradeValue) {
		this.upgradeValue = upgradeValue;
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
	
	public class choixArcher implements ActionListener{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + gold );
	              degatLabel.setText("degats actuels :" + myHero.getDamage());
	              coutUPLabel.setText("cout : " + upgradeValue);
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
				reborn(myMonster, myHero);
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
		
		myGame.myHero.buyArtefact(myGame.myArtf);
		myGame.applyArtefacts();
		myGame.genererUI();
		myGame.myConsole.Scan(myGame);
	}
}
