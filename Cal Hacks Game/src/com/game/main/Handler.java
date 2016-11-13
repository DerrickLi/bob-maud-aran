package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean gameStarted;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.id == ID.TitleScreen) {
				tempObject.tick();
			}
			else if (gameStarted) tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.id == ID.TitleScreen) {
				tempObject.draw(g);
				tempObject.render(g);
			}
			else if (gameStarted) {
				tempObject.draw(g);
				tempObject.render(g);
			}
			
		}
	}
	
	public void setPlayerStarted(boolean start) {
		gameStarted = start;
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
