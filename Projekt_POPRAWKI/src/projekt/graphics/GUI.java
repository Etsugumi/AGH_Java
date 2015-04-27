package projekt.graphics;

import java.awt.Color;
import java.awt.Graphics;
import projekt.entity.Mob.Player;

public class GUI {
    
    //protected int HP;
    //private Player gracz;
    
    public GUI(){
        //this.HP = gracz.HP;
    }
    
    public void update(){
       //this.HP = gracz.HP;
    }
    
    public void render(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.fillRect(0, 0, 200, 300);
    }
}
