package Contrôleur;

import java.util.Timer;

import Vue.Console;
import Vue.GUI;
import model.game;
import model.game.ArcherPetsDamages;
import model.game.ChronoMonstre;
import model.game.PetsDamages;

public class Principal {

	public static void main(String[] args) {
		//Cr�ation du mod�le
		
		
		
		game myGame = new game();
		//Cr�ation des contr�leurs : Un pour chaque vue
		//Chaque contr�leur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander
		gameController controlCons = new gameController(myGame);
		gameController controlGUI = new gameController(myGame);
		//Cr�ation des vues.
		
		// Timer pour les degats des pets
				Timer timerPets = new Timer();
				PetsDamages aille = myGame.new PetsDamages();
				timerPets.schedule(aille, 0, 2000);
				
				// Timer pour les dégats supplémentaires de la classe Archer
				Timer timerArcher = new Timer();
				ArcherPetsDamages ouille = myGame.new ArcherPetsDamages();
				timerArcher.schedule(ouille, 1000, 2000);
						
				// Timer pour le décompte de 20/25 secondes au moment des boss
				Timer timerBoss = new Timer();
				ChronoMonstre chrono = myGame.new ChronoMonstre();
				timerBoss.schedule(chrono, 0, 1000);
				
		//Chaque vue doit conna�tre son contr�leur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer
		Console myConsole = new Console(myGame, controlCons);
		GUI myGUI = new GUI(myGame, controlGUI);
		//On donne la r�f�rence � la vue pour chaque contr�leur
		controlCons.addView(myConsole);
		controlGUI.addView(myGUI);
		//genere l'interface graphique 
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		//genere le scanner pour rentrer les commandes dans la console
		myConsole.Scan();
		
	}
}
