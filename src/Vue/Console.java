package Vue;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


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
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Solde : " + model.getGold());
			System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
			System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
			System.out.println("Choix des heros : " + model.getHeroChoice());
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
			System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
			System.out.println("Points de vie restants : " + model.myMonster.getPV() + " PV");
			System.out.println("Temps restant : " + model.myMonster.getTempsBossConsole());
			System.out.println("-------------------------------------------------------------------------------------");
			System.out.println("Artefacts actuels : " + model.myArtf.getCurrentArtefacts() );
			System.out.println("Type du monstre : " + model.myMonster.getAttribute());
			System.out.println("Type du heros : " + model.myHero.getAttributeHero());
			System.out.println("-------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
			System.out.println("Attaque 		(enter)				Passer en type Aqua (w)");
			System.out.println("Amelioration 		(a) (" + model.getUpgradeMoneyValue() + ")			Passer en type Pyro (p)");
			System.out.println("Acheter familier 	(f) (" + model.myPets.getPetCostBuy() + ")			Passer en type Tera (t)");
			System.out.println("Acheter un artefact 	(x) (" + model.myHero.getArtefactCost() + ")");
			System.out.println("Redemarrer 		(r) (+"+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 + ")"));
			System.out.println(model.myDatabase.data("*","player",""));
			
			/* for(int j=0; j < 5; j++) {
				System.out.println(model.myDatabase.classement(true, "pets"));
			}
			*/
			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("")) {
				model.attack(model.myMonster,model.myArtf, model.myHero.getDamage(), model.myHero.getAttributeHero(), model.myMonster.getAttribute());
			}
			if (userAction.contentEquals("a")) {
				model.upgrade(model.myHero,model.myHero.getConstUpgradeDamage());
				System.out.println("vous avez ameliore vos degats");
				
			}	
			if (userAction.contentEquals("r")) {
				model.reborn(model.myMonster, model.myHero, model.myPets);
			}
			if (userAction.contentEquals("w")) {
				model.myHero.setAttributeHero("aqua");
			}
			if (userAction.contentEquals("p")) {
				model.myHero.setAttributeHero("pyro");	
			}
			if (userAction.contentEquals("t")) {
				model.myHero.setAttributeHero("tera");
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
	public void update(Observable o, Object arg) { // remet � r�guli�rement l'interface console 
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Solde : " + model.getGold());
		System.out.println("Vous infligez : " + model.myHero.getDamage() + " degats");
		System.out.println("Vous possedez : " + model.myPets.getPetNumber() + " familiers");
		System.out.println("Choix des heros : " + model.getHeroChoice());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Vous etes au monstre : " + model.myMonster.getNumber());
		System.out.println("Vous etes a la vague : " + model.myMonster.getWaveNumber());
		System.out.println("Points de vie restants : " + model.myMonster.getPV() + " PV");
		System.out.println("Temps restant : " + model.myMonster.getTempsBossConsole());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Artefacts actuels : " + model.myArtf.getCurrentArtefacts() );
		System.out.println("Type du monstre : " + model.myMonster.getAttribute());
		System.out.println("Type du heros : " + model.myHero.getAttributeHero());
		System.out.println("-------------------------------------------------------------------------------------"); // cr�e une s�paration pour plus de propret�
		System.out.println("Attaque 		(enter)				Passer en type Aqua (w)");
		System.out.println("Amelioration 		(a) (" + model.getUpgradeMoneyValue() + ")			Passer en type Pyro (p)");
		System.out.println("Acheter familier 	(f) (" + model.myPets.getPetCostBuy() + ")			Passer en type Tera (t)");
		System.out.println("Acheter un artefact 	(x) (" + model.myHero.getArtefactCost() + ")");
		System.out.println("Redemarrer 		(r) (+"+ (model.myMonster.getWaveNumber() + model.getNbrUpgrade() / 10 + model.myPets.getPetNumber() /10 -1 + ")"));
		System.out.println(model.myDatabase.data("*","player",""));
	}

	@Override
	public void enableWarning() {
		System.out.println("Alerte"); // pr�vient en cas de probl�me
		
	}

	@Override
	public void disableWarning() {
		
	}
}
