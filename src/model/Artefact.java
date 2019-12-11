/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author lucas Pastori
 * classe regoupant les diff�rents art�facts (pouvoirs suppl�mentaires) 
 * et les activant si il se trouvent dans le bon tableau.
 */
public class Artefact {
	
	private ArrayList<String> currentArtefacts = new ArrayList<String>(); 
	public String[] noArtefacts = {"doublePet","doubleDMG","+5DMG","-1Boss","every10Hit"};
	boolean activate10hit = false;
	
	/**
	 * @return the currentArtefacts
	 */
	public ArrayList<String> getCurrentArtefacts() {
		return currentArtefacts;
	}

	/**
	 * @param currentArtefacts the currentArtefacts to set
	 */
	public void setCurrentArtefacts(ArrayList<String> currentArtefacts) {
		this.currentArtefacts = currentArtefacts;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
