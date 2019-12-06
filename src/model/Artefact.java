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
	private String[] currentArtefacts = {"","","","",""};
	String[] noArtefacts = {"doublePet","doubleDMG","+5DMG","-1Boss","every10Hit"};
	boolean activate10hit = false;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String[] getCurrentArtefacts() {
		return currentArtefacts;
	}

	public void setCurrentArtefacts(String[] currentArtefacts) {
		this.currentArtefacts = currentArtefacts;
	}

}
