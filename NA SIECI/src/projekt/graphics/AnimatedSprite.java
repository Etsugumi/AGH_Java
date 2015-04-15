package projekt.graphics;

public class AnimatedSprite extends Sprite{
    
    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int animSize = -1;
    private int time = 0;
    
    public AnimatedSprite(int width, int height, int animLength, SpriteSheet sheet) {
        super(width, height, sheet);
        animSize = animLength;
        sprite = sheet.getSprites()[0];
        if(animSize > sheet.getSprites().length) System.err.println("Error! Zbyt długa animacja!");
    }
    
    public void update(){
        time++;
        if(time % rate == 0){
            if(frame >= animSize - 1) frame = 0;
            else frame++;
            sprite = sheet.getSprites()[frame];
        }
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    public void setFrameRate(int frameRate){
        rate = frameRate;
    }
    
}
