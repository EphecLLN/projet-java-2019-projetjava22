package Vue;

import java.util.Observer;

import Controleur.gameController;
import model.game;

public abstract class gameVue implements Observer{
	protected game model;
	protected gameController controller;
    
    gameVue(game model,
    		gameController controller) {
            this.model = model;
            this.controller = controller;
            model.addObserver(this); // Connexion entre la vue et le modele
        }
        public abstract void enableWarning();
        public abstract void disableWarning() ;
}

