package Vue;

import java.util.Scanner;

import model.game;

public class Console {
	
	
	public void Scan(game game) {
		Scanner myScan = new Scanner(System.in);
		for (int i = 0; i < 1; ) {
			
			System.out.println("attaque / amélioration / solde ?");

			String userAction = myScan.nextLine();  
			if (userAction.contentEquals("attaque")) {
				game.attack(game.myMonster,game.myHero,game.myArtf);
			}
			if (userAction.contentEquals("amélioration")) {
				game.upgrade(game.myHero);
				System.out.println("vous avez ameliore vos degats");
				System.out.println("Vous infligez maintenant : " + game.myHero.getDamage() + " degats");
			}	
			if (userAction.contentEquals("reborn")) {
				game.reborn(game.myMonster, game.myHero);
			}
			if (userAction.contentEquals("solde")) {
				System.out.println("vous avez " + model.game.gold + " pièces d'or");
			}
		}
}
	
	public static void main(String[] args) {
	    
   	}
}
