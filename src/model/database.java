package model;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class database {
	int affectedRows;
		
	//valeures temporaires d'un nouveau joueur (nécessite implémentation de la connexion via les vues)
	String name = "Swithan";
	String pswd = "123abc";
		
	int gold, damage;
	
	public ResultSet data (String column,String table, String where) {
		ResultSet result = null;
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//L'objet result contient le résultat de la requête SQL
			result = state.executeQuery("SELECT "+ column +" FROM "+ table + where);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return result;
	};
	
	public int dataCount (Connection connect, String column, String table, String where) {
		ResultSet resultCount = null;
		int counter = 0;
		try {
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultCount = state.executeQuery("SELECT COUNT("+ column +") FROM "+ table + where);
			resultCount.next();
			counter = Integer.parseInt(resultCount.getObject(1).toString());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
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

	public Connection connexion () throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/Clicker";
	    String user = "postgres";
	    String passwd = "DBA";   
	    Connection conn;
		conn = DriverManager.getConnection(url,user, passwd);
		return conn;
	};
	
	private int team (String team, ArrayList<String> values) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = data("teamname", "team", "");
			//ResultSetMetaData resultMeta = metaData(result);
			while(result.next()) {
				values.add(result.getObject(1).toString());
			}
			System.out.print("Combien d'équipes sont dans la table? "+ values.size() + "\n");
			result.beforeFirst();
			System.out.println("next "+ result.next());
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
		} catch (SQLException | ClassNotFoundException ex){
			ex.printStackTrace();
		}
		return affectedRows;
	};

	private Object player (String name, String password, String team, ArrayList<String> values) {
		try {
			Connection connect = connexion();
			Statement state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = data("playername, playerid", "player", "");
			//ResultSet teamResult = data("teamid, teamname", "team", "");
			//ResultSetMetaData resultMeta = result.getMetaData();
			while(result.next()) {
				values.add(result.getObject(1).toString());
			}
			System.out.println(values);
			//inscription ou connexion
			for (String i : values) {
				if(name.equals(i.toString())) {
					System.out.println("Joueur existe déjà");
					playerConnection(name, password);
					result.close();
					state.close();
					return null;
				}
			}
			//inscription
			values.add(name);
			System.out.println(values);
			playerInscription(state, values, name, password, team);
			//puis connexion a ce compte
			playerConnection(name, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	private void playerConnection(String name, String password) {
		ResultSet result = data("playerid", "player", " WHERE playername = "+ name.toString());
		
	}

	private void playerInscription (Statement state, ArrayList<String> player, String name, String password, String team) {
		try {
			affectedRows = state.executeUpdate("INSERT INTO player(playerid, playername, playerpassword, teamid) "
					+" VALUES ("+ player.size() +", '"+ name.toString() +"', '"+ password.toString() +"', (SELECT teamid FROM team WHERE teamname = '"+ team.toString() +"'))");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	private void updateHero() {
		int damage = model.myHero.getDamage();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ArrayList<String> values = new ArrayList<>();
		database db = new database(); 
	    //db.metaData(db.data("*", "team"));
	    //db.data("teamname, teamid", "team");
	    //db.data("playername, playerid", "player");
        db.player("MatthFlash","pswd","Var Motiv = 1", values);
	    //db.team("Var Motiv = 0", values);   
	    //System.out.println(values);
	}
}

//Changer artefact dans la db : supprimer la table artefact , remplacer artefactid dans hero par un string : "artefact1, artefact2" ==> séparer la chaine après 