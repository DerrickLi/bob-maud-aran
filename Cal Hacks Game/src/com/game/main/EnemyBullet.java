package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBullet extends GameObject{

	private Handler handler;
	
	public EnemyBullet(int x, int y, int velX, int velY, ID id, String ref, Handler handler) {
		super(x, y, id, ref);
		
		this.handler = handler; 
		this.velX = velX;
		this.velY = velY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - 20) handler.removeObject(this);
		if (y <= 0 || y >= Game.HEIGHT - 50) handler.removeObject(this);
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 8, 16, 0.1f, handler));
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		//g.fillOval(x, y, 16, 32);
	}
}
