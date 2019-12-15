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
    	model.upgrade(model.myHero, model.myHero.getConstUpDamage());
    	control();
    }
    public void attack() {
    	model.attack(model.myMonster, model.myArtf,  model.myHero.getDamage(),  model.myHero.getAttributeHero(), model.myMonster.getAttribute());
    	control();
    }
    public void reset() {
    	model.reborn(model.myMonster, model.myHero, model.myPets,model.myArtf);
    	control();
    }
    public void oneMorePet() {
    	model.myPets.buyPet(model, model.getGold(), model.myPets.getPetCostBuy(),model.myPets.getPetNumber());
    	control();
    }
    public void upgradePets() {
    	model.myPets.upgradePet(model, model.myPets);
    }
    public void oneMoreArtf() {
    	model.myHero.buyArtefact(model.myArtf, model, model.myHero.getArtefactMoney());
    }
    public void classChoice(int x) {
    	if (x == 1) {
    		model.archerChoice(model.myHero);
    	}
    	if (x == 2) {
    		model.mageChoice(model.myHero);
    	}
    	if (x == 3) {
    		model.berzerkerChoice(model.myHero);
    	}
    	control();
    }
    public void attributeChoice(int x) {
    	if (x == 1) {
    		model.myHero.setAttributeHero("aqua");
    	}
    	if (x == 2) {
    		model.myHero.setAttributeHero("pyro");
    	}
    	if (x == 3) {
    		model.myHero.setAttributeHero("tera");
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

