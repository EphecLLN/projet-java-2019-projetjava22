package model;

import java.util.Observable;

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
	
	/**
	 * @author Lucas Pastori
	 * m�thode v�rifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die(Monster monstre, game game) {
		if (monstre.PV <= 0 && monstre.Number == monstre.bossNumber) { 	//verifie que le boss est mort
			game.setGold(game.getGold() + monstre.goldIncrease);
			game.setGold(game.getGold() + game.getGold() / 5) ;					//donne un grosse prime d�pendant du montant d'argent que poss�de le h�ros actuellement
			monstre.goldIncrease += monstre.goldIncrease;				//augmente le nombre de pi�ce que les prochain monstre donnerons
			monstre.pvIncrease += monstre.pvIncrease;					//augmente les pvs des prochains monstres
			
			System.out.println("Vous avez battu la vague " + monstre.waveNumber + ". Bravo !");
			monstre.waveNumber ++;										//augmente la vague
			monstre.Number = 1;											
			monstre.PV = monstre.pvIncrease;
			System.out.println("Vague " + monstre.waveNumber + ". Ils sont plus corriaces !");
			System.out.println("Vous etes au monstre  " + monstre.Number + " de la vague numero " + monstre.waveNumber + ".");
			if(monstre.waveNumber == 2) {
				game.heroChoice();
			}
		}
		if (monstre.PV <= 0 && monstre.Number == (monstre.bossNumber -1)) { //prepare le boss
			monstre.Number++;
			monstre.PV = monstre.pvIncrease*3;
			game.setGold(game.getGold() + monstre.goldIncrease);
			System.out.println("Vous etes au boss. Force à vous !");
		}
		
		if (monstre.PV <= 0) {
			monstre.PV = monstre.pvIncrease;
			monstre.Number ++;
			game.setGold(game.getGold() + monstre.goldIncrease);	//donne de l'argent a la mort du monstre(ancien goldDrop())
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

	//la m�thode goldDrop() a �t� directement mise dans die()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
