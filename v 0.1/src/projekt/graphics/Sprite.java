package projekt.graphics;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    
    public static Sprite voidSprite = new Sprite(16, 0x000000);
    public static Sprite Grass = new Sprite(16,0,0,SpriteSheet.tiles);
    public static Sprite Dirt = new Sprite(16,1,0,SpriteSheet.tiles);
    public static Sprite Sand = new Sprite(16,2,0,SpriteSheet.tiles);
    public static Sprite Player1spawn = new Sprite(16,3,0,SpriteSheet.tiles);
    public static Sprite Player2spawn = new Sprite(16,4,0,SpriteSheet.tiles);
    public static Sprite Hole = new Sprite(16,5,0,SpriteSheet.tiles);
    public static Sprite Rock = new Sprite(16,6,0,SpriteSheet.tiles);
    public static Sprite Life = new Sprite(16,7,0,SpriteSheet.tiles);
    
    public static Sprite player1_up = new Sprite(32, 0, 0, SpriteSheet.hero1);
    public static Sprite player1_up1 = new Sprite(32, 0, 1, SpriteSheet.hero1);
    public static Sprite player1_up2 = new Sprite(32, 0, 3, SpriteSheet.hero1);
    
    public static Sprite player1_down = new Sprite(32, 2, 0, SpriteSheet.hero1);
    public static Sprite player1_down1 = new Sprite(32, 2, 1, SpriteSheet.hero1);
    public static Sprite player1_down2 = new Sprite(32, 2, 3, SpriteSheet.hero1);
    
    public static Sprite player1_side = new Sprite(32, 1, 1, SpriteSheet.hero1);
    public static Sprite player1_side1 = new Sprite(32, 1, 3, SpriteSheet.hero1);
    public static Sprite player1_side2 = new Sprite(32, 1, 4, SpriteSheet.hero1);
    
    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    
    public Sprite(int size, int kolor){
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColour(kolor);
    }
    
    private void setColour(int kolor){
        for(int i = 0; i< SIZE*SIZE; i++){
            pixels[i] = kolor;
        }
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
