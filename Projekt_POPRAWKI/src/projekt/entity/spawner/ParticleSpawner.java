package projekt.entity.spawner;

import projekt.entity.particle.Particle;
import projekt.level.level;

public class ParticleSpawner extends Spawner {

    private int life;
    
    public ParticleSpawner(int x, int y, int life, int amount, level lvl) {
        super(x, y, Type.PARTICLE, amount, lvl);
        this.life = life;
        for(int i=0; i < amount; i++){
                lvl.add(new Particle(x,y,life));
        }
    }
    
}
