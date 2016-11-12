package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	private Handler handler;
	private int bullets;
	private int rows;
	
	private int mp;
	private int velZ;
	
	private int life;
	
	private long startTime = 0;
	private long delay;
	
	public Enemy(int x, int y, ID id, String ref, Handler handler, int bullets, int rows, int velZ, long delay, int life) {
		super(x, y, id, ref);
		this.handler = handler;
		this.delay = delay;
		this.bullets = bullets;
		this.rows = rows;
		this.velZ = velZ;
		
		mp = bullets/2;
		startTime = System.currentTimeMillis();
	}
	
	public void tick() {
		long timeElapsed = System.currentTimeMillis() - startTime;
		if (timeElapsed > delay) {
			pattern1();
			startTime = System.currentTimeMillis();
		}		
	}
	
	public void pattern1() {
		for (int i = 0; i <= bullets; i++) {
			for (int j = 0; j < rows; j++) {
				handler.addObject(new Bullet(x, 50*j, mp - i, velY(mp-i, velZ), ID.BasicEnemy, "resources/Bullet.png", handler));
			}
			//System.out.println(mp-i);
		}
	}
	private int velY(int x, int z) {
		return (int)Math.sqrt(z*z - x*x);
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
}
