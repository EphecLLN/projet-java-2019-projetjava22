package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class dataBase {

  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver");
         
      String url = "jdbc:postgresql://localhost:5432/Clicker";
      String user = "postgres";
      String passwd = "DBA";
         
      Connection conn = DriverManager.getConnection(url, user, passwd);
         
      //Création d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result = state.executeQuery("SELECT * FROM classement");
      //On récupère les MetaData
      ResultSetMetaData resultMeta = result.getMetaData();
         
      System.out.println("\n**********************************");
      //On affiche le nom des colonnes
      for(int i = 1; i <= resultMeta.getColumnCount(); i++)
        System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
         
      System.out.println("\n**********************************");
         
      while(result.next()){         
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
          System.out.print("\t" + result.getObject(i).toString() + "\t |");
            
        System.out.println("\n---------------------------------");

      }

      result.close();
      state.close();
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}