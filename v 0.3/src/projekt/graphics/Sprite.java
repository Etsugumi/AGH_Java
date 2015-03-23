package projekt.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    private int width, height;
    public int[] pixels;
    private SpriteSheet sheet;
    
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
    public static Sprite Life = new Sprite(16,13,1,SpriteSheet.tiles);
    public static Sprite Speed = new Sprite(16,13,0,SpriteSheet.tiles);
    public static Sprite Tarcza = new Sprite(16,14,0,SpriteSheet.tiles);
    public static Sprite Moc = new Sprite(16,15,0,SpriteSheet.tiles);
    
    public static Sprite Kszaki = new Sprite(16,10,0,SpriteSheet.tiles);
    
    public static Sprite kula = new Sprite(16,0,0,SpriteSheet.projectiles);
    
    //CZĄSTECZKI
    public static Sprite particle_normal = new Sprite(3, 0xffffff);
    
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
        pixels = new int[width*height];
        setColour(kolor);
    }
    
    public Sprite(int size, int kolor){
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE*SIZE];
        setColour(kolor);
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
