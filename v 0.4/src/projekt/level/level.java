package projekt.level;

import java.util.ArrayList;
import java.util.List;
import projekt.entity.Entity;
import projekt.entity.particle.Particle;
import projekt.entity.projectile.Projectile;
import projekt.graphics.AnimatedSprite;
import projekt.graphics.Screen;
import projekt.graphics.Sprite;
import projekt.graphics.SpriteSheet;
import projekt.level.tile.Tile;

public class level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    
    private List<Entity> entities = new ArrayList<Entity>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    public List<Particle> particles = new ArrayList<Particle>();
    
    public static AnimatedSprite WODA = new AnimatedSprite(16, 16, 4, SpriteSheet.WODA_ANIM);
    
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
    
    protected void loadLevel(String path){     
    }
    
    public void update(){ 
        WODA.setFrameRate(30);
        WODA.update();
        for(int i=0; i < entities.size(); i++){
            entities.get(i).update();
        }
        for(int i=0; i < projectiles.size(); i++){
            projectiles.get(i).update();
        }
        for(int i=0; i < particles.size(); i++){
            particles.get(i).update();
        }
        remove();
    }
    
    private void remove(){
        for(int i=0; i < entities.size(); i++){
            if(entities.get(i).isRemoved()) entities.remove(i);
        }
        for(int i=0; i < projectiles.size(); i++){
            if(projectiles.get(i).isRemoved()) projectiles.remove(i);
        }
        for(int i=0; i < particles.size(); i++){
            if(particles.get(i).isRemoved()) particles.remove(i);
        }
    }
    
    public List<Projectile> getProjectiles(){
        return projectiles;
    }
    
    //do cyklu dnia i nocy
    private void time(){        
    }
    
    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset){
        boolean solid = false;
        for(int c=0; c<4; c++){
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;
            if(getTile(xt,yt).solid()) solid = true;
        }
        return solid;
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
                if(getTile(x,y) == Tile.water){
                    Tile.water.sprite = WODA.getSprite();
                    getTile(x,y).render(x,y,screen);
                }else{
                    getTile(x,y).render(x,y,screen);
                }
            }
        }
        
        for(int i=0; i < entities.size(); i++){
            entities.get(i).render(screen);
        }
        
        for(int i=0; i < projectiles.size(); i++){
            projectiles.get(i).render(screen);
        }
        
        for(int i=0; i < particles.size(); i++){
            particles.get(i).render(screen);
        }
    }
    
    public void add(Entity e){
        e.init(this);
        if(e instanceof Particle){
            particles.add((Particle)e);
        }else if(e instanceof Projectile){
            projectiles.add((Projectile)e);
        }else{
            entities.add(e);
        }
    }
    
    //sciana - 0x323232
    //trawa - 0x00ff00
    //woda - 0x50a0ff
    //ziemia - 0x6e5f3c
    //piasek - 0xffff00
    public Tile getTile(int x, int y){
        
        if(x<0 || y<0 || x>=width || y>=height) return Tile.voidTile;
        
        if(tiles[x+y*width] == 0xff00ff00) return Tile.grass;
        if(tiles[x+y*width] == 0xff6e5f3c) return Tile.dirt;
        if(tiles[x+y*width] == 0xffffff00) return Tile.sand;
        if(tiles[x+y*width] == 0xff50a0ff) return Tile.water;
        if(tiles[x+y*width] == 0xff323232) return Tile.rock;
        
        return Tile.voidTile;
    }
    
}
