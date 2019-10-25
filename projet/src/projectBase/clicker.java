/**
 * 
 */
package projectBase;

/**
 * @author lulu 
 *
 */
public class clicker {
	static int degats = 1;
	String equipement = "�p�e";
	static double monstrePV = 10;
	static double monstreValue = 10;
	static double pourcent = 0.01;
	static int gold = 0;
	static int value = 10;
	static int nbrMonstre= 0;
	static int nbrBoss = 10;
	static int goldValue = 6;
	static int petDmg = 0;
	static int petValue = 20;
	
	public static void kill() {
		if (monstrePV <= 0 && nbrMonstre == nbrBoss) {
			goldValue += goldValue;
			monstreValue += monstreValue;
			nbrMonstre = 0;
			monstrePV = monstreValue;
			System.out.println("le monstre est mort");
		}
		if (monstrePV <= 0 && nbrMonstre == (nbrBoss -1)) {
			monstrePV = monstreValue * 3;
			nbrMonstre++;
			System.out.println("le monstre est mort");
		}
		
		if (monstrePV <= 0) {
			monstrePV = monstreValue;
			nbrMonstre ++;
			System.out.println("le monstre est mort");
		}
	}
	
	public static void goldDrop() {
		if (monstrePV <= 0) {
			gold += goldValue;
		}
	}
	
	public static void clic() {
		monstrePV = monstrePV - (degats*( pourcent * 100));
		goldDrop();
		kill();
	}
	
	public static void pet() {
		if (gold >= petValue && petDmg == 0) {
			petDmg += 1;
		}
		if (gold >= petValue) {
			petDmg += petDmg;
		}
	}
	
	public static void upgrade() {
		if (gold >= value) {
			pourcent += 0.01;
			gold -= value;
			value += value % 4;
			System.out.println("vous avez am�lior� vos d�gats");
			System.out.println("Vous infligez maintenant : " + degats*( pourcent * 100) + " d�gats");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		clic();clic();clic();clic();clic();
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		clic();clic();clic();clic();
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		clic();
		System.out.println("Vous possedez : " + gold + " gold");
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();
		System.out.println("Vous possedez : " + gold + " gold");
		upgrade();
		System.out.println("Vous possedez : " + gold + " gold");
		clic();
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		clic();clic();clic();clic();clic();
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();
		System.out.println("le monstre poss�de : " + monstrePV + " PV");
		System.out.println(goldValue);
		clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();
		System.out.println("Vous possedez : " + gold + " gold");
		clic();clic();clic();clic();clic();clic();clic();clic();clic();clic();
		System.out.println("Vous possedez : " + gold + " gold");
	}

}
