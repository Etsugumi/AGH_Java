package projekt.level.tile;

import projekt.graphics.Screen;
import projekt.graphics.Sprite;

public class Tiles {
    
    public int x,y;
    public Sprite sprite;
    
    public static Tiles grass = new StandardTile(Sprite.Grass);    
    public static Tiles dirt = new StandardTile(Sprite.Dirt);    
    public static Tiles sand = new StandardTile(Sprite.Sand);
    public static Tiles water = new StandardTile(Sprite.Water);    
    public static Tiles rock = new CollisionTile(Sprite.Rock);
    
    public static Tiles voidTile = new StandardTile(Sprite.voidSprite);
    
    public Tiles(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void render(int x, int y, Screen screen){        
    }

    public boolean solid(){
        return false;
    }
    
}
