package model;

import java.sql.*;

public class database {
		
	public void addPlayer () {
		
	};
	
	public void insertPlayer () {
		
	};

	public void insertTeam () {
		
	};
	
	public void insertPlayerPets () {
		
	};
	
	public void insertPlayerMonster () {
		
	};

	public void insertPlayerHero () {
		
	};
	
	public static void getArtefact (Connection connect) throws SQLException {
		//Création d'un objet Statement
		Statement state = connect.createStatement();
	    //L'objet ResultSet contient le résultat de la requête SQL
	    ResultSet result = state.executeQuery("SELECT * FROM artefact");
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
	};
	
	public void updatePlayerPets () {
		
	};
	
	public void updatePlayerMonster () {
		
	};

	public void updatePlayerHero () {
		
	};

	public static Connection connexion () throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
	    
	    String url = "jdbc:postgresql://localhost:5432/Clicker";
	    String user = "postgres";
	    String passwd = "DBA";   
	    Connection conn = DriverManager.getConnection(url,user, passwd);	
	    return conn;
	};

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
         getArtefact(connexion());
	};

}