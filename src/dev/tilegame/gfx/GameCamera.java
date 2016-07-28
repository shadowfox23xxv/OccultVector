package dev.tilegame.gfx;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.tiles.Tile;


public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;
    
    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.handler = handler;
    }
    
    public void checkBlankSpace(){ //checks if blank space around world is showing, and if so adjusts camera to avoid showing that.
        if(xOffset < 0){
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }
            
        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }
    
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }
    
    public void move(float x, float y){
        xOffset += x;
        yOffset += y;
        checkBlankSpace();
    }

    
    public float getxOffset() {
        return xOffset;
    }

  
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

   
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}
