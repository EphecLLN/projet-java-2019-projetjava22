package Controleur;

import java.util.Timer;

import Vue.Console;
import Vue.GUI;
import model.game;
import model.game.ArcherPetsDamages;
import model.game.ChronoMonstre;
import model.game.PetsDamages;

public class principal {

	public static void main(String[] args) {
		//Creation du modele
		
		
		
		game myGame = new game();
		//Creation des controleurs : Un pour chaque vue
		//Chaque controleur doit avoir une reference vers le modele pour pouvoir le commander
		gameController controlCons = new gameController(myGame);
		gameController controlGUI = new gameController(myGame);
		//Creation des vues.
		
		// Timer pour les degats des pets
				Timer timerPets = new Timer();
				PetsDamages aille = myGame.new PetsDamages();
				timerPets.schedule(aille, 0, 2000);
				
				// Timer pour les degats supplementaires de la classe Archer
				Timer timerArcher = new Timer();
				ArcherPetsDamages ouille = myGame.new ArcherPetsDamages();
				timerArcher.schedule(ouille, 1000, 2000);
						
				// Timer pour le decompte de 20/25 secondes au moment des boss
				Timer timerBoss = new Timer();
				ChronoMonstre chrono = myGame.new ChronoMonstre();
				timerBoss.schedule(chrono, 0, 1000);
				
		//Chaque vue doit connaitre son controleur et avoir une reference vers le modele pour pouvoir l'observer
		Console myConsole = new Console(myGame, controlCons);
		GUI myGUI = new GUI(myGame, controlGUI);
		//On donne la reference a la vue pour chaque controleur
		controlCons.addView(myConsole);
		controlGUI.addView(myGUI);
		//genere l'interface graphique 
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		//genere le scanner pour rentrer les commandes dans la console
		myConsole.Scan();
		
	}
}
