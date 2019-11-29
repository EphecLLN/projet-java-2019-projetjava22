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
	boolean activate10hit = false;
	public void applyArtefacts(Pets pet, Monster monster, Hero jeanClaude, game myGame) {
		for (int i = 0; i<this.currentArtefacts.length ; i++) {
			if (this.currentArtefacts[i] == "doublePet") {
				pet.petNumber = pet.petNumber * 2;
				pet.setPetBuyIncrease(2);
			}
			if (this.currentArtefacts[i] == "doubleDMG") {
				jeanClaude.setDamage(jeanClaude.getDamage() * 2);
			}
			if (this.currentArtefacts[i] == "+5DMG") {
				jeanClaude.setDamage(jeanClaude.getDamage() + 5);
			}
			if (this.currentArtefacts[i] == "-1Boss") {
				monster.setbossNumber(monster.getbossNumber() -1 );
			}
			if (this.currentArtefacts[i] == "every10Hit") {
				if(myGame.nbrClic % 10 == 0) {
					jeanClaude.setDamage(jeanClaude.getDamage() * 5);
				}
				if(myGame.nbrClic % 10 == 1) {
					jeanClaude.setDamage(jeanClaude.getDamage() / 5);
				}
			}
		}
					
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
