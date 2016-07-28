package dev.OccultVector;

import dev.OccultVector.display.Display;

public class Launcher {
    
    public static void main(String[] args){
        Game game = new Game("Tile Game!", 640, 360);//create game object to start game
        game.start();
    }
    
}
