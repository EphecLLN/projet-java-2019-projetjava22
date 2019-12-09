package Contr�leur;

import Vue.Console;
import Vue.GUI;
import model.game;

public class principal {

	public static void main(String[] args) {
		//Cr�ation du mod�le
		game myGame = new game();
		//Cr�ation des contr�leurs : Un pour chaque vue
		//Chaque contr�leur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander
		gameController controlCons = new gameController(myGame);
		gameController controlGUI = new gameController(myGame);
		//Cr�ation des vues.
		//Chaque vue doit conna�tre son contr�leur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer
		Console myConsole = new Console(myGame, controlCons);
		GUI myGUI = new GUI(myGame, controlGUI);
		//On donne la r�f�rence � la vue pour chaque contr�leur
		controlCons.addView(myConsole);
		controlGUI.addView(myGUI);
		//genere l'interface graphique 
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		//genere le scanner pour rentrer les commandes dans la console
		myConsole.Scan(myGame);
	}
}
