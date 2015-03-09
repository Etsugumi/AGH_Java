package projekt.entity;

import java.util.Random;
import projekt.graphics.Screen;
import projekt.level.level;

public class Entity {
    public int x,y;
    private boolean removed = false;
    protected level lvl;
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
}
