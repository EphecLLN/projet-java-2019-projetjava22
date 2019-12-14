package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class database {
	int affectedRows;
			
	/**
	 * @param column
	 * @param table
	 * @param where
	 * @return result : renvoie le resultat de la requete
	 */
	private ResultSet data (String column,String table, String where) {
		ResultSet result = null;
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//L'objet result contient le résultat de la requête SQL
			result = state.executeQuery("SELECT "+ column +" FROM "+ table + where);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	};
	private ResultSet orderedData(String column, String table, String order) {
		ResultSet result = null;
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = state.executeQuery("SELECT "+ column + " FROM "+ table + order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	private Connection connexion (){
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/Clicker";
			String user = "postgres";
			String passwd = "DBA";   
			conn = DriverManager.getConnection(url,user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	};
	
	private int getId(String name) {
		ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
		int playerId = 0;
		try {
			if(result.first()){
				playerId = Integer.parseInt(result.getObject(1).toString());	
				playerId += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playerId;
	}
	private int getLastId() {
		int id = 0;
		ResultSet result = data("playerid", "player", "");
		try {
			if(result.first()){
				result.last();
				id = Integer.parseInt(result.getObject(1).toString());
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
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
	private int updateTeam (String team) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String whereTeam = " WHERE team.teamname = '"+team+"'";
			//SELECT playerid, gold, pets FROM hero
			ResultSet resultHero = data("playerid, gold, pets", "hero","");
			//SELECT playerid, teamname FROM player 
			ResultSet resultPlayer = data("playerid, teamname", "player JOIN team ON team.teamid = player.teamid", whereTeam);
			int teammoney = 0;
			int teampets = 0;
			int playerID;
			int heroID;
			resultPlayer.beforeFirst();
			resultHero.beforeFirst();
			while(resultPlayer.next()) {
				resultHero.next();
				playerID = Integer.parseInt(resultPlayer.getObject(1).toString());
				heroID = Integer.parseInt(resultHero.getObject(1).toString());
				if(playerID == heroID) {
					teammoney += Integer.parseInt(resultHero.getObject(2).toString());
					teampets += Integer.parseInt(resultHero.getObject(3).toString());
				}
				affectedRows = state.executeUpdate("UPDATE team SET teammoney = "+teammoney+", teampets ="+teampets+" WHERE teamname = '"+team+"'");
			}
			//System.out.println(team + " : "+teammoney+" "+teampets);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

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
	
	private int insertMonster(String name) {
		Monster monster = new Monster();
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		try {
			int playerId = getLastId();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO monster VALUES (" + playerId + ", " + monsterPV + ", " + pvIncrease + ", " + monsterNumber + ", " + goldIncrease + ", " + wave + ")");
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int updateMonster(String name) {
		Monster monster = new Monster();
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		try {
			int playerId = getId(name);
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE monster SET pv=" + monsterPV + ", add=" + pvIncrease + ", number=" + monsterNumber + ", goldincrease=" + goldIncrease + ", wave="+wave+"WHERE playerid="+playerId);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	
	
	private int insertPets(String name) {
		Pets pets = new Pets(); 
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsNumber = pets.getPetNumber();
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			int playerId = getLastId();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO pets VALUES (" + playerId + ", " + petsDamage + ", " + petsIncrease + ", " + petsNumber + ", " + petsBuyCost + ", " + petsUpgradeCost + ")");
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int updatePets(String name) {
		Pets pets = new Pets(); 
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsNumber = pets.getPetNumber();
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			
			int playerId = getId(name);
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE pets SET damage="+petsDamage + ", add=" + petsIncrease + ", amount=" + petsNumber + ", buycost=" + petsBuyCost + ", upgradecost=" + petsUpgradeCost +"WHERE playerid="+playerId);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	
	
	private int createTeamPlayer(String name, String password, String team) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = insertTeam(team);
			affectedRows += insertPlayer(name, password, team);
			affectedRows += insertHero(name);
			affectedRows += insertPets(name);
			affectedRows += insertMonster(name);
			affectedRows += updateTeam(team);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int createPlayer(String name, String password, String team) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = insertPlayer(name, password, team);
			affectedRows += insertHero(name);
			affectedRows += insertPets(name);
			affectedRows += insertMonster(name);
			affectedRows += updateTeam(team);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return affectedRows;
	}
	
	private boolean checkTeam(String team) {
		boolean check = false;
		ResultSet resultTeam = data("*","team","");
		try {
			//si il y a des équipes dans la table
			if(resultTeam.first()) {
				resultTeam.beforeFirst();
				//tant qu'il y a des équipes dans la table
				while(resultTeam.next()) {
					//si l'équipe en paramètre = l'équipe sélectionnée
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
	private boolean checkPlayerName (String name) {
		boolean check = false;
		ResultSet resultPlayer = data("playername", "player", "");
		
		try {
			//si il y a au moins 1 joueur
			//if(resultPlayer.first()) {
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
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	private boolean gameConnection(String name, String password, String team) {
		boolean connect = false;
		boolean checkTeam = checkTeam(team);
		System.out.println(checkTeam);
		boolean checkPlayer = checkPlayer(name, password);
		boolean checkPlayerName = checkPlayerName(name);
		//si l'équipe existe
		if(checkTeam) {
			//si le joueur existe
			if(checkPlayer) {
				connect = true;
			}
			//si le joueur existe pas
			else {
				if(!checkPlayerName) {
					//rajouter le joueur (dans toutes les tables)
					createPlayer(name, password, team);
					if(checkPlayer) {
						connect = true;
					}
				}
			}
		}
		//si l'équipe existe pas
		else {
			if(!checkPlayerName) {
				//rajouter l'équipe et le joueur (dans toutes les tables)
				createTeamPlayer(name, password, team);
				if(checkPlayer && checkTeam) {
					connect=true;
				}
			}
		}
		return connect;
	}
	
	public ArrayList<String> classement(ArrayList<String> classement, boolean table, String ordre){
		if(table == true) {
			//classement par équipe
			if(ordre.equals("pets")) {
				ResultSet result = orderedData("*", "team", "ORDER BY teampets");
				
			}
		}
		else {
			//classement par joueurs
		}
		return classement;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		database db = new database(); 
				
		//player, team et password sont à insérer dans l'interface graphique
		
		//a garder dans l'interface graphique ==> classement : boutons pour choisir le paramètre pour ordoner
			//db.getClassement();
				//boolean team : true=classement/équipe false=classement/joueur
				//int choice :  0=pets 1=money 2=artefactmoney 3=wave
		
		db.gameConnection("MatthFlash", "abc123", "Var Motiv = 0");
		
		//db.updateTeam("Var Motiv = 0");
		
		
		//a faire pour créer un joueur dans une nouvelle équipe createTeamPlayer ==> appelé dans gameConnection() SI getTeam = false
		//db.insertTeam(team);
		//db.insertPlayer(player, password, team);
		//db.insertHero(player);
		//db.insertMonster(player);
		//db.insertPets(player);
		//db.updateTeam(team);
		
		//a faire pour créer un joueur dans une équipe existante createPlayer() ==> appelé dans gameConnection SI getTeam() = true
		//db.insertPlayer(player, password, team);
		//db.insertHero(player);
		//db.insertMonster(player);
		//db.insertPets(player);
		//db.updateTeam(team);
		
		//a faire lors de la connexion : gameConnection
		//db.getPlayer()
		//db.getHero()
		//db.getMonster()
		//db.getPets()
		
		//a faire lors de la deconnexion : gameDeconnection()
		//db.updateHero(player);
		//db.updateMonster(player);
		//db.updatePets(player);
		//db.updateTeam(team);
	}
}
