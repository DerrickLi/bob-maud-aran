package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	private long lastFire = 0;
	private long firingInterval = 200;	
	private boolean firePressed = false;
	
	public Player(int x, int y, ID id, String ref, Handler handler, boolean firePressed) {
		super(x, y, id, ref);
		this.handler = handler;
		this.firePressed = firePressed;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 6, 6);
	}
	
	public void setFirePressed(boolean fire) {
		firePressed = fire;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0,  Game.WIDTH - 38);
		y = Game.clamp(y,  0,  Game.HEIGHT - 68);
		
		collision();
		if (firePressed) tryToFire();
	}
	
	public void tryToFire() {
		// check that we have waiting long enough to fire
		if (System.currentTimeMillis() - lastFire < firingInterval) {
			return;
		}
		
		// if we waited long enough, create the shot entity, and record the time.
		lastFire = System.currentTimeMillis();
		handler.addObject(new PlayerBullet(this.getX(), this.getY(), 10, handler, ID.PlayerBullet, "resources/player_bullet.png"));
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.EnemyBullet) {
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
