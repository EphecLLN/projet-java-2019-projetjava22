package Contrôleur;

import Vue.Console;
import Vue.GUI;
import model.game;

public class principal {

	public static void main(String[] args) {
		//Création du modèle
		game myGame = new game();
		//Création des contrôleurs : Un pour chaque vue
		//Chaque contrôleur doit avoir une référence vers le modèle pour pouvoir le commander
		gameController controlCons = new gameController(myGame);
		gameController controlGUI = new gameController(myGame);
		//Création des vues.
		//Chaque vue doit connaître son contrôleur et avoir une référence vers le modèle pour pouvoir l'observer
		Console myConsole = new Console(myGame, controlCons);
		GUI myGUI = new GUI(myGame, controlGUI);
		//On donne la référence à la vue pour chaque contrôleur
		controlCons.addView(myConsole);
		controlGUI.addView(myGUI);
		//genere l'interface graphique 
		myGUI.genererUI(myGame.myMonster, myGame.myHero, myGame.myPets, myGame);
		//genere le scanner pour rentrer les commandes dans la console
		myConsole.Scan(myGame);
	}
}
