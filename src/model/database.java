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
			
	public ResultSet data (String column,String table, String where) {
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
	
	public ResultSetMetaData metaData (ResultSet resultat) {
		ResultSetMetaData resultMeta = null;
		try {
			resultMeta = resultat.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMeta;
	}

	public Connection connexion (){
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
	
	private int playerConnection(String name, String password, String team) {
		try {
			ResultSet result = data("playername, playerpasswoed, teamname", "player", "JOIN team ON player.teamid = team.teamid");
			ResultSet resultPlayer = data("playername, playerpassword", "player", "WHERE playername = '"+ name +"'");
			ResultSet resultTeam = data("*", "team"," WHERE teamname = '"+team+"'");
			System.out.println(resultPlayer.getObject(1).toString());
			
			//Si l'équipe et le joueur existent
			if(resultTeam.first() && resultPlayer.first()) {
				//mot de passe correct
				if(name.equals(resultPlayer.getObject(1).toString()) && password.equals(resultPlayer.getObject(2).toString())) {	
					//récupère les données du joueur et lance le jeu avec les données du joueur et update player (last connection)
					System.out.println("Jeu lancé");
					updatePlayer(name);
					return affectedRows;
				}
				//mot de passe incorrect
				else {
					System.out.println("verifier le mot de passe");
				}
			}					
			//Si l'équipe existe
			else if(resultTeam.first()) {
				while(result.next()) {
					affectedRows = insertTeam(team);
				System.out.println("Team added");
				affectedRows += insertPlayer(name, password, team);
				System.out.println("Player added");
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	private int insertTeam (String team) {
		try {
			ArrayList<String> values = new ArrayList<String>();
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = data("teamname", "team", "");
			//ResultSetMetaData resultMeta = metaData(result);
			while(result.next()) {
				values.add(result.getObject(1).toString());
			}
			System.out.print("Combien d'équipes sont dans la table? "+ values.size() + "\n");
			for(String i : values) {
		       	System.out.println("\n Test egalite : "+ i + " =? " + team);
				if (team.equals(i.toString())) {
					System.out.println("Equipe "+ i +" existe déjà \n");
					result.close();
					state.close();
					return affectedRows;
				}
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

	private int insertPlayer (String name, String password, String team) {
		try {
			Connection connect = connexion();
			int id = 1;
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = data("playerid", "player", "");
			while(result.next()) {
				if(result.isLast()) {
					id = Integer.parseInt(result.getObject(1).toString())+1;
				}
			}
				affectedRows = state.executeUpdate("INSERT INTO player(playerid, playername, playerpassword, teamid) "
					+" VALUES ("+ id +", '"+ name.toString() +"', '"+ password.toString() +"', (SELECT teamid FROM team WHERE teamname = '"+ team.toString() +"'))");
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
	
	private int insertHero(game game, Hero hero, Pets pets, Artefact artefact, String name) {
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
		System.out.println(playerArtefacts);
		try {
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO hero VALUES (" + playerId + ", " + gold + ", " + damage + ", " + pet + ", " + artefactMoney + ", '" + playerArtefacts + "')");
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int updateHero(game game, Hero hero, Pets pets, Artefact artefact, String name) {
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
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE hero SET gold ="+gold+", damage ="+damage+", pets = "+pet+" WHERE playerid = "+playerId);
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	
	private int insertMonster(Monster monster, String name) {
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		try {
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO monster VALUES (" + playerId + ", " + monsterPV + ", " + pvIncrease + ", " + monsterNumber + ", " + goldIncrease + ", " + wave + ")");
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int updateMonster(Monster monster, String name) {
		int monsterPV = monster.getPV();
		int pvIncrease = monster.getPvIncrease(); // = add dans la DB
		int monsterNumber = monster.getNumber();
		int goldIncrease = monster.getGoldIncrease();
		int wave = monster.getWaveNumber();
		try {
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE monster SET pv=" + monsterPV + ", add=" + pvIncrease + ", number=" + monsterNumber + ", goldincrease=" + goldIncrease + ", wave="+wave+"WHERE playerid="+playerId);
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}	

	private int insertPets(Pets pets, String name) {
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsNumber = pets.getPetNumber();
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("INSERT INTO monster VALUES (" + playerId + ", " + petsDamage + ", " + petsIncrease + ", " + petsNumber + ", " + petsBuyCost + ", " + petsUpgradeCost + ")");
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	private int updatePets(Pets pets, String name) {
		int petsDamage = pets.getPetDamages();
		int petsIncrease = pets.getPetCostUpgrade(); // = add dans la DB
		int petsNumber = pets.getPetNumber();
		int petsBuyCost = pets.getPetCostBuy();
		int petsUpgradeCost = pets.getPetCostUpgrade();
		try {
			ResultSet result = data("playerid","player"," WHERE playername = '"+name+"'");
			int playerId;
			result.next();
			playerId = Integer.parseInt(result.getObject(1).toString());
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			affectedRows = state.executeUpdate("UPDATE monster SET damage="+petsDamage + ", add=" + petsIncrease + ", amount=" + petsNumber + ", buycost=" + petsBuyCost + ", upgradecost=" + petsUpgradeCost +"WHERE playerid="+playerId);
			System.out.println(affectedRows);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		ArrayList<String> values = new ArrayList<>();
		database db = new database(); 
		game game = new game();
		Hero hero = new Hero();
		Pets pets = new Pets();
		Monster monster = new Monster();
		Artefact artefact = new Artefact();
		//db.data("playername, playerpassword, teamname", "player JOIN team ON player.teamid = team.teamid", " WHERE playername = 'Swithan'");
        //db.player("InkMonster","pswd","Var Motiv = 2", values);
	    //db.team("Var Motiv = 0", values);   
		//db.updateMonster(monster, "InkMonster");
	    //System.out.println(values);
		db.playerConnection("Swithan", "pswd", "Var Motiv = 0");
	}
}
