package model;


/**
 * @author Lucas Pastori
 * classe créant les monstres 
 */
public class Monster {
	int PV = 10 ;							//pv du monstre actuel
	int pvIncrease = 10;					//incrémentation des pvs
	int Number = 1;							//Numero du monstre dans la vague
	int bossNumber = 10;					//Nombre de monstre a tuer pour arriver au boss
	int waveNumber = 1;						//Nombre de monstre tués au total
	int goldIncrease = 6;				
	
	/**
	 * @author Lucas Pastori
	 * méthode vérifiant q'un monstre est mort pour le remplacer par un autre
	 * 
	 */
	public void die(Monster monstre, game game) {
		if (monstre.PV <= 0 && monstre.Number == monstre.bossNumber) { 	//vérifie que le boss est mort
			model.game.gold += monstre.goldIncrease;
			model.game.gold += model.game.gold / 5 ;					//donne un grosse prime dépendant du montant d'argent que possède le héros actuellement
			monstre.goldIncrease += monstre.goldIncrease;				//augmente le nombre de pièce que les prochain monstre donnerons
			monstre.pvIncrease += monstre.pvIncrease;					//augmente les pvs des prochains monstres
			
			monstre.waveNumber ++;										//augmente la vague
			monstre.Number = 1;											
			monstre.PV = monstre.pvIncrease;
			System.out.println("vous êtes à la vague :" + monstre.waveNumber );
			System.out.println("vous êtes au monstre :" + monstre.Number);
		}
		if (monstre.PV <= 0 && monstre.Number == (monstre.bossNumber -1)) { //prépare le boss
			monstre.Number++;
			monstre.PV = monstre.pvIncrease*3;
			model.game.gold += monstre.goldIncrease;
			System.out.println("vous êtes au monstre :" + monstre.Number);
		}
		
		if (monstre.PV <= 0) {
			monstre.PV = monstre.pvIncrease;
			monstre.Number ++;
			model.game.gold += monstre.goldIncrease;	//donne de l'argent à la mort du monstre(ancien goldDrop())
			System.out.println("vous êtes au monstre :" + monstre.Number);
		}
	}
	//la méthode goldDrop() a été directement mise dans die()
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

}
