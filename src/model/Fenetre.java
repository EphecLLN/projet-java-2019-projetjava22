package model;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre extends JFrame {

public Fenetre(){
  this.setLocationRelativeTo(null);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setTitle("JTable");
  this.setSize(300, 120);

  //Les données du tableau
  /*Object[][] data = {
    {"Cysboy", "28 ans", "1.80 m"},
    {"BZHHydde", "28 ans", "1.80 m"},
    {"IamBow", "24 ans", "1.90 m"},
    {"FunMan", "32 ans", "1.85 m"}
  };*/

  //Les titres des colonnes
  //String  title[] = {"Pseudo", "Age", "Taille"};
  //Nous ajoutons notre tableau à notre contentPane dans un scroll
  //Sinon les titres des colonnes ne s'afficheront pas !
}   

public static void main(String[] args){
  Fenetre fen = new Fenetre();
  database db = new database();
  
  ArrayList<Object> classement = db.classement("team");
  System.out.println(classement);
  String[] title = new String[3];
  boolean d1 = false;
  boolean d2 = false;
  boolean d3 = false;
  boolean d4 = false;
  boolean d5 = false;
  Object[] data1 = new Object[3];
  Object[] data2 = new Object[3];
  Object[] data3 = new Object[3];
  Object[] data4 = new Object[3];
  Object[] data5 = new Object[3];
  Object[][] data = new Object[5][3];
  
  for(int i = 0; i<classement.size();i++) {
	  if(i>15 && classement.size()>i) {
		  data5[i-15] = classement.get(i);
		  d5 = true;
	  }
	  else if(i>12 && classement.size()>i) {
		  data4[i-12] = classement.get(i);
		  d4 = true;
	  }
	  else if(i>8 && classement.size()>i) {
		  data3[i-9] = classement.get(i);
		  d3 = true;
	  }
	  else if(i>5 && classement.size()>i) {
		  data2[i-6] = classement.get(i);
		  d2= true;
	  }
	  else if(i>2 && classement.size()>i) {
		  data1[i-3] = classement.get(i);
		  d1= true;
	  }
	  else {
		  System.out.println(classement.get(i));
		  title[i] = classement.get(i).toString();
	  }
  }
  if(d5) {
	  data[1] = data1;
	  data[2] = data2;
	  data[3] = data3;
	  data[4] = data4;
	  data[5] = data5;
  }
  else if(d4) {
	  data[1] = data1;
	  data[2] = data2;
	  data[3] = data3;
	  data[4] = data4;
  }
  else if(d3) {
	  data[1] = data1;
	  data[2] = data2;
	  data[3] = data3;
  }
  else if(d2) {
	  data[1] = data1;
	  data[2] = data2;
  }
  else if(d1) {
	  data[1] = data1;
  }
  
  JTable tableau = new JTable(data, title);
  fen.getContentPane().add(new JScrollPane(tableau));
  
  fen.setVisible(true);
}   
}