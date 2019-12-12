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
 *	interface console se mettant a jour rï¿½guliï¿½rement
 *	liï¿½e avec l'interface graphique
 */
public class Console extends gameVue implements Observer{

	
	public Console(game model, gameController controller) {
		super(model, controller);
	}
	
	
	
	/**
	 * donne une premiï¿½re fois le texte a l'utilisateur et permet d'activer une mï¿½thode 
	 * en encodant la bonne input dans la console 
	 * @param game classe du modï¿½le affectï¿½e
	 */
	public void Scan(game game) {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // permet de toujours garder le texte au mï¿½me niveau 
			System.out.println("solde : " + model.getGold());
			System.out.println("solde artefact : " + model.myHero.getArtefactMoney());
			System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
			System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
			System.out.println("Choix des heros : " + model.getHeroChoice());
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
			System.out.println("Vous etes ï¿½ la vague : " + model.myMonster.getWaveNumber());
			System.out.println("monstre : " + model.myMonster.getPV() + " pv");
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts() );
			System.out.println("type du monstre : " + model.myMonster.getAttribute());
			System.out.println("-------------------------------------------------------------------------------------"); // crï¿½e une sï¿½paration pour plus de propretï¿½
			System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeMoneyValue() + ") / acheter familier (f) (" + model.myPets.getPetCostBuy() + ") / acheter un artefact (x) (" + model.myHero.getArtefactCost() + ") / redï¿½marrer (r) ("+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1) + ")");
			System.out.println();
					
			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("")) {
				game.attack(game.myMonster,game.myHero,game.myArtf);
			}
			if (userAction.contentEquals("a")) {
				game.upgrade(game.myHero);
				System.out.println("vous avez ameliore vos degats");
				
			}	
			if (userAction.contentEquals("r")) {
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
			if (userAction.contentEquals("x")) {
				game.myHero.buyArtefact(game.myArtf, game);
			}
		}
}
	
	public static void main(String[] args) {
	    
   	}

	@Override
	public void update(Observable o, Object arg) { // remet ï¿½ rï¿½guliï¿½rement l'interface console 
		
		System.out.println("\n\n\n\n");
		System.out.println("solde : " + model.getGold());
		System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
		System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
		System.out.println("Choix des heros : " + model.getHeroChoice());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
		System.out.println("Vous etes ï¿½ la vague : " + model.myMonster.getWaveNumber());
		System.out.println("monstre : " + model.myMonster.getPV() + " pv");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts() );
		System.out.println("type du monstre : " + model.myMonster.getAttribute());
		System.out.println("-------------------------------------------------------------------------------------"); // crï¿½e une sï¿½paration pour plus de propretï¿½
		System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeMoneyValue() + ") / acheter familier (f) (" + model.myPets.getPetCostBuy() + ") / acheter un artefact (x) (" + model.myHero.getArtefactCost() + ") / redï¿½marrer (r) ("+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 +")"));
		
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte"); // prï¿½vient en cas de problï¿½me
		
	}

	@Override
	public void disableWarning() {
		
	}
}
