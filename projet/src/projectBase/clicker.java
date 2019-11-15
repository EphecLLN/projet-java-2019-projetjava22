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
	 static int degats = 10;
	 String equipement = "epee";
	 static double monstrePV = 10;
	 static double monstreValue = 10;
	 static double pourcent = 0.01;
	 static int gold = 0;
	 static int upgradeValue = 10;
	 static int nbrMonstre= 1;
	 static int nbrBoss = 10;
	 static int implementation = 30;
	 static int goldValue = 6;
	 static int nbrPet = 1;
	 static int petValue = 20;
	 static int petDmg = 1;
	 static double petPourcent = 0.01;
	 static int croissanceUpgrade = 2;
	 static JLabel PVLabel = new JLabel(); // pv en int. graph.
	 static JLabel argentLabel = new JLabel(); // pas encore implï¿½menter (dï¿½gï¿½ts par seconde en int. graph.
	 static clic actionClic = new clic();
	 static upgrade UP = new upgrade();
	 static JLabel degatLabel = new JLabel();
	 static JLabel coutUPLabel = new JLabel();
	 
	/**
	 * @author lulu 
	 * commande permettant de crï¿½er l'interface graphique
	 */
	public static void genererUI() { //commande gï¿½nï¿½rant l'inteface ainsi que les bouttons
		 
		JFrame window = new JFrame();
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 400, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);
		
		ImageIcon slimeBleu = new ImageIcon(clicker.class.getResource("/images/slime_bleu.png")); //crï¿½ation d'une image en tant que icï¿½ne.
		
		JButton bouttonMonstre = new JButton();
		bouttonMonstre.setBackground(Color.white);
		bouttonMonstre.setFocusPainted(false);
		bouttonMonstre.setBorder(null);
		bouttonMonstre.setIcon(slimeBleu); // attribution de l'icï¿½ne au boutton (faire ressembler a un monstre).
		bouttonMonstre.addActionListener(actionClic); // activation de clic() en int.graph.
		monstrePanel.add(bouttonMonstre);
		
		JPanel ensembleBoutton = new JPanel();
		ensembleBoutton.setLayout(new GridLayout(6,1));
		ensembleBoutton.setBounds(600,100,150,400);
		ensembleBoutton.setBackground(Color.white);
		window.add(ensembleBoutton);
		
		ImageIcon UPIcon = new ImageIcon(clicker.class.getResource("/images/anim up.gif"));
		
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
		PVLabel.setText("PV : " + monstrePV);
		compteur.add(PVLabel);
		
		argentLabel.setForeground(Color.black );
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + gold );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("dï¿½gï¿½ts actuels :" + degats*( pourcent * 100));
		compteur.add(degatLabel);
		
		coutUPLabel.setForeground(Color.black);
		coutUPLabel.setFont(stats);
		coutUPLabel.setText("coï¿½t : " + upgradeValue);
		ensembleBoutton.add(coutUPLabel);
		
		
		window.setVisible(true);
	}

	public static void kill() { //commande vï¿½rifiant le nbr de monstre tuï¿½s et rï¿½initialisant les monstres selon si c'est un boss ou normal
		if (monstrePV <= 0 && nbrMonstre == nbrBoss) {
			gold += gold / 5 ;
			goldValue += goldValue;
			monstreValue += monstreValue;
			nbrMonstre = 1;
			monstrePV = monstreValue;
			System.out.println("le monstre est mort");
			System.out.println("vous possï¿½dez :" + gold);
		}
		if (monstrePV <= 0 && nbrMonstre == (nbrBoss -1)) {
			nbrMonstre++;
			System.out.println("le monstre est mort");
			preparerBoss();
			System.out.println("vous possï¿½dez :" + gold);
		}
		
		if (monstrePV <= 0) {
			monstrePV = monstreValue;
			nbrMonstre ++;
			System.out.println("le monstre est mort");
			System.out.println("vous possï¿½dez :" + gold);
		}
	}
	
	public static void goldDrop() { //vï¿½rification permettant de donner l'argent
		if (monstrePV <= 0) {
			gold += goldValue;
		}
	}
	
	public static void clic() { //commande dï¿½clancher lors d'un clic sur le monstre pour infliger des dï¿½gï¿½ts et vï¿½rfifer kill et goldDrop
		monstrePV = monstrePV - (degats*( pourcent * 100));
		goldDrop();
		kill();
	}
	
	public static class MinuteurBoss extends TimerTask {
	    public void run() {
	    	if (implementation >= 0) {
	    		System.out.println("Temps restant pour vaincre le monstre : " + implementation);
	    		implementation --;
	    	}
	    }
	}
	
	public static void preparerBoss() {
		monstrePV = monstreValue * 3;
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
	
	public static void upgrade() { // commande pour amï¿½liorer les dï¿½gï¿½ts
		if (gold >= upgradeValue) {
			pourcent += 0.01;
			gold -= upgradeValue;
			upgradeValue += croissanceUpgrade;
			croissanceUpgrade += 2 ;
			System.out.println("vous avez ameliore vos degats");
			System.out.println("Vous infligez maintenant : " + degats*( pourcent * 100) + " degats");
		}
	}
	
	public static void reborn() {
		degats = 10;
		String equipement = "epee";
		monstrePV = 10;
		monstreValue = 10;
		pourcent = 0.01;
		gold = 0;
		upgradeValue = 10;
		implementation = 30;
		goldValue = 6;
		nbrPet = 0;
		petValue = 20;
		petDmg = 1;
		petPourcent = 0.01;
		croissanceUpgrade = 2;
	}
	
	public static class clic implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
				clic();
				PVLabel.setText("monstre PV : " + monstrePV);
				argentLabel.setText("argent : " + gold );
			}
	 }
	
	public static class upgrade implements ActionListener{
        public void actionPerformed(ActionEvent event) {
               upgrade();
               argentLabel.setText("argent : " + gold );
               degatLabel.setText("dégâts actuels :" + degats*( pourcent * 100));
               coutUPLabel.setText("coût : " + upgradeValue);
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
		MinuteurBoss tempsBoss = new MinuteurBoss();
		timer.schedule(aille, 0, 1000);
		timer.schedule(tempsBoss, 0, 1000);
	}

}
