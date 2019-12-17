package model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre extends JFrame {

public Fenetre(){
  this.setLocationRelativeTo(null);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setTitle("JTable");
  this.setSize(300, 120);


public static void main(String[] args){
  Fenetre fen = new Fenetre();
  database db = new database();

  ArrayList<Object> classement = db.classement("team");
  String[] title = new String[4];
  boolean d1 = false;
  boolean d2 = false;
  boolean d3 = false;
  boolean d4 = false;
  boolean d5 = false;
  Object[] data1 = new Object[4];
  Object[] data2 = new Object[4];
  Object[] data3 = new Object[4];
  Object[] data4 = new Object[4];
  Object[] data5 = new Object[4];
  
  System.out.println(classement.size());
  System.out.println(classement);

  for(int i = 1; i<classement.size();i++) {
	  if(i>15 && classement.size()>i) {
		  data5[0] = '5';
		  for(int e = 0; e<3;e++) {
			  data5[e+1] = classement.get(e+15);
		  }	
		  d5 = true;
	  }
	  else if(i>12 && classement.size()>i) {
		  data4[0] = '4';
		  for(int e = 0; e<3;e++) {
			  data4[e+1] = classement.get(e+12);
		  }	
		  d4 = true;
	  }
	  else if(i>9 && classement.size()>i) {
		  data3[0] = '3';
		  for(int e = 0; e<3;e++) {
			  data3[e+1] = classement.get(e+9);
		  }	
		  d3 = true;
	  }
	  else if(i>6 && classement.size()>i) {
		  data2[0] = '2';
		  for(int e = 0; e<3;e++) {
			  data2[e+1] = classement.get(e+6);
		  }
		  d2= true;
	  }
	  //si i>3 ==> classement.get(i) ==> i=1: 
	  else if(i>3 && classement.size()>i && data1[3]==null) {
		  data1[0] = '1';
		  for(int e = 0; e<3;e++) {
			  data1[e+1] = classement.get(e+3);
		  }
		  System.out.println(Arrays.toString(data1));
		  d1= true;
	  }
	  else if(title[title.length-1] == null){
		  title[0] = "place";
		  title[i] = classement.get(i-1).toString();
	  }
  }
  Object[][] data = null ;
  if(d5) {
	 data = new Object[5][4];
	  data[0] = data1;
	  data[1] = data2;
	  data[2] = data3;
	  data[3] = data4;
	  data[4] = data5;
  }
  else if(d4) {
	  data = new Object[4][4];
	  data[0] = data1;
	  data[1] = data2;
	  data[2] = data3;
	  data[3] = data4;
  }
  else if(d3) {
	  data = new Object[3][4];
	  data[0] = data1;
	  data[1] = data2;
	  data[2] = data3;
  }
  else if(d2) {
	  data = new Object[2][4];
	  data[0] = data1;
	  data[1] = data2;
  }
  else if(d1) {
	  data = new Object[1][4];
	  data[0] = data1;
  }
  
  JTable tableau = new JTable(data, title);
  fen.getContentPane().add(new JScrollPane(tableau));
  
  fen.setVisible(true);
}   
}