package model;

/**
 * @author Matthieu Luyckx
 * classe gérant le héros Mage
 * Heros permettant d'avoir une probabilité de 15% de chance de faire un coup critique (=200% de degats)
 */

public class Berzerker extends Hero {
	public Berzerker() {
		this.checkClassBerzerker = 1;
	}
}

