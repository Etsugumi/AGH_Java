package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import projekt.entity.Mob.Player;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import projekt.input.Keyboard;
import projekt.level.SpawnLevel;
import projekt.level.level;
import projekt.level.tile.TileCoordinates;
import sun.security.krb5.internal.crypto.Des3;

public class Game extends Canvas implements Runnable {
    
    private static int width = 500;
    private static int height = (width / 16) * 9;
    private static int scale = 3;
    private static String title = "Mages Battle";
    
    private Thread watek;
    protected JFrame frame;
    
    private enum STATE{GAME,MENU};
    private STATE State = STATE.GAME;
    
    private Keyboard key;
    private level lvl;
    private Player player;
    private boolean running = false;
    
    private Screen screen;
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    
    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
        
        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        
        //lvl = new RandomLevel(64,64);
        lvl = new SpawnLevel("/textures/mapa.png");
        TileCoordinates playerSpawn = new TileCoordinates(3,3);
        player = new Player(playerSpawn.x(),playerSpawn.y(),key, "test");
        player.init(lvl);        
        addKeyListener(key);
        
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    public static int getWindowWidth(){
        return width * scale;
    }
    
    public static int getWindowHeight(){
        return height * scale;
    }
    
    public static String getTitle(){
        return title;
    }
    
    //uruchamianie wątku
    public synchronized void start(){
        running = true;
        watek = new Thread(this, "Mages Battle");
        watek.start();
    } 
    
    //zatrzymywanie wątku
    public synchronized void stop(){
        running = false;
        try{
            watek.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double maxFPS = 60;
        long timer = System.currentTimeMillis();
        
        double ns = 1000000000D / maxFPS;
        
        double delta = 0;
        int frames = 0;
        int updates = 0;
        
        requestFocus();
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            boolean shouldRender = false;
            
            while (delta >= 1) {
                updates++;
                update();
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
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;      
                frame.setTitle(title + "  |  " + "UPS = " + updates + "   FPS = " + frames );
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    
    public void update(){
        if(State == STATE.GAME){
            key.update();
            player.update();
            lvl.update();
        }
    }
    
   public void render(){
        
        //tworzymy miejsce w pamięci do renderowania obrazu do późniejszego jego wyświetlenia
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        
        int xScroll = player.x - screen.width/2;
        int yScroll = player.y - screen.height/2;
        
        //wiążemy grafikę z buforem
        Graphics g = bs.getDrawGraphics();
 
        //w zależności od stanu renderujemy gre lub menu
        if(State == STATE.GAME){
            lvl.render(xScroll, yScroll, screen);
            player.render(screen);
        }else if(State == STATE.MENU){
            
        }
        
        //rysowanie obrazu na ekranie
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        
        for(int i=0; i< pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }
        
        //rysowanie obrazu na ekranie
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.fillRect(Mouse.getX()-32, Mouse.getY()-32, 64, 64);
        if(Mouse.getButton() != -1){
            g.drawString("Button: " + Mouse.getButton(), 80, 80);
        }
        //usuwanie już wyświetlonej grafiki
        g.dispose();
        bs.show();
        
    }
    
    public static void main(String[] args){
        Game game = new Game();
        Menu menu = new Menu();
        
        menu.setResizable(false); //blokada zmiany rozmiaru ekranu
        menu.setTitle(Game.title);
        menu.add(game);
        menu.pack();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //standardowe wyjście z programu
        menu.setLocationRelativeTo(null); //umieszczenie okna na ekranie
        menu.setVisible(true); //umożliwia wyświetlenie okna
    }
}
