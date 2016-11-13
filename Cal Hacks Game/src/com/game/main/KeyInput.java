package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//key events for player 1
				if (key == KeyEvent.VK_UP) { tempObject.setVelY(-10); upPressed = true; }
				if (key == KeyEvent.VK_DOWN) { tempObject.setVelY(10); downPressed = true; }
				if (key == KeyEvent.VK_LEFT) { tempObject.setVelX(-10); leftPressed = true; } 
				if (key == KeyEvent.VK_RIGHT) { tempObject.setVelX(10); rightPressed = true; }
				if (key == KeyEvent.VK_SPACE) {
					Player player = (Player) tempObject;
					player.setFirePressed(true);
				}
			}
			
			if (tempObject.getId() == ID.TitleScreen) {
				//key events for player 1
				if (key == KeyEvent.VK_ENTER) {
					TitleScreen inter = (TitleScreen) tempObject;
					inter.start();
				}
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				//key events for player 1
				if (key == KeyEvent.VK_UP) upPressed = false;
				if (key == KeyEvent.VK_DOWN) downPressed = false;
				if (key == KeyEvent.VK_LEFT) leftPressed = false;
				if (key == KeyEvent.VK_RIGHT) rightPressed = false;
				if (key == KeyEvent.VK_SPACE) {
					Player player = (Player) tempObject;
					player.setFirePressed(false);
				}
							
				//vertical movement
				if(!upPressed && !downPressed) tempObject.setVelY(0);
				//horizontal movement
				if(!leftPressed && !rightPressed) tempObject.setVelX(0);
			}
		}
	}
}
