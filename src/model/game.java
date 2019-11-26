/**
 * 
 */
package model;

/**
 * @author Lucas Pastori
 * classe permettant d'utiliser les autres classes / de faire fonctionner le jeu
 */
public class game {
	
	void attack() {
		monster.PV = monster.PV - Hero.damage;
	}

	void upgrade() {
		Hero.damage ++ ;
	}
	
	void reborn() {
		Hero.damage = 1;
		Hero.gold = 0;
		Hero.goldIncrease = 6;
		monster.PV = 10;
		monster.Increase = 10;
		monster.goldIncrease = 6;
		monster.Number = 1;
		monster.waveNumber = 1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
