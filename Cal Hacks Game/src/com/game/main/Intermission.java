package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.main.Game.STATE;

public class Intermission extends KeyAdapter{

	private String[] message;
	private String nextMessage;
	private Game game;
	private Window window;
	private int numFrames = 0;
	
	public Intermission(String[] message, Game game, Window window) {
		this.message = message;
		this.game = game;
		this.window = window;
		
		numFrames = message.length;
		nextMessage = message[message.length - numFrames];
		numFrames--;
	}
	
	public void end() {
		game.setPaused(false);
		game.gameState = STATE.Game;
		window.setVisible(false);
	}
	
	public void render() {
		game.gameState = STATE.Paused;
		game.setPaused(true);
		window.setMessageBox(nextMessage);
		System.out.println(nextMessage);
		window.setVisible(true);
		
		nextMessage = message[message.length - numFrames];
		numFrames--;
	}
	
	public void advanceText() {
		if (numFrames > 0) {
			window.setMessageBox(nextMessage);
			nextMessage = message[message.length - numFrames];
			numFrames--;
		}
		else {
			end();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			advanceText();
		}
	}
}
