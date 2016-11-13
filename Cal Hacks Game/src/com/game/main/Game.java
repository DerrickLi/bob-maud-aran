package com.game.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -4124798433549628964L;
	
	public static final int WIDTH = 768, HEIGHT = 1024;
	
	private Thread thread;
	private boolean gameRunning = false;
	public static boolean paused = false;
	private int fps = 60;
	private int frameCount = 0;
    private final double GAME_HERTZ = 60.0;
    private Window window;
    
    //Calculate how many ns each frame should take for our target game hertz.
    private final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
    //At the very most we will update the game this many times before a new render.
    //If you're worried about visual hitches more than perfect timing, set this to 1.
    private final int MAX_UPDATES_BEFORE_RENDER = 2;
    //We will need the last update time.
	
	private Handler handler;
	private HUD hud;
	private TitleScreen title;
	private Intermission inter;
	
	public enum STATE {
		Title,
		Game,
		Paused,
		End
	};
	
	public static STATE gameState = STATE.Title;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler, this));
		

		title = new TitleScreen("resources/test.png", this, handler);
		window = new Window(WIDTH, HEIGHT, "bobmaudaran", this);
		

		this.addKeyListener(title);
		//window.setMessageBox("Test run ayy");
		
		hud = new HUD();
		handler.addObject(new Player(WIDTH/2-32, HEIGHT - 100, ID.Player, "resources/player.png", handler, false));
		
		
		
		handler.addObject(new Enemy(200, 50, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 2000, 1, 3, 2));
		handler.addObject(new Enemy(100, 50, ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 2000, 1, 5, 3));	
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		gameRunning = true;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			gameRunning = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPaused(boolean pause) {
		paused = pause;
	}
	
	public void run() {
      double lastUpdateTime = System.nanoTime();
      //Store the last time we rendered.
      double lastRenderTime = System.nanoTime();
      
      //If we are able to get as high as this FPS, don't render again.
      final double TARGET_FPS = 60;
      final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
      
      //Simple way of finding FPS.
      int lastSecondTime = (int) (lastUpdateTime / 1000000000);
      
      while (gameRunning)
      {
         double now = System.nanoTime();
         int updateCount = 0;
         //Do as many game updates as we need to, potentially playing catchup.
         while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
         {
            tick();
            lastUpdateTime += TIME_BETWEEN_UPDATES;
            updateCount++;
         }

         //If for some reason an update takes forever, we don't want to do an insane number of catchups.
         //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
         if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
         {
            lastUpdateTime = now - TIME_BETWEEN_UPDATES;
         }
      
         //Render. To do so, we need to calculate interpolation for a smooth render.
         /*float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
         drawGame(interpolation);
         lastRenderTime = now;*/
         render();
      
         //Update the frames we got.
         int thisSecond = (int) (lastUpdateTime / 1000000000);
         if (thisSecond > lastSecondTime)
         {
            System.out.println(frameCount);
            fps = frameCount;
            frameCount = 0;
            lastSecondTime = thisSecond;
         }
      
         //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
         while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
         {
            Thread.yield();
         
            //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
            //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
            //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
           // try {Thread.sleep(1);} catch(Exception e) {} 
         
            now = System.nanoTime();
         }
      }
	}
	
	private void tick()
	{
		if (gameState == STATE.Game) {
			if (!paused) {
				handler.tick();
				hud.tick();					
			}
		} 
		else if (gameState == STATE.Title) {
			title.tick();
			handler.tick();
		}
		/*
		 * if (HUD.HEALTH <= 0) {
		 * HUD. HEALTH = 100;
		 * GAMESTATE = STATE.END
		 * HANDLER.CLEARENEMYS();
		 */
		//generate enemies
		//if(handler.object.size()<10){
		//	handler.addObject(new Enemy(ThreadLocalRandom.current().nextInt(-100, WIDTH+100), ThreadLocalRandom.current().nextInt(0, HEIGHT/3), ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 2000, 1, 
		//			ThreadLocalRandom.current().nextInt(1, 9), ThreadLocalRandom.current().nextInt(1, 5)));
		//	handler.addObject(new Enemy(ThreadLocalRandom.current().nextInt(-100, WIDTH+100), ThreadLocalRandom.current().nextInt(0, HEIGHT/3), ID.BasicEnemy, "resources/Enemy.png", handler, 20, 4, 10, 2000, 1, 
		//			ThreadLocalRandom.current().nextInt(1, 9), ThreadLocalRandom.current().nextInt(1, 5)));
		//}
		
	   /*for (int i = 0; i < stuff.size(); i++)
	   {
	      // all time-related values must be multiplied by delta!
	      Stuff s = stuff.get(i);
	      s.velocity += Gravity.VELOCITY * delta;
	      s.position += s.velocity * delta;
	      
	      // stuff that isn't time-related doesn't care about delta...
	      if (s.velocity >= 1000)
	      {
	         s.color = Color.RED;
	      }
	      else
	      {
	         s.color = Color.BLUE;
	      }
	   }*/
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		if (paused) {
			g.drawString("PAUSED", 100, 100);
		}
		if (gameState == STATE.Game) {
			hud.render(g);	
		}
		else if (gameState == STATE.Title) {
			title.render(g);
		}
		
		g.dispose();
		bs.show();
		
		frameCount++;
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
