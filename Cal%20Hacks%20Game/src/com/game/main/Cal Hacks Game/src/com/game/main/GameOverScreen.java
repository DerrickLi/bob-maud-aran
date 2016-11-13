package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class GameOverScreen extends KeyAdapter {

	private Sprite sprite;
	private Game game;
	private Handler handler;
	private long blink, breathe;
	private HUD hud;
	private int finalScore;
	
	public GameOverScreen(String ref, Game game, HUD hud, Handler handler) {
		sprite = SpriteStore.get().getSprite(ref);
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		blink = System.currentTimeMillis();
	}

	public void tick() {
		finalScore = hud.getScore();
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
		sprite.draw(g, 0, 0);
		long elapsed = System.currentTimeMillis() - blink;
		if (elapsed < 1000) {
			g.setColor(Color.red);
			g.setFont(new Font("MS PGothic", Font.BOLD, 30));
			g.drawString("GAME OVER",(Game.WIDTH-g.getFontMetrics().stringWidth("bobmaudaran"))/2,250);
			g.drawString("Final Score: " + finalScore,(Game.WIDTH-g.getFontMetrics().stringWidth("Press enter to start"))/2,300);
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
			if (game.gameState == STATE.End) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT - 100, ID.Player, "resources/player.png", handler, hud, false));
			}
		}		
	}
}
