package dev.OccultVector.entities;

import dev.OccultVector.Game;
import dev.OccultVector.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Entity {

    private int health;
    protected float x, y;
    protected int width, height;
    protected Handler handler;
    protected Rectangle bounds;
    private boolean active = true;
    
    public static final int DEFAULT_HEALTH = 10;
    
    
    public Entity(Handler handler, float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
     
        this.handler = handler;
        bounds = new Rectangle(0, 0, width, height);
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public void hurt(int amt){
        health -= amt;
        if(health <= 0)
            active = false;
            die();
    }
    
    public abstract void die();
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) ( y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))//ignores collision check with self
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }
    
    //Getters and Setter
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    
    public boolean isActive() {
        return active;
    }

  
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
