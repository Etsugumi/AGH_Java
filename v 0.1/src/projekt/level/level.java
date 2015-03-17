package projekt.level;

import projekt.graphics.Screen;
import projekt.level.tile.Tile;

public class level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    
    //konstruktor generujący losowo level
    public level(int width, int height){
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }
    
    //generowanie levelu z pliku
    public level(String path){
        loadLevel(path);
        generateLevel();
    }
    
    protected void generateLevel(){
    }
    
    private void loadLevel(String path){       
    }
    
    //aktualizacja naszego levelu 
    public void update(){        
    }
    
    //do cyklu dnia i nocy
    private void time(){        
    }
    
    //no i trzeba wyrenderować nasz level
    public void render(int xScroll, int yScroll, Screen screen){       
        screen.setOffset(xScroll, yScroll);
        
        //to samo co dzielenie przez 16(rozmiar 1 Tile)
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        
        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                getTile(x,y).render(x,y,screen);
            }
        }
    }
    
    //trawa - 0xff00
    //piasek - 0xffff00
    //ziemia - 0x7f7f00
    public Tile getTile(int x, int y){
        if(x<0 || y<0 || x>=width || y>=height) return Tile.voidTile;
        if(tiles[x+y*width] == 0xff00ff00) return Tile.grass;
        if(tiles[x+y*width] == 0xff7f7f00) return Tile.dirt;
        if(tiles[x+y*width] == 0xffffff00) return Tile.sand;
        return Tile.voidTile;
    }
    
}
