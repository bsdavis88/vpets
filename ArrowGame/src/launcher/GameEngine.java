package launcher;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class GameEngine implements Runnable{

	public static final int width = 640, height = 480;
	public static final int FPS = 30;

	private Thread thread;
	private BufferedImage image;
	private boolean running;
	private Graphics2D g;
	private BufferStrategy bs;
	private JFrame frame;
	private Canvas canvas;
	private Target target;
	private Arrow arrow;
	private KeyManager keyManager;
	private JoystickManager joystickManager;
	
	public GameEngine(){
		createDisplay();
		keyManager = new KeyManager();
		joystickManager = new JoystickManager();
		target = new Target(keyManager, joystickManager);
		arrow = new Arrow(target, 400, 240);
		frame.addKeyListener(keyManager);
		thread = new Thread(this);
		thread.start();
	}
	
	private void createDisplay(){
		frame = new JFrame("Arrow Game");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(640, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}

	@Override
	public void run() {
		running = true;
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) (image.getGraphics());
		
		long startTime = System.currentTimeMillis();
		long frameCool = 0;
		
		while(running){
			frameCool = (System.currentTimeMillis() - startTime);
		
			if (frameCool >= 1000 / FPS){
				frameCool = 0;
				startTime = System.currentTimeMillis();
				tick();
				render();
			}else{
				try{
					thread.sleep((1000 / FPS) - frameCool);
				}catch(Exception e){
				
				}
			}
		}
	}
	
	public void tick(){
		keyManager.tick();
		target.tick();
		arrow.tick();
	}
	
	public void render(){
		bs = canvas.getBufferStrategy();
		if (bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.BLACK);
		g.drawString("Point Dir: " + arrow.cAngle, 10, 20);
		g.drawString("       DX: " + arrow.x, 10, 40);
		g.drawString("       DY: " + arrow.y, 10, 60);
		g.drawString("Direction: " + arrow.direction, 10, 80);
		
		target.render(g);
		arrow.render(g);
		
		bs.show();
		g.dispose();
	}
}
