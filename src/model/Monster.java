package model;


/**
 * @author Lucas Pastori
 * classe cr�ant les monstres 
 */
public class Monster {				
	private int PV = 10 ;							//pv du monstre actuel
	private int pvIncrease = 10;					//incr�mentation des pvs
	private int Number = 1;							//Numero du monstre dans la vague
	private int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	private int waveNumber = 1;						//Nombre de monstre tu�s au total
	private int goldIncrease = 6;
	int tempsBoss = 30;
	
	/**
	 * @author Lucas Pastori
	 * m�thode v�rifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die(Monster monstre, game game) {
		if (monstre.PV <= 0 && monstre.Number == monstre.bossNumber) { 	//verifie que le boss est mort
			model.game.gold += monstre.goldIncrease;
			model.game.gold += model.game.gold / 5 ;					//donne un grosse prime d�pendant du montant d'argent que poss�de le h�ros actuellement
			monstre.goldIncrease += monstre.goldIncrease;				//augmente le nombre de pi�ce que les prochain monstre donnerons
			monstre.pvIncrease += monstre.pvIncrease;					//augmente les pvs des prochains monstres
			
			System.out.println("Vous avez battu la vague " + monstre.waveNumber + ". Bravo !");
			monstre.waveNumber ++;										//augmente la vague
			monstre.Number = 1;											
			monstre.PV = monstre.pvIncrease;
			System.out.println("Vague " + monstre.waveNumber + ". Ils sont plus corriaces !");
			System.out.println("Vous etes au monstre  " + monstre.Number + " de la vague n°" + monstre.waveNumber + ".");
			if(monstre.waveNumber == 2) {
				game.heroChoice();
			}
		}
		if (monstre.PV <= 0 && monstre.Number == (monstre.bossNumber -1)) { //prepare le boss
			monstre.Number++;
			monstre.PV = monstre.pvIncrease*3;
			model.game.gold += monstre.goldIncrease;
			System.out.println("Vous etes au boss de la vague n°" + monstre.waveNumber + ". Force à vous !");
		}
		
		if (monstre.PV <= 0) {
			monstre.PV = monstre.pvIncrease;
			monstre.Number ++;
			model.game.gold += monstre.goldIncrease;	//donne de l'argent a la mort du monstre(ancien goldDrop())
			System.out.println("Vous etes au monstre : " + monstre.Number + " de la vague n°" + monstre.waveNumber);
		}
		else {
			System.out.println("il reste " + game.myMonster.getPV() + " pv au monstre");
		}
	}

	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}
	public int getPvIncrease() {
		return pvIncrease;
	}
	public void setPvIncrease(int pvIncrease) {
		this.pvIncrease = pvIncrease;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}
	
	public int getbossNumber() {
		return bossNumber;
	}
	//la methode goldDrop() a ete directement mise dans die()

	public void setbossNumber(int bossnumber) {
		bossNumber = bossnumber;
	}

	public int getWaveNumber() {
		return waveNumber;
	}

	public void setWaveNumber(int waveNumber) {
		this.waveNumber = waveNumber;
	}

	public int getGoldIncrease() {
		return goldIncrease;
	}

	public void setGoldIncrease(int goldIncrease) {
		this.goldIncrease = goldIncrease;
	}
	
	public int getTempsBoss() {
		return tempsBoss;
	}
	
	public void setTempsBoss(int tempsboss) {
		this.tempsBoss = tempsboss;
	}

	//la m�thode goldDrop() a �t� directement mise dans die()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
