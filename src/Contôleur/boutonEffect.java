package Contôleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.GUI;
import model.game;
import model.Hero;
import model.Monster;
import model.Pets;
import model.game.clic;
import model.game.upgrade;

public class boutonEffect implements ActionListener{
		GUI myGUI = new GUI();
		clic actionClic = new clic();		//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton
		upgrade UP = new upgrade();			//devra être remplacer par des event.getsource pour éviter le surplus de classe par bouton		
		
		public class clic{
			 public void actionPerformed(ActionEvent event) {
					attack(game.myMonster,game.myHero,myArtf);
					myGUI.PVLabel.setText("monstre PV : " + getMyMonster().getPV());
					argentLabel.setText("argent : " + getGold() );
				}
		 }
		
		public class upgrade{
	       public void actionPerformed(ActionEvent event) {
	              upgrade(myHero);
	              argentLabel.setText("argent : " + getGold() );
	              degatLabel.setText("d�g�ts actuels :" + myHero.getDamage());
	              coutUPLabel.setText("co�t : " + getUpgradeValue());
	          }
	   }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == )
			
		}
}
