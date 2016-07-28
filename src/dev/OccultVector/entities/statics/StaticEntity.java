package dev.OccultVector.entities.statics;

import dev.OccultVector.Handler;
import dev.OccultVector.entities.Entity;


public abstract class StaticEntity extends Entity {
    
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
    
}
