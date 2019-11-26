package model;


/**
 * @author Lucas Pastori
 * classe créant les monstres 
 */
public class monster {
	int PV = 10 ;							//pv du monstre actuel
	int Increase = 10;					//incrémentation des pvs
	int Number = 1;					//Numero du monstre dans la vague
	int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	int waveNumber = 1;						//Nombre de monstre tués au total
	int goldIncrease = 6;				
	int gold;								//simule Hero.gold pour le test
	
	monster(int PV,int Increase,int Gold,int goldIncrease) { //constructeur pour le test
		this.PV = PV;
		this.Increase = Increase;
		this.goldIncrease = goldIncrease;
		this.gold = Gold;
	}
	/**
	 * @author Lucas Pastori
	 * méthode vérifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die() {
		if (PV <= 0 && Number == bossNumber) { //vérifie que le boss est mort
			gold += goldIncrease;
			gold += gold / 5 ;						//donne un grosse prime dépendant du montant d'argent que possède le héros actuellement
			goldIncrease += goldIncrease; 			//augmente le nombre de pièce que les prochain monstre donnerons

			Increase += Increase;		//augmente les pvs des prochains monstres
			Number = 1;
			PV = Increase;
		}
		if (PV <= 0 && Number == (bossNumber -1)) { //prépare le boss
			Number++;
			PV = Increase*3;
			gold += goldIncrease;
		}
		
		if (PV <= 0) {
			PV = Increase;
			Number ++;
			gold += goldIncrease;	//donne de l'argent à la mort du monstre(ancien goldDrop())
		}
	}
	//la méthode goldDrop() a été directement mise dans die()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
