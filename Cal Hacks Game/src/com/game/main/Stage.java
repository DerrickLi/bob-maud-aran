package com.game.main;

public class Stage {
	Handler handler;
	Game game;
	HUD hud;
	private static int gap = 900;
	
	public Stage(Game game, Handler handler){
		this.handler = handler;
		this.game = game;
	}
	
	public void stage1(){
		tripleTeam(0);
		//game.interm(in1);
		tripleTeam(1);
		vForm(2);
		miniBoss(3);
		

	}
	
	public void stage2(){
		miniBoss(0);
		halfPeel(1);
		tripleTeam(2);
		halfPeel(3);
		miniBoss(4);
	}
	
	public void stage3(){
		vForm(0);
		tripleTeam(1);
		halfPeel(2);
		vForm(3);
		bigBoss(4);
	}
	
	public void stage4(){
		halfPeel(0);
		vForm(1);
		tripleTeam(2);
		bigBoss(3);
		bigBoss(4);
	}
	
	public void stage5(){
		halfPeel(0);
		miniBoss(1);
		halfPeel(2);
		bigBoss(3);
		bigBoss(4);
	}
	
	public void vForm(int order){
		handler.addObject(new Enemy(Game.WIDTH/2, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		
		handler.addObject(new Enemy(Game.WIDTH/2-100, -65 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2-200, -125 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2+100, -65 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2+200, -125 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
	}
	
	public void halfPeel(int order){
		handler.addObject(new Enemy(Game.WIDTH/2+100, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 6, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2-100, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 6, (int)(Math.random()*5)));
		
		handler.addObject(new Enemy(Game.WIDTH/2+100, -75 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 6, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2-100, -75 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 6, (int)(Math.random()*5)));
		
		handler.addObject(new Enemy(Game.WIDTH/2+100, -145 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2-100, -145 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
	}
	
	public void miniBoss(int order){
		handler.addObject(new Enemy(Game.WIDTH/2, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 15, 9, 1));
	}
	
	public void bigBoss(int order){
		handler.addObject(new Enemy(Game.WIDTH/2, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 35, 10, 1));
	}
	
	public void tripleTeam(int order){
		handler.addObject(new Enemy(Game.WIDTH/2-200, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		handler.addObject(new Enemy(Game.WIDTH/2+150, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, (int)(Math.random()*5)));
		//handler.addObject(new Enemy(Game.WIDTH/2, -5 - gap*order, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 3000, 3, 9, 4));
	}
}
