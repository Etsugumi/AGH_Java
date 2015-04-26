package game;

import game.grafika.Screen;
import game.grafika.Sprite;
import game.grafika.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class GAME extends Canvas implements Runnable{
	
	//-------------------------------------------------------------------------------------------------------- ZMIENNE

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 350;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String TITLE = "MageBattle";

	private JFrame frame;
	protected Thread game;
	protected Screen screen;
	
	private Keyboard keyboard;
	
	private boolean running = false;
	private int tickCount = 0;
	
	private enum STATE{GAME,INFO};
    private STATE state = STATE.GAME;
	
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();	
	
	//------------------------------------------- NOWY KURSOR
	
    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	
	//-------------------------------------------------------------------------------------------------------- KONSTRUKTORY
	
	public GAME(){
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame(TITLE);
		
		frame.getContentPane().setCursor(blankCursor);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        
        keyboard = new Keyboard();
        addKeyListener(keyboard);
	}

	//-------------------------------------------------------------------------------------------------------- METODY
	
	//------------------------------------------------------------------- WATKI
	
	public synchronized void start(){
		running = true;
		game = new Thread(this, "MageBattle");
		game.start();
	}
	
	public synchronized void stop(){
		running = false;
		
		try {
			game.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------- LICZNIK FPS
	
	public void run() {
        long lastTime = System.nanoTime();
        double maxFPS = 60;
        long timer = System.currentTimeMillis();
        
        double ns = 1000000000D / maxFPS;
        
        double delta = 0;
        int frames = 0;
        int ticks = 0;
        
        requestFocus();
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            boolean shouldRender = false;
            
            while (delta >= 1) {
            	ticks++;
                tick();
                delta--;
                shouldRender = true;
            }
            
             try{
                Thread.sleep(2);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
            if(shouldRender){
                frames++;
                render();
            }
            
            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;      
                frame.setTitle(TITLE + " | " + "FPS [" + frames + "]" );
                ticks = 0;
                frames = 0;
            }
        }
        stop();
    }
	
	//------------------------------------------------------------------- LOGIKA GRY I RENDEROWANIE
	
	public void tick(){
		tickCount++;
		
		if(state == STATE.GAME){
			keyboard.update();
		}
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.drawRect(Mouse.getX()- 4, Mouse.getY()- 4, 8, 8);
		
		g.dispose();
		bs.show();
	}
	
	//------------------------------------------------------------------- GLOWNA METODA
	
	public static void main(String[] args){
		new GAME().start();
	}
}
