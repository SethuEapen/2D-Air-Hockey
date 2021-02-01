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

	public static final int FRAME_WIDTH = 500, FRAME_HEIGHT = 900; 
	public static int WIDTH, HEIGHT; 
	public static final double update = 800000000;
	public static int playerScore = 0;
	public static int AIScore = 0;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	public Game() {
		new Window(FRAME_WIDTH, FRAME_HEIGHT, "Air Hockey", this);
		handler = new Handler();
		handler.addObject(new Puck(WIDTH/2, HEIGHT/2, ID.Puck));
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
		double amountOfTicks = 144.0;
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
				//System.out.println("FPS: " + frames);
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
		makeTable(g);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	private void makeTable(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(WIDTH/2 - WIDTH/6, 0, WIDTH/3, 10);
		g.fillRect(WIDTH/2 - WIDTH/6, HEIGHT - 10,WIDTH/3 ,10);
		g.setColor(Color.red);
		g.drawOval(WIDTH/2 - 50, HEIGHT/2 - 50, 100, 100);
		g.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
		g.setColor(Color.blue);
		g.drawOval(WIDTH/4 - 40, HEIGHT/5 - 40, 80, 80);
		g.drawOval(WIDTH/4 - 40, HEIGHT/5*4 - 40, 80, 80);
		g.drawOval(WIDTH/4*3 - 40, HEIGHT/5 - 40, 80, 80);
		g.drawOval(WIDTH/4*3 - 40, HEIGHT/5*4 - 40, 80, 80);
		g.drawLine(0, HEIGHT/3, WIDTH, HEIGHT/3);
		g.drawLine(0, HEIGHT/3*2, WIDTH, HEIGHT/3*2);



	}
	
	public static void main(String args[]) {
		new Game();
	}

	

}
