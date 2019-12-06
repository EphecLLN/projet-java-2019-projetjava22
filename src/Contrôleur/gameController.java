package Contrôleur;

import Vue.gameVue;
import model.game;

public class gameController {
	private game model;
    private gameVue view = null;
    
    public gameController(game m) {
        model = m;
    }
}

