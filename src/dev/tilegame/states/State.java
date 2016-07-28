package dev.tilegame.states;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import java.awt.Graphics;


public abstract class State {
    
    //Gamestate Manager
    //Can be moved to seperate class
    //See above; may be necessary to implement a state stack function as planned
    private static State currentState = null;
    
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    
    
    //CLASS
    protected Handler handler;
    
    public State(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}
