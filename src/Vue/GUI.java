package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	public JLabel tempsBoss1Label = new JLabel();
	public JLabel tempsBoss2Label = new JLabel();
	
	public JLabel argentLabel = new JLabel(); 			// pas encore implementer (degats par seconde en int. graph.
	public JButton buttonUP = new JButton();		
	public static JButton buttonMonster = new JButton();
	public JButton buttonReborn = new JButton();
	public JButton buttonNewPet = new JButton();
	public JButton buttonUpPets = new JButton();
	public JButton buttonArtf = new JButton();
	public JButton archerChoice = new JButton();
	public JButton mageChoice = new JButton();
	public JButton berserkChoice = new JButton();
	public JButton goldButton = new JButton();
	public JButton artefactGoldButton = new JButton();
	public JButton epeeButton = new JButton();
	public JButton save = new JButton();
	
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
	public JLabel resetLabel = new JLabel();
	public JLabel artfMoneyCost = new JLabel();
	public JLabel degatsLabel = new JLabel();
	public JLabel vague = new JLabel();
	public JLabel numberMonstreLabel = new JLabel();
	public JButton buttonHero = new JButton();
	public JButton buttonCoeur1 = new JButton();
	public JButton buttonCoeur2 = new JButton();
	public ImageIcon aquaIcon = new ImageIcon(game.class.getResource("/images/aqua.png"));
	public ImageIcon pyroIcon = new ImageIcon(game.class.getResource("/images/fire.png"));
	public ImageIcon teraIcon = new ImageIcon(game.class.getResource("/images/plant.png"));
	public ImageIcon imageHero0 = new ImageIcon(game.class.getResource("/images/héro-1.png.png"));
	public ImageIcon imageHero1 = new ImageIcon(game.class.getResource("/images/héro-2.png.png"));
	public ImageIcon imageHero2 = new ImageIcon(game.class.getResource("/images/héro-3.png.png"));
	public ImageIcon imageHero3 = new ImageIcon(game.class.getResource("/images/héro-4.png.png"));
	public ImageIcon imageCoeur = new ImageIcon(game.class.getResource("/images/coeur.gif"));
	public ImageIcon bigGold = new ImageIcon(game.class.getResource("/images/bigGold.gif"));
	public ImageIcon littleGold = new ImageIcon(game.class.getResource("/images/littleGold.gif"));
	public ImageIcon bigArtefactGold = new ImageIcon(game.class.getResource("/images/bigArtefactGold.gif"));
	public ImageIcon littleArtefactGold = new ImageIcon(game.class.getResource("/images/littleArtefactGold.gif"));
	public ImageIcon epeeDegats = new ImageIcon(game.class.getResource("/images/epeeDegats.jpg"));
	
	public JLabel tableauDesScores = new JLabel();
	
	public JButton buttonEpeeDecor1 = new JButton();
	public JButton buttonEpeeDecor2 = new JButton();
	public JButton buttonEpeeDecor3 = new JButton();
	public JButton buttonEpeeDecor4 = new JButton();


	public JFrame window = new JFrame("Clicker");
	public JPanel choiceClass = new JPanel();
	
	public static ImageIcon slimeBleu = new ImageIcon(game.class.getResource("/images/slime bleu.png")); //cr�ation d'une image en tant que ic�ne.
	public static ImageIcon slimeVert = new ImageIcon(game.class.getResource("/images/slime vert.png"));
	public static ImageIcon slimeRouge = new ImageIcon(game.class.getResource("/images/slime rouge.png"));
	public static ImageIcon aquaBoss = new ImageIcon(game.class.getResource("/images/aquaBoss.png"));
	public static ImageIcon teraBoss = new ImageIcon(game.class.getResource("/images/teraBoss.png"));
	public static ImageIcon pyroBoss = new ImageIcon(game.class.getResource(""
			+ ""
			+ ""
			+ "/images/pyroBoss.png"));
	
	public static JButton aqua = new JButton();
	public static JButton tera = new JButton();
	public static JButton pyro = new JButton();
	
	public JComboBox table;
	public JComboBox ordre;

	/**
	 * methode qui genere l'interface graphique 
	 * @param monstre : monstre a modifier
	 * @param hero : hero a modifier
	 * @param pet : pet a modifier
	 * @param myGame : jeu a afficher
	 */
	public void genererUI(Monster monstre,Hero hero,Pets pet,game myGame) {		
		window.setSize(1200, 700);
		ajouterTempsBoss(model.myMonster);
		
		JFrame window = new JFrame();
		window.setSize(1200, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setLayout(null);
		Font stats = new Font("Comic Sans MS", Font.PLAIN, 18);

		/*
		 * 		PARAMETRES DE LA FENETRE DU NUMERO DE MONSTRE ET DU
		 * 		NUMERO DE LA VAGUE. 
		 * 
		 * 		-> En haut à droite
		 */
		
		//LOGOS DES EPEES EN DECOR
		
		JPanel imageEpeeDecor1 = new JPanel();
		imageEpeeDecor1.setBounds(310, 150, 40, 50);
		imageEpeeDecor1.setBackground(Color.darkGray);
		window.add(imageEpeeDecor1);
		
		JPanel imageEpeeDecor2 = new JPanel();
		imageEpeeDecor2.setBounds(20, 20, 40, 50);
		imageEpeeDecor2.setBackground(Color.darkGray);
		window.add(imageEpeeDecor2);
		
		JPanel imageEpeeDecor3 = new JPanel();
		imageEpeeDecor3.setBounds(310, 20, 40, 50);
		imageEpeeDecor3.setBackground(Color.darkGray);
		window.add(imageEpeeDecor3);
		
		JPanel imageEpeeDecor4 = new JPanel();
		imageEpeeDecor4.setBounds(20, 150, 40, 50);
		imageEpeeDecor4.setBackground(Color.darkGray);
		window.add(imageEpeeDecor4);
		
		buttonEpeeDecor1.setBackground(Color.lightGray);
		buttonEpeeDecor1.setFocusPainted(false);
		buttonEpeeDecor1.setBorder(null);
		buttonEpeeDecor1.setIcon(epeeDegats);
		imageEpeeDecor1.add(buttonEpeeDecor1);
		
		buttonEpeeDecor2.setBackground(Color.lightGray);
		buttonEpeeDecor2.setFocusPainted(false);
		buttonEpeeDecor2.setBorder(null);
		buttonEpeeDecor2.setIcon(epeeDegats);
		imageEpeeDecor2.add(buttonEpeeDecor2);
		
		buttonEpeeDecor3.setBackground(Color.lightGray);
		buttonEpeeDecor3.setFocusPainted(false);
		buttonEpeeDecor3.setBorder(null);
		buttonEpeeDecor3.setIcon(epeeDegats);
		imageEpeeDecor3.add(buttonEpeeDecor3);
		
		buttonEpeeDecor4.setBackground(Color.lightGray);
		buttonEpeeDecor4.setFocusPainted(false);
		buttonEpeeDecor4.setBorder(null);
		buttonEpeeDecor4.setIcon(epeeDegats);
		imageEpeeDecor4.add(buttonEpeeDecor4);
		
		//ZONE POUR LE NUMERO DE LA VAGUE
		
		JPanel vaguePanel = new JPanel();
		vaguePanel.setBounds(90, 60, 200, 70);
		vaguePanel.setBackground(Color.darkGray);
		window.add(vaguePanel);
		
		vague.setForeground(Color.white);
		vague.setFont(stats);
		vague.setText("Vague n°" + model.myMonster.getWaveNumber());
		vaguePanel.add(vague);
		
		//ZONE POUR LE NUMERO DU MONSTRE EN COURS
		
		JPanel numberMonstrePanel = new JPanel();
		numberMonstrePanel.setBounds(90, 120, 200, 70);
		numberMonstrePanel.setBackground(Color.darkGray);
		window.add(numberMonstrePanel);
		
		numberMonstreLabel.setForeground(Color.white);
		numberMonstreLabel.setFont(stats);
		numberMonstreLabel.setText("Monstre n°" + model.myMonster.getNumber());
		numberMonstrePanel.add(numberMonstreLabel);
		
		//ZONE GLOBALE POUR INSERER UNE COULEUR DE FOND
		
		
		
		/*
		 * 		PARAMETRES DE LA FENETRE POUR LE CLASSEMENT
		 * 
		 * 		-> INFERIEUR GAUCHE
		 */
		
		JPanel texteTableauDesScores = new JPanel();
		texteTableauDesScores.setBounds(10,220,350,426);
		texteTableauDesScores.setBackground(Color.darkGray);
		window.add(texteTableauDesScores);
		
		tableauDesScores.setForeground(Color.white);
		tableauDesScores.setFont(stats);
		tableauDesScores.setText("Meilleurs joueurs :");
		texteTableauDesScores.add(tableauDesScores);
		
		/*
		 * 		PARAMETRES DE LA FENETRE MONSTRE, HEROS, ATTRIBUTS,
		 * 		POINTS DE VIE DU MONSTRE ET TIMERBOSS
		 * 
		 * 		-> MILIEU CENTRE
		 */
		
		//ZONE POUR LE MONSTRE PRINCIPAL AU CENTRE DE L'ECRAN
		
		JPanel monstrePanel = new JPanel();
		monstrePanel.setBounds(500, 155, 200, 215);
		monstrePanel.setBackground(Color.darkGray);
		window.add(monstrePanel);
		
		buttonMonster.setBackground(Color.white);
		buttonMonster.setFocusPainted(false);
		buttonMonster.setBorder(null);
		buttonMonster.addActionListener(this);
		buttonMonster.setIcon(slimeBleu);
		monstrePanel.add(buttonMonster);
		
		//ZONE POUR LES DIFFERENTS ATTRIBUTS DU HEROS (AQUA, PYRO, TERA)
		
		JPanel attributePanel = new JPanel();
		attributePanel.setLayout(new GridLayout(1,3));
		attributePanel.setBounds(490,560,220,61);
		attributePanel.setBackground(Color.white);
		window.add(attributePanel);	
		
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
		
		//ZONE POUR LE HERO PRINCIPAL
		
		JPanel heroPanel = new JPanel();
		heroPanel.setLayout(new GridLayout(1,1));
		heroPanel.setBounds(525, 390, 150, 150);
		heroPanel.setBackground(Color.lightGray);
		window.add(heroPanel);
		
		buttonHero.setBackground(Color.white);
		buttonHero.setFocusPainted(false);
		buttonHero.setBorder(null);
		buttonHero.setIcon(imageHero1);
		
		heroPanel.add(buttonHero);
		
		//ZONE POUR LES POINTS DE VIE
		
		JPanel pvMonstrePanel1 = new JPanel();
		pvMonstrePanel1.setBounds(505,101,44,50);
		pvMonstrePanel1.setBackground(Color.white);
		window.add(pvMonstrePanel1);		
	
			buttonCoeur1.setBackground(Color.white);
			buttonCoeur1.setFocusPainted(false);
			buttonCoeur1.setBorder(null);
			buttonCoeur1.setIcon(imageCoeur);
			pvMonstrePanel1.add(buttonCoeur1);
	
		
		JPanel pvMonstrePanel2 = new JPanel();
		pvMonstrePanel2.setBounds(650,101,44,50);
		pvMonstrePanel2.setBackground(Color.white);
		window.add(pvMonstrePanel2);

			buttonCoeur2.setBackground(Color.white);
			buttonCoeur2.setFocusPainted(false);
			buttonCoeur2.setBorder(null);
			buttonCoeur2.setIcon(imageCoeur);
			pvMonstrePanel2.add(buttonCoeur2);
			
		JPanel pointDeVie = new JPanel();
		pointDeVie.setBounds(550,101,100,50);
		pointDeVie.setBackground(Color.white);
		window.add(pointDeVie);
			
			PVLabel.setForeground(Color.red);
			Font PVEcriture = new Font("Comic Sans MS", Font.PLAIN, 35 );
			PVLabel.setFont(PVEcriture);
			PVLabel.setText("   " + monstre.getPV() + "   ");
			pointDeVie.add(PVLabel);
		
		JPanel compteurMonstre1 = new JPanel();
		compteurMonstre1.setBounds(400,200,100, 100);
		compteurMonstre1.setBackground(Color.white);
		window.add(compteurMonstre1);
		
			tempsBoss1Label.setForeground(Color.red);
			Font compteurEcriture = new Font("Tempus Sans ITC", Font.PLAIN, 50 );
			tempsBoss1Label.setFont(compteurEcriture);
			tempsBoss1Label.setText("");
			compteurMonstre1.add(tempsBoss1Label);
		
		JPanel compteurMonstre2 = new JPanel();
		compteurMonstre2.setBounds(700,200,100, 100);
		compteurMonstre2.setBackground(Color.white);
		window.add(compteurMonstre2);
		
			tempsBoss2Label.setForeground(Color.red);
			tempsBoss2Label.setFont(compteurEcriture);
			tempsBoss2Label.setText("");
			compteurMonstre2.add(tempsBoss2Label);
		
		/*
		 * 		PARAMETRES DE LA FENETRE AMELIORATIONS ET LEUR PRIX
		 * 
		 * 		-> SUPERIEUR DROIT
		 */
			/*
			 * 		IMAGES DE PIECES
			 * 
			 * 		-> SUPERIEUR DROIT
			 */
			
			//ZONE POUR LE LOGO DE LA PIECE POUR L'AMELIORATION DES DEGATS
			
			JPanel coinUpgrade = new JPanel();
			coinUpgrade.setBounds(1050,43,30,34);
			coinUpgrade.setBackground(Color.darkGray);
			window.add(coinUpgrade);
			
				upgradeGold.setBackground(Color.darkGray);
				upgradeGold.setFocusPainted(false);
				upgradeGold.setBorder(null);
				upgradeGold.setIcon(littleGold);
				coinUpgrade.add(upgradeGold);
			
			//ZONE POUR LE LOGO DE LA PIECE POUR LES NOUVEAUX FAMILIERS
			
			JPanel coinNewPet = new JPanel();
			coinNewPet.setBounds(1050,123,30,34);
			coinNewPet.setBackground(Color.darkGray);
			window.add(coinNewPet);
				
				newPetGold.setBackground(Color.darkGray);
				newPetGold.setFocusPainted(false);
				newPetGold.setBorder(null);
				newPetGold.setIcon(littleGold);
				coinNewPet.add(newPetGold);
			
			
			//ZONE POUR LE LOGO DE LA PIECE POUR LE REBORN
			
			JPanel coinReset = new JPanel();
			coinReset.setBounds(1050,283,30,34);
			coinReset.setBackground(Color.darkGray);
			window.add(coinReset);
			
				resetGold.setBackground(Color.darkGray);
				resetGold.setFocusPainted(false);
				resetGold.setBorder(null);
				resetGold.setIcon(littleGold);
				coinReset.add(resetGold);
			
			//ZONE POUR LE LOGO DE LA PIECE POUR L'AMELIORATION DES FAMILIERS
			
			JPanel coinUpgradePets = new JPanel();
			coinUpgradePets.setBounds(1050,203,30,34);
			coinUpgradePets.setBackground(Color.darkGray);
			window.add(coinUpgradePets);
			
				upgradePetsGold.setBackground(Color.darkGray);
				upgradePetsGold.setFocusPainted(false);
				upgradePetsGold.setBorder(null);
				upgradePetsGold.setIcon(littleArtefactGold);
				coinUpgradePets.add(upgradePetsGold);
				
			//ZONE POUR LE LOGO DE LA PIECE POUR LES NOUVEAUX ARTEFACTS
			
			JPanel coinNewArtefact = new JPanel();
			coinNewArtefact.setBounds(1050,363,30,34);
			coinNewArtefact.setBackground(Color.darkGray);
			window.add(coinNewArtefact);
		
				newArtefactGold.setBackground(Color.darkGray);
				newArtefactGold.setFocusPainted(false);
				newArtefactGold.setBorder(null);
				newArtefactGold.setIcon(littleArtefactGold);
				coinNewArtefact.add(newArtefactGold);

			
				/*
				 * 		IMAGES DE BOUTTONS
				 * 
				 * 		-> SUPERIEUR DROIT
				 */
			
				//ZONE POUR LE BOUTTON DEGATS
			
				JPanel imagesUpDegats = new JPanel();
				imagesUpDegats.setBounds(900,40,112,67);
				imagesUpDegats.setLayout(new GridLayout(1,1));
				imagesUpDegats.setBackground(Color.darkGray);
				window.add(imagesUpDegats);
								 
					ImageIcon UPIcon = new ImageIcon(game.class.getResource("/images/anim up.gif"));
				
					buttonUP.setBackground(Color.darkGray);
					buttonUP.setFocusPainted(false);
					buttonUP.setBorder(null);
					buttonUP.setIcon(UPIcon);
					buttonUP.addActionListener(this);
					imagesUpDegats.add(buttonUP);
					
				JPanel textesUpDegats = new JPanel();
				textesUpDegats.setBounds(1050,47,100,67);
				textesUpDegats.setBackground(Color.darkGray);
				window.add(textesUpDegats);
					
					dmgUPLabel.setForeground(Color.white);
					dmgUPLabel.setFont(stats);
					dmgUPLabel.setText("" + myGame.getUpgradeMoneyValue());
					textesUpDegats.add(dmgUPLabel);
					
				//ZONE POUR LE BOUTTON NOUVEAU FAMILIER
			
				JPanel imagesNewPixie = new JPanel();
				imagesNewPixie.setBounds(900,120,112,67);
				imagesNewPixie.setLayout(new GridLayout(1,1));
				imagesNewPixie.setBackground(Color.darkGray);
				window.add(imagesNewPixie);
				
				ImageIcon newPet = new ImageIcon(game.class.getResource("/images/anim pixie.gif"));

					buttonNewPet.setBackground(Color.darkGray);
					buttonNewPet.setFocusPainted(false);
					buttonNewPet.setBorder(null);
					buttonNewPet.setIcon(newPet);
					buttonNewPet.addActionListener(this);
					imagesNewPixie.add(buttonNewPet);
					
				JPanel textesNewPixie = new JPanel();
				textesNewPixie.setBounds(1050,127,100,67);
				textesNewPixie.setBackground(Color.darkGray);
				window.add(textesNewPixie);
				
					newPetLabel.setForeground(Color.white);
					newPetLabel.setFont(stats);
					newPetLabel.setText("" + pet.getPetCostBuy());
					textesNewPixie.add(newPetLabel);
					
				//ZONE POUR LE BOUTTON RESET
				
				JPanel imagesReset = new JPanel();
				imagesReset.setBounds(900,200,112,67);
				imagesReset.setLayout(new GridLayout(1,1));
				imagesReset.setBackground(Color.darkGray);
				window.add(imagesReset);
				
					ImageIcon reset = new ImageIcon(game.class.getResource("/images/anim reset.gif"));
	
					buttonReborn.setBackground(Color.darkGray);
					buttonReborn.setFocusPainted(false);
					buttonReborn.setBorder(null);
					buttonReborn.setIcon(reset);
					buttonReborn.addActionListener(this);
					imagesReset.add(buttonReborn);
				
				JPanel textesReset = new JPanel();
				textesReset.setBounds(1050,207,140,67);
				textesReset.setBackground(Color.darkGray);
				window.add(textesReset);
				
					resetLabel.setForeground(Color.white);
					resetLabel.setFont(stats);
					resetLabel.setText("Gain : +" + (monstre.getWaveNumber() + myGame.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
					textesReset.add(resetLabel);
					
				//ZONE POUR LE BOUTTON UPGRADE DES FAMILIERS
				
				JPanel imagesUpPet = new JPanel();
				imagesUpPet.setBounds(900,280,112,67);
				imagesUpPet.setLayout(new GridLayout(1,1));
				imagesUpPet.setBackground(Color.darkGray);
				window.add(imagesUpPet);
				
					ImageIcon upPetIcon = new ImageIcon(game.class.getResource("/images/anim upPets.gif"));
					
					buttonUpPets.setBackground(Color.darkGray);
					buttonUpPets.setFocusPainted(false);
					buttonUpPets.setBorder(null);
					buttonUpPets.setIcon(upPetIcon);
					buttonUpPets.addActionListener(this);
					imagesUpPet.add(buttonUpPets);
				
				JPanel textesUpPet = new JPanel();
				textesUpPet.setBounds(1050,287,100,67);
				textesUpPet.setBackground(Color.darkGray);
				window.add(textesUpPet);
				
					upPetsLabel.setForeground(Color.white);
					upPetsLabel.setFont(stats);
					upPetsLabel.setText("" + pet.getPetCostUpgrade());
					textesUpPet.add(upPetsLabel);
				
				//ZONE POUR LE BOUTTON BUYARTEFACT
				
				JPanel imageBuyArtefact = new JPanel();
				imageBuyArtefact.setBounds(900,360,112,67);
				imageBuyArtefact.setLayout(new GridLayout(1,1));
				imageBuyArtefact.setBackground(Color.darkGray);
				window.add(imageBuyArtefact);
				
					ImageIcon artfIcon = new ImageIcon(game.class.getResource("/images/anim newArtefact.gif"));
	
					buttonArtf.setBackground(Color.darkGray);
					buttonArtf.setFocusPainted(false);
					buttonArtf.setBorder(null);
					buttonArtf.setIcon(artfIcon);
					buttonArtf.addActionListener(this);
					imageBuyArtefact.add(buttonArtf);
				
				JPanel textesBuyArtefact = new JPanel();
				textesBuyArtefact.setBounds(1050,367,100,67);
				textesBuyArtefact.setBackground(Color.darkGray);
				window.add(textesBuyArtefact);
				
					artfMoneyCost.setForeground(Color.white);
					artfMoneyCost.setFont(stats);
					artfMoneyCost.setText("" + hero.getArtefactCost());
					textesBuyArtefact.add(artfMoneyCost);
					
			/*
			 * 		PARAMETRES DE LA FENETRE DU CHOIX DE LA CLASSE
			 * 
			 * 		-> SUPERIEUR CENTRE
			 */
				
			JPanel choiceClass = new JPanel();
	        choiceClass.setLayout(new GridLayout(1,3));
	        choiceClass.setBounds(400,20,400,70);
	        choiceClass.setBackground(Color.darkGray);
	        window.add(choiceClass);
	        
				ImageIcon archerIcon = new ImageIcon(game.class.getResource("/images/anim archer.gif"));
				
				archerChoice.setBackground(Color.darkGray);
				archerChoice.setFocusPainted(false);
				archerChoice.setBorderPainted(false);
				archerChoice.addActionListener(this);
				archerChoice.setIcon(archerIcon);
				archerChoice.setEnabled(false);
				choiceClass.add(archerChoice);
				
				ImageIcon berserkIcon = new ImageIcon(game.class.getResource("/images/anim berzerker.gif"));
				
				berserkChoice.setBackground(Color.darkGray);
				berserkChoice.setFocusPainted(false);
				berserkChoice.setBorderPainted(false);
				berserkChoice.addActionListener(this);
				berserkChoice.setIcon(berserkIcon);
				berserkChoice.setEnabled(false);
				choiceClass.add(berserkChoice);
				
				ImageIcon mageIcon = new ImageIcon(game.class.getResource("/images/anim mage.gif"));

				mageChoice.setBackground(Color.darkGray);
				mageChoice.setFocusPainted(false);
				mageChoice.setBorderPainted(false);
				mageChoice.addActionListener(this);
				mageChoice.setIcon(mageIcon);
				mageChoice.setEnabled(false);
				choiceClass.add(mageChoice);
	    	
		/*
		 * 		PARAMETRES DE LA FENETRE INFORMATIONS TEL QUE LES DEGATS, LE SOLDE ET LE SOLDE D'ARTEFACTS.
		 * 
		 * 		-> INFERIEUR DROIT
		 */      
		
		//ZONE POUR L'AFFICHAGE DES DEGATS
		        
        JPanel degats = new JPanel();
        degats.setBounds(950,476,50,70);
        degats.setBackground(Color.darkGray);
        window.add(degats);
        
	        epeeButton.setBackground(Color.darkGray);
			epeeButton.setFocusPainted(false);
			epeeButton.setBorder(null);
			epeeButton.setIcon(epeeDegats);
			degats.add(epeeButton);
		
        JPanel textesDegats = new JPanel();
        textesDegats.setBounds(910,482,280,30);
        textesDegats.setBackground(Color.darkGray);
		window.add(textesDegats);
        
			degatLabel.setForeground(Color.white);
			degatLabel.setFont(stats);
			degatLabel.setText("Degats : " + hero.getDamage());
			textesDegats.add(degatLabel);
		
		//ZONE POUR L'AFFICHAGE DU SOLDE
		
        JPanel coin = new JPanel();
        coin.setBounds(950,520,50,70);
        coin.setBackground(Color.darkGray);
        window.add(coin);
        
	        goldButton.setBackground(Color.darkGray);
			goldButton.setFocusPainted(false);
			goldButton.setBorder(null);
			goldButton.setIcon(bigGold);
			coin.add(goldButton);
		
        JPanel textesCoin = new JPanel();
		textesCoin.setBounds(900,540,270,30);
		textesCoin.setBackground(Color.darkGray);
	    window.add(textesCoin);
	    
		    coinLabel.setForeground(Color.white);
			coinLabel.setFont(stats);
			coinLabel.setText(" " + model.getGold());
			textesCoin.add(coinLabel);
	    
	    //ZONE POUR L'AFFICHAGE DU SOLDE D'ARTEFACT
	    
        JPanel artefactCoin = new JPanel();
        artefactCoin.setBounds(950,570,50,70);
        artefactCoin.setBackground(Color.darkGray);
        window.add(artefactCoin);	
        
	        artefactGoldButton.setBackground(Color.darkGray);
			artefactGoldButton.setFocusPainted(false);
			artefactGoldButton.setBorder(null);
			artefactGoldButton.setIcon(bigArtefactGold);
			artefactCoin.add(artefactGoldButton);
		
	    JPanel textesArtefactCoin = new JPanel();
	    textesArtefactCoin.setBounds(900,590,270,30);
	    textesArtefactCoin.setBackground(Color.darkGray);
		window.add(textesArtefactCoin);

			artefactCoinLabel.setForeground(Color.white);
			artefactCoinLabel.setFont(stats);
			artefactCoinLabel.setText(" " + hero.getArtefactMoney());
			textesArtefactCoin.add(artefactCoinLabel);		
		
			/*
			 * 		PARAMETRES DES FENETRES DE FOND POUR DEFINIR LES COULEURS
			 * 
			 * 		-> PARTOUT
			 */      
			
		JPanel vagueMonstrePanel = new JPanel();
		vagueMonstrePanel.setBounds(10, 10, 350, 200);
		vagueMonstrePanel.setBackground(Color.darkGray);
		window.add(vagueMonstrePanel); 
		
		JPanel ameliorationPanel = new JPanel();
		ameliorationPanel.setBounds(840, 10, 350, 430);
		ameliorationPanel.setBackground(Color.darkGray);
		window.add(ameliorationPanel);
		
		JPanel informationsPanel = new JPanel();
		informationsPanel.setBounds(840, 450, 350, 196);
		informationsPanel.setBackground(Color.darkGray);
		window.add(informationsPanel);
			
		JPanel backgroundGauche = new JPanel();
		backgroundGauche.setBounds(0,0,373,700);
		backgroundGauche.setBackground(Color.lightGray);
		window.add(backgroundGauche);
		
		JPanel backgroundHaut2 = new JPanel();
		backgroundHaut2.setBounds(373,10,455,80);
		backgroundHaut2.setBackground(Color.darkGray);
		window.add(backgroundHaut2);
		
		JPanel backgroundHaut1 = new JPanel();
		backgroundHaut1.setBounds(373,0,455,100);
		backgroundHaut1.setBackground(Color.lightGray);
		window.add(backgroundHaut1);

		JPanel backgroundMilieu = new JPanel();
		backgroundMilieu.setBounds(373,0,455,700);
		backgroundMilieu.setBackground(Color.white);
		window.add(backgroundMilieu);
		
		JPanel backgroundDroite = new JPanel();
		backgroundDroite.setBounds(828,0,373,700);
		backgroundDroite.setBackground(Color.lightGray);
		window.add(backgroundDroite);
		
			
		
		screenMonster(this, monstre);
		changerAttaqueHero(myGame, this);
		ajouterClasses(this, monstre, model.myHero);
		ajouterTempsBoss(model.myMonster);
		window.setVisible(true);
		
	}
	/**
	 * change l'affichage du hero dans la GUI
	 * @param game : permet d'aller prendre l'image du hero 
	 * @param myGUI : GUI a modifier
	 */
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
	
	/**
	 * permet de changer l'icone du monstre selon son attribut et si c'est un boss ou non
	 * @param myGUI : GUI a modifier
	 * @param myMonster : monstre a verifier
	 */
	public void screenMonster(GUI myGUI, Monster myMonster) {
		if(myMonster.getNumber() == myMonster.getbossNumber()) {
			if (myMonster.getAttribute() == "aqua") {
				GUI.buttonMonster.setIcon(GUI.aquaBoss);
			}
			if (myMonster.getAttribute() == "tera") {
				GUI.buttonMonster.setIcon(GUI.teraBoss);
			}
			if (myMonster.getAttribute() == "pyro") {
				GUI.buttonMonster.setIcon(GUI.pyroBoss);
			}
		}
		
		else {
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
	}
	/**
	 * methode qui rajoute les boutons de classe a la GUI
	 * @param myGUI : gui affectee
	 * @param monstre : permet de verifier la vague a laquelle afficher les boutons
	 * @param myHero : permet de verifier qu'aucune class a deja ete choisie
	 */
	public void ajouterClasses(GUI myGUI, Monster monstre, Hero myHero) {
		if(monstre.getWaveNumber() == 2 && myHero.getCheckClass() == 0) {
			myGUI.archerChoice.setEnabled(true);
			myGUI.berserkChoice.setEnabled(true);
			myGUI.mageChoice.setEnabled(true);
		}
	}
	/**
	 * affiche le chrono a l'apparition d'un boss
	 * @param monstre : permet de verifier que le monstre est bien un boss
	 */
	public void ajouterTempsBoss(Monster monstre) {
		if(monstre.getNumber() == monstre.getbossNumber()) {
			tempsBoss1Label.setText("" + model.myMonster.getTimeBoss() + "");
			tempsBoss2Label.setText("" + model.myMonster.getTimeBoss() + "");
		}
		else {
			tempsBoss1Label.setText("");
			tempsBoss2Label.setText("");
		}
	}
	
	
	public static void main(String[] args) {
	}
	
	/**
	 * met a jour la GUI lors de modification dans le model
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		if(model.myMonster.getNumber() == model.myMonster.getbossNumber()) {
			tempsBoss1Label.setText("" + model.myMonster.getTimeBoss() + "");
			tempsBoss2Label.setText("" + model.myMonster.getTimeBoss() + "");
		}
		else {
			tempsBoss1Label.setText("");
			tempsBoss2Label.setText("");
		}
		PVLabel.setText("   " + model.myMonster.getPV() + "   ");
		
		numberMonstreLabel.setText("Monstre n°" + model.myMonster.getNumber());
		vague.setText("Vague n°" + model.myMonster.getWaveNumber());
		
		dmgUPLabel.setText("" + model.getUpgradeMoneyValue());
		newPetLabel.setText("" + model.myPets.getPetCostBuy());		
		resetLabel.setText("Gain : +" + (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1));
		upPetsLabel.setText("" + model.myPets.getPetCostUpgrade());
		artfMoneyCost.setText("" + model.myHero.getArtefactCost());
		
		degatLabel.setText("Degats : " + model.myHero.getDamage());
		coinLabel.setText(" " + model.getGold() );
		artefactCoinLabel.setText(" " + model.myHero.getArtefactMoney());	

	}

	@Override
	public void enableWarning() {
		System.out.println("Vous ne pouvez plus acheter d'artefacts.");	
	}

	@Override
	public void disableWarning() {

	}
	/**
	 * verifie quel bouton a ete active et agit en consuequence
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object x = e.getSource();
		if (x == buttonMonster) {
			controller.attack();
			screenMonster(this, model.myMonster);
			ajouterClasses(this, model.myMonster, model.myHero);
			changerAttaqueHero(model, this);
			ajouterTempsBoss(model.myMonster);
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
			archerChoice.setEnabled(true);
			berserkChoice.setEnabled(false);
			mageChoice.setEnabled(false);
		}
		if (x == berserkChoice) {
			controller.classChoice(3);
			archerChoice.setEnabled(false);
			berserkChoice.setEnabled(true);
			mageChoice.setEnabled(false);
		}
		if (x == mageChoice) {
			controller.classChoice(2);
			archerChoice.setEnabled(false);
			berserkChoice.setEnabled(false);
			mageChoice.setEnabled(true);
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
		if (x == save) {
		}
	}

}



