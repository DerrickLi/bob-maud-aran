package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.Thread.State;

import com.game.main.Game.STATE;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean upPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean waitingForKeyPress;
	private int pressCount;
	private boolean keyPressed;
	Game game;	
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		waitingForKeyPress = game.getWaitingForKeyPress();
	}
	
	public boolean waitForKeyPress() {
		return keyPressed;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (waitingForKeyPress) {
			keyPressed = true;
		}
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
		}
		if (key == KeyEvent.VK_P) {
			game.gameState = STATE.Intermission;
			
			/*if (game.gameState == STATE.Game) {
				if (Game.paused) Game.paused = false;	
				else {
					Game.paused = true;
					inter.render();
				}
			}*/
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (waitingForKeyPress) return;
		
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
	
	public void keyTyped(KeyEvent e) {
		// if we're waiting for a "any key" type then
		// check if we've recieved any recently. We may
		// have had a keyType() event from the user releasing
		// the shoot or move keys, hence the use of the "pressCount"
		// counter.
		if (waitingForKeyPress) {
			if (pressCount == 1) {
				// since we've now recieved our key typed
				// event we can mark it as such and start 
				// our new game
				waitingForKeyPress = false;
				pressCount = 0;
			} else {
				pressCount++;
			}
		}
	}
}
