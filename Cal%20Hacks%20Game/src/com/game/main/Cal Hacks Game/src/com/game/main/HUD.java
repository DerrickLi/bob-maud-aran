package com.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	
	private int score = 0;
	private int finalScore = 0;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);;
		
		g.drawString("Score: " + score, 15, 64);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setFinalScore(int score) {
		finalScore = score;
	}
	
	public int getFinalScore() {
		return finalScore;
	}
}
