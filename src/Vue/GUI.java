package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Hero;
import model.Monster;
import model.Pets;
import model.game;
import model.game.clic;
import model.game.upgrade;

public class GUI {
	JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	JLabel argentLabel = new JLabel(); 	// pas encore impl�menter (d�g�ts par seconde en int. graph.
	clic actionClic = new clic();		//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	upgrade UP = new upgrade();			//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
	JLabel degatLabel = new JLabel();
	JLabel coutUPLabel = new JLabel(); 
	
	public void genererUI(Monster monstre,Hero hero,Pets pet,game game) { //commande g�n�rant l'inteface ainsi que les bouttons
		 
		JFrame window = new JFrame();
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 400, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);
		
		ImageIcon slimeBleu = new ImageIcon(game.class.getResource("/images/slime_bleu.png")); //cr�ation d'une image en tant que ic�ne.
		
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
		PVLabel.setText("PV : " + game.getMyMonster().getPV());
		compteur.add(PVLabel);
		
		argentLabel.setForeground(Color.black );
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + game.getGold() );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("d�g�ts actuels :" + hero.getDamage());
		compteur.add(degatLabel);
		
		coutUPLabel.setForeground(Color.black);
		coutUPLabel.setFont(stats);
		coutUPLabel.setText("co�t : " + game.getUpgradeValue());
		ensembleBoutton.add(coutUPLabel);
		
		
		window.setVisible(true);
	}
}
