package Contôleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.clic;
import model.game;
import model.upgrade;

public class boutonEffect {

		clic actionClic = new clic();		//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
		upgrade UP = new upgrade();			//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton		
		
		public class clic implements ActionListener{
			 public void actionPerformed(ActionEvent event) {
					attack(getMyMonster(),game.myHero,myArtf);
					PVLabel.setText("monstre PV : " + getMyMonster().getPV());
					argentLabel.setText("argent : " + getGold() );
				}
		 }
		
		public class upgrade implements ActionListener{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + getGold() );
	              degatLabel.setText("d�g�ts actuels :" + myHero.getDamage());
	              coutUPLabel.setText("co�t : " + getUpgradeValue());
	          }
	   }
}
