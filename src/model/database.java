package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Nathan Debongnie
 *
 */
public class database {
	/**
	 * Cet attribut sert � v�rifier si la table a �t� modifi�e
	 */
	int affectedRows;
			
	/**
	 * M�thode qui renvoie les donn�es des colonnes demand�es de la table avec ou sans condition
	 * @param column : la ou les colonnes dont on veut re�evoir les donn�es
	 * @param table : la table qui contient ces colonnes
	 * @param condition : Si n�cessaire, les condition de la requete 
	 * 			exemple : WHERE ou ORDER BY
	 * @return result : renvoie le resultat de la requete en format ResultSet
	 */
	private ResultSet data (String column,String table, String condition) {
		ResultSet result = null;
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//L'objet result contient le r�sultat de la requ�te SQL
			result = state.executeQuery("SELECT "+ column +" FROM "+ table + condition);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	};
	
	/**
	 * M�thode qui permet la connexion � la db
	 * @return conn : Contient l'adresse de la base de donn�es
	 */
	private Connection connexion (){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://127.0.0.1:5432/Clicker";
			String user = "postgres";
			String passwd = "DBA";   
			conn = DriverManager.getConnection(url,user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	};
	
	/**
	 * M�thode permettant de r�cuperer l'identifiant d'un joueur dans une table
	 * @param name : nom du joueur dont on veut connaitre l'identifiant
	 * @return playerId : l'identifiant du joueur concern�
	 */
	private int getId(String name) {
		ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
		int playerId = 0;
		try {
			if(result.first()){
				playerId = result.getInt(1);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playerId;
	}
	/**
	 * M�thode permettant de r�cuperer le dernier identifiant de la table player
	 * @return id : l'identifiant du dernier joueur de la base de donn�e
	 */
	private int getLastId() {
		int id = 0;
		ResultSet result = data("playerid", "player", "");
		try {
			if(result.first()){
				result.last();
				id = result.getInt(1);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * M�thode permettant d'inserer une nouvelle �quipe dans la base de donn�es
	 * @param team : le nom de l'�quipe qu'il faut rajouter dans la BDD
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int insertTeam (String team) {
		try {
			ArrayList<String> values = new ArrayList<String>();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = data("teamname", "team", "");
			
			while(result.next()) {
				values.add(result.getObject(1).toString());
			}
			values.add(team);
			affectedRows = state.executeUpdate("INSERT INTO team(teamid, teamname) VALUES ("+ values.size() +", '"+ team.toString()+ "')");
			result.close();
			state.close();
		} catch (SQLException ex){
			ex.printStackTrace();
		}
		return affectedRows;
	};
	/**
	 * M�thode permettant de modifier les valeurs d'une �quipe d�j� existante dans la BDD
	 * @param team : l'�quipe qu'on souhaite modifier
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int updateTeam (String team) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String whereTeam = " WHERE team.teamname = '"+team+"'";
			//SELECT playerid, gold, pets FROM hero
			ResultSet resultHero = data("playerid, topwave, topnumber", "monster","");
			//SELECT playerid, teamname FROM player 
			ResultSet resultPlayer = data("playerid, teamname", "player JOIN team ON team.teamid = player.teamid", whereTeam);
			int topwave = 0;
			int topnumber = 0;
			int playerID;
			int heroID;
			resultPlayer.beforeFirst();
			resultHero.beforeFirst();
			while(resultPlayer.next()) {
				resultHero.next();
				playerID = resultPlayer.getInt(1);
				heroID = resultHero.getInt(1);
				if(playerID == heroID) {
					topwave += Integer.parseInt(resultHero.getObject(2).toString());
					topnumber += Integer.parseInt(resultHero.getObject(3).toString());
				}
			}
			affectedRows = state.executeUpdate("UPDATE team SET teamwave = "+topwave+", teammonsternumber ="+topnumber+" WHERE teamname = '"+team+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	/**
	 * M�thode permettant de rajouter un joueur dans la BDD
	 * @param name : nom du joueur � rajouter dans la table
	 * @param password : mot de passe pour la connexion
	 * @param team : nom de l'�quipe dans laquelle le joueur joue
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int insertPlayer (String name, String password, String team) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int id = getLastId()+1;
			String whereTeam = " WHERE teamname = '"+team+"'";
			ResultSet result = data("teamid", "team", whereTeam);
			result.next();
			int teamid = Integer.parseInt(result.getObject(1).toString());
				affectedRows = state.executeUpdate("INSERT INTO player(playerid, playername, playerpassword, teamid) "
					+" VALUES ("+ id +", '"+ name.toString() +"', '"+ password.toString() +"', "+ teamid +")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	/**
	 * M�thode permettant de modifier les donn�es (le moment de la derni�re connection) d'un joueur en particulier
	 * @param name : nom du joueur que l'on souhaite modifier
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int updatePlayer(String name) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE player SET lastconnexion = CURRENT_TIMESTAMP WHERE playername = '"+name+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	/**
	 * M�thode permettant de rajouter les donn�es appartenant au joueur dans la table hero
	 * @param name : nom du joueur que l'on souhaite ajouter dans la BDD
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int insertHero(String name) {
		game game = new game();
		Hero hero = new Hero();
		Pets pets = new Pets();
		Artefact artefact = new Artefact();
		int id = getLastId();
		int gold = game.getGold();
		int damage = hero.getDamage();
		int pet = pets.getPetNumber() ; 
		int artefactMoney = hero.getArtefactMoney(); 
		ArrayList<String> currentArtefact = artefact.getCurrentArtefacts();
		String playerArtefacts = "";
		for(String i : currentArtefact) {
			playerArtefacts.concat(i);
			playerArtefacts.concat(" ");
		}
		
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO hero VALUES (" + id + ", " + gold + ", " + damage + ", " + pet + ", " + artefactMoney + ", '" + playerArtefacts + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	/**
	 * M�thode permettant de modifier les donn�es concernant le hero d'un joueur
	 * @param name : nom du joueur concern� par la modification
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int updateHero(String name) {
		game game = new game();
		Hero hero = new Hero();
		Pets pets = new Pets();
		Artefact artefact = new Artefact();
		
		int gold = game.getGold();
		int damage = hero .getDamage();
		int pet = pets.getPetNumber() ; 
		//int artefactMoney = hero.getArtefactMoney(); 
		
		//Changer artefact en TEXT[] dans la db ???
		ArrayList<String> currentArtefact = artefact.getCurrentArtefacts();
		String playerArtefacts = "";	
		for(String i : currentArtefact) {
			playerArtefacts.concat(i);
			playerArtefacts.concat(" ");
		}
		try {
			int playerId = getId(name);
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE hero SET gold ="+gold+", damage ="+damage+", pets = "+pet+" WHERE playerid = "+playerId);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	
	/**
	 * M�thode permettant de rajouter les donn�es concernant l'�tat des monstres 
	 * @param name : nom du joueur concern� par la modification
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int insertMonster(String name) {
		Monster monster = new Monster();
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		int topWave = 0;
		int topNumber = 0;
		if(wave>topWave) {
			topWave= wave;
			topNumber = monsterNumber;
		}
		try {
			int playerId = getLastId();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO monster VALUES (" + playerId + ", " + monsterPV + ", " + pvIncrease + ", " + monsterNumber + ", " + goldIncrease + ", " + wave + ", "+topWave+", "+topNumber+")");
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	/**
	 * M�thode permettant de modifier les donn�es concernant l'�tat des monstres
	 * @param name : nom du joueur concern� par la modification
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int updateMonster(String name) {
		Monster monster = new Monster();
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		int topWave = 0;
		int topNumber = 0;
		if(wave>topWave) {
			topWave= wave;
			topNumber = monsterNumber;
		}
		try {
			int playerId = getId(name);
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE monster SET pv=" + monsterPV + ", add=" + pvIncrease + ", number=" + monsterNumber + ", goldincrease=" + goldIncrease + ", wave="+wave+", topwave="+topWave+", topnumber="+topNumber+"WHERE playerid="+playerId);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	
	
	/**
	 * M�thode permettant de rajouter les donn�es concernant l'�tat des familiers
	 * @param name : le joueur concern� 
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int insertPets(String name) {
		Pets pets = new Pets(); 
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			int playerId = getLastId();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO pets VALUES (" + playerId + ", " + petsDamage + ", " + petsIncrease + ", " + petsBuyCost + ", " + petsUpgradeCost + ")");
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	/**
	 * M�thode permettant de modifier les donn�es concernant l'�tat des familiers
	 * @param name : le joueur concern�
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int updatePets(String name) {
		Pets pets = new Pets(); 
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			
			int playerId = getId(name);
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE pets SET damage="+petsDamage + ", add=" + petsIncrease +", buycost=" + petsBuyCost + ", upgradecost=" + petsUpgradeCost +"WHERE playerid="+playerId);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	
	
	/**
	 * M�thode permettant de rajouter un joueur dans une nouvelle �quipe, ainsi que ses donn�es actuelles dans le jeu
	 * @param name : nom du joueur que l'on souhaite rajouter
	 * @param password : mot de passe du joueur � rajouter
	 * @param team : nom de l'�quipe qui doit �tre cr��e et � laquelle le joueur doit appartenir
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int createTeamPlayer(String name, String password, String team) {
		affectedRows = insertTeam(team);
		affectedRows += insertPlayer(name, password, team);
		affectedRows += insertHero(name);
		affectedRows += insertPets(name);
		affectedRows += insertMonster(name);
		affectedRows += updateTeam(team);
		return affectedRows;
	}
	/**
	 * M�thode permettant de rajouter un joueur dans une �quipe existante, ainsi que ses donn�es actuelles dans le jeu
	 * @param name : nom du joueur que l'on souhaite rajouter
	 * @param password : mot de passe du joueur que l'on souhaite rajouter
	 * @param team : nom de l'�quipe � laquelle le joueur doit appartenir
	 * @return affectedRows : nombre de lignes modifi�es dans la BDD
	 */
	private int createPlayer(String name, String password, String team) {
		affectedRows = insertPlayer(name, password, team);
		affectedRows += insertHero(name);
		affectedRows += insertPets(name);
		affectedRows += insertMonster(name);
		affectedRows += updateTeam(team);
		return affectedRows;
	}
	
	/**
	 * M�thode permettant de v�rifier si l'�quipe existe d�j� dans la BDD
	 * @param team : nom de l'�quipe que l'on souhaite rechercher dans la BDD
	 * @return check : 
	 * 				true si l'�quipe existe d�j�
	 * 				false si l'�quipe n'existe pas encore
	 */
	private boolean checkTeam(String team) {
		boolean check = false;
		ResultSet resultTeam = data("*","team","");
		try {
			//si il y a des �quipes dans la table
			if(resultTeam.first()) {
				resultTeam.beforeFirst();
				//tant qu'il y a des �quipes dans la table
				while(resultTeam.next()) {
					//si l'�quipe en param�tre = l'�quipe s�lectionn�e
					if(team.equals(resultTeam.getObject(2))) {
						check = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	/**
	 * M�thode permettant de v�rifier si le joueur existe d�j� dans la BDD et si le mot de passe est correct
	 * @param name : nom du joueur que l'on souhaite retrouver
	 * @param password : mot de passe du joueur que l'on souhaite retrouver
	 * @return check : 
	 * 				true si le joueur existe d�j� et que le mot de passe est correct
	 * 				false si le joueur n'existe pas encore ou/et le mot de passe n'est pas correct
	 */
	private boolean checkPlayer (String name, String password) {
		boolean check = false;
		ResultSet resultPlayer = data("playername, playerpassword", "player", "");
		
		try {
			//si il y a au moins 1 joueur
			if(resultPlayer.first()) {
				//tant qu'il y a des joueurs
				resultPlayer.beforeFirst();
				while(resultPlayer.next()) {
					String resultName = resultPlayer.getObject(1).toString();
					String resultPassword = resultPlayer.getObject(2).toString();
					//Si le mot de passe est correct pour ce joueur
					if(resultName.equals(name) && resultPassword.equals(password)) {
						check = true;
						System.out.println("Player ok");
						return check;
					}
					else if(resultName.equals(name)) {
						System.out.println("Password error");
						return check;
					}
				}
				System.out.println("Player not found");
				return check;
			}
			System.out.println("No player found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	/**
	 * M�thode permetant de v�rifier si le joueur existe dans la base de donn�e sans v�rifier le mot de passe
	 * @param name : nom du joueur que l'on souhaite retrouver
	 * @return check : 
	 * 				true si le joueur existe d�j�
	 * 				false si le joueur n'existe pas encore
	 */
	private boolean checkPlayerName (String name) {
		boolean check = false;
		ResultSet resultPlayer = data("playername", "player", "");
		
		try {
			//tant qu'il y a des joueurs
			resultPlayer.beforeFirst();
			while(resultPlayer.next()) {
				String resultName = resultPlayer.getObject(1).toString();
				//Si le mot de passe est correct pour ce joueur
				if(resultName.equals(name)) {
					check = true;
					return check;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	/**
	 * M�thode permettant de connecter ou d'inscrire un nouveau joueur
	 * @param name : nom du joueur concern�
	 * @param password : mot de passe du joueur concern�
	 * @param team : �quipe dans laquelle le joueur (doit) se trouve(r)
	 * @return connect : 
	 * 				true si l'�quipe existe d�j�
	 * 				false si l'�quipe n'existe pas encore
	 */
	private boolean playerConnection(String name, String password, String team) {
		boolean connect = false;
		boolean checkTeam = checkTeam(team);
		System.out.println(checkTeam);
		boolean checkPlayer = checkPlayer(name, password);
		boolean checkPlayerName = checkPlayerName(name);
		//si l'�quipe existe
		if(checkTeam) {
			//si le joueur existe
			if(checkPlayer) {
				connect = true;
				gameConnection(name);
			}
			//si le joueur existe pas
			else {
				if(!checkPlayerName) {
					//rajouter le joueur (dans toutes les tables)
					createPlayer(name, password, team);
					if(checkPlayer) {
						connect = true;
						gameConnection(name);
					}
				}
			}
		}
		//si l'�quipe existe pas
		else {
			if(!checkPlayerName) {
				//rajouter l'�quipe et le joueur (dans toutes les tables)
				createTeamPlayer(name, password, team);
				if(checkPlayer && checkTeam) {
					connect=true;
					gameConnection(name);
				}
			}
		}
		return connect;
	}
	
	/**
	 * M�thode permettant de connecter le joueur et de r�cuperer son �tat d'avancement dans la BDD
	 * @param name : nom du joueur concern�
	 */
	private void gameConnection(String name) {
		ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'"); 
		game game = new game();
		Hero hero = new Hero();
		Pets pets = new Pets();
		Monster monster = new Monster();
		Artefact artefact = new Artefact();
		try {
			result.first();
			int playerID = result.getInt(1);
			String where = " WHERE playerid = "+playerID;
			ResultSet resultHero = data("*","hero", where);
			ResultSet resultMonster = data("*","monster", where);
			ResultSet resultPets = data("*","pets", where);
			
			resultHero.beforeFirst();
			resultMonster.beforeFirst();
			resultPets.beforeFirst();
			resultHero.next();
			resultMonster.next();
			resultPets.next();
			int gold = resultHero.getInt(2);
			int damage = resultHero.getInt(3);
			int petsCount = resultHero.getInt(4);
			int artefactMoney = resultHero.getInt(5);
			String artefacts = resultHero.getString(6);
			ArrayList<String> artefactList = new ArrayList<String>();
			String[] artefactArray = artefacts.split(" ");
			for(String e : artefactArray) {
				artefactList.add(e);
			};
			
			int pv = resultMonster.getInt(2);
			int pvIncrease = resultMonster.getInt(3);
			int monsterNumber = resultMonster.getInt(4);
			int goldincrease = resultMonster.getInt(5);
			int wave = resultMonster.getInt(6);
			
			int petsDamage = resultPets.getInt(1);
			int petsIncrease = resultPets.getInt(2);
			int petsBuyCost = resultPets.getInt(3);
			int petsUpgradeCost = resultPets.getInt(4);
			
			game.setGold(gold);
			hero.setDamage(damage);
			pets.setPetNumber(petsCount);
			hero.setArtefactMoney(artefactMoney);
			artefact.setCurrentArtefacts(artefactList);
			
			monster.setPV(pv);
			monster.setPvIncrease(pvIncrease);
			monster.setNumber(monsterNumber);
			monster.setGoldIncrease(goldincrease);
			monster.setWaveNumber(wave);
			
			pets.setPetDamages(petsDamage);
			pets.setPetNumberUP(petsIncrease);
			pets.setPetCostBuy(petsBuyCost);
			pets.setPetCostUpgrade(petsUpgradeCost);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * M�thode permettant de mettre � jour l'�tat du joueur avant qu'il ne quitte le jeu
	 * @param name : nom du joueur concern�
	 */
	public void gameDeconnection(String name) {
		String team = "";
		ResultSet result = data("playername, teamname", "player JOIN team ON team.teamid = player.teamid", " WHERE playername = '"+name+"'");
		try {
			result.first();
			team = result.getObject(2).toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		affectedRows = updateTeam(team);
		affectedRows += updatePlayer(name);
		affectedRows += updateHero(name);
		affectedRows += updateMonster(name);
		affectedRows += updatePets(name);
	}
	
	public ArrayList<Object> classement( String ordre){
		ArrayList<Object> classement = new ArrayList<Object>();
		try {
			//classement par �quipe
			if(ordre.equals("team")) {
				ResultSet result = data("teamname AS equipe, teamwave AS vague, teammonsternumber AS monstre", "team", " ORDER BY teamwave DESC, teammonsternumber DESC");
				for(int i = 1;i<result.getMetaData().getColumnCount()+1;i++) {
					classement.add(result.getMetaData().getColumnName(i));
				}
				result.beforeFirst();
				while(!result.isLast()) {
					result.next();
					for(int i = 1; i< result.getMetaData().getColumnCount()+1; i++) {
						classement.add(result.getObject(i));
					}
				}
			}
			else {
				//classement par joueurs
				ResultSet result = data("playername AS joueur, topwave AS vague, topnumber AS monstre", "player", " JOIN monster ON monster.playerid=player.playerid ORDER BY topwave DESC, topnumber DESC");
				result.beforeFirst();
				while(!result.isLast()) {
					result.next();
					for(int i = 1; i< result.getMetaData().getColumnCount()+1; i++) {
						classement.add(result.getObject(i));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return classement;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		database db = new database();
		//db.playerConnection("Paul", "test", "Var Motiv = 1");
	}
}
