package game.grafika;

public class Screen {
	public static final int MapSize = 64;
	public static final int MapSizeMask =  MapSize - 1;
	
	public int[] pixels;
	public int[] colors;
	public int[] tiles = new int[MapSize * MapSize];
	
	public int yOffset = 0;
	public int xOffset = 0;
	public int width, height;
	
	public SpriteSheet sheet;
	
	//konstruktor
	public Screen(int width, int height){
		this.width = width;
		this.height = height;

		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i=0; i<pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
	
	public void renderSheet(int xPos, int yPos, SpriteSheet sheet, boolean fixed){
        if(fixed){
            xPos -= xOffset;
            yPos -= yOffset;
        }
        for(int y=0; y < sheet.height; y++){
            int ya = y + yPos;
            for(int x=0; x < sheet.width; x++){
                int xa = x + xPos;
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.width];
            }
        }
    }
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
        if(!fixed){
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int y=0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x=0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }
}
