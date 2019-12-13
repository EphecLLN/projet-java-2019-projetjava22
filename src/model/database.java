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
			String whereTeam = " WHERE teamname = '"+team+"'";
			ResultSet resultHero = data("playerid, gold, pets", "hero","");
			ResultSet resultPlayer = data("playerid", "player JOIN team ON team.teamid = player.teamid", whereTeam);
			int teammoney = 0;
			int teampets = 0;
			while(resultPlayer.next()) {
				resultHero.next();
				if(resultPlayer.getObject(1).toString().equals(resultHero.getObject(1).toString())) {
					teammoney += Integer.parseInt(resultHero.getObject(2).toString());
					teampets += Integer.parseInt(resultHero.getObject(3).toString());
				}
			}
			affectedRows = state.executeUpdate("UPDATE team SET teammoney = "+teammoney+", teampets ="+teampets);
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
	
	private int connectionPlayer(String name, String password, String team) {
		
		return affectedRows;
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
		int id = db.getId("Swithan");
		System.out.println(id);
		
		//player, team et password sont à insérer dans l'interface graphique
		
		//a garder dans l'interface graphique
		//db.getTeam()
		
		//a faire pour créer un joueur dans une nouvelle équipe
		//db.insertTeam(team);
		//db.insertPlayer(player, password, team);
		//db.insertHero(player);
		//db.insertMonster(player);
		//db.insertPets(player);
		//db.updateTeam(team);
		
		//a faire pour créer un joueur dans une équipe existante
		//db.insertPlayer(player, password, team);
		//db.insertHero(player);
		//db.insertMonster(player);
		//db.insertPets(player);
		//db.updateTeam(team);
		
		//a faire lors de la connexion
		//db.getPlayer()
		//db.getHero()
		//db.getMonster()
		//db.getPets()
		
		//a faire lors de la deconnexion
		//db.updateHero(player);
		//db.updateMonster(player);
		//db.updatePets(player);
		//db.updateTeam(team);
	}
}
