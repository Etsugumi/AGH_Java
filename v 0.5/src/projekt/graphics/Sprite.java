package projekt.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    private int width, height;
    public int[] pixels;
    protected SpriteSheet sheet;
    
    //POZIOM
    public static Sprite voidSprite = new Sprite(16, 0x50a0ff);
    
    public static Sprite Grass = new Sprite(16,0,1,SpriteSheet.tiles);
    public static Sprite Dirt = new Sprite(16,1,1,SpriteSheet.tiles);
    public static Sprite Sand = new Sprite(16,2,1,SpriteSheet.tiles);   
    public static Sprite Water = new Sprite(16,3,1,SpriteSheet.tiles);
    
    public static Sprite Player1spawn = new Sprite(16,8,0,SpriteSheet.tiles);
    public static Sprite Player2spawn = new Sprite(16,9,0,SpriteSheet.tiles);

    public static Sprite Rock = new Sprite(16,7,0,SpriteSheet.tiles);
    
    //UPGRADE-Y I INNE
    public static Sprite heart = new Sprite(16,0,3,SpriteSheet.tiles);
    public static Sprite DEF = new Sprite(4,1,SpriteSheet.tiles,16,32);
    
    //public static Sprite kula = new Sprite(16,0,0,SpriteSheet.projectiles);
    public static Sprite kula = new Sprite(4, 0xffff00);
    
    //CZĄSTECZKI
    public static Sprite particle_normal = new Sprite(1, 0xffff00);
    
    //GRACZE
    public static Sprite player1_up = new Sprite(32, 2, 0, SpriteSheet.hero1);
    public static Sprite player1_up1 = new Sprite(32, 2, 1, SpriteSheet.hero1);
    public static Sprite player1_up2 = new Sprite(32, 2, 2, SpriteSheet.hero1);
    
    public static Sprite player1_down = new Sprite(32, 0, 0, SpriteSheet.hero1);
    public static Sprite player1_down1 = new Sprite(32, 0, 1, SpriteSheet.hero1);
    public static Sprite player1_down2 = new Sprite(32, 0, 2, SpriteSheet.hero1);
    
    public static Sprite player1_side = new Sprite(32, 1, 1, SpriteSheet.hero1);
    public static Sprite player1_side1 = new Sprite(32, 1, 2, SpriteSheet.hero1);
    public static Sprite player1_side2 = new Sprite(32, 1, 3, SpriteSheet.hero1);
    
    public static Sprite player1_shoot = new Sprite(32, 1, 4, SpriteSheet.hero1);
    
    protected Sprite(int width, int height, SpriteSheet sheet){
        if(width == height) SIZE = width;
        else SIZE = -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }
    
    public Sprite(int x, int y, SpriteSheet sheet, int width, int height){
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        this.x = x * width;
        this.y = y * height;
        this.sheet = sheet;
        load();
    }
    
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE*SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite (int width, int height, int kolor){
        SIZE = -1;
        this.width = width;
        this.height = height;
        //pixels = new int[width*height];
        setColour(kolor);
    }
    
    public Sprite(int size, int kolor){
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE*SIZE];
        setColour(kolor);
    }
    
    public Sprite(int[] pixels, int width, int height){
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
    
    private void setColour(int kolor){
        for(int i = 0; i< width*height; i++){
            pixels[i] = kolor;
        }
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    //ładujemy pojedynczego sprita ze SpiteSheet
    private void load(){
        for(int y=0; y<SIZE; y++){
            for(int x=0; x<SIZE; x++){
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x)+(y + this.y) * sheet.SIZE];
            }
        }
    }
    
}
