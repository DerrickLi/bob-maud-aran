package com.game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;
	protected Sprite sprite;
	private boolean hasSprite = true;
	
	public GameObject(int x, int y, ID id, String ref) {
		this.x = x;
		this.y = y;
		this.id = id;
		if (ref == null) hasSprite = false;
		else this.sprite = SpriteStore.get().getSprite(ref);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void draw(Graphics g) {
		if (hasSprite)
			sprite.draw(g,(int) x,(int) y);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
}
