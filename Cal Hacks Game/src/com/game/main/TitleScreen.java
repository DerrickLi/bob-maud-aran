package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class TitleScreen extends KeyAdapter {

	private Sprite sprite;
	private Game game;
	private Handler handler;
	private long blink, breathe;
	
	public TitleScreen(String ref, Game game, Handler handler) {
		sprite = SpriteStore.get().getSprite(ref);
		this.game = game;
		this.handler = handler;
		blink = System.currentTimeMillis();
	}

	public void tick() {
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
		sprite.draw(g, 0, 0);
		long elapsed = System.currentTimeMillis() - blink;
		if (elapsed < 1000) {
			g.setColor(Color.red);
			g.setFont(new Font("MS PGothic", Font.BOLD, 20));
			g.drawString("bobmaudaran",(Game.WIDTH-g.getFontMetrics().stringWidth("bobmaudaran"))/2,250);
			g.drawString("Press enter to start",(Game.WIDTH-g.getFontMetrics().stringWidth("Press enter to start"))/2,300);
			breathe = System.currentTimeMillis();
		}
		long breatheElapsed = System.currentTimeMillis() - breathe;
		if (breatheElapsed > 500) {
			blink = System.currentTimeMillis();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			game.gameState = STATE.Game;
		}		
	}
}
