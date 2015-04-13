package projekt.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    //ścieżka do SpriteSheet
    private String path;
    public final int SIZE;
    public final int WIDTH, HEIGHT;
    public int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("/textures/spriteSheet.png", 256);
    public static SpriteSheet hero1 = new SpriteSheet("/textures/hero1.png", 256);
    public static SpriteSheet projectiles = new SpriteSheet("/textures/projectiles.png", 256);
    
    private Sprite[] sprites;
    
    //-------------------------- SUB SPRITESHEETS ------------------------------------------
    //public static SpriteSheet player1 = new SpriteSheet("/textues/hero1.png", 96, 160);
    public static SpriteSheet player1_down = new SpriteSheet(hero1, 0, 0, 1, 3, 32);
    
    //ANIMACJE
    public static SpriteSheet defense = new SpriteSheet(tiles, 4, 1, 3, 1, 32);
    public static SpriteSheet WODA_ANIM = new SpriteSheet(tiles, 3, 1, 1,4, 16);
    
    //subSheet builder
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize){
       
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w =  width * spriteSize;
        int h =  height * spriteSize;
        
        if(width == height) SIZE = width;
        else SIZE = -1;
        
        WIDTH = w;
        HEIGHT = h;
        
        pixels = new int[w*h];
        
        for(int y0=0; y0 < h; y0++){
            int yp = yy + y0;
            for(int x0=0; x0 < w; x0++){
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }
        
        int frame = 0;
        sprites = new Sprite[width*height];
        for(int ya=0; ya < height; ya++){
            for(int xa=0; xa < width; xa++){
                int[] SpritePixels = new int[spriteSize * spriteSize];
                for(int y0=0; y0 < spriteSize; y0++){
                    for(int x0=0; x0 < spriteSize; x0++){
                        SpritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
                    }
                }
                Sprite sprite = new Sprite(SpritePixels, spriteSize, spriteSize);
                sprites[frame] = sprite;
                frame++;
            }
        }
    }
    
    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE*SIZE];
        load();
    }
    
    public SpriteSheet(String path, int w, int h){
        this.path = path;
        SIZE = -1;
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[WIDTH*HEIGHT];
        load();
    }
    
    public Sprite[] getSprites(){
        return sprites;
    }
    
    private void load(){
        try{
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
