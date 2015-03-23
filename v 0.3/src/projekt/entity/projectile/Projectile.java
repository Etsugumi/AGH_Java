package projekt.entity.projectile;

import projekt.entity.Entity;
import projekt.graphics.Sprite;

public abstract class Projectile extends Entity {
    
    //miejsce pojawiania sie duszków
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double x, y;
    //nowy wektor
    protected double nx, ny;
    protected double speed, damage, range;
    
    public Projectile(int x, int y, double dir){
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    public int getSpriteSize(){
        return sprite.SIZE;
    }
    
    protected void move(){
        
    }
}
