package Vue;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


import Contrôleur.gameController;
import model.game;
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
	 * donne une premiere fois le texte a l'utilisateur et permet d'activer une methode 
	 * en encodant la bonne input dans la console 
	 * (scan la ligne de commande)
	 */
	public void Scan() {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
 
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Solde : " + model.getGold());
			System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
			System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
			System.out.println("Vos familiers infligent : " + model.myPets.getPetDamages() + " degats");
			System.out.println("Choix du heros : " + model.getHeroChoice());
			System.out.println("-----------------------------------------------------------------------------------------------------");
			System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
			System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
			System.out.println("Points de vie restants : " + model.myMonster.getPV() + " PV");
			System.out.println("Temps restant : " + model.myMonster.getTempsBossConsole());
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Artefacts actuels : " + model.myArtf.getCurrentArtefacts() );
			System.out.println("Type du monstre : " + model.myMonster.getAttribute());
			System.out.println("Type du heros : " + model.myHero.getAttributeHero());
			System.out.println("-------------------------------------------------------------------------------------"); // cree une sï¿½paration pour plus de propretï¿½
			System.out.println("Attaque 		(enter)				Passer en type Aqua (w)");
			System.out.println("Amelioration 		(a) (" + model.getUpgradeMoneyValue() + ")			Passer en type Pyro (p)");
			System.out.println("Acheter familier 	(f) (" + model.myPets.getPetCostBuy() + ")			Passer en type Tera (t)");
			System.out.println("Acheter un artefact 	(x) (" + model.myHero.getArtefactCost() + ")			Ameliorer les familers (p) (" + model.myPets.getPetCostBuy() + ")");
			System.out.println("Redemarrer 		(r) (+"+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 + ")"));
			
			/* for(int j=0; j < 5; j++) {
				System.out.println(model.myDatabase.classement(true, "pets"));
			}
			*/
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
			if (userAction.contentEquals("w")) {
				controller.attributeChoice(1);
				GUI.aqua.setEnabled(false);
				GUI.pyro.setEnabled(true);
				GUI.tera.setEnabled(true);
			}
			if (userAction.contentEquals("p")) {
				controller.attributeChoice(2);	
				GUI.aqua.setEnabled(true);
				GUI.pyro.setEnabled(false);
				GUI.tera.setEnabled(true);
			}
			if (userAction.contentEquals("t")) {
				controller.attributeChoice(3);
				GUI.aqua.setEnabled(true);
				GUI.pyro.setEnabled(true);
				GUI.tera.setEnabled(false);
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
	
	/**
	 * met a jour les donnees de la console lors de modification dans le model
	 */
	@Override
	public void update(Observable o, Object arg) { // remet regulierement a jour l'interface console 
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Solde : " + model.getGold());
		System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
		System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
		System.out.println("Vos familiers infligent : " + model.myPets.getPetDamages() + " degats");
		System.out.println("Choix du heros : " + model.getHeroChoice());
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
		System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
		System.out.println("Points de vie restants : " + model.myMonster.getPV() + " PV");
		System.out.println("Temps restant : " + model.myMonster.getTempsBossConsole());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Artefacts actuels : " + model.myArtf.getCurrentArtefacts() );
		System.out.println("Type du monstre : " + model.myMonster.getAttribute());
		System.out.println("Type du heros : " + model.myHero.getAttributeHero());
		System.out.println("-------------------------------------------------------------------------------------"); // crï¿½e une sï¿½paration pour plus de propretï¿½
		System.out.println("Attaque 		(enter)				Passer en type Aqua (w)");
		System.out.println("Amelioration 		(a) (" + model.getUpgradeMoneyValue() + ")			Passer en type Pyro (p)");
		System.out.println("Acheter familier 	(f) (" + model.myPets.getPetCostBuy() + ")			Passer en type Tera (t)");
		System.out.println("Acheter un artefact 	(x) (" + model.myHero.getArtefactCost() + ")			Ameliorer les familers (p) (" + model.myPets.getPetCostBuy() + ")");			
		System.out.println("Redemarrer 		(r) (+"+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 + ")"));
	}

	/**
	 * previens en cas de probleme
	 */
	@Override
	public void enableWarning() {
		System.out.println("Alerte"); // prï¿½vient en cas de problï¿½me
		
	}

	@Override
	public void disableWarning() {
		
	}

}
