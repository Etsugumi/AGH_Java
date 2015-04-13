package projekt.entity;

import java.util.Random;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import projekt.level.level;

public class Entity {
    public int x,y;
    private boolean removed = false;
    public level lvl;
    protected Sprite sprite;
    protected final Random random = new Random();
    
    public void update(){
    }
    
    public void render(Screen screen){
    } 
    
    //usuwanie z naszego levelu
    public void remove(){
        removed = true;
    }
    
    public boolean isRemoved(){
        return removed;
    }
    
    public void init(level lvl){
        this.lvl = lvl;
    }
}
