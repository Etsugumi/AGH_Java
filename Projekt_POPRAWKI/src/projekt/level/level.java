package projekt.level;

import java.util.ArrayList;
import java.util.List;
import projekt.entity.Entity;
import projekt.entity.particle.Particle;
import projekt.entity.projectile.Projectile;
import projekt.graphics.AnimatedSprite;
import projekt.graphics.Screen;
import projekt.graphics.SpriteSheet;
import projekt.level.tile.Tiles;

public class level {

    //----------------------------------------------------------------------------------- ZMIENNE
    
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    
    private List<Entity> entities = new ArrayList<Entity>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    public List<Particle> particles = new ArrayList<Particle>();
    
    public static AnimatedSprite WODA = new AnimatedSprite(16, 16, 4, SpriteSheet.WODA_ANIM);
    
    //----------------------------------------------------------------------------------- KONSTRUKTORY
    
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
    
    //----------------------------------------------------------------------------------- METODY
    
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

        if(xScroll<0) xScroll=0;
        if(xScroll > screen.width + 24) xScroll = screen.width + 24;
        if(yScroll<0) yScroll=0;
        if(yScroll >= screen.height+466) yScroll = (screen.height+466);
        
        
        screen.setOffset(xScroll, yScroll);
        
        //od kąd do kąd renderujemy obraz
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;
        
        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                if(getTile(x,y) == Tiles.water){
                    Tiles.water.sprite = WODA.getSprite();
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
    public Tiles getTile(int x, int y){
        
        if(x<0 || y<0 || x>=width || y>=height) return Tiles.voidTile;
        
        if(tiles[x+y*width] == 0xff00ff00) return Tiles.grass;
        if(tiles[x+y*width] == 0xff6e5f3c) return Tiles.dirt;
        if(tiles[x+y*width] == 0xffffff00) return Tiles.sand;
        if(tiles[x+y*width] == 0xff50a0ff) return Tiles.water;
        if(tiles[x+y*width] == 0xff323232) return Tiles.rock;
        
        return Tiles.voidTile;
    }
    
}
