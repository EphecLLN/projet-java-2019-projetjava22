package model;

import java.sql.*;
import java.util.ArrayList;

public class database {

	Statement state;
	Connection connect;
	int affectedRows;
		
	//valeures temporaires d'un nouveau joueur (nécessite implémentation de la connexion via les vues)
	String name = "Swithan";
	String pswd = "123abc";
	
	String team = "'Var Motiv=0'";
	int teams = 0;
	
	int gold, damage;
	

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
		ArrayList<String> values = new ArrayList<String>();
		boolean teamCount = false;
		try {
				connect = connexion();
				state = connect.createStatement();

				ResultSet result = data("teamname","team");
				if(result.next()) {
					teamCount = true;
				}
				System.out.print(teamCount);
				//L'objet affectedRows contient le le nombre de lignes modifiées par la requete SQL
				if(teamCount == false) {
					affectedRows = state.executeUpdate("INSERT INTO team(teamname) VALUES ("+ team.toString() + ");", Statement.RETURN_GENERATED_KEYS);
					ResultSet resultat = data("*", "team");
			        ResultSetMetaData resultatMeta = metaData(resultat);
			        while(resultat.next()) {
			        	for (int i = 1; i< resultatMeta.getColumnCount() ; i++) {
			        		System.out.println(resultat.getObject(i));
			        	}
			        }
				}
				else {
					ResultSetMetaData resultMeta = metaData(result);
					while (result.next()) {
						for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
							values.add(result.getObject(i).toString());
							System.out.println(values);
						}
					}
					for(int v = 1; v < values.size(); v++) {
						if (team == values.get(v)) {
							System.out.println("Cette équipe existe déjà");
						}
						else {
							affectedRows = state.executeUpdate("INSERT INTO team(teamname) VALUES ("+ team.toString() + ");", Statement.RETURN_GENERATED_KEYS);
						}
					}
				}
				result.close();
				state.close();
				
			} catch (SQLException | ClassNotFoundException ex){
				ex.printStackTrace();
		}
	};
		
	public ResultSet data (String column,String table) {
		ResultSet result = null;
		try {
			Connection connect = connexion();
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
	
	public ResultSetMetaData metaData (ResultSet resultat) {
		ResultSetMetaData resultMeta = null;
		try {
			resultMeta = resultat.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMeta;
		
	}
	
	public boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM `the_app_users` WHERE `u_uname` =?";
        
        try {
            ps = connexion().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
         return checkUser;
    }
	
	/**
	 * méthode permettant de retrouver l'artefact du joueur dans la DB
	 * @param id : numéro de l'artefact
	 */
	public void getArtefact (int id){
		
		try {
			Connection connect = connexion();
			state = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
			//L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT artefactname FROM artefact WHERE artefactid = "+ id);

			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();

	       
			System.out.println("\n***************************************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
	        	
	    		System.out.println("\n***************************************************");
	    		
	    		while(result.next()){         
	    			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
	    				System.out.print("\t" + result.getObject(i).toString() + "\t |");
	    				
	    				
	    			}
	    			System.out.println("\n-------------------------------------------------");
	    		}
	    		result.close();
	    		state.close();	
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		};

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	     database db = new database(); 
        db.team("'Var Motiv = 1'");   
	};

}