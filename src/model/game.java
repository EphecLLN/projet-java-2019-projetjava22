/**
 * 
 */
package model;

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game {
	static int gold = 0;
	Monster monstre = new Monster();
	Hero heroJeu = new Hero();
	
	void attack(Monster monstre,Hero heroJeu) {
		monstre.PV = monstre.PV - heroJeu.damage;
	}
	void upgrade(Hero heroJeu) {
		heroJeu.damage ++ ;
	}
	
	void reborn(Monster monstre,Hero heroJeu) {
		heroJeu.damage = 1;
		monstre.goldIncrease = 6;
		monstre.PV = 10;
		monstre.pvIncrease = 10;
		monstre.goldIncrease = 6;
		monstre.Number = 1;
		monstre.waveNumber = 1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
