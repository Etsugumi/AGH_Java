package projekt.entity.spawner;

import java.util.List;
import java.util.ArrayList;
import projekt.entity.Entity;
import projekt.level.level;

public class Spawner extends Entity{
    
    private List<Entity> entities = new ArrayList<Entity>();
    
    public enum Type{
        PARTICLE;
    }
    
    private Type type;
    
    public Spawner(int x, int y, Type type, int amount, level lvl){
        init(lvl);
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
