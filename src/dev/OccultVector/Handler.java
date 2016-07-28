package dev.OccultVector;

import dev.OccultVector.gfx.GameCamera;
import dev.OccultVector.input.KeyManager;
import dev.OccultVector.input.MouseManager;
import dev.OccultVector.worlds.World;

/**
 * Handler acts as a wrapper for several other objects and values, allowing them to be passed 
 * to and accessed from other classes without each value or object needing to be passed
 * individually.
 * 
 */
public class Handler {

    private Game game;
    private World world;
    
    public Handler(Game game){
        this.game = game;
    }

    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager(){
            return game.getMouseManager();
    }
    
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    //GETTERS/SETTERS
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
}
