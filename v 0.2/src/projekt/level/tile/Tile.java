package projekt.level.tile;

import projekt.graphics.Screen;
import projekt.graphics.Sprite;

public class Tile {
    
    public int x,y;
    public Sprite sprite;
    
    public static Tile grass = new GrassTile(Sprite.Grass);
    public static Tile grass1 = new GrassTile(Sprite.Grass_Dirt);
    public static Tile grass2 = new GrassTile(Sprite.Grass_Dirt1);
    
    public static Tile dirt = new Dirt(Sprite.Dirt);
    public static Tile dirt1 = new Dirt(Sprite.Dirt_Sand);
    public static Tile dirt2 = new Dirt(Sprite.Dirt_Sand1);
    
    public static Tile sand = new SandTile(Sprite.Sand);
    public static Tile sand1 = new SandTile(Sprite.Sand_Grass);
    public static Tile sand2 = new SandTile(Sprite.Sand_Grass1);
    
    public static Tile water = new SandTile(Sprite.Water);
    public static Tile water1 = new SandTile(Sprite.Water_Sand);
    public static Tile water2 = new SandTile(Sprite.Water_Sand1);
    
    public static Tile rock = new RockTile(Sprite.Rock);
    public static Tile rock1 = new RockTile(Sprite.Rock1);
    public static Tile rock2 = new RockTile(Sprite.Rock2);
    
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
