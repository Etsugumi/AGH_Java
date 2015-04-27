package projekt.entity.Mob;

import javax.swing.JOptionPane;
import projekt.Game;
import projekt.input.Mouse;
import projekt.entity.projectile.Projectile;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import projekt.input.Keyboard;
import projekt.level.level;
import projekt.level.tile.Tiles;

public class Player extends Mob{
    
    //----------------------------------------------------------------------------------- ZMIENNE
    
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    
    private boolean walking = false;
    private boolean isSwimming = false;
    
    protected int fireRate = 5;
    public int oldFR = fireRate;
    protected int speed = 1;
    public int HP = 120;
    private String imie;
    
    Projectile p;
    
    //----------------------------------------------------------------------------------- KONSTRUKTORY
    
    //tworzy gracza w standardowym miejscu 
    public Player(Keyboard input,level lvl){
        this.input = input;
        sprite = Sprite.player1_up;
        //this.imie = JOptionPane.showInputDialog("Enter character name:");
    }
    
    //tworzy gracza w wyznaczomym miejscu(spawn)
    public Player(int x, int y, Keyboard input){
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player1_up;
        //this.imie = JOptionPane.showInputDialog("Enter character name:");
    }
    
    //----------------------------------------------------------------------------------- METODY
    
    private void clear(){
        for(int i=0; i < lvl.getProjectiles().size(); i++){
            Projectile p = lvl.getProjectiles().get(i);
            if(p.isRemoved()) lvl.getProjectiles().remove(i);
        }
    }
    
    public void update(){        
        int xa = 0,ya = 0;
        
        if(fireRate > 0) fireRate--;
        
        //ilosc/szybkosc animacji
        if(anim < 20) anim++; else anim = 0;
        
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

    //--------------------------------------------------- TRZEBA ZMIENIC MIEJSCE OD KTÓREGO LICZY SIĘ KĄT
    
    private void shootUpdate(){
        if(Mouse.getButton() == 1 && fireRate <= 0){
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            double dir = Math.atan2(dy, dx);
            shoot(x,y, dir);
            fireRate = oldFR;
        }
    }
    
    //------------------------------------------------------------- RENDEROWANIE
    
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

            if((lvl.getTile(this.x >> 4, this.y >> 4) == Tiles.water)){
                isSwimming = true;
                screen.renderPlayerHead(x - 16, y - 18, sprite, flip);
            }else{
                isSwimming = false;
                screen.renderPlayerHead(x - 16, y - 25, sprite, flip);
                screen.renderPlayerBody(x - 16, y - 25, sprite, flip);
            }
    }
    
    //------------------------------------------------------------- DO POBIERANIA|USTAWIANIA WARTOŚCI
    
    public int getHP(){
        return this.HP;
    }
    
    public void setHP(int HP){
        this.HP = HP;
    }
    
    public void takeDMG(int dmg){
        this.HP -= dmg;
    }
    
    public String getName(){
        return this.imie;
    }
    
    public void setName(String name){
        this.imie = name;
    }
    
    public int getSPEED(){
        return this.speed;
    }
    
    public void setSPEED(int speed){
        this.speed = speed;
    }
    
    public void incSPEED(int add){
        this.speed += add;
    }
}
