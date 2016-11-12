package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	
	public Player(int x, int y, ID id, String ref, Handler handler) {
		super(x, y, id, ref);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 6, 6);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0,  Game.WIDTH - 38);
		y = Game.clamp(y,  0,  Game.HEIGHT - 68);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, 6, 6);
	}
}
