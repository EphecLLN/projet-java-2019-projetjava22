package Vue;

import java.util.Scanner;
import java.util.Timer;

import model.game;
import model.game.PetsDamages;

public class Console {
	
	
	public void Scan(game game) {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("attaque (enter) / amelioration (a) (" + game.getUpgradeValue() + ") / acheter familier (f) (" + game.myPets.getPetCostBuy() + ") / \nsolde : " + game.getGold());

			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("")) {
				game.attack(game.myMonster,game.myHero,game.myArtf);
			}
			if (userAction.contentEquals("a")) {
				game.upgrade(game.myHero);
				System.out.println("vous avez ameliore vos degats");
				System.out.println("Vous infligez maintenant : " + game.myHero.getDamage() + " degats");
			}	
			if (userAction.contentEquals("reborn")) {
				game.reborn(game.myMonster, game.myHero, game.myPets);
			}
			if (userAction.contentEquals("s")) {
				System.out.println("vous avez " + model.game.gold + " pieces d'or");
			}
			if (userAction.contentEquals("f")) {
				game.myPets.buyPet();
				System.out.println("Vous avez acquéri un nouveau familier.");
			}
			if (userAction.contentEquals("archer")) {
				game.archerChoice();
				System.out.println("AttackSpeed fixee a 0.7/sec");
			}
		}
}
	
	public static void main(String[] args) {
	    
   	}
}
