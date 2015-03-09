package projekt.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpawnLevel extends level {
    
    public SpawnLevel(String path){
        super(path);
    }
    
    protected void loadLevel(String path){
        try{
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            tiles = new int[w*h];
            image.getRGB(0,0,w,h, tiles,0,w);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Błąd! Nie można załadować poziomu :<");
        }
    }
    
    protected void generateLevel(){
        //System.out.println("Tiles: " + tiles[0]);
    }
}
