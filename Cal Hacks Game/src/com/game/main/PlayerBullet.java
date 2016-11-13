package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject{
	
	private Handler handler;
	private int vel;

	public PlayerBullet(int x, int y, int vel, Handler handler, ID id, String ref) {
		super(x, y, id, ref);
		this.vel = vel;
		this.handler = handler;
	}

	public void tick() {
		y -= vel;
		if (y<=0) handler.removeObject(this);
		collision();
	}

	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					Enemy enemy = (Enemy)tempObject;
					enemy.setHealth(enemy.getHealth() - 1);
				}
			}
			
		}
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}

}
