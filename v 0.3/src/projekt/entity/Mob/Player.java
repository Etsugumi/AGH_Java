package projekt.entity.Mob;

import projekt.Game;
import projekt.Mouse;
import projekt.entity.projectile.Projectile;
import projekt.entity.projectile.WizardProjectile;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import static projekt.graphics.Sprite.*;
import projekt.input.Keyboard;
import projekt.level.level;

public class Player extends Mob{
    
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;
    private boolean isSwimming = false;
    private int speed = 1;
    
    private int fireRate = 0;
    Projectile p;
    
    private String imie;
    
    //tworzy gracza w standardowym miejscu 
    public Player(Keyboard input, String imie, level lvl){
        this.input = input;
        sprite = Sprite.player1_up;
        this.imie = imie;
    }
    
    //tworzy gracza w wyznaczomym miejscu(spawn)
    public Player(int x, int y, Keyboard input, String imie){
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = player1_up;
        this.imie = imie;
        fireRate = WizardProjectile.FIRE_RATE; 
    }
    
    public void update(){
        if(fireRate > 0) fireRate--;
        int xa = 0,ya = 0;
        //takie male zabezpieczenie przed idiotami(ilosc/szybkosc animacji)
        if(anim < 7500) anim++; else anim = 0;
        if(input.up) ya-=speed;
        if(input.down) ya+=speed;
        if(input.left) xa-=speed;
        if(input.right) xa+=speed;
        if(xa != 0 || ya != 0){
            move(xa,ya);
            walking = true;
        }else{
            walking = false;
        }
        
        clear();
        shootUpdate();
    }
    
    private void clear(){
        for(int i=0; i < lvl.getProjectiles().size(); i++){
            Projectile p = lvl.getProjectiles().get(i);
            if(p.isRemoved()) lvl.getProjectiles().remove(i);
        }
    }
    
    private void shootUpdate(){
        if(Mouse.getButton() == 1 && fireRate <= 0){
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            double dir = Math.atan2(dy, dx);
            shoot(x,y, dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }
    
    public void render(Screen screen){
            int flip = 0;
            if(dir == 0){
                sprite = Sprite.player1_up;
                if(walking){
                    if(anim % 20 > 10){
                        sprite = Sprite.player1_up1;
                    }else{
                        sprite = Sprite.player1_up2;
                    }
                }
            }
            if(dir == 1){
                sprite = Sprite.player1_side;
                if(walking){
                    if(anim % 20 > 10){
                        sprite = Sprite.player1_side1;
                    }else{
                        sprite = Sprite.player1_side2;
                    }
                }
            }
            if(dir == 2){
                sprite = Sprite.player1_down;
                if(walking){
                    if(anim % 20 > 10){
                        sprite = Sprite.player1_down1;
                    }else{
                        sprite = Sprite.player1_down2;
                    }
                }
            }
            if(dir == 3){
                sprite = Sprite.player1_side;
                flip = 1;
                if(walking){
                    if(anim % 20 > 10){
                        sprite = Sprite.player1_side1;
                  }else{
                        sprite = Sprite.player1_side2;
                  }
                }
            }
            
            screen.renderPlayerHead(x - 16, y - 16, sprite, flip);
            screen.renderPlayerBody(x - 16, y - 16, sprite, flip);
    }
}
