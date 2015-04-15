package projekt.level.tile;

public class TileCoordinates {
    
    private int x, y;
    private final int TILE_SIZE = 16;
    
    public TileCoordinates(int x, int y){
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }
    
    public int x(){
        return x;
    }
    
    public int y(){
        return y;
    }
    
    public int[] xy(){
        int[] wynik = new int[2];
        wynik[0] = x;
        wynik[1] = y;
        return wynik;
    }
}
