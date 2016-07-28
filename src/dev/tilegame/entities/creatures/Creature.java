package dev.tilegame.entities.creatures;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.tiles.Tile;


public abstract class Creature extends Entity{
    
    
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;
    
   
    protected float speed;
    protected float xMove;
    protected float yMove;
    
    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    

    public void move(){//using xmove and ymove allows us to check for collisions where 
                        //we want to move the entity.  If there is a collision it
                        //doesn't move.  If not, it allows the entity to move
        if(!checkEntityCollisions(xMove, 0f))
            moveX();
        if(!checkEntityCollisions(0f, yMove))    
            moveY();
        
    }
    
    public void moveX(){ //checks for collision and if there is none, moves in the x direction
        if(xMove >0){//moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
        }else if(xMove < 0){//moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
        }
    }
    
    public void moveY(){
        if(yMove < 0){//up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
        }else if(yMove > 0){//down
            int ty = (int) (y + yMove + bounds.y  + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }
        }
    }
    
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }
   
    //Getters/Setter
    public int getHealth() {
        return getHealth();
    }


    public void setHealth(int health) {
        this.setHealth(health);
    }


    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }

 
    public float getxMove() {
        return xMove;
    }

  
    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    
    public float getyMove() {
        return yMove;
    }

  
    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
    
    
    

}
