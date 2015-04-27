package projekt;

import projekt.input.Mouse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekt.entity.Mob.Player;
import projekt.graphics.Screen;
import projekt.input.Keyboard;
import projekt.level.SpawnLevel;
import projekt.level.level;
import projekt.level.tile.TileCoordinates;
import projekt.net.Client;
import projekt.net.Server;

public class Game extends Canvas implements Runnable {
    
    //----------------------------------------------------------------------------------- ZMIENNE
    
    private static final int scale = 3;
    private static final int width = 500;
    private static final int height = width / 16 * 9;
    
    public static String title = "Mages Battle";
    
    private Thread watek;
    protected JFrame frame;
    
    public Graphics g;
    
    private enum STATE{GAME,MENU};
    private STATE State = STATE.GAME;
    
    private level lvl;
    public Screen screen;
    private Keyboard key;
    private Player player;
    private boolean running = false;
    
    private Client client;
    private Server server;
    
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

    //----------------------------------------------------------------------------------- KONSTRUKTORY
    
    public Game(){
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
        
        frame = new JFrame();
        screen = new Screen(width, height);
        
        frame.getContentPane().setCursor(blankCursor);
        
        key = new Keyboard();
        addKeyListener(key);
        lvl = new SpawnLevel("/textures/mapa.png");    
        
        TileCoordinates playerSpawn = new TileCoordinates(3,3);
        player = new Player(playerSpawn.x(),playerSpawn.y(),key);
        player.init(lvl);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }
    
    //----------------------------------------------------------------------------------- METODY
    
    public static int getWindowWidth(){
        return width * scale;
    }
    
    public static int getWindowHeight(){
        return height * scale;
    }
    
    public static String getTitle(){
        return title;
    }

    //------------------------------------------------------------------ WĄTKI
    
    //uruchamianie wątku
    public synchronized void start(){
        running = true;
        
        watek = new Thread(this, "MageBattle");
        watek.start();
        
        if(JOptionPane.showConfirmDialog(this, "Start server?")==0){
            try {
                server = new Server(1331);
                server.start();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        client = new Client();
        client.go(); 
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
    
    //------------------------------------------------------------------ FPS & RUN
    
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
                frame.setTitle(title + " | " + "FPS [" + frames + "]" );
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //------------------------------------------------------------------ UPDATE
    
    public void update(){
        if(State == STATE.GAME){
            lvl.update();
            key.update();
            player.update();
        }
    }
    
    //------------------------------------------------------------------ RENDEROWANIE
    
    public void render(){

            //Font f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("/font/FreePixel.ttf")).deriveFont(Font.PLAIN, 14);
            
            BufferStrategy bs = getBufferStrategy();
            if(bs == null){
                createBufferStrategy(3);
                return;
            }
            
            screen.clear();
            
            int xScroll = player.x - screen.width/2;
            int yScroll = player.y - screen.height/2;
            
            //wiążemy grafikę z buforem
            g = bs.getDrawGraphics();
            
            //w zależności od stanu renderujemy gre lub menu
            if(State == STATE.GAME){
                lvl.render(xScroll, yScroll, screen);
                player.render(screen);
            }else if(State == STATE.MENU){
                
            }
            
            //rysowanie obrazu na ekranie
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            
            g.setColor(Color.WHITE);
            g.fillRect(Mouse.getX()-4, Mouse.getY()-4, 8, 8);
            
            for(int i=0; i< pixels.length; i++){
                pixels[i] = screen.pixels[i];
            }
            
            //usuwanie już wyświetlonej grafiki
            g.dispose();
            bs.show();        
    }
    
    //----------------------------------------------------------------------------------- MAIN METHOD
    
    public static void main(String[] args){
        Menu menu = new Menu();
        
        menu.setResizable(false); //blokada zmiany rozmiaru ekranu
        menu.setTitle(Game.title);
        menu.pack();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //standardowe wyjście z programu
        menu.setLocationRelativeTo(null); //umieszczenie okna na ekranie
        menu.setVisible(true); //umożliwia wyświetlenie okna
    }
}
