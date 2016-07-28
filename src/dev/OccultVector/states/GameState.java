package dev.OccultVector.states;

import dev.OccultVector.Game;
import dev.OccultVector.Handler;
import dev.OccultVector.entities.creatures.Player;
import dev.OccultVector.worlds.World;
import java.awt.Graphics;
import dev.OccultVector.entities.statics.Tree;


public class GameState extends State {
    
   
    private World world;
    
    
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
       
     
    }
    
    
    @Override
    public void tick() {
        world.tick();
       
      
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
      
       
        
    }

    
    
}
