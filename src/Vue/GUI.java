package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.ui.Window;

import Contrôleur.gameController;
import model.Hero;
import model.Monster;
import model.Pets;
import model.database;
import model.game;

public class GUI extends gameVue implements Observer, ActionListener{
	public GUI(game model, gameController controller) {
		super(model, controller);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public JLabel PVLabel = new JLabel(); 		// pv en int. graph.
	public JLabel argentLabel = new JLabel(); 			// pas encore implemente (degats par seconde en int. graph.
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
	public JButton artefactGoldButton = new JButton();
	public JButton epeeButton = new JButton();
	
	public JButton upgradeGold = new JButton();
	public JButton newPetGold = new JButton();
	public JButton upgradePetsGold = new JButton();
	public JButton resetGold = new JButton();
	public JButton newArtefactGold = new JButton();

	public JButton buttonArcher = new JButton();
	public JButton buttonMage= new JButton();
	public JButton buttonBerzerker = new JButton();
	public JLabel degatLabel = new JLabel();
	public JLabel coinLabel = new JLabel();
	public JLabel artefactCoinLabel = new JLabel();
	public JLabel newPetLabel = new JLabel(); 
	public JLabel upPetsLabel = new JLabel();
	public JLabel dmgUPLabel = new JLabel();
	public JLabel artfMoney = new JLabel();
	public JLabel artfMoneyToGet = new JLabel();
	public JLabel artfMoneyCost = new JLabel();
	public JLabel degatsLabel = new JLabel();
	public JLabel vague = new JLabel();
	public JLabel numberMonstreLabel = new JLabel();
	public JButton buttonHero = new JButton();
	public JButton buttonCoeur1 = new JButton();
	public JButton buttonCoeur2 = new JButton();
	public ImageIcon imageHero0 = new ImageIcon("./images/héro-1.png.png");
	public ImageIcon imageHero1 = new ImageIcon("./images/héro-2.png.png");
	public ImageIcon imageHero2 = new ImageIcon("./images/héro-3.png.png");
	public ImageIcon imageHero3 = new ImageIcon("./images/héro-4.png.png");
	public ImageIcon imageCoeur = new ImageIcon("./images/coeur.gif");
	public ImageIcon bigGold = new ImageIcon("./images/bigGold.gif");
	public ImageIcon littleGold = new ImageIcon("./images/littleGold.gif");
	public ImageIcon bigArtefactGold = new ImageIcon("./images/bigArtefactGold.gif");
	public ImageIcon littleArtefactGold = new ImageIcon("./images/littleArtefactGold.gif");
	public ImageIcon epeeDegats = new ImageIcon("./images/epeeDegats.jpg");
	
	public JLabel tableauDesScores = new JLabel();
	
	public JButton buttonEpeeDecor1 = new JButton();
	public JButton buttonEpeeDecor2 = new JButton();
	public JButton buttonEpeeDecor3 = new JButton();
	public JButton buttonEpeeDecor4 = new JButton();


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
		screenMonster(this, monstre);
		
		JFrame window = new JFrame();
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setLayout(null);
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);

		
		JPanel imageEpeeDecor1 = new JPanel();
		imageEpeeDecor1.setBounds(310, 150, 40, 50);
		imageEpeeDecor1.setBackground(Color.white);
		window.add(imageEpeeDecor1);
		
		JPanel imageEpeeDecor2 = new JPanel();
		imageEpeeDecor2.setBounds(20, 20, 40, 50);
		imageEpeeDecor2.setBackground(Color.white);
		window.add(imageEpeeDecor2);
		
		JPanel imageEpeeDecor3 = new JPanel();
		imageEpeeDecor3.setBounds(310, 20, 40, 50);
		imageEpeeDecor3.setBackground(Color.white);
		window.add(imageEpeeDecor3);
		
		JPanel imageEpeeDecor4 = new JPanel();
		imageEpeeDecor4.setBounds(20, 150, 40, 50);
		imageEpeeDecor4.setBackground(Color.white);
		window.add(imageEpeeDecor4);
		
		buttonEpeeDecor1.setBackground(Color.cyan);
		buttonEpeeDecor1.setFocusPainted(false);
		buttonEpeeDecor1.setBorder(null);
		buttonEpeeDecor1.setIcon(epeeDegats);
		imageEpeeDecor1.add(buttonEpeeDecor1);
		
		buttonEpeeDecor2.setBackground(Color.cyan);
		buttonEpeeDecor2.setFocusPainted(false);
		buttonEpeeDecor2.setBorder(null);
		buttonEpeeDecor2.setIcon(epeeDegats);
		imageEpeeDecor2.add(buttonEpeeDecor2);
		
		buttonEpeeDecor3.setBackground(Color.cyan);
		buttonEpeeDecor3.setFocusPainted(false);
		buttonEpeeDecor3.setBorder(null);
		buttonEpeeDecor3.setIcon(epeeDegats);
		imageEpeeDecor3.add(buttonEpeeDecor3);
	
		buttonEpeeDecor4.setBackground(Color.cyan);
		buttonEpeeDecor4.setFocusPainted(false);
		buttonEpeeDecor4.setBorder(null);
		buttonEpeeDecor4.setIcon(epeeDegats);
		imageEpeeDecor4.add(buttonEpeeDecor4);
		
		JPanel vaguePanel = new JPanel();
		vaguePanel.setBounds(90, 60, 200, 70);
		vaguePanel.setBackground(Color.white);
		window.add(vaguePanel);
		
		vague.setForeground(Color.black);
		vague.setFont(stats);
		vague.setText("Vague n°" + model.myMonster.getWaveNumber());
		vaguePanel.add(vague);
		
		JPanel numberMonstrePanel = new JPanel();
		numberMonstrePanel.setBounds(90, 120, 200, 70);
		numberMonstrePanel.setBackground(Color.white);
		window.add(numberMonstrePanel);
		
		numberMonstreLabel.setForeground(Color.black);
		numberMonstreLabel.setFont(stats);
		numberMonstreLabel.setText("Monstre n°" + model.myMonster.getNumber());
		numberMonstrePanel.add(numberMonstreLabel);
		
		JPanel vagueMonstrePanel = new JPanel();
		vagueMonstrePanel.setBounds(10, 10, 350, 200);
		vagueMonstrePanel.setBackground(Color.white);
		window.add(vagueMonstrePanel);
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(500, 140, 200, 230);
		monstrePanel.setBackground(Color.white);
		window.add(monstrePanel);
		
		JPanel attributePanel = new JPanel();
		attributePanel.setLayout(new GridLayout(1,3));
		attributePanel.setBounds(490,560,220,61);
		attributePanel.setBackground(Color.white);
		window.add(attributePanel);	
		
		JPanel heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayout(1,1));
		heroPanel.setBounds(525, 390, 150, 150);
		heroPanel.setBackground(Color.white);
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
		
		JPanel pvMonstrePanel1 = new JPanel();
		pvMonstrePanel1.setBounds(505,87,44,64);
		pvMonstrePanel1.setBackground(Color.white);
		window.add(pvMonstrePanel1);
		
		JPanel pvMonstrePanel2 = new JPanel();
		pvMonstrePanel2.setBounds(650,87,44,64);
		pvMonstrePanel2.setBackground(Color.white);
		window.add(pvMonstrePanel2);
		
		JPanel coinUpgrade = new JPanel();
		coinUpgrade.setBounds(910,162,30,34);
		coinUpgrade.setBackground(Color.cyan);
		window.add(coinUpgrade);
		
		upgradeGold.setBackground(Color.cyan);
		upgradeGold.setFocusPainted(false);
		upgradeGold.setBorder(null);
		upgradeGold.setIcon(littleGold);
		coinUpgrade.add(upgradeGold);
		
		JPanel coinNewPet = new JPanel();
		coinNewPet.setBounds(1020,162,30,34);
		coinNewPet.setBackground(Color.cyan);
		window.add(coinNewPet);
		
		newPetGold.setBackground(Color.cyan);
		newPetGold.setFocusPainted(false);
		newPetGold.setBorder(null);
		newPetGold.setIcon(littleGold);
		coinNewPet.add(newPetGold);
		
		JPanel coinUpgradePets = new JPanel();
		coinUpgradePets.setBounds(1020,271,30,34);
		coinUpgradePets.setBackground(Color.cyan);
		window.add(coinUpgradePets);
		
		upgradePetsGold.setBackground(Color.cyan);
		upgradePetsGold.setFocusPainted(false);
		upgradePetsGold.setBorder(null);
		upgradePetsGold.setIcon(littleGold);
		coinUpgradePets.add(upgradePetsGold);
		
		JPanel coinReset = new JPanel();
		coinReset.setBounds(910,271,30,34);
		coinReset.setBackground(Color.cyan);
		window.add(coinReset);
		
		resetGold.setBackground(Color.cyan);
		resetGold.setFocusPainted(false);
		resetGold.setBorder(null);
		resetGold.setIcon(littleArtefactGold);
		coinReset.add(resetGold);
		
		JPanel coinNewArtefact = new JPanel();
		coinNewArtefact.setBounds(965,382,30,44);
		coinNewArtefact.setBackground(Color.cyan);
		window.add(coinNewArtefact);

		newArtefactGold.setBackground(Color.cyan);
		newArtefactGold.setFocusPainted(false);
		newArtefactGold.setBorder(null);
		newArtefactGold.setIcon(littleArtefactGold);
		coinNewArtefact
		.add(newArtefactGold);
		
		buttonCoeur1.setBackground(Color.white);
		buttonCoeur1.setFocusPainted(false);
		buttonCoeur1.setBorder(null);
		buttonCoeur1.setIcon(imageCoeur);
		pvMonstrePanel1.add(buttonCoeur1);
		
		buttonCoeur2.setBackground(Color.white);
		buttonCoeur2.setFocusPainted(false);
		buttonCoeur2.setBorder(null);
		buttonCoeur2.setIcon(imageCoeur);
		pvMonstrePanel2.add(buttonCoeur2);
		
		JPanel texteTableauDesScores = new JPanel();
		texteTableauDesScores.setBounds(10,400,350,250);
		texteTableauDesScores.setBackground(Color.cyan);
		window.add(texteTableauDesScores);
		
		tableauDesScores.setForeground(Color.black);
		tableauDesScores.setFont(stats);
		tableauDesScores.setText("Meilleurs joueurs :");
		texteTableauDesScores.add(tableauDesScores);
		
		

		//  BOUTONS D'AMELIORATION

		JPanel imagesUpDegatsNewPixie = new JPanel();
		imagesUpDegatsNewPixie.setBounds(900,100,230,67);
		imagesUpDegatsNewPixie.setLayout(new GridLayout(1,2));
		imagesUpDegatsNewPixie.setBackground(Color.cyan);
		window.add(imagesUpDegatsNewPixie);
		
		JPanel textesUpDegatsNewPixie = new JPanel();
		textesUpDegatsNewPixie.setBounds(900,150,230,67);
		textesUpDegatsNewPixie.setLayout(new GridLayout(1,2));
		textesUpDegatsNewPixie.setBackground(Color.cyan);
		window.add(textesUpDegatsNewPixie);
		
		JPanel imagesResetUpPet = new JPanel();
		imagesResetUpPet.setBounds(900,210,230,67);
		imagesResetUpPet.setLayout(new GridLayout(1,2));
		imagesResetUpPet.setBackground(Color.cyan);
		window.add(imagesResetUpPet);
		
		JPanel textesResetUpPet = new JPanel();
		textesResetUpPet.setBounds(900,260,230,67);
		textesResetUpPet.setLayout(new GridLayout(1,2));
		textesResetUpPet.setBackground(Color.cyan);
		window.add(textesResetUpPet);
		
		JPanel imageBuyArtefact = new JPanel();
		imageBuyArtefact.setBounds(900,320,230,67);
		imageBuyArtefact.setLayout(new GridLayout(1,2));
		imageBuyArtefact.setBackground(Color.cyan);
		window.add(imageBuyArtefact);
		
		JPanel textesBuyArtefact = new JPanel();
		textesBuyArtefact.setBounds(900,370,230,67);
		textesBuyArtefact.setLayout(new GridLayout(1,2));
		textesBuyArtefact.setBackground(Color.cyan);
		window.add(textesBuyArtefact);

		JPanel choiceClass = new JPanel();
        choiceClass.setLayout(new GridLayout(1,3));
        choiceClass.setBounds(400,10,400,70);
        choiceClass.setBackground(Color.white);
        window.add(choiceClass);
        
        JPanel degats = new JPanel();
        degats.setBounds(950,476,50,70);
        degats.setBackground(Color.white);
        window.add(degats);
        
        JPanel coin = new JPanel();
        coin.setBounds(950,520,50,70);
        coin.setBackground(Color.white);
        window.add(coin);
        
        JPanel artefactCoin = new JPanel();
        artefactCoin.setBounds(950,570,50,70);
        artefactCoin.setBackground(Color.white);
        window.add(artefactCoin);
        
        JPanel textesDegats = new JPanel();
        textesDegats.setBounds(900,482,300,30);
        textesDegats.setBackground(Color.white);
		window.add(textesDegats);
		
		JPanel textesCoin = new JPanel();
		textesCoin.setBounds(900,540,300,30);
		textesCoin.setBackground(Color.white);
	    window.add(textesCoin);
			
	    JPanel textesArtefactCoin = new JPanel();
	    textesArtefactCoin.setBounds(900,590,300,30);
	    textesArtefactCoin.setBackground(Color.white);
		window.add(textesArtefactCoin);
        
		ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
		
		buttonUP.setBackground(Color.cyan);
		buttonUP.setFocusPainted(false);
		buttonUP.setBorderPainted(false);
		buttonUP.setIcon(UPIcon);
		buttonUP.addActionListener(this);
		imagesUpDegatsNewPixie.add(buttonUP);
		
		ImageIcon newPixie = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));
		
		buttonNewPet.setBackground(Color.cyan);
		buttonNewPet.setFocusPainted(false);
		buttonNewPet.setBorder(null);
		buttonNewPet.setIcon(newPixie);
		buttonNewPet.addActionListener(this);
		imagesUpDegatsNewPixie.add(buttonNewPet);
		
		dmgUPLabel.setForeground(Color.black);
		dmgUPLabel.setFont(stats);
		dmgUPLabel.setText("        " + myGame.getUpgradeMoneyValue());
		textesUpDegatsNewPixie.add(dmgUPLabel);
		
		newPetLabel.setForeground(Color.black);
		newPetLabel.setFont(stats);
		newPetLabel.setText("        " + pet.getPetCostBuy());
		textesUpDegatsNewPixie.add(newPetLabel);
		
		ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));

		buttonReborn.setBackground(Color.cyan);
		buttonReborn.setFocusPainted(false);
		buttonReborn.setBorder(null);
		buttonReborn.setIcon(reset);
		buttonReborn.addActionListener(this);
		imagesResetUpPet.add(buttonReborn);
		
		ImageIcon upPetIcon = new ImageIcon(game.class.getResource("/images/anim upPets.gif"));
		
		buttonUpPets.setBackground(Color.cyan);
		buttonUpPets.setFocusPainted(false);
		buttonUpPets.setBorder(null);
		buttonUpPets.setIcon(upPetIcon);
		buttonUpPets.addActionListener(this);
		imagesResetUpPet.add(buttonUpPets);
		
		
		artfMoneyToGet.setForeground(Color.black);
		artfMoneyToGet.setFont(stats);
		artfMoneyToGet.setText("        +" + (monstre.getWaveNumber() + myGame.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		textesResetUpPet.add(artfMoneyToGet);
		
		upPetsLabel.setForeground(Color.black);
		upPetsLabel.setFont(stats);
		upPetsLabel.setText("        " + pet.getPetCostUpgrade());
		textesResetUpPet.add(upPetsLabel);

		ImageIcon artfIcon = new ImageIcon(game.class.getResource("/images/anim newArtefact.gif"));

		buttonArtf.setBackground(Color.cyan);
		buttonArtf.setFocusPainted(false);
		buttonArtf.setBorder(null);
		buttonArtf.setIcon(artfIcon);
		buttonArtf.addActionListener(this);
		imageBuyArtefact.add(buttonArtf);
		
		artfMoneyCost.setForeground(Color.black);
		artfMoneyCost.setFont(stats);
		artfMoneyCost.setText("                     " + hero.getArtefactCost() + "€");
		textesBuyArtefact.add(artfMoneyCost);
				
		epeeButton.setBackground(Color.cyan);
		epeeButton.setFocusPainted(false);
		epeeButton.setBorder(null);
		epeeButton.setIcon(epeeDegats);
		degats.add(epeeButton);
		
		goldButton.setBackground(Color.white);
		goldButton.setFocusPainted(false);
		goldButton.setBorder(null);
		goldButton.setIcon(bigGold);
		coin.add(goldButton);
		
		artefactGoldButton.setBackground(Color.white);
		artefactGoldButton.setFocusPainted(false);
		artefactGoldButton.setBorder(null);
		artefactGoldButton.setIcon(bigArtefactGold);
		artefactCoin.add(artefactGoldButton);
		
		degatLabel.setForeground(Color.black);
		degatLabel.setFont(stats);
		degatLabel.setText("Degats : " + hero.getDamage());
		textesDegats.add(degatLabel);
		
		coinLabel.setForeground(Color.black);
		coinLabel.setFont(stats);
		coinLabel.setText(" " + model.getGold());
		textesCoin.add(coinLabel);
		
		artefactCoinLabel.setForeground(Color.black);
		artefactCoinLabel.setFont(stats);
		artefactCoinLabel.setText(" " + hero.getArtefactMoney());
		textesArtefactCoin.add(artefactCoinLabel);

		

		

	/*	
		artfMoneyCost.setForeground(Color.black);
		artfMoneyCost.setFont(stats);
		artfMoneyCost.setText(hero.getArtefactCost() + "$");
		ensembleBoutton1.add(artfMoneyCost);
	
	*/	
		// COMPTEUR
		

		
		JPanel pointDeVie = new JPanel();
		pointDeVie.setBounds(550,100,100,50);
		pointDeVie.setBackground(Color.white);
		window.add(pointDeVie);
		
		PVLabel.setForeground(Color.black );
		Font PVEcriture = new Font("Comic Sans MS", Font.PLAIN, 24 );
		PVLabel.setFont(PVEcriture);
		PVLabel.setText("-  " + monstre.getPV() + "  -");
		pointDeVie.add(PVLabel);
		
		
		
		
		ImageIcon aquaIcon = new ImageIcon(game.class.getResource("/images/aqua.png"));
		ImageIcon pyroIcon = new ImageIcon(game.class.getResource("/images/fire.png"));
		ImageIcon teraIcon = new ImageIcon(game.class.getResource("/images/plant.png"));
		screenMonster(this, monstre);
		changerAttaqueHero(myGame, this);
		
		aqua.setBackground(Color.white);
		pyro.setBackground(Color.white);
		tera.setBackground(Color.white);
		aqua.setBorderPainted(false);
		pyro.setBorderPainted(false);
		tera.setBorderPainted(false);
		aqua.setFocusPainted(false);
		pyro.setFocusPainted(false);
		tera.setFocusPainted(false);
		aqua.addActionListener(this);
		pyro.addActionListener(this);
		tera.addActionListener(this);
		aqua.setIcon(aquaIcon);
		pyro.setIcon(pyroIcon);
		tera.setIcon(teraIcon);
		aqua.setEnabled(false);
		attributePanel.add(aqua);
		attributePanel.add(pyro);
		attributePanel.add(tera);
		
		ImageIcon archerIcon = new ImageIcon(game.class.getResource("/images/anim archer.gif"));
		ImageIcon berserkIcon = new ImageIcon(game.class.getResource("/images/anim berzerker.gif"));
		ImageIcon mageIcon = new ImageIcon(game.class.getResource("/images/anim mage.gif"));
		
		archerChoice.setBackground(Color.white);
		archerChoice.setFocusPainted(false);
		archerChoice.setBorderPainted(false);
		archerChoice.addActionListener(this);
		archerChoice.setIcon(archerIcon);
		archerChoice.setEnabled(false);
		
		berserkChoice.setBackground(Color.white);
		berserkChoice.setFocusPainted(false);
		berserkChoice.setBorderPainted(false);
		berserkChoice.addActionListener(this);
		berserkChoice.setIcon(berserkIcon);
		berserkChoice.setEnabled(false);
		
		mageChoice.setBackground(Color.white);
		mageChoice.setFocusPainted(false);
		mageChoice.setBorderPainted(false);
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
		PVLabel.setText("-  " + model.myMonster.getPV() + "  -");
		
		numberMonstreLabel.setText("Monstre n°" + model.myMonster.getNumber());
		vague.setText("Vague n°" + model.myMonster.getWaveNumber());
		
		dmgUPLabel.setText("        " + model.getUpgradeMoneyValue());
		newPetLabel.setText("        " + model.myPets.getPetCostBuy());		
		artfMoneyToGet.setText("        +" + (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		upPetsLabel.setText("        " + model.myPets.getPetCostUpgrade());
		artfMoneyCost.setText("                     " + model.myHero.getArtefactCost());
		
		degatLabel.setText("Degats : " + model.myHero.getDamage());
		coinLabel.setText(" " + model.getGold() );
		artefactCoinLabel.setText("" + model.myHero.getArtefactMoney());	

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
		if (x == archerChoice) {
			controller.classChoice(1);
		}
		if (x == berserkChoice) {
			controller.classChoice(3);
		}
		if (x == mageChoice) {
			controller.classChoice(2);
		}
		if (x == buttonArtf) {
			controller.oneMoreArtf();
		}
		if (x == aqua) {
			controller.attributeChoice(1);
			aqua.setEnabled(false);
			pyro.setEnabled(true);
			tera.setEnabled(true);		
		}
		if (x == pyro) {
			controller.attributeChoice(2);
			aqua.setEnabled(true);
			pyro.setEnabled(false);
			tera.setEnabled(true);
		}
		if (x == tera) {
			controller.attributeChoice(3);
			aqua.setEnabled(true);
			pyro.setEnabled(true);
			tera.setEnabled(false);
		}
	}

}



