package model;


/**
 * @author Lucas Pastori
 * classe créant les monstres 
 */
public class monster {
	int monsterPV;							//pv du monstre actuel
	int monsterIncrease;					//incrémentation des pvs
	int monsterNumber = 1;					//Numero du monstre dans la vague
	int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	int waveNumber = 1;						//Nombre de monstre tués au total
	int goldIncrease;				
	int gold;								//simule Hero.gold pour le test
	
	monster(int PV,int Increase,int Gold,int goldIncrease) { //constructeur pour le test
		this.monsterPV = PV;
		this.monsterIncrease = Increase;
		this.goldIncrease = goldIncrease;
		this.gold = Gold;
	}
	/**
	 * @author Lucas Pastori
	 * méthode vérifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die() {
		if (monsterPV <= 0 && monsterNumber == bossNumber) { //vérifie que le boss est mort
			gold += goldIncrease;
			gold += gold / 5 ;						//donne un grosse prime dépendant du montant d'argent que possède le héros actuellement
			goldIncrease += goldIncrease; 			//augmente le nombre de pièce que les prochain monstre donnerons
			monsterIncrease += monsterIncrease;		//augmente les pvs des prochains monstres
			monsterNumber = 1;
			monsterPV = monsterIncrease;
		}
		if (monsterPV <= 0 && monsterNumber == (bossNumber -1)) { //prépare le boss
			monsterNumber++;
			monsterPV = monsterIncrease*3;
			gold += goldIncrease;
		}
		
		if (monsterPV <= 0) {
			monsterPV = monsterIncrease;
			monsterNumber ++;
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
