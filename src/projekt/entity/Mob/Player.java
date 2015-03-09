package projekt.entity.Mob;

import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import projekt.input.Keyboard;

public class Player extends Mob{
    
    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;
    
    //tworzy gracza w standardowym miejscu (spawn)
    public Player(Keyboard input){
        this.input = input;
        sprite = Sprite.player1_up;
    }
    
    //tworzy gracza w wyznaczomym miejscu
    public Player(int x, int y, Keyboard input){
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player1_up;
    }
    
    public void update(){
        int xa = 0,ya = 0;
        //takie male zabezpieczenie przed idiotami
        if(anim < 7500) anim++; else anim = 0;
        if(input.up) ya--;
        if(input.down) ya++;
        if(input.left) xa--;
        if(input.right) xa++;
        
        if(xa != 0 || ya != 0){
            move(xa,ya);
            walking = true;
        }else{
            walking = false;
        }
    }
    
    public void render(Screen screen){
        int flip = 0;
        if(dir == 0){
            sprite = Sprite.player1_down;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player1_down1;
                }else{
                    sprite = Sprite.player1_down2;
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
            sprite = Sprite.player1_up;
            if(walking){
                if(anim % 20 > 10){
                    sprite = Sprite.player1_up1;
                }else{
                    sprite = Sprite.player1_up2;
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
        screen.renderPlayer(x - 16, y - 16, sprite, flip);      
    }
}
