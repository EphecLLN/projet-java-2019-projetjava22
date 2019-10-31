package projectBase;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	 String equipement = "�p�e";
	 static double monstrePV = 10;
	 static double monstreValue = 10;
	 static double pourcent = 0.01;
	 static int gold = 0;
	 static int upgradeValue = 10;
	 static int nbrMonstre= 0;
	 static int nbrBoss = 10;
	 static int goldValue = 6;
	 static int petDmg = 0;
	 static int petValue = 20;
	 static int croissanceUpgrade = 2;
	 static JLabel PVLabel = new JLabel(); // pv en int. graph.
	 static JLabel parSec = new JLabel(); // pas encore impl�menter (d�g�ts par seconde en int. graph.
	 static action actionClic = new action();
	 
	/**
	 * @author lulu 
	 * commande permettant de cr�er l'interface graphique
	 */
	public static void genererUI() { //commande g�n�rant l'inteface ainsi que les bouttons
		 
		JFrame window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 220, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);
		
		ImageIcon slimeBleu = new ImageIcon(clicker.class.getResource("/images/slime_bleu.png")); //cr�ation d'une image en tant que ic�ne.
		
		JButton bouttonMonstre = new JButton();
		bouttonMonstre.setBackground(Color.white);
		bouttonMonstre.setFocusPainted(false);
		bouttonMonstre.setBorder(null);
		bouttonMonstre.setIcon(slimeBleu); // attribution de l'ic�ne au boutton (faire ressembler a un monstre).
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

	public static void kill() { //commande v�rifiant le nbr de monstre tu�s et r�initialisant les monstres selon si c'est un boss ou normal
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
	
	public static void goldDrop() { //v�rification permettant de donner l'argent
		if (monstrePV <= 0) {
			gold += goldValue;
		}
	}
	
	public static void clic() { //commande d�clancher lors d'un clic sur le monstre pour infliger des d�g�ts et v�rfifer kill et goldDrop
		monstrePV = monstrePV - (degats*( pourcent * 100));
		goldDrop();
		kill();
	}
	
	public void pet() { //cr�ation de familier (pas encore fini)
		if (gold >= petValue && petDmg == 0) {
			petDmg += 1;
		}
		if (gold >= petValue) {
			petDmg += petDmg;
		}
	}
	
	public void upgrade() { // commande pour am�liorer les d�g�ts
		if (gold >= upgradeValue) {
			pourcent += 0.01;
			gold -= upgradeValue;
			upgradeValue += croissanceUpgrade;
			croissanceUpgrade += 2 ;
			System.out.println("vous avez am�lior� vos d�gats");
			System.out.println("Vous infligez maintenant : " + degats*( pourcent * 100) + " d�gats");
		}
	}
	
	public static class action implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
				clic();
				PVLabel.setText("monstre PV : " + monstrePV);
				
			}
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genererUI();
	}

}
