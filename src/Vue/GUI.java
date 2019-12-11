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
	public static JButton buttonMonster = new JButton();
	public JButton buttonReborn = new JButton();
	public JButton buttonNewPet = new JButton();
	public JButton buttonUpPets = new JButton();
	public JButton buttonArtf = new JButton();
	public static JButton archerChoice = new JButton();
	public static JButton mageChoice = new JButton();
	public static JButton berserkChoice = new JButton();
	public JButton goldButton = new JButton();


	public JButton buttonArcher = new JButton();
	public JButton buttonMage= new JButton();
	public JButton buttonBerzerker = new JButton();
	public JLabel degatLabel = new JLabel();
	public JLabel newPetLabel = new JLabel(); 
	public JLabel upPetsLabel = new JLabel();
	public JLabel dmgUPLabel = new JLabel();
	public JLabel artfMoney = new JLabel();
	public JLabel artfMoneyToGet = new JLabel();
	public JLabel artfMoneyCost = new JLabel();
	public JButton buttonHero = new JButton();
	public ImageIcon imageHero0 = new ImageIcon("./images/héro-1.png.png");
	public ImageIcon imageHero1 = new ImageIcon("./images/héro-2.png.png");
	public ImageIcon imageHero2 = new ImageIcon("./images/héro-3.png.png");
	public ImageIcon imageHero3 = new ImageIcon("./images/héro-4.png.png");
	public ImageIcon gold = new ImageIcon("./images/gold.png");
	Image newImage = gold.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT);

	public JFrame window = new JFrame("Clicker");
	public JPanel choiceClass = new JPanel();
	public static ImageIcon slimeBleu = new ImageIcon("./images/slime bleu.png"); //cr�ation d'une image en tant que ic�ne.
	public static ImageIcon slimeVert = new ImageIcon("./images/slime vert.png");
	public static ImageIcon slimeRouge = new ImageIcon("./images/slime rouge.png");
	public JButton aqua = new JButton();
	public JButton tera = new JButton();
	public JButton pyro = new JButton();
	
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

		JPanel imagesUpDegatsNewPixie = new JPanel();
		imagesUpDegatsNewPixie.setBounds(900,100,230,67);
		imagesUpDegatsNewPixie.setLayout(new GridLayout(1,2));
		imagesUpDegatsNewPixie.setBackground(Color.white);
		window.add(imagesUpDegatsNewPixie);
		
		JPanel textesUpDegatsNewPixie = new JPanel();
		textesUpDegatsNewPixie.setBounds(900,150,230,67);
		textesUpDegatsNewPixie.setLayout(new GridLayout(1,2));
		textesUpDegatsNewPixie.setBackground(Color.white);
		window.add(textesUpDegatsNewPixie);
		
		JPanel imagesResetUpPet = new JPanel();
		imagesResetUpPet.setBounds(900,250,230,67);
		imagesResetUpPet.setLayout(new GridLayout(1,2));
		imagesResetUpPet.setBackground(Color.white);
		window.add(imagesResetUpPet);
		
		JPanel textesResetUpPet = new JPanel();
		textesResetUpPet.setBounds(900,300,230,67);
		textesResetUpPet.setLayout(new GridLayout(1,2));
		textesResetUpPet.setBackground(Color.white);
		window.add(textesResetUpPet);
		
		JPanel ensembleBoutton1 = new JPanel();
		ensembleBoutton1.setBounds(500,100,450,137);
		ensembleBoutton1.setLayout(new GridLayout(4,2));
		ensembleBoutton1.setBackground(Color.white);
		window.add(ensembleBoutton1);
		
		JPanel attributePanel = new JPanel();
		attributePanel.setLayout(new GridLayout(1,3));
		attributePanel.setBounds(100,420,400,150);
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
		imagesUpDegatsNewPixie.add(buttonUP);
		
		ImageIcon UPPixie = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));
		
		buttonNewPet.setBackground(Color.white);
		buttonNewPet.setFocusPainted(false);
		buttonNewPet.setBorder(null);
		buttonNewPet.setIcon(UPPixie);
		buttonNewPet.addActionListener(this);
		imagesUpDegatsNewPixie.add(buttonNewPet);
		
		dmgUPLabel.setForeground(Color.black);
		dmgUPLabel.setFont(stats);
		dmgUPLabel.setText("       " + myGame.getUpgradeMoneyValue() + "$");
		textesUpDegatsNewPixie.add(dmgUPLabel);
		
		newPetLabel.setForeground(Color.black);
		newPetLabel.setFont(stats);
		newPetLabel.setText("       " + pet.getPetCostBuy() + "$");
		textesUpDegatsNewPixie.add(newPetLabel);
		
		ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));

		buttonReborn.setBackground(Color.white);
		buttonReborn.setFocusPainted(false);
		buttonReborn.setBorder(null);
		buttonReborn.setIcon(reset);
		buttonReborn.addActionListener(this);
		imagesResetUpPet.add(buttonReborn);
		
		ImageIcon upPetIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		buttonUpPets.setBackground(Color.white);
		buttonUpPets.setFocusPainted(false);
		buttonUpPets.setBorder(null);
		buttonUpPets.setIcon(upPetIcon);
		buttonUpPets.addActionListener(this);
		imagesResetUpPet.add(buttonUpPets);
		
		
		artfMoneyToGet.setForeground(Color.black);
		artfMoneyToGet.setFont(stats);
		artfMoneyToGet.setText("    Gain : " + (monstre.getWaveNumber() + myGame.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1) + "€");
		textesResetUpPet.add(artfMoneyToGet);
		
		upPetsLabel.setForeground(Color.black);
		upPetsLabel.setFont(stats);
		upPetsLabel.setText("       " + pet.getPetCostUpgrade() + "$");
		textesResetUpPet.add(upPetsLabel);

		ImageIcon artfIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));

		buttonArtf.setBackground(Color.white);
		buttonArtf.setFocusPainted(false);
		buttonArtf.setBorder(null);
		buttonArtf.setIcon(artfIcon);
		buttonArtf.addActionListener(this);
		ensembleBoutton1.add(buttonArtf);


		

		

		
		artfMoneyCost.setForeground(Color.black);
		artfMoneyCost.setFont(stats);
		artfMoneyCost.setText(hero.getArtefactCost() + "$");
		ensembleBoutton1.add(artfMoneyCost);
	
		
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
		
		
		ImageIcon aquaIcon = new ImageIcon(game.class.getResource("/images/aqua.png"));
		ImageIcon pyroIcon = new ImageIcon(game.class.getResource("/images/fire.png"));
		ImageIcon teraIcon = new ImageIcon(game.class.getResource("/images/plant.png"));
		screenMonster(this, monstre);
		changerAttaqueHero(myGame, this);
		
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
		archerChoice.setEnabled(false);
		
		berserkChoice.setBackground(Color.gray);
		berserkChoice.setFocusPainted(false);
		berserkChoice.addActionListener(this);
		berserkChoice.setIcon(berserkIcon);
		berserkChoice.setEnabled(false);
		
		mageChoice.setBackground(Color.gray);
		mageChoice.setFocusPainted(false);
		mageChoice.addActionListener(this);
		mageChoice.setIcon(mageIcon);
		mageChoice.setEnabled(false);
		
		choiceClass.add(archerChoice);
		choiceClass.add(berserkChoice);
		choiceClass.add(mageChoice);
		
		ajouterClasses(this, monstre);
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
			GUI.buttonMonster.setIcon(GUI.slimeBleu);
		}
		if (myMonster.getAttribute() == "tera") {
			GUI.buttonMonster.setIcon(GUI.slimeVert);
		}
		if (myMonster.getAttribute() == "pyro") {
			GUI.buttonMonster.setIcon(GUI.slimeRouge);
		}
	}
	
	public void ajouterClasses(GUI myGUI, Monster monstre) {
		if(monstre.getWaveNumber() == 2) {
			GUI.archerChoice.setEnabled(true);
			GUI.berserkChoice.setEnabled(true);
			GUI.mageChoice.setEnabled(true);
		}
	}	
	
	public static void main(String[] args) {
	}

	@Override
	public void update(Observable o, Object arg) {
		degatLabel.setText("degats actuels :" + model.myHero.getDamage());
		dmgUPLabel.setText("       " + model.getUpgradeMoneyValue() + "$");
		PVLabel.setText("PV : " + model.myMonster.getPV());
		artfMoney.setText("monnaie artefacts actuels :" + model.myHero.getArtefactMoney());
		argentLabel.setText("argent : " + model.getGold() );
		newPetLabel.setText("       " + model.myPets.getPetCostBuy() + "$");
		artfMoneyToGet.setText("    Gain : " + (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1) + "€");
		artfMoneyCost.setText(model.myHero.getArtefactCost() + "€");
		upPetsLabel.setText("       " + model.myPets.getPetCostUpgrade() + "$");

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
		if (x == buttonNewPet) {
			controller.oneMorePet();
		}
		if (x == buttonUpPets) {
			controller.upgradePets();
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



