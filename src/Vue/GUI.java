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

public class GUI extends gameVue implements Observer, ActionListener{
	public GUI(game model, gameController controller) {
		super(model, controller);
		// TODO Auto-generated constructor stub
	}

	public JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	public JLabel argentLabel = new JLabel(); 			// pas encore implementer (degats par seconde en int. graph.
	public JButton buttonUP = new JButton();		
	public static JButton buttonMonster = new JButton();
	public JButton buttonReborn = new JButton();
	public JButton buttonUPPets = new JButton();
	public JButton buttonArtf = new JButton();
	public JButton archerChoice = new JButton();
	public JButton berserkChoice = new JButton();
	public JButton mageChoice = new JButton();
	public JLabel degatLabel = new JLabel();
	public JLabel petUPLabel = new JLabel(); 
	public JLabel dmgUPLabel = new JLabel();
	public JLabel artfMoney = new JLabel();
	public JLabel artfMoneyToGet = new JLabel();
	public JLabel artfMoneyCost = new JLabel();
	public static ImageIcon slimeBleu = new ImageIcon("./images/slime bleu.png"); //cr�ation d'une image en tant que ic�ne.
	public static ImageIcon slimeVert = new ImageIcon("./images/slime vert.png");
	public static ImageIcon slimeRouge = new ImageIcon("./images/slime rouge.png");
	public JButton aqua = new JButton();
	public JButton tera = new JButton();
	public JButton pyro = new JButton();
	
	public void genererUI(Monster monstre,Hero hero,Pets pet,game myGame) { //commande g�n�rant l'inteface ainsi que les bouttons
			
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white );;
		window.setLayout(null);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(100, 400, 200, 200);
		monstrePanel.setBackground(Color.blue);
		window.add(monstrePanel);	
			
		buttonMonster.setBackground(Color.white);
		buttonMonster.setFocusPainted(false);
		buttonMonster.setBorder(null);
		buttonMonster.addActionListener(this);
		buttonMonster.setIcon(slimeBleu);
		
		monstrePanel.add(buttonMonster);
		
		JPanel ensembleBoutton1 = new JPanel();
		ensembleBoutton1.setLayout(new GridLayout(6,2));
		ensembleBoutton1.setBounds(600,100,400,400);
		ensembleBoutton1.setBackground(Color.white);
		window.add(ensembleBoutton1);
		
		JPanel attributePanel = new JPanel();
		attributePanel.setLayout(new GridLayout(1,3));
		attributePanel.setBounds(100,620,400,150);
		attributePanel.setBackground(Color.gray);
		window.add(attributePanel);	

		JPanel choiceClass = new JPanel();
        choiceClass.setLayout(new GridLayout(2,3));
        choiceClass.setBounds(600,550,300,200);
        choiceClass.setBackground(Color.gray);
        window.add(choiceClass);
        
		ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		buttonUP.setBackground(Color.white);
		buttonUP.setFocusPainted(false);
		buttonUP.setBorder(null);
		buttonUP.setIcon(UPIcon);
		buttonUP.addActionListener(this);
		ensembleBoutton1.add(buttonUP);
		
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
		
		artfMoney.setForeground(Color.black);
		artfMoney.setFont(stats);
		artfMoney.setText("monnaie artefacts actuels :" + hero.getArtefactMoney());
		compteur.add(artfMoney);
		
		dmgUPLabel.setForeground(Color.black);
		dmgUPLabel.setFont(stats);
		dmgUPLabel.setText("cout : " + myGame.getUpgradeMoneyValue());
		ensembleBoutton1.add(dmgUPLabel);
		
		ImageIcon UPPixie = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));
		
		buttonUPPets.setBackground(Color.white);
		buttonUPPets.setFocusPainted(false);
		buttonUPPets.setBorder(null);
		buttonUPPets.setIcon(UPPixie);
		buttonUPPets.addActionListener(this);
		ensembleBoutton1.add(buttonUPPets);
				
		petUPLabel.setForeground(Color.black);
		petUPLabel.setFont(stats);
		petUPLabel.setText("co�t : " + pet.getPetCostBuy());
		ensembleBoutton1.add(petUPLabel);
		
		ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));
		
		buttonReborn.setBackground(Color.white);
		buttonReborn.setFocusPainted(false);
		buttonReborn.setBorder(null);
		buttonReborn.setIcon(reset);
		buttonReborn.addActionListener(this);
		ensembleBoutton1.add(buttonReborn);
		
		artfMoneyToGet.setForeground(Color.black);
		artfMoneyToGet.setFont(stats);
		artfMoneyToGet.setText("gain : " + (monstre.getWaveNumber() + myGame.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		ensembleBoutton1.add(artfMoneyToGet);
		
		buttonArtf.setBackground(Color.gray);
		buttonArtf.setFocusPainted(false);
		buttonArtf.addActionListener(this);
		ensembleBoutton1.add(buttonArtf);
		
		artfMoneyCost.setForeground(Color.black);
		artfMoneyCost.setFont(stats);
		artfMoneyCost.setText("cout : " + hero.getArtefactCost());
		ensembleBoutton1.add(artfMoneyCost);
		
		ImageIcon aquaIcon = new ImageIcon(game.class.getResource("/images/aqua.png"));
		ImageIcon pyroIcon = new ImageIcon(game.class.getResource("/images/fire.png"));
		ImageIcon teraIcon = new ImageIcon(game.class.getResource("/images/plant.png"));
		
		aqua.setBackground(Color.white);
		pyro.setBackground(Color.white);
		tera.setBackground(Color.white);
		aqua.setFocusPainted(false);
		pyro.setFocusPainted(false);
		tera.setFocusPainted(false);
		aqua.addActionListener(this);
		pyro.addActionListener(this);
		tera.addActionListener(this);
		aqua.setIcon(aquaIcon);
		pyro.setIcon(pyroIcon);
		tera.setIcon(teraIcon);
		attributePanel.add(aqua);
		attributePanel.add(pyro);
		attributePanel.add(tera);
		
		ImageIcon archerIcon = new ImageIcon(game.class.getResource("/images/anim archer.gif"));
		ImageIcon berserkIcon = new ImageIcon(game.class.getResource("/images/anim berserk.gif"));
		ImageIcon mageIcon = new ImageIcon(game.class.getResource("/images/anim mage.gif"));
		
		archerChoice.setBackground(Color.gray);
		archerChoice.setFocusPainted(false);
		archerChoice.addActionListener(this);
		archerChoice.setIcon(archerIcon);
		choiceClass.add(archerChoice);
		
		berserkChoice.setBackground(Color.gray);
		berserkChoice.setFocusPainted(false);
		berserkChoice.addActionListener(this);
		berserkChoice.setIcon(berserkIcon);
		choiceClass.add(berserkChoice);
		
		mageChoice.setBackground(Color.gray);
		mageChoice.setFocusPainted(false);
		mageChoice.addActionListener(this);
		mageChoice.setIcon(mageIcon);
		choiceClass.add(mageChoice);
		
		window.setVisible(true);
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
		dmgUPLabel.setText("cout : " + model.getUpgradeMoneyValue());
		PVLabel.setText("PV : " + model.myMonster.getPV());
		artfMoney.setText("monnaie artefacts actuels :" + model.myHero.getArtefactMoney());
		argentLabel.setText("argent : " + model.getGold() );
		petUPLabel.setText("cout : " + model.myPets.getPetCostBuy());
		artfMoneyToGet.setText("gain : " + (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		artfMoneyCost.setText("cout : " + model.myHero.getArtefactCost());
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
			ajouterClasses(this, model.myMonster);
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
		if (x == berserkChoice) {
			controller.classChoice(2);
		}
		if (x == mageChoice) {
			controller.classChoice(3);
		}
		if (x == buttonArtf) {
			controller.oneMoreArtf();
		}
		if (x == aqua) {
			controller.attributeChoice(1);
		}
		if (x == pyro) {
			controller.attributeChoice(2);
		}
		if (x == tera) {
			controller.attributeChoice(3);
		}
	}

}



