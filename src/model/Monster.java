package model;


/**
 * @author Lucas Pastori
 * classe crï¿½ant les monstres 
 */
public class Monster {
<<<<<<< HEAD
	int PV = 10 ;							//pv du monstre actuel
	int pvIncrease = 10;					//incrementation des pvs
	int Number = 1;							//Numero du monstre dans la vague
	int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	int waveNumber = 1;						//Nombre de monstre tuï¿½s au total
	int goldIncrease = 6;				
=======
	private int PV = 10 ;							//pv du monstre actuel
	private int pvIncrease = 10;					//incrémentation des pvs
	private int Number = 1;							//Numero du monstre dans la vague
	private int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	private int waveNumber = 1;						//Nombre de monstre tués au total
	private int goldIncrease = 6;				
>>>>>>> master
	
	/**
	 * @author Lucas Pastori
	 * mï¿½thode vï¿½rifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die(Monster monstre, game game) {
<<<<<<< HEAD
		if (monstre.PV <= 0 && monstre.Number == monstre.bossNumber) { 	//verifie que le boss est mort
			model.game.gold += monstre.goldIncrease;
			model.game.gold += model.game.gold / 5 ;					//donne un grosse prime dï¿½pendant du montant d'argent que possï¿½de le hï¿½ros actuellement
			monstre.goldIncrease += monstre.goldIncrease;				//augmente le nombre de piï¿½ce que les prochain monstre donnerons
			monstre.pvIncrease += monstre.pvIncrease;					//augmente les pvs des prochains monstres
			
			monstre.waveNumber ++;										//augmente la vague
			monstre.Number = 1;											
			monstre.PV = monstre.pvIncrease;
			System.out.println("vous etes a la vague :" + monstre.waveNumber );
			System.out.println("vous etes au monstre :" + monstre.Number);
			if(monstre.waveNumber == 2) {
				game.heroChoice();
			}
		}
		if (monstre.PV <= 0 && monstre.Number == (monstre.bossNumber -1)) { //prepare le boss
			monstre.Number++;
			monstre.PV = monstre.pvIncrease*3;
			model.game.gold += monstre.goldIncrease;
			System.out.println("vous etes au monstre :" + monstre.Number);
		}
		
		if (monstre.PV <= 0) {
			monstre.PV = monstre.pvIncrease;
			monstre.Number ++;
			model.game.gold += monstre.goldIncrease;	//donne de l'argent a la mort du monstre(ancien goldDrop())
			System.out.println("vous ï¿½tes au monstre :" + monstre.Number);
=======
		if (monstre.getPV() <= 0 && monstre.getNumber() == monstre.bossNumber) { 	//vérifie que le boss est mort
			model.game.gold += monstre.getGoldIncrease();
			model.game.gold += model.game.gold / 5 ;					//donne un grosse prime dépendant du montant d'argent que possède le héros actuellement
			monstre.setGoldIncrease(monstre.getGoldIncrease() + monstre.getGoldIncrease());				//augmente le nombre de pièce que les prochain monstre donnerons
			monstre.setPvIncrease(monstre.getPvIncrease() + monstre.getPvIncrease());					//augmente les pvs des prochains monstres
			
			monstre.setWaveNumber(monstre.getWaveNumber() + 1);										//augmente la vague
			monstre.setNumber(1);											
			monstre.setPV(monstre.getPvIncrease());
			System.out.println("vous êtes à la vague : " + monstre.getWaveNumber() );
			System.out.println("vous êtes au monstre : " + monstre.getNumber());
			System.out.println("vous avez " + model.game.gold + " pièces d'or");
		}
		if (monstre.getPV() <= 0 && monstre.getNumber() == (monstre.bossNumber -1)) { //prépare le boss
			monstre.setNumber(monstre.getNumber() + 1);
			monstre.setPV(monstre.getPvIncrease()*3);
			model.game.gold += monstre.getGoldIncrease();
			System.out.println("vous êtes au monstre : " + monstre.getNumber());
			System.out.println("vous avez " + model.game.gold + " pièces d'or");
		}
		
		if (monstre.getPV() <= 0) {
			monstre.setPV(monstre.getPvIncrease());
			monstre.setNumber(monstre.getNumber() + 1);
			model.game.gold += monstre.getGoldIncrease();	//donne de l'argent à la mort du monstre(ancien goldDrop())
			System.out.println("vous êtes au monstre : " + monstre.getNumber());
			System.out.println("vous avez " + model.game.gold + " pièces d'or");
>>>>>>> master
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
<<<<<<< HEAD
	//la methode goldDrop() a ete directement mise dans die()
=======

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

	//la méthode goldDrop() a été directement mise dans die()
>>>>>>> master
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
