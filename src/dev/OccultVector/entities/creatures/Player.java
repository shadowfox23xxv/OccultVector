package dev.OccultVector.entities.creatures;

import dev.OccultVector.Game;
import dev.OccultVector.Handler;
import dev.OccultVector.gfx.Animation;
import dev.OccultVector.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Player extends Creature {

    //Animations
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
        
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
    }
    
    
    
    @Override
    public void tick() {//consider revising to only tick the animation being used
                        //combine some varible with the getCurrentAnimationFrame() method
                        //to store the current direction or animation being shown
                        //and tick only that animation object.  Current method seems to 
                        //be inefficient, especially when more than just walking animations
                        //get involved
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }
    
    public void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
//                bounds.width, bounds.height);

    }
    
    @Override
    public void die(){
        System.out.println("You lose!");
    }
//    adjusts animation frame being sent to render() based on direction moving.
//    defaults to down animation
    private BufferedImage getCurrentAnimationFrame(){ 
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if (xMove > 0){
            return animRight.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
        }else{return animDown.getCurrentFrame();
        }
    }
    
    

}
