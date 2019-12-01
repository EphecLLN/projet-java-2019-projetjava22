package model;

/**
 * @author Matthieu Luyckx
 * classe gérant le héros Mage
 * Heros permettant de gagner 5 secondes supplémentaires pour vaincre les boss
 */

public class Mage extends Hero {
	int checkClassMage = 0;
	
	public int getCheckClassMage() {
		return checkClassMage;
	}
	
	public void setCheckClassMage(int binary) {
		this.checkClassMage = binary;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
