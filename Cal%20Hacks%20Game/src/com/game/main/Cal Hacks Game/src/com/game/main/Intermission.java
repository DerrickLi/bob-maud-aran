package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class Intermission extends KeyAdapter{

	private String message;
	private Game game;
	private Sprite sprite;
	private boolean waitingForKeyPress = true;
	
	public Intermission(String message, Game game, String ref, Window window) {
		sprite = SpriteStore.get().getSprite(ref);
		this.message = message;
		this.game = game;
	}
	
	public boolean getWaitingForKeyPress() {
		return waitingForKeyPress;
	}
	
	public void render(Graphics g) {
		game.setWaitingForKeyPress(waitingForKeyPress);
		g.drawRect(0, 740, 720, 200);
		sprite.draw(g, 0, 735);
		g.setColor(Color.black);
		g.setFont(new Font("MS PGothic", Font.BOLD, 20));
		multidrawString(g, message, 750);
	}
	
	private void multidrawString(Graphics g, String text, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, (Game.WIDTH-g.getFontMetrics().stringWidth(line))/2, y += g.getFontMetrics().getHeight());
    }
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			waitingForKeyPress = false;
			game.gameState = STATE.Game;
		}
	}
}
