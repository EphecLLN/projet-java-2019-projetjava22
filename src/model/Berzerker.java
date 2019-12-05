package model;

/**
 * @author Matthieu Luyckx
 * classe gérant le héros Mage
 * Heros permettant d'avoir une probabilité de 15% de chance de faire un coup critique (=200% de degats)
 */

public class Berzerker extends Hero {
	int checkClassBerzerker = 0;
	
	public int getCheckClassBerzerker() {
		return checkClassBerzerker;
	}
	
	public void setCheckClassBerzerker(int binary) {
		this.checkClassBerzerker = binary;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
