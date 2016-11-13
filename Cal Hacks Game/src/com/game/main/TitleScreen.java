package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TitleScreen extends GameObject {

	private Handler handler;
	
	public TitleScreen(int x, int y, ID id, String ref, Handler handler) {
		super(x, y, id, ref);
		this.handler = handler;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.red);
		g.setFont(new Font("MS PGothic", Font.BOLD, 20));
		g.drawString("bobmaudaran",(Game.WIDTH-g.getFontMetrics().stringWidth("bobmaudaran"))/2,250);
		g.drawString("Press enter to start",(Game.WIDTH-g.getFontMetrics().stringWidth("Press enter to start"))/2,300);
		
	}
	
	public void start() {
		handler.setPlayerStarted(true);
		handler.removeObject(this);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
