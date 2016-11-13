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
	
	private int health;
	
	private long startTime = 0;
	private long delay;
	
	private int mvmt;
	private int pattern;
	private int xOffSet = 16;
	private int yOffSet = 18;
	
	public Enemy(int x, int y, ID id, String ref, Handler handler, int bullets, int rows, int velZ, long delay, int health, int mvmt, int pattern) {
		super(x, y, id, ref);
		this.handler = handler;
		this.delay = delay;
		this.bullets = bullets;
		this.health = health;
		this.rows = rows;
		this.velZ = velZ;
		this.mvmt = mvmt;
		this.pattern = pattern;
		this.setHealth(health);
		velX = 7;
		velY = 5;
		mp = bullets/2;
		startTime = System.currentTimeMillis();
	}
	
	public void tick() {
		if (y<0) y+=1;
		else selectMvmt(mvmt);
		long timeElapsed = System.currentTimeMillis() - startTime;
		if (timeElapsed > delay) {
			if (y>=0) selectPattern(pattern);
			startTime = System.currentTimeMillis();
		}		
		
		if(y>=Game.HEIGHT+100 || x<=-100 || x>=Game.WIDTH+100) handler.removeObject(this);
	}
		
	public void selectPattern(int num){
		switch(num){
		case 1: pattern1();
			break;
		case 2: pattern2();
			break;
		case 3: pattern3();
			break;
		case 4: pattern4();
			break;
		default: pattern1();
			break;
		}
	}
	public void selectMvmt(int num){
		switch(num){
		case 1: mvmt1();
			break;
		case 2: mvmt2();
			break;
		case 3: mvmt3();
			break;
		case 4: mvmt4();
			break;
		case 5: mvmt5();
			break;
		case 6: mvmt6();
			break;
		case 7: mvmt7();
			break;
		case 8: mvmt8();
			break;
		case 9: mvmt9();
		break;
		case 10: mvmt10();
		break;
		default: mvmt1();
			break;
		}
	}
	
	public void pattern1() {
		this.delay = 300;
		for (int i = 0; i <= bullets; i++) {
			handler.addObject(new EnemyBullet(x, y, mp - i, velY(mp-i, velZ), ID.EnemyBullet, "resources/red_bullet.png", handler));
			
		}
	}
	private boolean evens = true;
	public void pattern2() {
		this.delay = 100;
		for (int i = 0; i <= bullets; i++) {
				if (evens && i%2 == 0){
					handler.addObject(new EnemyBullet(x, y, mp - i, velY(mp-i, velZ), ID.EnemyBullet, "resources/red_bullet.png", handler));
				}
				else if(!evens && i%2 == 1){
					handler.addObject(new EnemyBullet(x, y, mp - i, velY(mp-i, velZ), ID.EnemyBullet, "resources/red_bullet.png", handler));
				}
		}
		evens = !evens;
	}
			
	
	public void pattern3() {
		this.delay = 700;
		for (int i = 0; i <= bullets; i++) {
			for (int j = 0; j < 3; j++) {
					handler.addObject(new EnemyBullet(x, y, mp-i, velY(mp-i, velZ+j), ID.EnemyBullet, "resources/red_bullet.png", handler));
			}
		}
	}
	private int counter = 0;
	//pattern4 is good for mobs
	public void pattern4() {
		this.delay = 1;
		if(counter <= bullets)
			counter += 1;
		else 
			counter = 0;
		handler.addObject(new EnemyBullet(x, y-counter, mp-counter, velY(mp-counter, velZ), ID.EnemyBullet, "resources/red_bullet.png", handler));
	}
	private int counter2 = bullets;
	public void pattern5() {
		this.delay = 1;
		if(counter2 > 0)
			counter2 -= 1;
		else 
			counter = 20;
		handler.addObject(new EnemyBullet(x, y-counter2, mp-counter2, velY(mp-counter2, velZ), ID.EnemyBullet, "resources/red_bullet.png", handler));
	}
	
	
	private int velY(int x, int z) {
		return (int)Math.sqrt(z*z - x*x);
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x+xOffSet, y+yOffSet, 64, 64);
	}

	public Rectangle getBounds() {
		return new Rectangle(x+xOffSet, y+yOffSet, 64, 64);
	}
	
	public void mvmt1(){
		velY = 5;
		x += 6;
		if(x>Game.WIDTH / 10) velY = 4;
		if(x>Game.WIDTH / 5) velY = 3;
		if(x>Game.WIDTH / 3) velY = 2;
		if(x>Game.WIDTH*2 / 5) velY = 1;
		if(x>Game.WIDTH / 2) velY = -1;
		if(x>Game.WIDTH*3 / 5) velY = -2;
		if(x>Game.WIDTH*2 / 3) velY = -3;
		if(x>Game.WIDTH*4 / 5) velY = -4;
		if(x>Game.WIDTH *9/ 10) velY = -5;
		y += velY;
	}
	
	public void mvmt2(){

		velY = 5;
		x -= 6;

		if(x>Game.WIDTH *9/ 10) velY = 4;
		if(x>Game.WIDTH*4 / 5) velY = 3;
		if(x>Game.WIDTH*2 / 3) velY = 2;
		if(x>Game.WIDTH*3 / 5) velY = 1;
		if(x>Game.WIDTH / 2) velY = -1;
		if(x>Game.WIDTH*2 / 5) velY = -2;
		if(x>Game.WIDTH / 3) velY = -3;
		if(x>Game.WIDTH / 5) velY = -4;
		if(x>Game.WIDTH / 10) velY = -5;
		y += velY;
	}
	
	public void mvmt3(){
		velY = 5;
		x += 7;

		if(x>Game.WIDTH *19/ 20) velY = -5;
		else if(x>Game.WIDTH*9 / 10) velY = -4;
		else if(x>Game.WIDTH*17 / 20) velY = -3;
		else if(x>Game.WIDTH*4 / 5) velY = -2;
		else if(x>Game.WIDTH*3/ 4) velY = -1;
		else if(x>Game.WIDTH*7 / 10) velY = 0;
		else if(x>Game.WIDTH*13/ 20) velY = 1;
		else if(x>Game.WIDTH*3/ 5) velY = 2;
		else if(x>Game.WIDTH / 2) velY = 3;
		else if(x>Game.WIDTH*9/ 20) velY = 4;
		else if(x>Game.WIDTH*2 / 5) velY = 5;
		else if(x>Game.WIDTH*7 / 20) velY = 4;
		else if(x>Game.WIDTH*3 / 10) velY = 3;
		else if(x>Game.WIDTH / 4) velY = 2;
		else if(x>Game.WIDTH / 5) velY = 1;
		else if(x>Game.WIDTH*3 / 20) velY = 0;
		else if(x>Game.WIDTH / 10) velY = -1;
		else if(x>Game.WIDTH / 20) velY = -2;
		y += velY;
	}
	
	public void mvmt4(){
		velY = 5;
		x -= 7;

		if(x>Game.WIDTH *19/ 20) velY = -5;
		else if(x>Game.WIDTH*9 / 10) velY = -4;
		else if(x>Game.WIDTH*17 / 20) velY = -3;
		else if(x>Game.WIDTH*4 / 5) velY = -2;
		else if(x>Game.WIDTH*3/ 4) velY = -1;
		else if(x>Game.WIDTH*7 / 10) velY = 0;
		else if(x>Game.WIDTH*13/ 20) velY = 1;
		else if(x>Game.WIDTH*3/ 5) velY = 2;
		else if(x>Game.WIDTH / 2) velY = 3;
		else if(x>Game.WIDTH*9/ 20) velY = 4;
		else if(x>Game.WIDTH*2 / 5) velY = 5;
		else if(x>Game.WIDTH*7 / 20) velY = 4;
		else if(x>Game.WIDTH*3 / 10) velY = 3;
		else if(x>Game.WIDTH / 4) velY = 2;
		else if(x>Game.WIDTH / 5) velY = 1;
		else if(x>Game.WIDTH*3 / 20) velY = 0;
		else if(x>Game.WIDTH / 10) velY = -1;
		else if(x>Game.WIDTH / 20) velY = -2;
		y += velY;
	}
	
	public void mvmt5(){
		Player temp = null;
		for (int i = 0; i<handler.object.size(); i++){
			if(handler.object.get(i).getId()==ID.Player) temp = (Player)handler.object.get(i);
		}
		float diffX = x - temp.getX();
		float diffY = y - temp.getY();
		float distance = (float)Math.sqrt((x-temp.getX())*(x-temp.getX()) + (y-temp.getY())*(y-temp.getY()));
	
		if (y<Game.HEIGHT/2){
			velX = (int)((-1.0/distance) * diffX * 5);
			velY = (int)((-1.0/distance) * diffY * 5);
		}
		else{
			if (x>Game.WIDTH/2) velX = 7;
			else velX = -7;
			velY /= 3;
		}
		x += velX;
		y += velY;
	}
	
	public void mvmt6(){

		velY = 5;
		velX = 0;
		if(y>Game.HEIGHT*1/3){
			velY = 2;
			velX = -6;
			if(x>Game.WIDTH / 10) velX = -5;
			if(x>Game.WIDTH / 5) velX = -4;
			if(x>Game.WIDTH / 3) velX = -3;
			
			if(x>Game.WIDTH / 2) velX = 3;
			if(x>Game.WIDTH*2 / 3) velX = 4;
			if(x>Game.WIDTH*4 / 5) velX = 5;
			if(x>Game.WIDTH *9/ 10) velX = 6;
		}
		x += velX;
		y += velY;
		
	}

	public void mvmt7(){
		velX = 6;
		velY = 2;
		x+=velX;
		y+=velY;
	}
	
	public void mvmt8(){
		velX = -5;
		velY = 2;
		x+=velX;
		y+=velY;
	}
	
	public void mvmt9(){
		velY = 3;
		if(y <= Game.HEIGHT/6){
			y += velY;
		}
	}
	
	boolean dir = true;
	
	public void mvmt10(){
		if(y <= Game.HEIGHT/5){
			y += 3;
			velX = 0;
		}
		else{
			velX = 4;
			if(x>Game.WIDTH *19/ 20) velY = 0;
			else if(x>Game.WIDTH*9 / 10) velY = 1;
			else if(x>Game.WIDTH*17 / 20) velY = 2;
			else if(x>Game.WIDTH*4 / 5) velY = 1;
			else if(x>Game.WIDTH*3/ 4) velY = 0;
			else if(x>Game.WIDTH*7 / 10) velY = -1;
			else if(x>Game.WIDTH*13/ 20) velY = -2;
			else if(x>Game.WIDTH*11/ 20) velY = -1;
			else if(x>Game.WIDTH / 2) velY = 0;
			else if(x>Game.WIDTH*9/ 20) velY = 1;
			else if(x>Game.WIDTH*2 / 5) velY = 2;
			else if(x>Game.WIDTH*7 / 20) velY = 1;
			else if(x>Game.WIDTH*3 / 10) velY = 0;
			else if(x>Game.WIDTH / 4) velY = -1;
			else if(x>Game.WIDTH / 5) velY = -2;
			else if(x>Game.WIDTH*3 / 20) velY = -1;
			else if(x>Game.WIDTH / 10) velY = 0;
			else if(x>Game.WIDTH / 20) velY = 0;
		}
		if(x<=Game.WIDTH && dir){
			y += velY;
			x += velX;
		}
		if(x > Game.WIDTH && dir) dir = false;
		if(x >= 0 && !dir){
			y += velY;
			x -= velX;
		}
		if(x < 0 && !dir) dir = true;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (health == 0) {
			handler.removeObject(this);
		}
	}
}
