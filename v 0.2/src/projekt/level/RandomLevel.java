package projekt.level;

import java.util.Random;

public class RandomLevel extends level {

    private static final Random random = new Random();
    
    public RandomLevel(int width, int height) {
        super(width, height);
    }
    
    protected void generateLevel(){
        //wypełaniamy sobie tablicę tiles losowymi liczbami od 0-2
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                tilesInt[x+y*width] = random.nextInt(4);
            }
        }
    }
}
