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
				model.attack(model.myMonster,model.myArtf, model.myHero.getDamage(), model.myHero.getAttribute(), model.myMonster.getAttribute());
			}
			if (userAction.contentEquals("a")) {
				model.upgrade(model.myHero,model.myHero.getConstUpgradeDamage());
				System.out.println("vous avez ameliore vos degats");
				
			}	
			if (userAction.contentEquals("r")) {
				model.reborn(model.myMonster, model.myHero, model.myPets);
			}
			if (userAction.contentEquals("s")) {
			}
			if (userAction.contentEquals("f")) { 
				model.myPets.buyPet(model, model.getGold(), model.myPets.getPetCostBuy(), model.myPets.getPetNumber());
			}
			if (userAction.contentEquals("archer")) {
				model.archerChoice(model.myHero);
			}
			if (userAction.contentEquals("mage")) {
				model.mageChoice(model.myHero);
			}
			if (userAction.contentEquals("berzerker")) {
				model.berzerkerChoice(model.myHero);
			}
			if (userAction.contentEquals("x")) {
				model.myHero.buyArtefact(model.myArtf, model, model.myHero.getArtefactMoney());
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
