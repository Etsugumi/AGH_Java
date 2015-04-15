package projekt.entity.projectile;

//import projekt.entity.particle.Particle;
import projekt.entity.spawner.ParticleSpawner;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;

public class WizardProjectile extends Projectile {
    
    public static int FIRE_RATE = 20; //im większa liczba tym wolniej strzelamy    
    public WizardProjectile(int x, int y, double dir){
        super(x, y, dir);
        range = 250;
        speed = 4;
        damage = 200;
        
        sprite = Sprite.kula;
        
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    
    public void update(){
        if(lvl.tileCollision((int)(x+nx), (int)(y+ny -2), 4, 3, 5)){
            lvl.add(new ParticleSpawner((int)(x + 1),(int)(y),  5, 10, lvl));
            remove();
        }
        move();
    }
    
    protected void move(){
        x += nx;
        y += ny;
        if(distance() > range){
            //lvl.add(new ParticleSpawner((int)x + 15,(int)y, 44, 50, lvl));
            remove();
        }
    }
    
    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs(((xOrigin - x)*(xOrigin - x)+(yOrigin - y)*(yOrigin - y))));
        return dist;
    }
    
    public void render(Screen screen){
        screen.renderProjectile((int)x, (int)y, this);
    }

    
}
