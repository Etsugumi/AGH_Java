package game.grafika;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	//-------------------------------------------------------------------------------------------------------------------- ZMIENNE
	public String path;
	
	public int width;
	public int height;
	public int size;
	
	public int[] pixels;
	public Sprite[] sprites;
	
	//-------------------------------------------------------------------------------------------------------------------- SPRITESHEETS
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/TILES.png", 256);
    public static SpriteSheet hero = new SpriteSheet("/textures/HERO.png", 256);
    
    //------------------------------------------------------------------------------- SUB-SPRITESHEETS
    public static SpriteSheet woda = new SpriteSheet(tiles, 3, 0, 1, 9, 16);
	
	//-------------------------------------------------------------------------------------------------------------------- KONSTRUKTORY
	
	//------------------------------------------------------------------------------------------- SUB-SHEET
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize){
       
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w =  width * spriteSize;
        int h =  height * spriteSize;
        
        if(width == height) size= width;
        else size = -1;
        
        this.width = w;
        this.height = h;
        
        pixels = new int[w*h];
        
        for(int y0=0; y0 < h; y0++){
            int yp = yy + y0;
            for(int x0=0; x0 < w; x0++){
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.width];
            }
        }
        
        int frame = 0;
        sprites = new Sprite[width*height];
        for(int ya=0; ya < height; ya++){
            for(int xa=0; xa < width; xa++){
                int[] SpritePixels = new int[spriteSize * spriteSize];
                for(int y0=0; y0 < spriteSize; y0++){
                    for(int x0=0; x0 < spriteSize; x0++){
                        SpritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * this.width];
                    }
                }
                Sprite sprite = new Sprite(SpritePixels, spriteSize, spriteSize);
                sprites[frame] = sprite;
                frame++;
            }
        }
    }
    
    //------------------------------------------------------------------------------------------- NORMAL SHEETS
    
    public SpriteSheet(String path, int size){
        this.path = path;
        this.size = size;
        this.width = size;
        this.height = size;
        pixels = new int[this.size*this.size];
        load();
    }
    
    public SpriteSheet(String path, int width, int height){
        this.path = path;
        size = -1;
        this.width = width;
        this.height = height;
        pixels = new int[this.width*this.height];
        load();
    }
    
    //-------------------------------------------------------------------------------------------------------------------- METODY
    
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
