package Contrôleur;

import Vue.gameVue;
import model.game;

public class gameController {
	private game model;
    private gameVue view = null;
    
    public gameController(game m) {
        model = m;
    }
    public void upgrade() {
    	model.upgrade(model.myHero);
    	control();
    }
    public void attack() {
    	model.attack(model.myMonster, model.myHero, model.myArtf);
    	control();
    }
    public void reset() {
    	model.reborn(model.myMonster, model.myHero, model.myPets);
    	control();
    }
    public void oneMorePet() {
    	model.myPets.buyPet(model);
    	control();
    }
    public void oneMoreArtf() {
    	model.myHero.buyArtefact(model.myArtf, model);
    }
    public void classChoice(int x) {
    	if (x == 1) {
    		model.archerChoice(model.myHero);
    	}
    	if (x == 2) {
    		
    	}
    	if (x == 3) {
    		
    	}
    	control();
    }
    public void control() {
        if (view != null) {
            if (model.myArtf.noArtefacts.length == 0) {
                view.enableWarning();
            } else {
                view.disableWarning();
            }
        }
    }
    public void addView(gameVue view) {
        this.view = view;
    }
}

