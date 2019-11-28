/**
 * 
 */
package model;

/**
 * @author lucas Pastori
 * classe regoupant les diff�rents art�facts (pouvoirs suppl�mentaires) 
 * et les activant si il se trouvent dans le bon tableau.
 */
public class Artefact {
	String[] currentArtefacts = {"","","","",""};
	String[] noArtefacts = {"doublePet","doubleDMG","+5DMG","-1Boss","every10Hit"};

<<<<<<< HEAD
	public void applyArtefacts(Pets pet, Monster monster, Hero JeanClaude, game myGame) {
		for (int i = 0; i<this.currentArtefacts.length ; i++) {
			if (this.currentArtefacts[i] == "doublePet") {
				pet.petNumber = pet.petNumber * 2;
				pet.petBuyIncrease = 2;
			}
			if (this.currentArtefacts[i] == "doubleDMG") {
				JeanClaude.damage = JeanClaude.damage * 2;
			}
			if (this.currentArtefacts[i] == "+5DMG") {
				JeanClaude.damage += 5;
			}
			if (this.currentArtefacts[i] == "-1Boss") {
				monster.bossNumber -= 1;
			}
			if (this.currentArtefacts[i] == "every10Hit") {
				if(myGame.nbrClic % 10 == 0) {
					JeanClaude.damage = JeanClaude.damage * 5;
				}
				if(myGame.nbrClic % 10 == 1) {
					JeanClaude.damage = JeanClaude.damage / 5;
				}
			}
		}
					
	}
=======
>>>>>>> master
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
