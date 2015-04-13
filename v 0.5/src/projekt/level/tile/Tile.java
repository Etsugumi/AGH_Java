package projekt.level.tile;

import projekt.graphics.Screen;
import projekt.graphics.Sprite;

public class Tile {
    
    public int x,y;
    public Sprite sprite;
    
    public static Tile grass = new GrassTile(Sprite.Grass);    
    public static Tile dirt = new Dirt(Sprite.Dirt);    
    public static Tile sand = new SandTile(Sprite.Sand);
    public static Tile water = new SandTile(Sprite.Water);    
    public static Tile rock = new RockTile(Sprite.Rock);
    
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    
    public Tile(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void render(int x, int y, Screen screen){        
    }
    
    //czy dana texturka jest "ścianą"
    public boolean solid(){
        return false;
    }
    
}
