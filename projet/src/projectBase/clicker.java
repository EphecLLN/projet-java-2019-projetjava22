package projectBase;

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
 * @author lulu 
 *
 */
public class clicker {
	 static int degats = 1;
	 String equipement = "epee";
	 static double monstrePV = 10;
	 static double monstreValue = 10;
	 static double pourcent = 0.01;
	 static int gold = 0;
	 static int upgradeValue = 10;
	 static int nbrMonstre= 0;
	 static int nbrBoss = 10;
	 static int goldValue = 6;
	 static int nbrPet = 1;
	 static int petValue = 20;
	 static int petDmg = 1;
	 static double petPourcent = 0.01;
	 static int croissanceUpgrade = 2;
	 static JLabel PVLabel = new JLabel(); // pv en int. graph.
	 static JLabel parSec = new JLabel(); // pas encore implémenter (dégâts par seconde en int. graph.)
	 static action actionClic = new action();
	 
	/**
	 * @author lulu 
	 * commande permettant de créer l'interface graphique
	 */
	public static void genererUI() { //commande générant l'inteface ainsi que les bouttons
		 
		JFrame window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 220, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);
		
		ImageIcon slimeBleu = new ImageIcon(clicker.class.getResource("/images/slime_bleu.png")); //création d'une image en tant que icône.
		
		JButton bouttonMonstre = new JButton();
		bouttonMonstre.setBackground(Color.white);
		bouttonMonstre.setFocusPainted(false);
		bouttonMonstre.setBorder(null);
		bouttonMonstre.setIcon(slimeBleu); // attribution de l'icône au boutton (faire ressembler a un monstre).
		bouttonMonstre.addActionListener(actionClic); // activation de clic() en int.graph.
		monstrePanel.add(bouttonMonstre);
		
		JPanel compteur = new JPanel();
		compteur.setBounds(100,100,200,100);
		compteur.setBackground(Color.white);
		compteur.setLayout(new GridLayout(2,1));
		window.add(compteur);
		
		PVLabel.setForeground(Color.black );
		Font PVEcriture = new Font("Comic Sans MS", Font.PLAIN, 24 );
		PVLabel.setFont(PVEcriture);
		compteur.add(PVLabel);
		
		parSec.setForeground(Color.white );
		Font parSecEcriture = new Font("Comic Sans MS", Font.PLAIN, 5);
		parSec.setFont(parSecEcriture);
		compteur.add(parSec);
		
		
		
		window.setVisible(true);
	}

	public static void kill() { //commande vérifiant le nbr de monstre tués et réinitialisant les monstres selon si c'est un boss ou normal
		if (monstrePV <= 0 && nbrMonstre == nbrBoss) {
			goldValue += goldValue;
			monstreValue += monstreValue;
			nbrMonstre = 0;
			monstrePV = monstreValue;
			System.out.println("le monstre est mort");
		}
		if (monstrePV <= 0 && nbrMonstre == (nbrBoss -1)) {
			monstrePV = monstreValue * 3;
			nbrMonstre++;
			System.out.println("le monstre est mort");
		}
		
		if (monstrePV <= 0) {
			monstrePV = monstreValue;
			nbrMonstre ++;
			System.out.println("le monstre est mort");
		}
	}
	
	public static void goldDrop() { //vérification permettant de donner l'argent
		if (monstrePV <= 0) {
			gold += goldValue;
		}
	}
	
	public static void clic() { //commande déclancher lors d'un clic sur le monstre pour infliger des dégâts et vérfifer kill et goldDrop
		monstrePV = monstrePV - (degats*( pourcent * 100));
		goldDrop();
		kill();
	}
	
	public static void buyPet() {
		if (gold >= petValue) {
			nbrPet ++;
			gold = gold - petValue;
			petValue += petValue;
			System.out.println("Vous avez acqueri un nouveau familier.");
		}
		else {
			System.out.println("Vous n'avez pas assez d'argent pour acheter une familier supplementaire. Il vous manque " + ((petValue + 1) - gold) + " gold pour en acquerir un.");
		}
	}
	public void upgrade() { // commande pour améliorer les dégâts
		if (gold >= upgradeValue) {
			pourcent += 0.01;
			gold -= upgradeValue;
			upgradeValue += croissanceUpgrade;
			croissanceUpgrade += 2 ;
			System.out.println("vous avez ameliore vos degats");
			System.out.println("Vous infligez maintenant : " + degats*( pourcent * 100) + " degats");
		}
	}
	
	public static class action implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
				clic();
				PVLabel.setText("monstre PV : " + monstrePV);
				
			}
	 }
	
	public static class PetsDamages extends TimerTask {
	    public void run() {
	    	monstrePV = monstrePV - ((petDmg*( petPourcent * 100))*nbrPet);
			goldDrop();
			kill();
			PVLabel.setText("monstre PV : " + monstrePV);
			System.out.println("Aille");
	    }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genererUI();
		
		//Generation des clics reguliers de la part des familiers
		Timer timer = new Timer();
		PetsDamages aille = new PetsDamages();
		timer.schedule(aille, 0, 1000);
	}

}
