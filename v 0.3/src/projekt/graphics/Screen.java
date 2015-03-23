package projekt.graphics;

import java.util.Random;
import projekt.entity.projectile.Projectile;
import projekt.level.tile.Tile;

public class Screen {
    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
    
    private Random random = new Random();

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        
        for(int i=0; i<(MAP_SIZE*MAP_SIZE); i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }
    
    public void clear(){
        for(int i=0; i<pixels.length; i++){
            pixels[i] = 0;
        }
    }
    
    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
        if(fixed){
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int y=0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x=0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[x + y * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }
    
    //renderowanie konkretnego elementu
    public void renderTile(int xPos, int yPos, Tile tile){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y=0; y<tile.sprite.SIZE; y++){
            int yAbsolut = y + yPos;
            for(int x=0; x<tile.sprite.SIZE; x++){
                int xAbsolut = x + xPos;
                if(xAbsolut < -tile.sprite.SIZE || xAbsolut >= width || yAbsolut < 0 || yAbsolut >= height) break;
                if(xAbsolut < 0) xAbsolut = 0;
                pixels[xAbsolut + yAbsolut * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
    
    public void renderProjectile(int xPos, int yPos, Projectile p){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y=0; y<p.getSpriteSize(); y++){
            int yAbsolut = y + yPos;
            for(int x=0; x<p.getSpriteSize(); x++){
                int xAbsolut = x + xPos;
                if(xAbsolut < -p.getSpriteSize() || xAbsolut >= width || yAbsolut < 0 || yAbsolut >= height) break;
                if(xAbsolut < 0) xAbsolut = 0;
                int color = p.getSprite().pixels[x + y * p.getSprite().SIZE];
                if(color != 0xffff00ff) pixels[xAbsolut + yAbsolut * width] = color;
            }
        }
    }
    
    //renderujemy gorna czesc gracza
    public void renderPlayerHead(int xPos, int yPos, Sprite sprite, int flip){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y=0; y<16; y++){
            int yAbsolut = y + yPos;
            int ys = y;
            if(flip == 2 || flip ==3)ys = 31 - y;
            for(int x=0; x<32; x++){
                int xAbsolut = x + xPos;
                int xs = x;
                if(flip == 1 || flip ==3) xs = 31 - x;
                if(xAbsolut < -32 || xAbsolut >= width || yAbsolut < 0 || yAbsolut >= height) break;
                if(xAbsolut < 0) xAbsolut = 0;
                int color = sprite.pixels[xs+ys*32];
                if(color != 0xffff00ff) pixels[xAbsolut + yAbsolut * width] = color;
            }
        }
    }
    
    public void renderPlayerBody(int xPos, int yPos, Sprite sprite, int flip){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y=16; y<32; y++){
            int yAbsolut = y + yPos;
            int ys = y;
            if(flip == 2 || flip ==3)ys = 31 - y;
            for(int x=0; x<32; x++){
                int xAbsolut = x + xPos;
                int xs = x;
                if(flip == 1 || flip ==3) xs = 31 - x;
                if(xAbsolut < -32 || xAbsolut >= width || yAbsolut < 0 || yAbsolut >= height) break;
                if(xAbsolut < 0) xAbsolut = 0;
                int color = sprite.pixels[xs+ys*32];
                if(color != 0xffff00ff) pixels[xAbsolut + yAbsolut * width] = color;
            }
        }
    }
    
    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
