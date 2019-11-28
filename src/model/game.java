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

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game {
	int nbrClic = 0;
	static int gold = 0;
	int upgradeValue = 10;
	int upgradecroissance = 2;
	Pets myPets = new Pets();
	Artefact myArtf = new Artefact();
	Monster myMonster = new Monster();
	Archer myArcher = new Archer();
	Hero myHero = new Hero();
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore impl�menter (degats par seconde en int. graph.
	clic actionClic = new clic();		//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	upgrade UP = new upgrade();			//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel();
	private Timer timerPets = new Timer();
	
	void attack(Monster monstre,Hero heroGame, Artefact artf) {
		artf.applyArtefacts(null, monstre, heroGame, this);
		monstre.PV -= heroGame.damage;
		monstre.die(myMonster,this);
		this.nbrClic ++;

	}
	
	void attackPets(Monster monstre, Pets monToutou, Artefact artf) {
		artf.applyArtefacts(monToutou, monstre, null, this);
		monstre.PV -= monToutou.petDamages * monToutou.petNumber;
		monstre.die(myMonster,this);
		
	}
	
	void upgrade(Hero heroGame) {
		if (gold >= upgradeValue) {
			heroGame.damage ++ ;
			gold -= upgradeValue;
			upgradeValue += upgradecroissance ;
			upgradecroissance  += 2 ;
			System.out.println("vous avez ameliore vos degats");
			System.out.println("Vous infligez maintenant : " + heroGame.damage + " degats");
		}
	}
	
	void reborn(Monster monstre,Hero heroGame) {
		heroGame.damage = 1;
		monstre.goldIncrease = 6;
		monstre.PV = 10;
		monstre.pvIncrease = 10;
		monstre.goldIncrease = 6;
		monstre.Number = 1;
		monstre.waveNumber = 1;
	}
	
	public void timerPets() {
		game myGame = new game();
		Pets myPets = new Pets();
		timerPets = new Timer();
		PetsDamages aille = myGame.new PetsDamages();
		this.timerPets.schedule(aille, 0, myPets.petsAttackSpeed);
	}
	
	void heroChoice() {
		
	}
	
	public void archerChoice() {
		myPets.petsAttackSpeed = myArcher.petsAttackSpeed;
		this.timerPets.cancel();
	}
	
	void mageChoice() {
		
	}
	
	void berzerkerChoice() {
		
	}
	public void genererUI() { //commande generant l'inteface ainsi que les bouttons
		 
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
		PVLabel.setText("monstre PV : " + myMonster.PV);
		compteur.add(PVLabel);
		
		argentLabel.setForeground(Color.black );
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + gold );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("degats actuels :" + myHero.damage);
		compteur.add(degatLabel);
		
		coutUPLabel.setForeground(Color.black);
		coutUPLabel.setFont(stats);
		coutUPLabel.setText("cout : " + upgradeValue);
		ensembleBoutton.add(coutUPLabel);
		
		
		window.setVisible(true);
	}
	
	public class clic implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
				attack(myMonster,myHero,myArtf);
				PVLabel.setText("monstre PV : " + myMonster.PV);
				argentLabel.setText("argent : " + gold );
			}
	 }
	
	public class upgrade implements ActionListener{
       public void actionPerformed(ActionEvent event) {
              upgrade(myHero);
              argentLabel.setText("argent : " + gold );
              degatLabel.setText("degats actuels :" + myHero.damage);
              coutUPLabel.setText("cout : " + upgradeValue);
          }
	}
	
	public class choixArcher implements ActionListener{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + gold );
	              degatLabel.setText("degats actuels :" + myHero.damage);
	              coutUPLabel.setText("cout : " + upgradeValue);
	       }
	}
	
	public class PetsDamages extends TimerTask {
	    public void run() {
	    	attackPets(myMonster, myPets, myArtf);
			PVLabel.setText("monstre PV : " + myMonster.PV);
			argentLabel.setText("argent : " + gold );
	    }
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Monster myMonster = new Monster();

		if(myMonster.waveNumber == 2) {
			System.out.println("Choix du heros");
		}
		game myGame = new game();
		myGame.genererUI();		
		myGame.heroChoice();
	}
}
