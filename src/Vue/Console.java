package Vue;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;

import Contrôleur.gameController;
import model.game;
import model.game.PetsDamages;
/**
 * 
 * @author Lucas Pastori
 *	interface console se mettant a jour r�guli�rement
 *	li�e avec l'interface graphique
 */
public class Console extends gameVue implements Observer{

	
	public Console(game model, gameController controller) {
		super(model, controller);
	}
	/**
	 * donne une premi�re fois le texte a l'utilisateur et permet d'activer une m�thode 
	 * en encodant la bonne input dans la console 
	 * @param game classe du mod�le affect�e
	 */
	public void Scan(game game) {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // permet de toujours garder le texte au m�me niveau 
			System.out.println("solde : " + model.getGold());
			System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
			System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
			System.out.println("Choix des heros : " + model.getHeroChoice());
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
			System.out.println("Vous etes � la vague : " + model.myMonster.getWaveNumber());
			System.out.println("monstre : " + model.myMonster.getPV() + " pv");
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts()[0] + " / "+ model.myArtf.getCurrentArtefacts()[1] + " / "+ model.myArtf.getCurrentArtefacts()[2] + " / "+ model.myArtf.getCurrentArtefacts()[3] + " / "+ model.myArtf.getCurrentArtefacts()[4] + " / " );
			System.out.println("-------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
			System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeValue() + ") / acheter familier (f) (" + model.myPets.getPetCostBuy() + ")");
			
			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("")) {
				game.attack(game.myMonster,game.myHero,game.myArtf);
			}
			if (userAction.contentEquals("a")) {
				game.upgrade(game.myHero);
				System.out.println("vous avez ameliore vos degats");
				
			}	
			if (userAction.contentEquals("reborn")) {
				game.reborn(game.myMonster, game.myHero, game.myPets);
			}
			if (userAction.contentEquals("s")) {
			}
			if (userAction.contentEquals("f")) { 
				game.myPets.buyPet(game);
			}
			if (userAction.contentEquals("archer")) {
				game.archerChoice(game.myHero);
			}
			if (userAction.contentEquals("mage")) {
				game.mageChoice(game.myHero);
			}
			if (userAction.contentEquals("berzerker")) {
				game.berzerkerChoice(game.myHero);
			}
		}
}
	
	public static void main(String[] args) {
	    
   	}

	@Override
	public void update(Observable o, Object arg) { // remet � r�guli�rement l'interface console 
		
		System.out.println("\n\n\n\n");
		System.out.println("solde : " + model.getGold());
		System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
		System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
		System.out.println("Choix des heros : " + model.getHeroChoice());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
		System.out.println("Vous etes � la vague : " + model.myMonster.getWaveNumber());
		System.out.println("monstre : " + model.myMonster.getPV() + " pv");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts()[0] + " / "+ model.myArtf.getCurrentArtefacts()[1] + " / "+ model.myArtf.getCurrentArtefacts()[2] + " / "+ model.myArtf.getCurrentArtefacts()[3] + " / "+ model.myArtf.getCurrentArtefacts()[4] + " / " );
		System.out.println("-------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
		System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeValue() + ") / acheter familier (f) (" + model.myPets.getPetCostBuy() + ")");
		
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte"); // pr�vient en cas de probl�me
		
	}

	@Override
	public void disableWarning() {
		
	}
}
