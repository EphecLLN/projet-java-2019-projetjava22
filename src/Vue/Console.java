package Vue;

import java.util.Scanner;
import java.util.Timer;

import model.game;
import model.game.PetsDamages;

public class Console {
	
	
	public void Scan(game game) {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("attaque / amelioration (" + game.getUpgradeValue() + ") / acheter familier (" + game.myPets.getPetCostBuy() + ") / solde : " + game.getGold());

			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("attaque")) {
				game.attack(game.myMonster,game.myHero,game.myArtf);
			}
			if (userAction.contentEquals("amelioration")) {
				game.upgrade(game.myHero);
				System.out.println("vous avez ameliore vos degats");
				System.out.println("Vous infligez maintenant : " + game.myHero.getDamage() + " degats");
			}	
			if (userAction.contentEquals("reborn")) {
				game.reborn(game.myMonster, game.myHero);
			}
			if (userAction.contentEquals("solde")) {
				System.out.println("vous avez " + model.game.gold + " pieces d'or");
			}
			if (userAction.contentEquals("acheter pet")) {
				game.myPets.buyPet();
				System.out.println("Vous avez acquéri un nouveau familier.");
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
}
