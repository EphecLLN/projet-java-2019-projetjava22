package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Contrôleur.gameController;
import model.Hero;
import model.Monster;
import model.Pets;
import model.game;

public class GUI extends gameVue implements Observer{
	public GUI(game model, gameController controller) {
		super(model, controller);
		// TODO Auto-generated constructor stub
	}

	public static JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	public static JLabel argentLabel = new JLabel(); 			// pas encore implementer (degats par seconde en int. graph.
	public static JButton buttonUP = new JButton();		
	public static JButton buttonMonster = new JButton();
	public static JButton buttonReborn = new JButton();
	public static JButton buttonUPPets = new JButton();
	public static JButton archerChoice = new JButton();
	public static JLabel degatLabel = new JLabel();
	public static JLabel petUPLabel = new JLabel(); 
	public static JLabel dmgUPLabel = new JLabel();
	
	public void genererUI(Monster monstre,Hero hero,Pets pet,game myGame) { //commande g�n�rant l'inteface ainsi que les bouttons
		 
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
		
		
		buttonMonster.setBackground(Color.white);
		buttonMonster.setFocusPainted(false);
		buttonMonster.setBorder(null);
		buttonMonster.setIcon(slimeBleu); // attribution de l'ic�ne au boutton (faire ressembler a un monstre).
											// activation de clic() en int.graph.
		monstrePanel.add(buttonMonster);
		
		JPanel ensembleBoutton = new JPanel();
		ensembleBoutton.setLayout(new GridLayout(5,1));
		ensembleBoutton.setBounds(600,100,150,400);
		ensembleBoutton.setBackground(Color.white);
		window.add(ensembleBoutton);
		
		JPanel choiceClass = new JPanel();
        choiceClass.setLayout(new GridLayout(2,3));
        choiceClass.setBounds(600,550,300,200);
        choiceClass.setBackground(Color.black);
        window.add(choiceClass);
		
		ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		
		buttonUP.setBackground(Color.white);
		buttonUP.setFocusPainted(false);
		buttonUP.setBorder(null);
		buttonUP.setIcon(UPIcon);
		ensembleBoutton.add(buttonUP);
		
		JPanel compteur = new JPanel();
		compteur.setBounds(100,100,400,200);
		compteur.setBackground(Color.white);
		compteur.setLayout(new GridLayout(4,1));
		window.add(compteur);
		
		PVLabel.setForeground(Color.black );
		Font PVEcriture = new Font("Comic Sans MS", Font.PLAIN, 24 );
		PVLabel.setFont(PVEcriture);
		PVLabel.setText("PV : " + monstre.getPV());
		compteur.add(PVLabel);
		
		argentLabel.setForeground(Color.black );
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + myGame.getGold() );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("degats actuels :" + hero.getDamage());
		compteur.add(degatLabel);
		
		dmgUPLabel.setForeground(Color.black);
		dmgUPLabel.setFont(stats);
		dmgUPLabel.setText("cout : " + myGame.getUpgradeValue());
		ensembleBoutton.add(dmgUPLabel);
		
		ImageIcon UPPixie = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));
		
		buttonUPPets.setBackground(Color.white);
		buttonUPPets.setFocusPainted(false);
		buttonUPPets.setBorder(null);
		buttonUPPets.setIcon(UPPixie);
		ensembleBoutton.add(buttonUPPets);
				
		petUPLabel.setForeground(Color.black);
		petUPLabel.setFont(stats);
		petUPLabel.setText("co�t : " + pet.getPetCostBuy());
		ensembleBoutton.add(petUPLabel);
		
		ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));
		
		buttonReborn.setBackground(Color.white);
		buttonReborn.setFocusPainted(false);
		buttonReborn.setBorder(null);
		buttonReborn.setIcon(reset);
		ensembleBoutton.add(buttonReborn);
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
	}

	@Override
	public void update(Observable o, Object arg) {
		degatLabel.setText("degats actuels :" + model.myHero.getDamage());
		dmgUPLabel.setText("cout : " + model.getUpgradeValue());
		PVLabel.setText("PV : " + model.myMonster.getPV());
		argentLabel.setText("argent : " + model.getGold() );
		petUPLabel.setText("cout : " + model.myPets.getPetCostBuy());
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte");
		
	}

	@Override
	public void disableWarning() {
		// TODO Auto-generated method stub
		
	}

}



