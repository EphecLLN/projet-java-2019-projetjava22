package Vue;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;

import Contr�leur.gameController;
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
	public void Scan() {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("\n\n\n\n");
			System.out.println("solde : " + model.getGold());
			System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
			System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
			System.out.println("Vos familiers infligent : " + model.myPets.getPetDamages() + " degats");
			System.out.println("Choix du heros : " + model.getHeroChoice());
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
			System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
			System.out.println("monstre : " + model.myMonster.getPV() + " pv");
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts() );
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.println("type du monstre : " + model.myMonster.getAttribute());
			System.out.println("type d'attaque : " + model.myHero.getAttribute());
			System.out.println("changer d'attribut d'attaque : (\baqua) / (\bpyro) / (\btera)");
			System.out.println("-----------------------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
			System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeMoneyValue() + ")  / acheter un artefact (x) (" + model.myHero.getArtefactCost() + ") / redemarrer (r) ("+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 +")"));
			System.out.println("changer d'attribut d'attaque : (aqua) / (pyro) / (tera)");
			System.out.println("acheter familier (f) (" + model.myPets.getPetCostBuy() + ") / augmenter les degats des familiers (p) (" + model.myPets.getPetCostUpgrade() + ")" );
			
			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("")) {
				controller.attack();
			}
			if (userAction.contentEquals("a")) {
				controller.upgrade();
				System.out.println("vous avez ameliore vos degats");
				
			}	
			if (userAction.contentEquals("r")) {
				controller.reset();
			}
			if (userAction.contentEquals("f")) { 
				controller.oneMorePet();
			}
			if (userAction.contentEquals("p")) {
				controller.upgradePets();
			}
			if (userAction.contentEquals("archer")) {
				controller.classChoice(1);
			}
			if (userAction.contentEquals("mage")) {
				controller.classChoice(2);
			}
			if (userAction.contentEquals("berzerker")) {
				controller.classChoice(3);
			}
			if (userAction.contentEquals("x")) {
				controller.oneMoreArtf();
			}
			if (userAction.contentEquals("aqua")) {
				controller.attributeChoice(1);
			}
			if (userAction.contentEquals("pyro")) {
				controller.attributeChoice(2);
			}
			if (userAction.contentEquals("tera")) {
				controller.attributeChoice(3);
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
		System.out.println("Vos familiers infligent : " + model.myPets.getPetDamages() + " degats");
		System.out.println("Choix du heros : " + model.getHeroChoice());
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
		System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
		System.out.println("monstre : " + model.myMonster.getPV() + " pv");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("artefacts : " + model.myArtf.getCurrentArtefacts() );
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("type du monstre : " + model.myMonster.getAttribute());
		System.out.println("type d'attaque : " + model.myHero.getAttribute());
		System.out.println("changer d'attribut d'attaque : (\baqua) / (\bpyro) / (\btera)");
		System.out.println("-----------------------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
		System.out.println("attaque (enter) / amelioration (a) (" + model.getUpgradeMoneyValue() + ")  / acheter un artefact (x) (" + model.myHero.getArtefactCost() + ") / redemarrer (r) ("+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 +")"));
		System.out.println("acheter familier (f) (" + model.myPets.getPetCostBuy() + ") / augmenter les degats des familiers (p) (" + model.myPets.getPetCostUpgrade() + ")" );
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte"); // pr�vient en cas de probl�me
		
	}

	@Override
	public void disableWarning() {
		
	}

}
