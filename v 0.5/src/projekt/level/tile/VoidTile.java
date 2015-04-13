/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.level.tile;

import projekt.graphics.Screen;
import projekt.graphics.Sprite;

/**
 *
 * @author Shiro
 */
class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }
    
    public void render(int x, int y, Screen screen){        
        screen.renderTile(x << 4, y << 4, this);
    }
    
}
