/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author lucas Pastori
 * classe regoupant les differents artefacts (pouvoirs supplementaires) 
 * et les activant si il se trouvent dans le bon tableau.
 */
public class Artefact {
	
	private ArrayList<String> currentArtefacts = new ArrayList<String>(); // list des artefact possede
	public String[] noArtefacts = {"doublePet","doubleDMG","+5DMG","-1Boss","every10Hit"};
	private boolean activate10Hit = false; // emp�che la reactivation de l'artefact a chaque achat
	private boolean activate5More = false; // emp�che la reactivation de l'artefact a chaque achat
	private boolean activate2xDmg = false; // emp�che la reactivation de l'artefact a chaque achat
	private boolean activate2xPet = false; // emp�che la reactivation de l'artefact a chaque achat
	 
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

	}

	/**
	 * @return the activate10Hit
	 */
	public boolean isActivate10Hit() {
		return activate10Hit;
	}

	/**
	 * @param activate10Hit the activate10Hit to set
	 */
	public void setActivate10Hit(boolean activate10Hit) {
		this.activate10Hit = activate10Hit;
	}

	/**
	 * @return the activate2xPet
	 */
	public boolean isActivate2xPet() {
		return activate2xPet;
	}

	/**
	 * @param activate2xPet the activate2xPet to set
	 */
	public void setActivate2xPet(boolean activate2xPet) {
		this.activate2xPet = activate2xPet;
	}

	/**
	 * @return the activate5More
	 */
	public boolean isActivate5More() {
		return activate5More;
	}

	/**
	 * @param activate5More the activate5More to set
	 */
	public void setActivate5More(boolean activate5More) {
		this.activate5More = activate5More;
	}

	/**
	 * @return the activate2xDmg
	 */
	public boolean isActivate2xDmg() {
		return activate2xDmg;
	}

	/**
	 * @param activate2xDmg the activate2xDmg to set
	 */
	public void setActivate2xDmg(boolean activate2xDmg) {
		this.activate2xDmg = activate2xDmg;
	}

}
