package projekt.entity.projectile;

import projekt.entity.particle.Particle;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;

public class WizardProjectile extends Projectile {
    
    public static int FIRE_RATE = 15; //im większa liczba tym wolniej strzelamy
    
    public WizardProjectile(int x, int y, double dir){
        super(x, y, dir);
        range = 250;
        speed = 3;
        damage = 200;
        
        sprite = Sprite.kula;
        
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    
    public void update(){
        if(lvl.tileCollision(x, y, nx, ny, 4)){
            Particle p = new Particle((int)x, (int)y, 50, 50);
            lvl.add(p);
            remove();
        }
        move();
    }
    
    protected void move(){
        x += nx;
        y += ny;
        if(distance() > range) remove();
    }
    
    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs(((xOrigin - x)*(xOrigin - x)+(yOrigin - y)*(yOrigin - y))));
        return dist;
    }
    
    public void render(Screen screen){
        screen.renderProjectile((int)x + 7, (int)y-9, this);
    }

    
}
