package dev.OccultVector;

import dev.OccultVector.display.Display;
import dev.OccultVector.gfx.Assets;
import dev.OccultVector.gfx.GameCamera;
import dev.OccultVector.input.KeyManager;
import dev.OccultVector.input.MouseManager;
import dev.OccultVector.states.GameState;
import dev.OccultVector.states.MenuState;
import dev.OccultVector.states.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {//runnable implementation allows this class to run on it's own thread
    
    private Display display;
    private boolean running = false;
    private int width, height;
    public String title;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    
    
    //States
    public State gameState;
    private State menuState;
    
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //Handler
    private Handler handler;
    
    //Camera
    private GameCamera gameCamera;
    
    
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
    }
    
    private void init(){//only run once to initialize game on startup
        display = new Display(title, width, height);//create new display object to display the game
        display.getFrame().addKeyListener(keyManager);//Adds keylistener to the JFrame
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
        
    }
    
    //update 
    private void tick(){
        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();
        
    }
    

    //draw
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){//if no buffer stratagey set, run code below to set up triple buffer
            display.getCanvas().createBufferStrategy(3);//sets triple buffer
            return;
        }
        g = bs.getDrawGraphics();//sets g, our "paintbrush", to the getDrawGraphics() method of the buffer strategy
        //Clear screen
        g.clearRect(0, 0, width, height);
        //Draw here!-----------------------------------------------------------------------------------------------------------------------------------------------------
        
        if(State.getState() != null)
            State.getState().render(g);
        
        //End drawing!--------------------------------------------------------------------------------------------------------------------------------------------------
        //next two lines draws buffer to canvas and eliminates g for this round of the game loop to avoid errors
        bs.show();
        g.dispose();
    }
    
    public void run(){//requirment of implementing runnable
        init();
        
        /**
         * The following code should normalize the speed of the game loop regardless of individual computer speed.
         * 
         * 1,000,000,000 is the number of nanoseconds in a second.  Dividing by fps(frames per second) gives us how long in nanoseconds
         *should elapse between each pass of the game loop
         *
         * System.nanoTime() gives current computer time in nanoseconds.
         * 
         * delta tracks the amount of time remaining until the loop needs to run again.
         * 
         * (now - lastTime) / timePerTick takes how long has passed since the last run method was called and converts it into a decimal 
         * percentage of the amount of time that needs to pass before the loop can run again, then adds that to delta.  Once delta reaches
         * 1 or higher (100% of the needed time has passed since the last loop ran) then the loop is allowed to run and delta is reduced by 1. 
         * 
         * timer, ticks, and all related code to those variables below are related to a consol debug functionality to display FPS.  
         * This should eventually be moved or modified in such a way that this info only displays when in debug mode.
        **/
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        
        //game loop
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            
            if(delta >= 1){
                tick();
                render();
                ticks ++;
                delta--;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();//calls stop to stop thread in case this has not already happned
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public GameCamera getGameCamera(){
        return gameCamera;
    }
    
    public synchronized void start(){//synchronized keyword is used when working directly with threads
        if(running) return;//exits method if game is already running(running = true)
        running = true;
        thread = new Thread(this);//starts this class on a new thread
        thread.start();//calls run method
    }
    
    public synchronized void stop(){
        if(!running) return;//exit method if game has already stopped
        running = false;
        try {
            thread.join();
	} catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

}

    


