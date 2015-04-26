package game.grafika;

public class Sprite {
	//-------------------------------------------------------------------------------------------------------------------- ZMIENNE
    public final int SIZE;
    private int x, y, width, height;
    
    protected SpriteSheet sheet;
        
    public int[] pixels;
    
    //-------------------------------------------------------------------------------------------------------------------- SPRITES
    public static Sprite voidSprite = new Sprite(16, 0x000000);
    
    public static Sprite grass = new Sprite(16,0,0,SpriteSheet.tiles);
    public static Sprite dirt = new Sprite(16,1,0,SpriteSheet.tiles);
    public static Sprite sand = new Sprite(16,2,0,SpriteSheet.tiles);   
    public static Sprite water = new Sprite(16,3,0,SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16,4,0,SpriteSheet.tiles);
    
    //----------------------------------------------------------------------- POCISKI I CZASTECZKI
    
    public static Sprite pocisk = new Sprite(4, 0xffff00);
    public static Sprite partYellow = new Sprite(1, 0xffff00);
    
    //----------------------------------------------------------------------- GRACZ 1
    
    public static Sprite player1_up = new Sprite(32, 2, 0, SpriteSheet.hero);
    public static Sprite player1_up1 = new Sprite(32, 2, 1, SpriteSheet.hero);
    public static Sprite player1_up2 = new Sprite(32, 2, 2, SpriteSheet.hero);
    
    public static Sprite player1_down = new Sprite(32, 0, 0, SpriteSheet.hero);
    public static Sprite player1_down1 = new Sprite(32, 0, 1, SpriteSheet.hero);
    public static Sprite player1_down2 = new Sprite(32, 0, 2, SpriteSheet.hero);
    
    public static Sprite player1_side = new Sprite(32, 1, 1, SpriteSheet.hero);
    public static Sprite player1_side1 = new Sprite(32, 1, 2, SpriteSheet.hero);
    public static Sprite player1_side2 = new Sprite(32, 1, 3, SpriteSheet.hero);
    
    public static Sprite player1_shoot = new Sprite(32, 1, 4, SpriteSheet.hero);
    
    //----------------------------------------------------------------------- GRACZ 2
    
    public static Sprite player2_up = new Sprite(32, 5, 0, SpriteSheet.hero);
    public static Sprite player2_up1 = new Sprite(32, 5, 1, SpriteSheet.hero);
    public static Sprite player2_up2 = new Sprite(32, 5, 2, SpriteSheet.hero);
    
    public static Sprite player2_down = new Sprite(32, 3, 0, SpriteSheet.hero);
    public static Sprite player2_down1 = new Sprite(32, 3, 1, SpriteSheet.hero);
    public static Sprite player2_down2 = new Sprite(32, 3, 2, SpriteSheet.hero);
    
    public static Sprite player2_side = new Sprite(32, 4, 1, SpriteSheet.hero);
    public static Sprite player2_side1 = new Sprite(32, 4, 2, SpriteSheet.hero);
    public static Sprite player2_side2 = new Sprite(32, 4, 3, SpriteSheet.hero);
    
    public static Sprite player2_shoot = new Sprite(32, 4, 4, SpriteSheet.hero);
    
    //-------------------------------------------------------------------------------------------------------------------- KONSTRUKTORY
    
    protected Sprite(int width, int height, SpriteSheet sheet){
        if(width == height) SIZE = width;
        else SIZE = -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }
    
    //sprity niestandardowe (x|y - pozycja sprita w poziomie|pionie na SpriteSheet)
    public Sprite(int width, int height, int x, int y, SpriteSheet sheet){
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        this.x = x * width;
        this.y = y * height;
        this.sheet = sheet;
        load();
    }
    
    //kwadratowe sprity (x|y - pozycja sprita w poziomie|pionie na SpriteSheet)
    public Sprite(int x, int y, int size, SpriteSheet sheet){
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
    
  //-------------------------------------------------------------------------------------------------------------------- METODY
    
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
    
    //³adujemy pojedynczego sprita ze SpiteSheet
    private void load(){
        for(int y=0; y<SIZE; y++){
            for(int x=0; x<SIZE; x++){
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x)+(y + this.y) * sheet.size];
            }
        }
    }
    
}
