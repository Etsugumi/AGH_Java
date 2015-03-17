package projekt.entity.Mob;

import projekt.entity.Entity;
import projekt.graphics.Sprite;

public abstract class Mob extends Entity{
    
    //wyglad
    protected Sprite sprite;
    //kierunek
    protected int dir = 0; //0-N, 1-E, 2-S, 3-W
    protected boolean moving = false;
    
    //tutaj opisane jak każdy pixel jest transformowany na ekranie
    public void move(int xa, int ya){
        
        if(xa > 0) dir = 1;
        if(xa < 0) dir = 3;
        if(ya > 0) dir = 2;
        if(ya < 0) dir = 0;
        
        if(!collision()){
            //xa|ya -> -1,0,1
            x += xa;
            y += ya;
        }
    }
    
    public void update(){
        
    }
    
    //czy mamy z czymś kolizję
    private boolean collision(){
        return false;
    }
    
    public void render(){
        
    }
}
