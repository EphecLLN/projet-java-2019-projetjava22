package model;

import java.sql.*;

public class database {

	Statement state;
	Connection connect;
	int affectedRows;
		
	//valeures temporaires d'un nouveau joueur (nécessite implémentation de la connexion via les vues)
	String name = "Swithan";
	String pswd = "123abc";
		
	int gold, damage;
	
	
	public ResultSet data (String column,String table) {
		ResultSet result = null;
		try {
			connect = connexion();
			state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//L'objet result contient le résultat de la requête SQL
			result = state.executeQuery("SELECT "+ column +" FROM "+ table);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return result;
	};

	public String dataCount (String column, String table) {
		ResultSet resultCount = null;
		String count = "0";
		try {
			Connection connect = connexion();
			state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultCount = state.executeQuery("SELECT COUNT("+ column +") FROM "+ table);
			resultCount.next();
			count = resultCount.getObject(1).toString();
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return count;
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

	
	public void team (String team) {
		try {
			connect = connexion();
			state = connect.createStatement();
			ResultSet result = data("teamname", "team");
	        ResultSetMetaData resultMeta = metaData(result);
	        int teams = Integer.parseInt(dataCount("teamname","team"));
	        int teamId = teams+1;
			//System.out.print("Combien d'équipes sont dans la table? "+ teams +"\n");
			//System.out.println("Test équipes : 0 =? " + teams);
			if(teams == 0) {
				affectedRows = state.executeUpdate("INSERT INTO team(teamid, teamname) VALUES (1,'"+ team.toString() + "');", Statement.RETURN_GENERATED_KEYS);
			}
			else {
		        while(result.next()) {
		        	for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
			        	System.out.println("\n Test egalite : "+result.getObject(i).toString() + " " + team);
						if (team.compareTo(result.getObject(i).toString()) == 0) {
							System.out.println("Equipe existe déjà \n");
							break;
						}
					}
		        }
		        affectedRows = state.executeUpdate("INSERT INTO team(teamid, teamname) VALUES ("+ teamId +", '"+ team.toString()+ "')");
			}
			result.close();
			state.close();
		} catch (SQLException | ClassNotFoundException ex){
			ex.printStackTrace();
		}
	};

	public void player (String name, String password, String team) {
		
	}
	
	/**
	 * méthode permettant de retrouver l'artefact du joueur dans la DB
	 * @param id : numéro de l'artefact
	 * @return 
	 */
	public ResultSetMetaData getArtefact (int id){
		ResultSetMetaData resultMeta = null;
		try {
			Connection connect = connexion();
			state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT artefactname FROM artefact WHERE artefactid = "+ id);

			//On récupère les MetaData
			resultMeta = result.getMetaData();
    		result.close();
    		state.close();	
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return resultMeta;
		};

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	     database db = new database(); 
        db.team("Var Motiv = 4");   
	};

}