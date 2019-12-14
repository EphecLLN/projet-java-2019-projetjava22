package model;

import java.util.Observable;

import Vue.GUI;

/**
 * @author Lucas Pastori
 * classe cr�ant les monstres 
 */
public class Monster extends Observable {				
	private int PV = 10 ;							//pv du monstre actuel
	private int pvIncrease = 10;					//incr�mentation des pvs
	private int Number = 1;							//Numero du monstre dans la vague
	private int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	private int waveNumber = 1;						//Nombre de monstre tu�s au total
	private int goldIncrease = 6;
	private String attribute = "aqua";
	private int tempsBoss = 20;
	
	/**
	 * @author Lucas Pastori
	 * Methode verifiant a chaque clic si le monstre est mort et les actions que celui-ci devra effectuer
	 * en fonction de certaines conditions
	 * @param monstre	renvoie la classe Monstre dans le but de verifier les differents parametres pour choisir
	 * l'action a effectuer
	 * @param game		renvoie la classe game qui permettra d'incrementer l'argent du joueur a chaque foi qu'un
	 * monster meurt
	 * @see Monster
	 * 
	 */
	public void die(Monster monstre, game game) {
		if (monstre.PV <= 0 && monstre.Number == monstre.bossNumber) { 	//verifie que le boss est mort
			game.setGold(game.getGold() + monstre.goldIncrease);
			game.setGold(game.getGold() + game.getGold() / 5) ;					//donne un grosse prime d�pendant du montant d'argent que poss�de le h�ros actuellement
			monstre.goldIncrease += monstre.goldIncrease;				//augmente le nombre de pi�ce que les prochain monstre donnerons
			monstre.pvIncrease += monstre.pvIncrease;					//augmente les pvs des prochains monstres
			monstre.waveNumber ++;										//augmente la vague
			monstre.Number = 1;											
			monstre.PV = monstre.pvIncrease;
			if(monstre.waveNumber == 2) {
				game.heroChoice();
			}
			randomMonster();
			
			if(game.myHero.getCheckClass() == 2) {
				monstre.tempsBoss = 25;
			}
			else {
				monstre.tempsBoss = 20;
			}
			
		}
		if (monstre.PV <= 0 && monstre.Number == (monstre.bossNumber -1)) { //prepare le boss
			monstre.Number++;
			monstre.PV = monstre.pvIncrease*3;
			game.setGold(game.getGold() + monstre.goldIncrease);
			System.out.println("Vous etes au boss. Force à vous !");
			randomMonster();
		}
		
		if (monstre.PV <= 0) {
			monstre.PV = monstre.pvIncrease;
			monstre.Number ++;
			game.setGold(game.getGold() + monstre.goldIncrease);	//donne de l'argent a la mort du monstre(ancien goldDrop())
			randomMonster();
		}
	}
	
	/**
	 */
	
	public void randomMonster() {
		int x = ((int) ((Math.random() * 100) % 3));
		if (x == 0) {
			attribute = "aqua";
			GUI.buttonMonster.setIcon(GUI.slimeBleu);
		}
		if (x == 1) {
			attribute = "tera";
			GUI.buttonMonster.setIcon(GUI.slimeVert);
		}
		if (x == 2) {
			attribute = "pyro";
			GUI.buttonMonster.setIcon(GUI.slimeRouge);
		}
	}
	
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
		setChanged();
        notifyObservers();
	}
	public int getPvIncrease() {
		return pvIncrease;
	}
	public void setPvIncrease(int pvIncrease) {
		this.pvIncrease = pvIncrease;
		setChanged();
        notifyObservers();
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
		setChanged();
        notifyObservers();
	}
	
	public int getbossNumber() {
		return bossNumber;
	}
	//la methode goldDrop() a ete directement mise dans die()

	public void setbossNumber(int bossnumber) {
		bossNumber = bossnumber;
		setChanged();
        notifyObservers();
	}

	public int getWaveNumber() {
		return waveNumber;
	}

	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
		setChanged();
        notifyObservers();
	}

	public int getGoldIncrease() {
		return goldIncrease;
	}

	public void setGoldIncrease(int goldIncrease) {
		this.goldIncrease = goldIncrease;
		setChanged();
        notifyObservers();
	}
	
	public int getTempsBoss() {
		return tempsBoss;
	}
	
	public void setTempsBoss(int tempsboss) {
		this.tempsBoss = tempsboss;
		setChanged();
        notifyObservers();
	}

	//la m�thode goldDrop() a �t� directement mise dans die()
	/**
	 * @param args
	 */

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
		setChanged();
        notifyObservers();
	}
	
	public static void main(String[] args) {
		
	}
}
