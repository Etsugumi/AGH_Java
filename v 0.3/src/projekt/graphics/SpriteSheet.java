package projekt.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    //ścieżka do SpriteSheet
    private String path;
    public final int SIZE;
    public int[] pixels;
    
    public static SpriteSheet tiles = new SpriteSheet("/textures/spriteSheet.png", 256);
    public static SpriteSheet hero1 = new SpriteSheet("/textures/hero1.png", 256);
    public static SpriteSheet projectiles = new SpriteSheet("/textures/projectiles.png", 256);
    
    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load();
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
