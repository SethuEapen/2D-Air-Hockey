package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable { //main class that does all of the stuff
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1500, HEIGHT = 900; 
	public static final double friction = 0.1;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Air Hockey", this);
		handler = new Handler();
		handler.addObject(new Puck(300, 300, ID.Puck));
		handler.addObject(new Player(100, 100, ID.Player, handler));

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();//killing the thread
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 /amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime =  now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]) {
		new Game();
	}

	

}
