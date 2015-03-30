package projekt.entity.Mob;

import projekt.entity.Entity;
import projekt.entity.projectile.Projectile;
import projekt.entity.projectile.WizardProjectile;

public abstract class Mob extends Entity{
    //kierunek
    protected int dir = 2; //0-N, 1-E, 2-S, 3-W
    protected boolean moving = false;

    //tutaj opisane jak każdy pixel jest transformowany na ekranie
    public void move(int xa, int ya){   
        if(xa != 0 && ya != 0){
            move(xa,0);
            move(0,ya);
            return; 
       }
        
        if(xa > 0) dir = 1;
        if(xa < 0) dir = 3;
        if(ya > 0) dir = 2;
        if(ya < 0) dir = 0;
        
        if(!collision(xa, ya) && !collision2(xa,ya)){
            y += ya;
            x += xa;
        }
    }
    
    public void update(){
        
    }
    
    protected void shoot(int x, int y, double dir){
            Projectile p = new WizardProjectile(x, y, dir);
            lvl.add(p);
    }
    
    //czy mamy z czymś kolizję
    private boolean collision(int xa, int ya){
        boolean solid = false;
        
        for(int c=0; c<4; c++){
            int xt = ((x+xa) + c % 2 * 23 - 12)/16;
            int yt = ((y+ya) + c / 2 * 14 - 11)/16;
            if(lvl.getTile(xt,yt).solid()) solid = true;
        }
        return solid;
    }
    
    private boolean collision2(int xa, int ya){
        boolean solid = false;
        
        for(int c=0; c<4; c++){
            int xt = ((x+xa) + c % 2 * 5 - 5)/16;
            int yt = ((y+ya) + c / 2 * 14 - 11)/16;
            if(lvl.getTile(xt,yt).solid()) solid = true;
        }
        return solid;
    }
    
    public void render(){
        
    }
}
