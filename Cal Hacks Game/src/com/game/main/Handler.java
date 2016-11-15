package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	Intermission inter;
	TitleScreen title;
	
	private Game game;
	private HUD hud;
	
	public Handler (Game game, HUD hud) {
		this.game = game;
		this.hud = hud;
	}
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		//if (inter != null)
			//inter.start();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.draw(g);
			tempObject.render(g);
			
		}
	}
	
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getId() == ID.Player) {
				object.clear();
				if(Game.gameState != Game.STATE.End)
					addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT - 100, ID.Player, "resources/player.png", this, hud, false));
					game.startStages();
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void addIntermission(Intermission intermission) {
		this.inter = intermission;
	}
	
}
