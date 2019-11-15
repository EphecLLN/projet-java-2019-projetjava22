package model;

public class Pets {
	
	/**
	 * Cette m�thode sert � am�liorer les d�g�ts des pets
	 * @param petDamage
	 * 		d�g�ts initiaux(avant l'am�lioration) des pets (valeur positive)
	 * @param petIncrease
	 * 		valeur d'am�lioration des d�g�ts
	 * @param gold (valeur strictement positive)
	 * 		solde obtenu par le h�ros, il faut obligatoirement �tre sup�rieur ou �gal � l'am�lioration
	 * 		pour pouvoir effectuer celle-ci (valeur positive)
	 * @param costUpgrade
	 * 		co�t de l'am�lioration, il faut avoir un montant sup�rieur ou �gal de golds pour pouvoir
	 * 		effectuer l'am�lioration (valeur positive)
	 * @return petDamage
	 * 		retourne les d�g�ts des pets apr�s avoir �t� am�lior�s	
	 */
	
	static int upgradePet(int petDamage, int petIncrease, int gold, int costUpgrade) {
		if(gold >= costUpgrade) {
			petDamage = petDamage + petIncrease;
			gold -= 30;
		}
		else {
			System.out.println("Vous n'avez pas assez de gold pour am�liorer.");
		}
		return petDamage;
	}
	
	/**
	 * Cette m�thode sert � calculer les d�g�ts totaux des pets
	 * @param petDamage
	 * 		d�g�ts initiaux(avant l'am�lioration) des pets
	 * @param petNumber
	 * 		nombre de pets poss�d�s par le h�ros
	 * @return damages
	 * 		retourne les d�g�ts totaux des pets	
	 */
	
	static int attackPet(int petDamage, int petNumber) {
		int damages = petDamage * petNumber;
		return damages;
	}
}