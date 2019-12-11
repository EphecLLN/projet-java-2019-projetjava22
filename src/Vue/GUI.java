package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
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

public class GUI extends gameVue implements Observer, ActionListener{
	public GUI(game model, gameController controller) {
		super(model, controller);
		// TODO Auto-generated constructor stub
	}

	public JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	public JLabel argentLabel = new JLabel(); 			// pas encore implementer (degats par seconde en int. graph.
	public JButton buttonUP = new JButton();		
	public JButton buttonMonster = new JButton();
	public JButton buttonReborn = new JButton();
	public JButton buttonUPPets = new JButton();
	public JButton buttonArtf = new JButton();
	public JButton archerChoice = new JButton();
	public JButton buttonArcher = new JButton();
	public JButton buttonMage = new JButton();
	public JButton buttonBerzerker = new JButton();
	public JLabel degatLabel = new JLabel();
	public JLabel petUPLabel = new JLabel(); 
	public JLabel dmgUPLabel = new JLabel();
	public JLabel artfMoney = new JLabel();
	public JLabel artfMoneyToGet = new JLabel();
	public JLabel artfMoneyCost = new JLabel();
	public JButton buttonHero = new JButton();
	public ImageIcon slimeBleu = new ImageIcon("./images/slime bleu.png"); //cr�ation d'une image en tant que ic�ne.
	public ImageIcon slimeVert = new ImageIcon("./images/slime vert.png");
	public ImageIcon slimeRouge = new ImageIcon("./images/slime rouge.png");
	public ImageIcon imageHero0 = new ImageIcon("./images/héro-1.png.png");
	public ImageIcon imageHero1 = new ImageIcon("./images/héro-2.png.png");
	public ImageIcon imageHero2 = new ImageIcon("./images/héro-3.png.png");
	public ImageIcon imageHero3 = new ImageIcon("./images/héro-4.png.png");

	public JFrame window = new JFrame("Clicker");
	public JPanel choiceClass = new JPanel();

	public void genererUI(Monster monstre,Hero hero,Pets pet,game myGame) { //commande g�n�rant l'inteface ainsi que les bouttons
			
		window.setSize(1200, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);

		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(500, 140, 200, 230);
		monstrePanel.setBackground(Color.white);
		window.add(monstrePanel);	
		
		JPanel heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayout(1,1));
		heroPanel.setBounds(500, 390, 200, 230);
		heroPanel.setBackground(Color.gray);
		window.add(heroPanel);
		
		buttonHero.setBackground(Color.white);
		buttonHero.setFocusPainted(false);
		buttonHero.setBorder(null);
		buttonHero.setIcon(imageHero1);
		
		heroPanel.add(buttonHero);
		
		buttonMonster.setBackground(Color.white);
		buttonMonster.setFocusPainted(false);
		buttonMonster.setBorder(null);
		buttonMonster.addActionListener(this);
		buttonMonster.setIcon(slimeBleu);
		
		monstrePanel.add(buttonMonster);
		
		
		//  BOUTONS D'AMELIORATION
		
		JPanel ensembleBoutton1 = new JPanel();
		ensembleBoutton1.setLayout(new GridLayout(2,3));
		ensembleBoutton1.setBounds(75,500,350,130);
		ensembleBoutton1.setBackground(Color.white);
		window.add(ensembleBoutton1);
		
		JPanel ensembleBoutton2 = new JPanel();
		ensembleBoutton2.setLayout(new GridLayout(1,6));
		ensembleBoutton2.setBounds(100,900,150,400);
		ensembleBoutton2.setBackground(Color.white);
		window.add(ensembleBoutton2);
		
		
        choiceClass.setLayout(new GridLayout(3,1));
        choiceClass.setBounds(900,100,200,450);
        choiceClass.setBackground(Color.gray);
        window.add(choiceClass);
        
		ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		buttonUP.setBackground(Color.white);
		buttonUP.setFocusPainted(false);
		buttonUP.setBorder(null);
		buttonUP.setIcon(UPIcon);
		buttonUP.addActionListener(this);
		ensembleBoutton1.add(buttonUP);
		
		ImageIcon UPPixie = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));
		
		buttonUPPets.setBackground(Color.white);
		buttonUPPets.setFocusPainted(false);
		buttonUPPets.setBorder(null);
		buttonUPPets.setIcon(UPPixie);
		buttonUPPets.addActionListener(this);
		ensembleBoutton1.add(buttonUPPets);
		
		ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));

		buttonReborn.setBackground(Color.white);
		buttonReborn.setFocusPainted(false);
		buttonReborn.setBorder(null);
		buttonReborn.setIcon(reset);
		buttonReborn.addActionListener(this);
		ensembleBoutton1.add(buttonReborn);
		
		dmgUPLabel.setForeground(Color.black);
		dmgUPLabel.setFont(stats);
		dmgUPLabel.setText("   Cout : " + myGame.getUpgradeMoneyValue());
		ensembleBoutton1.add(dmgUPLabel);
		
		petUPLabel.setForeground(Color.black);
		petUPLabel.setFont(stats);
		petUPLabel.setText("   Cout : " + pet.getPetCostBuy());
		ensembleBoutton1.add(petUPLabel);
		
		artfMoneyToGet.setForeground(Color.black);
		artfMoneyToGet.setFont(stats);
		artfMoneyToGet.setText("    gain : " + (monstre.getWaveNumber() + myGame.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		ensembleBoutton1.add(artfMoneyToGet);
	
		
		// COMPTEUR
		
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
		argentLabel.setFont(stats);
		argentLabel.setText("argent : " + myGame.getGold() );
		compteur.add(argentLabel);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("degats actuels :" + hero.getDamage());
		compteur.add(degatLabel);
		
		artfMoney.setForeground(Color.black);
		artfMoney.setFont(stats);
		artfMoney.setText("monnaie artefacts actuels :" + hero.getArtefactMoney());
		compteur.add(artfMoney);
		
		
		buttonArtf.setBackground(Color.gray);
		buttonArtf.setFocusPainted(false);
		buttonArtf.addActionListener(this);
		ensembleBoutton2.add(buttonArtf);
		
		artfMoneyCost.setForeground(Color.black);
		artfMoneyCost.setFont(stats);
		artfMoneyCost.setText("cout : " + hero.getArtefactCost());
		ensembleBoutton2.add(artfMoneyCost);
		
		screenMonster(this, monstre);
		ajouterClasses(this, monstre);
		changerAttaqueHero(myGame, this);
		
		window.setVisible(true);
	}
	
	public void changerAttaqueHero(game game, GUI myGUI) {
		if(game.getImageHero()%4 == 0) {
			myGUI.buttonHero.setIcon(myGUI.imageHero0);
		}
		if(game.getImageHero()%4 == 1) {
			myGUI.buttonHero.setIcon(myGUI.imageHero1);
		}
		if(game.getImageHero()%4 == 2) {
			myGUI.buttonHero.setIcon(myGUI.imageHero2);
		}
		if(game.getImageHero()%4 == 3) {
			myGUI.buttonHero.setIcon(myGUI.imageHero3);
		}
	}
	
	
	public void screenMonster(GUI myGUI, Monster myMonster) {
		if (myMonster.getAttribute() == "aqua") {
			myGUI.buttonMonster.setIcon(myGUI.slimeBleu);
		}
		if (myMonster.getAttribute() == "tera") {
			myGUI.buttonMonster.setIcon(myGUI.slimeVert);
		}
		if (myMonster.getAttribute() == "pyro") {
			myGUI.buttonMonster.setIcon(myGUI.slimeRouge);
		}
	}
	
	public void ajouterClasses(GUI myGUI, Monster monstre) {
		if(monstre.getWaveNumber() == 2) {
		
			ImageIcon ArcherIcon = new ImageIcon(game.class.getResource("/images/héro-4.png.png"));	
			ImageIcon MageIcon = new ImageIcon(game.class.getResource("/images/héro-3.png.png"));	
			ImageIcon BerzerkerIcon = new ImageIcon(game.class.getResource("/images/héro-2.png.png"));
		
			myGUI.buttonArcher.setBackground(Color.white);
			myGUI.buttonArcher.setFocusPainted(false);
			myGUI.buttonArcher.setBorder(null);
			myGUI.buttonArcher.setIcon(ArcherIcon);
			myGUI.buttonArcher.addActionListener(this);
		
			myGUI.buttonMage.setBackground(Color.white);
			myGUI.buttonMage.setFocusPainted(false);
			myGUI.buttonMage.setBorder(null);
			myGUI.buttonMage.setIcon(MageIcon);
			myGUI.buttonMage.addActionListener(this);
		
			myGUI.buttonBerzerker.setBackground(Color.white);
			myGUI.buttonBerzerker.setFocusPainted(false);
			myGUI.buttonBerzerker.setBorder(null);
			myGUI.buttonBerzerker.setIcon(BerzerkerIcon);
			myGUI.buttonBerzerker.addActionListener(this);
		
		
			myGUI.choiceClass.add(buttonArcher);
			myGUI.choiceClass.add(buttonMage);
			myGUI.choiceClass.add(buttonBerzerker);
		}
	}	
	
	public static void main(String[] args) {
	}

	@Override
	public void update(Observable o, Object arg) {
		degatLabel.setText("degats actuels :" + model.myHero.getDamage());
		dmgUPLabel.setText("   Cout : " + model.getUpgradeMoneyValue());
		PVLabel.setText("PV : " + model.myMonster.getPV());
		artfMoney.setText("monnaie artefacts actuels :" + model.myHero.getArtefactMoney());
		argentLabel.setText("argent : " + model.getGold() );
		petUPLabel.setText("   Cout : " + model.myPets.getPetCostBuy());
		artfMoneyToGet.setText("    gain : " + (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
	}

	@Override
	public void enableWarning() {
		System.out.println("Vous ne pouvez plus acheter d'artefacts.");	
	}

	@Override
	public void disableWarning() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object x = e.getSource();
		if (x == buttonMonster) {
			controller.attack();
			screenMonster(this, model.myMonster);
			ajouterClasses(this, model.myMonster);
			changerAttaqueHero(model, this);
		}
		if (x == buttonUP) {
			controller.upgrade();
		}
		if (x == buttonUPPets) {
			controller.oneMorePet();
		}
		if (x == buttonReborn) {
			controller.reset();
		}
		if (x == buttonArcher) {
			controller.classChoice(1);
		}
		if (x == buttonMage) {
			controller.classChoice(2);
		}
		if (x == buttonBerzerker) {
			controller.classChoice(3);
		}
		if (x == buttonArtf) {
			controller.oneMoreArtf();
		}
	}

}



