package model;

import java.sql.*;

public class database {
	
	//valeures temporaires d'un nouveau joueur (nécessite implémentation de la connexion via les vues)
	String name = "Swithan";
	String pswd = "123abc";
	
	static String team = "'Var Motiv=0'";
	int teams = 0;
	
	int gold, damage;
	

	public static Connection connexion () throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://localhost:5432/Clicker";
	    String user = "postgres";
	    String passwd = "DBA";   
	    Connection conn;
		conn = DriverManager.getConnection(url,user, passwd);
		return conn;
	};
	
	public void updateDB () {
		
	};
	
	public void insertDB () {
		
	};
	
	public static void createTeam (int teamCount, String team) {
		try {
			if(teamCount == 0) {
				Statement state;
				Connection connect = connexion();
				state = connect.createStatement();
				
				long id = 0;
			
		    //L'objet affectedRows contient le résultat de la requête SQL
			int affectedRows = state.executeUpdate("INSERT INTO team(teamname) VALUES ("+ team + ");", Statement.RETURN_GENERATED_KEYS);
		    
		    
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = state.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
		    ResultSet result = state.executeQuery("SELECT * FROM team WHERE teamname = "+ team);
		    ResultSetMetaData resultMeta = result.getMetaData();
		    
		    System.out.println(id);
		    while(result.next()){         
		        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		          System.out.print("\t" + result.getObject(i).toString() + "\t |");
		            
		        System.out.println("\n---------------------------------");

		      }

		      result.close();
		      state.close();
			};
		} catch (SQLException | ClassNotFoundException ex){
			ex.printStackTrace();
		}
	};
	
	public void insertPlayer () {
		
	};
	
	public void insertPlayerPets () {
		
	};
	
	public void insertPlayerMonster () {
		
	};

	public void insertPlayerHero () {
		
	};
	
	
	/**
	 * méthode permettant de retrouver l'artefact du joueur dans la DB
	 * @param id : numéro de l'artefact
	 */
	public static void getArtefact (int id){
		
		//Création d'un objet Statement
		Statement state;
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
         createTeam(0, team);
	};

}